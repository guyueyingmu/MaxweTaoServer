package org.maxwe.tao.server.service.tao.alimama.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by Pengwei Ding on 2017-02-24 20; //56.
 * Email; // www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description; 阿里妈妈后台的商品的全部信息
 */
public class AliResponsePageEntity implements Serializable {
    private HashMap<String,String> tkSpecialCampaignIdRateMap;//{2616191: "15.01",4415440: "15.01",6011079: "15.38",35695643: "15.38",36555464: "15.38"}
    private long eventCreatorId; // 0,
    private long rootCatId; // 0,
    private long leafCatId; // 50010850,
    private String debugInfo; // null,
    private long rootCatScore; // 0,
    private long sellerId; // 728628560,//卖家的ID
    private long userType; // 0 淘宝 1天猫
    private String shopTitle; // "隆缘裳服饰旗舰店",
    private String pictUrl; // "http; ////image.taobao.com/bao/uploaded/i4/TB1kA3XPXXXXXcJXXXXYXGcGpXX_M2.SS2",
    private String title; // "隆缘裳短裙2017春装新款<span class=H>女装</span>简约时尚立领长袖修身印花连衣裙1892",
    private long auctionId; // 544504885384,商品ID
    private long tkRate; // 14,佣金率
    private float tkCommFee; // 55.72, 佣金
    private long totalNum; // 0,
    private long totalFee; // 0,
    private String couponActivityId; // null,
    private float reservePrice; // 663,原来价格
    private String auctionUrl; // "http; ////item.taobao.com/item.htm?id=544504885384",
    private String couponLink; // "",
    private String couponLinkTaoToken; // "",
    private long dayLeft; // -17221,活动剩余天数
    private float tk3rdRate; // null,活动发起者淘客的佣金比例
    private long biz30day; // 47,//月销量
    private float rlRate; // 39.96,
    private String nick; // "隆缘裳服饰旗舰店",卖家昵称
    private long hasRecommended; // 0,
    private long hasSame; // 0,
    private float zkPrice; // 398,//淘客价格
    private String sameItemPid; // "-616685232",
    private long couponTotalCount; // 100000,
    private long couponLeftCount; // 95200,
    private long includeDxjh; // 1,
    private String auctionTag; // "587 907 1154 1163 1478 1483 2049 2059 3851 3915 3974 4166 4491 4550 4555 6603 7105 7878 11083 11266 11339 11531 12491 13707 13771 16321 20609 21697 25282 28353 28802 30337 30401 30593 30657 30849 30977 34305 35713 36161 37569 37633 39233 39490 40897 46849 51585 51841 51969 52033 53377 57857 60418 62082 72386 82306 101762 107842 116546 119426 166402 167682 167810 100017049 205017049",
    private String couponShortLink; // null,
    private String couponInfo; // "满21元减20元",
    private float couponStartFee; // 21,使用优惠券的起始点
    private long couponAmount; // 20,优惠券的额度
    private String couponEffectiveStartTime; // "2016-11-29",优惠券可用起始时间
    private String couponEffectiveEndTime; // "2017-03-30",优惠券可用结束时间
    private float eventRate; // null,
    private String hasUmpBonus; // null,
    private String isBizActivity; // null,
    private String umpBonus; // null,
    private String rootCategoryName; // null,
    private String couponOriLink; // null,
    private String userTypeName; // null

    @JSONField(serialize = false)
    private String jsonString;// 这个字段是简化web操作而设置的
    @JSONField(serialize = false)
    private String jsonBase64String;// 这个字段是简化web操作而设置的

    public AliResponsePageEntity() {
        super();
    }

    public HashMap<String, String> getTkSpecialCampaignIdRateMap() {
        return tkSpecialCampaignIdRateMap;
    }

    public void setTkSpecialCampaignIdRateMap(HashMap<String, String> tkSpecialCampaignIdRateMap) {
        this.tkSpecialCampaignIdRateMap = tkSpecialCampaignIdRateMap;
    }

    public long getEventCreatorId() {
        return eventCreatorId;
    }

    public void setEventCreatorId(long eventCreatorId) {
        this.eventCreatorId = eventCreatorId;
    }

    public long getRootCatId() {
        return rootCatId;
    }

    public void setRootCatId(long rootCatId) {
        this.rootCatId = rootCatId;
    }

    public long getLeafCatId() {
        return leafCatId;
    }

