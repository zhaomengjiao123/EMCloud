package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/09/16:50
 * @Description: AGV协议头
 */
public class AGVProtocolHeader {

    private byte m_sync;
    private byte m_version;
    private short m_number;
    private int m_length;
    private short m_type;
    private int m_reserved;


    public byte getM_sync() {
        return m_sync;
    }

    public void setM_sync(byte m_sync) {
        this.m_sync = m_sync;
    }

    public byte getM_version() {
        return m_version;
    }

    public void setM_version(byte m_version) {
        this.m_version = m_version;
    }

    public short getM_number() {
        return m_number;
    }

    public void setM_number(short m_number) {
        this.m_number = m_number;
    }

    public int getM_length() {
        return m_length;
    }

    public void setM_length(int m_length) {
        this.m_length = m_length;
    }

    public short getM_type() {
        return m_type;
    }

    public void setM_type(short m_type) {
        this.m_type = m_type;
    }

    public int getM_reserved() {
        return m_reserved;
    }

    public void setM_reserved(int m_reserved) {
        this.m_reserved = m_reserved;
    }
}
