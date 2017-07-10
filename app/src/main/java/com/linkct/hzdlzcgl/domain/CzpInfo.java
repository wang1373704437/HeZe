package com.linkct.hzdlzcgl.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 操作票
 * Created by wlh on 2017/6/30.
 */
@DatabaseTable(tableName = "tb_czp")
public class CzpInfo {
    @DatabaseField(generatedId = true)
    int _id;//主键
    @DatabaseField(columnName = "uuid")
    String uuid;//对应的设备唯一id
    @DatabaseField(columnName = "czpPeople")
    String czpPeople;//操作票执行人
    @DatabaseField(columnName = "czpTime")
    String czpTime;//操作票时间
    @DatabaseField(columnName = "czpContent")
    String czpContent;//操作票内容

    public CzpInfo() {
    }

    public CzpInfo(int _id, String uuid, String czpPeople, String czpTime, String czpContent) {
        this._id = _id;
        this.uuid = uuid;
        this.czpPeople = czpPeople;
        this.czpTime = czpTime;
        this.czpContent = czpContent;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCzpPeople() {
        return czpPeople;
    }

    public void setCzpPeople(String czpPeople) {
        this.czpPeople = czpPeople;
    }

    public String getCzpTime() {
        return czpTime;
    }

    public void setCzpTime(String czpTime) {
        this.czpTime = czpTime;
    }

    public String getCzpContent() {
        return czpContent;
    }

    public void setCzpContent(String czpContent) {
        this.czpContent = czpContent;
    }

    public boolean search(String newText) {
        if (this.getCzpPeople().indexOf(newText) != -1 ||
                this.getCzpContent().indexOf(newText) != -1) {
            return true;
        } else {
            return false;
        }

    }
}