    public void setLeafCatId(long leafCatId) {
        this.leafCatId = leafCatId;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public long getRootCatScore() {
        return rootCatScore;
    }

    public void setRootCatScore(long rootCatScore) {
        this.rootCatScore = rootCatScore;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public long getTkRate() {
        return tkRate;
    }

    public void setTkRate(long tkRate) {
        this.tkRate = tkRate;
    }

    public float getTkCommFee() {
        return tkCommFee;
    }

    public void setTkCommFee(float tkCommFee) {
        this.tkCommFee = tkCommFee;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public String getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(String couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public float getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(float reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getAuctionUrl() {
        return auctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        this.auctionUrl = auctionUrl;
    }

    public String getCouponLink() {
        return couponLink;
    }

    public void setCouponLink(String couponLink) {
        this.couponLink = couponLink;
    }

    public String getCouponLinkTaoToken() {
        return couponLinkTaoToken;
    }

    public void setCouponLinkTaoToken(String couponLinkTaoToken) {
        this.couponLinkTaoToken = couponLinkTaoToken;
    }

    public long getDayLeft() {
        return dayLeft;
    }

    public void setDayLeft(long dayLeft) {
        this.dayLeft = dayLeft;
    }

    public float getTk3rdRate() {
        return tk3rdRate;
    }

    public void setTk3rdRate(float tk3rdRate) {
        this.tk3rdRate = tk3rdRate;
    }

    public long getBiz30day() {
        return biz30day;
    }

    public void setBiz30day(long biz30day) {
        this.biz30day = biz30day;
    }

    public float getRlRate() {
        return rlRate;
    }

    public void setRlRate(float rlRate) {
        this.rlRate = rlRate;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getHasRecommended() {
        return hasRecommended;
    }

    public void setHasRecommended(long hasRecommended) {
        this.hasRecommended = hasRecommended;
    }

    public long getHasSame() {
        return hasSame;
    }

    public void setHasSame(long hasSame) {
        this.hasSame = hasSame;
    }

    public float getZkPrice() {
        return zkPrice;
    }

    public void setZkPrice(float zkPrice) {
        this.zkPrice = zkPrice;
    }

    public String getSameItemPid() {
        return sameItemPid;
    }

    public void setSameItemPid(String sameItemPid) {
        this.sameItemPid = sameItemPid;
    }

    public long getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(long couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public long getCouponLeftCount() {
        return couponLeftCount;
    }

    public void setCouponLeftCount(long couponLeftCount) {
        this.couponLeftCount = couponLeftCount;
    }

    public long getIncludeDxjh() {
        return includeDxjh;
    }

    public void setIncludeDxjh(long includeDxjh) {
        this.includeDxjh = includeDxjh;
    }

    public String getAuctionTag() {
        return auctionTag;
    }

    public void setAuctionTag(String auctionTag) {
        this.auctionTag = auctionTag;
    }

    public String getCouponShortLink() {
        return couponShortLink;
    }

    public void setCouponShortLink(String couponShortLink) {
        this.couponShortLink = couponShortLink;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public float getCouponStartFee() {
        return couponStartFee;
    }

    public void setCouponStartFee(float couponStartFee) {
        this.couponStartFee = couponStartFee;
    }

    public long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponEffectiveStartTime() {
        return couponEffectiveStartTime;
    }

    public void setCouponEffectiveStartTime(String couponEffectiveStartTime) {
        this.couponEffectiveStartTime = couponEffectiveStartTime;
    }

    public String getCouponEffectiveEndTime() {
        return couponEffectiveEndTime;
    }

    public void setCouponEffectiveEndTime(String couponEffectiveEndTime) {
        this.couponEffectiveEndTime = couponEffectiveEndTime;
    }

    public float getEventRate() {
        return eventRate;
    }

    public void setEventRate(float eventRate) {
        this.eventRate = eventRate;
    }

    public String getHasUmpBonus() {
        return hasUmpBonus;
    }

    public void setHasUmpBonus(String hasUmpBonus) {
        this.hasUmpBonus = hasUmpBonus;
    }

    public String getIsBizActivity() {
        return isBizActivity;
    }

    public void setIsBizActivity(String isBizActivity) {
        this.isBizActivity = isBizActivity;
    }

    public String getUmpBonus() {
        return umpBonus;
    }

    public void setUmpBonus(String umpBonus) {
        this.umpBonus = umpBonus;
    }

    public String getRootCategoryName() {
        return rootCategoryName;
    }

    public void setRootCategoryName(String rootCategoryName) {
        this.rootCategoryName = rootCategoryName;
    }

    public String getCouponOriLink() {
        return couponOriLink;
    }

    public void setCouponOriLink(String couponOriLink) {
        this.couponOriLink = couponOriLink;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getJsonString() {
        return JSON.toJSONString(this);
    }

    public String getJsonBase64String() {
        return Base64.getEncoder().encodeToString(this.getJsonString().getBytes(Charset.forName("UTF-8")));
    }

    @Override
    public String toString() {
        return "AliResponsePageEntity{" +
                "title='" + title + '\'' +
                '}';
    }
}
