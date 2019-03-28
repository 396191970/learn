package com.jlpay.common.testmq.mode;

import com.jlpay.commons.command.CommandRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class PhoneAuthCommandRequest implements CommandRequest
{

	public static final String COMMAND_ID = "phoneAuthCommandService";

	@Override
	public String getCommandId() {
		return COMMAND_ID;
	}

	@Override
	public void validate() {

	}
}
