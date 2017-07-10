package com.linkct.hzdlzcgl.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 维修记录
 * Created by wlh on 2017/6/30.
 */
@DatabaseTable(tableName = "tb_Wxjl")
public class WxjlInfo {
    @DatabaseField(generatedId = true)
    int _id;//主键
    @DatabaseField(columnName = "uuid")
    String uuid;//对应的设备唯一id

    @DatabaseField(columnName = "wxTime")
    String wxTime;//维修记录时间

    @DatabaseField(columnName = "wxContent")
    String wxContent;//维修记录内容

    @DatabaseField(columnName = "wxPeople")
    String wxPeople;//维修记录行人

    @DatabaseField(columnName = "wxSerial")
    String wxSerial;//维修编号

    public WxjlInfo() {
    }

    public WxjlInfo(int _id, String uuid, String wxTime, String wxContent, String wxPeople, String wxSerial) {
        this._id = _id;
        this.uuid = uuid;
        this.wxTime = wxTime;
        this.wxContent = wxContent;
        this.wxPeople = wxPeople;
        this.wxSerial = wxSerial;
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

    public String getWxTime() {
        return wxTime;
    }

    public void setWxTime(String wxTime) {
        this.wxTime = wxTime;
    }

    public String getWxContent() {
        return wxContent;
    }

    public void setWxContent(String wxContent) {
        this.wxContent = wxContent;
    }

    public String getWxPeople() {
        return wxPeople;
    }

    public void setWxPeople(String wxPeople) {
        this.wxPeople = wxPeople;
    }

    public String getWxSerial() {
        return wxSerial;
    }

    public void setWxSerial(String wxSerial) {
        this.wxSerial = wxSerial;
    }

    public boolean search(String newText) {
        if(this.getWxPeople().indexOf(newText)!=-1||
                this.getWxSerial().indexOf(newText)!=-1||
                this.getWxContent().indexOf(newText)!=-1){
            return true;
        }else{
            return  false;
        }
    }
}
