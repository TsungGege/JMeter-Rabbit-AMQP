package com.zeroclue.jmeter.protocol.amqp.message;

/**
 * Created by liu on 2017-08-14.
 */
public class CenterTModbusMessage {
    public final String devId;

    public CenterTModbusMessage(String devId) {
        this.devId = devId;
    }

    @Override
    public String toString() {
        return "CenterTModbusMessage{" +
                "devId='" + devId + '\'' +
                '}';
    }
}
