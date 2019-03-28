package com.jlpay.common.testmq.services;

import com.alibaba.fastjson.JSON;
import com.jlpay.commons.command.DefaultCommandResponse;
import com.jlpay.commons.rpc.thrift.server.RpcAdapter;
import com.jlpay.commons.tools.shutdown.SystemSignalHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RpcAdapterImpl implements RpcAdapter.Iface{

	@Autowired private Dispatcher dispatcher;
	@Override
	public String Invoke(String logid, String request) throws TException {
		if(StringUtils.isEmpty(logid)){
			DefaultCommandResponse commandResponse = new DefaultCommandResponse();
			commandResponse.setRetCode("V0");
			commandResponse.setRetMsg("日志跟踪ID");
			return JSON.toJSONString(commandResponse);
		}
		if(SystemSignalHandler.isShutdown){
			DefaultCommandResponse commandResponse = new DefaultCommandResponse();
			commandResponse.setRetCode("S0");
			commandResponse.setRetMsg("系统暂停服务");
			return JSON.toJSONString(commandResponse);
		}
		MDC.put("logid", logid);
		String response = dispatcher.invoke(request);
		MDC.remove("logid");
		return response;
	}
	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
}
