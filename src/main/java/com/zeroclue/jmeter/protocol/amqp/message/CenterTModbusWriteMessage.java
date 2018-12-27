package com.zeroclue.jmeter.protocol.amqp.message;

/**
 *
 */
public class CenterTModbusWriteMessage extends CenterTModbusMessage {
    /** 数据点id */
    public final int dataid;
    /** 写的数值 */
    public final String value;
    /** 写的时间 */
    public final long timeMillis;
    /** 从机序号 */
    public String slaveIndex;
    /** 从机地址 */
    public String slaveAddr;

    public CenterTModbusWriteMessage(String devId, String slaveIndex, String slaveAddr, int dataid, String value, long timeMillis) {
        super(devId);
        this.dataid = dataid;
        this.value = value;
        this.timeMillis = timeMillis;
        this.slaveIndex = slaveIndex;
        this.slaveAddr = slaveAddr;
    }

    public CenterTModbusWriteMessage(String devId, int dataid, String value, long timeMillis) {
        super(devId);
        this.dataid = dataid;
        this.value = value;
        this.timeMillis = timeMillis;
    }

    @Override
    public String toString() {
        return "CenterTModbusWriteMessage{" +
                "devId='" + devId + '\'' +
                ", dataid=" + dataid +
                ", value='" + value + '\'' +
                ", timeMillis=" + timeMillis +
                ", slaveIndex=" + slaveIndex +
                ", slaveAddr='" + slaveAddr + '\'' +
                '}';
    }
}
