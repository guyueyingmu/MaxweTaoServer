package org.maxwe.tao.server.service.history;

import java.io.Serializable;

/**
 * Created by Pengwei Ding on 2017-01-09 15:49.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class HistoryEntity implements Serializable {
    private String id; // 业务ID
    private int fromId; // 操作来源ID
    private int toId; // 操作流向ID，如果类型为0，则此ID为后来补充
    private int type; // 1激活码，2批量激活码
    private String actCode; //如果类型为1，则是向单个用激活
    private int codeNum;//如果类型为2，则表示交易为数量
    private float price;//成交时候当时价格
    private float codeDeal;//成交价格 成交价格等于当前定价乘以交易码量
    private long createTime;//创建时间
    private long updateTime;//更新时间

    public HistoryEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public int getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(int codeNum) {
        this.codeNum = codeNum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCodeDeal() {
        return codeDeal;
    }

    public void setCodeDeal(float codeDeal) {
        this.codeDeal = codeDeal;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", codeNum=" + codeNum +
                ", actCode='" + actCode + '\'' +
                ", type=" + type +
                ", toId='" + toId + '\'' +
                ", fromId='" + fromId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
