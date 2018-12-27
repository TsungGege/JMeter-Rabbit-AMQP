package com.zeroclue.jmeter.protocol.amqp.message;

public class CenterTModbusOfflineMessage extends CenterTModbusMessage {
	public CenterTModbusOfflineMessage(String devId) {
		super(devId);
	}

	@Override
	public String toString() {
		return "CenterTModbusOfflineMessage{" +
				"devId='" + devId + '\'' +
				'}';
	}
}
