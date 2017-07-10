package com.linkct.hzdlzcgl.domain;

import java.util.List;

/**
 * 工作票
 * Created by wlh on 2017/6/30.
 */

public class GzpInfo {

    String gzpPeople;//工作票执行人
    String gzpTime;//工作票时间
    String gzpContent;//工作票内容
    List<String> gzpImage;//工作票包含的图片


    String uuid;//对应的设备唯一id
    String gzp_uuid;//数据库存储的唯一序列号，用于检索相关图片

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGzp_uuid() {
        return gzp_uuid;
    }

    public void setGzp_uuid(String gzp_uuid) {
        this.gzp_uuid = gzp_uuid;
    }

    public String getGzpPeople() {
        return gzpPeople;
    }

    public void setGzpPeople(String gzpPeople) {
        this.gzpPeople = gzpPeople;
    }

    public String getGzpTime() {
        return gzpTime;
    }

    public void setGzpTime(String gzpTime) {
        this.gzpTime = gzpTime;
    }

    public String getGzpContent() {
        return gzpContent;
    }

    public void setGzpContent(String gzpContent) {
        this.gzpContent = gzpContent;
    }

    public List<String> getGzpImage() {
        return gzpImage;
    }

    public void setGzpImage(List<String> gzpImage) {
        this.gzpImage = gzpImage;
    }
}
