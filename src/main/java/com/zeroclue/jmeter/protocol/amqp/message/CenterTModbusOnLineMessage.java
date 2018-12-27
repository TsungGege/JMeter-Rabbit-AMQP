package com.zeroclue.jmeter.protocol.amqp.message;

/**
 * Created by liu on 2017-08-14.
 */
public class CenterTModbusOnLineMessage extends CenterTModbusMessage {

    public CenterTModbusOnLineMessage(String devId) {
        super(devId);
    }

    @Override
    public String toString() {
        return "CenterTModbusOnLineMessage{" +
                "devId='" + devId + '\'' +
                '}';
    }
}
