package com.jlpay.common.testmq.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jlpay.commons.command.CommandRequest;
import com.jlpay.commons.command.CommandResponse;
import com.jlpay.commons.command.CommandService;
import com.jlpay.commons.command.DefaultCommandResponse;
import com.jlpay.commons.exception.JlpayAssert;
import com.jlpay.commons.exception.JlpayException;
import com.jlpay.commons.tools.log.MaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * thrift RPC分发器
 * @author lujianyuan
 *
 */
@Component()
public class Dispatcher implements ApplicationContextAware,InitializingBean{
	
	private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	
	private ApplicationContext context;
	public String invoke(String request){
		logger.info("thrift服务接收报文："+MaskUtil.maskJsonLog(request));
		JSONObject requestJson = null;
		long start = System.currentTimeMillis();
		long costTimes = 0;
		try {
			requestJson = JSON.parseObject(request);
			requestJson.put("retCode","00");
			requestJson.put("retMsg","test成功");
		}finally{
			costTimes = System.currentTimeMillis() - start;
		} 
		
		logger.info("thrift服务耗时："+costTimes+"，返回报文："+requestJson.toJSONString());
		return  requestJson.toJSONString();
	}
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
}
