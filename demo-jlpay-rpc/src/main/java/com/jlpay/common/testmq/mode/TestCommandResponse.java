package com.jlpay.common.testmq.mode;

import com.jlpay.commons.command.CommandResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class TestCommandResponse implements CommandResponse
{
	String retCode = "00";
	String retMsg = "测试成功";

	@Override
	public void setRetCode(String s) {
		retCode = s;
	}

	@Override
	public void setRetMsg(String s) {
		retMsg=s;

	}
}
