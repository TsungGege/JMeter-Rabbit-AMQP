package com.zeroclue.jmeter.protocol.amqp.message;

/**
 * Created by liu on 2017-08-14.
 */
public class CenterTModbusReadMessage extends CenterTModbusMessage {
    /** 数据点id */
    public final int dataId;
    /** 从机序号 */
    public String slaveIndex;
    /** 从机地址 */
    public String slaveAddr;

    public CenterTModbusReadMessage(String devId, String slaveIndex, String slaveAddr, int dataId) {
        super(devId);
        this.dataId = dataId;
        this.slaveIndex = slaveIndex;
        this.slaveAddr = slaveAddr;
    }

    public CenterTModbusReadMessage(String devId, int dataId) {
        super(devId);
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        return "CenterTModbusReadMessage{" +
                "devId='" + devId + '\'' +
                ", dataId=" + dataId +
                ", slaveIndex=" + slaveIndex +
                ", slaveAddr='" + slaveAddr + '\'' +
                '}';
    }
}
