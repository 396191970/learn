package com.jlpay.common.testmq.services;

import com.alibaba.fastjson.JSON;
import com.jlpay.common.testmq.mode.TestCommandResponse;
import com.jlpay.common.testmq.mode.VerifyCommandRequest;
import com.jlpay.commons.command.CommandResponse;
import com.jlpay.commons.command.CommandServiceFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: lishaofeng
 * 生成清算明细
 **/

@Data
@Service(VerifyCommandRequest.COMMAND_ID)
@Slf4j
public class VerifySign extends CommandServiceFactory<VerifyCommandRequest> {



	@Override
	protected CommandResponse executeCommand(VerifyCommandRequest commandRequest)  {
		String ret = "TestCommandRequest:"+JSON.toJSONString(commandRequest);
		log.info(ret);

		return new TestCommandResponse();
	}


}
