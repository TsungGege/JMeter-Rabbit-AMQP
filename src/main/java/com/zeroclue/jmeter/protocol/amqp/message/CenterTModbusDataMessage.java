package com.zeroclue.jmeter.protocol.amqp.message;

import java.util.Arrays;

/**
 * Created by liu on 2017-08-14.
 */
public class CenterTModbusDataMessage extends CenterTModbusMessage {
    public final byte[] data;
    public final long timeMillis;
    public CenterTModbusDataMessage(String devId, byte[] data, long timeMillis) {
        super(devId);
        this.data = data;
        this.timeMillis = timeMillis;
    }

    @Override
    public String toString() {
        return "CenterTModbusDataMessage{" +
                "data=" + Arrays.toString(data) +
                ", devId='" + devId + '\'' +
                ", timeMillis=" + timeMillis +
                '}';
    }
}
