package com.jlpay.common.testmq.services;

import com.alibaba.fastjson.JSON;
import com.jlpay.common.testmq.mode.SignCommandRequest;
import com.jlpay.common.testmq.mode.TestCommandResponse;
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
@Service(SignCommandRequest.COMMAND_ID)
@Slf4j
public class Sign extends CommandServiceFactory<SignCommandRequest> {



	@Override
	protected CommandResponse executeCommand(SignCommandRequest commandRequest)  {
		String ret = "TestCommandRequest:"+JSON.toJSONString(commandRequest);
		log.info(ret);

		return (CommandResponse)commandRequest;
	}


}
