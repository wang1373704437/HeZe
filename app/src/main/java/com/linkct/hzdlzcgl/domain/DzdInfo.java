package com.linkct.hzdlzcgl.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 定值单
 * Created by wlh on 2017/6/30.
 */
@DatabaseTable(tableName = "tb_dzd")
public class DzdInfo {
    @DatabaseField(generatedId = true)
    int _id;
    @DatabaseField(columnName = "uuid")
    String uuid;//对应的设备唯一id
    @DatabaseField(columnName = "dzdSerial")
    String dzdSerial;//定值单编号
    @DatabaseField(columnName = "dzdPeople")
    String dzdPeople;//定制单人员
    @DatabaseField(columnName = "dzd_acceptance")
    String dzd_acceptance;//定值单验收人
    @DatabaseField(columnName = "dzdTime")
    String dzdTime;//定值单时间

    public DzdInfo() {
    }

    public DzdInfo(int _id, String uuid, String dzdSerial, String dzdPeople, String dzd_acceptance, String dzdTime) {
        this._id = _id;
        this.uuid = uuid;
        this.dzdSerial = dzdSerial;
        this.dzdPeople = dzdPeople;
        this.dzd_acceptance = dzd_acceptance;
        this.dzdTime = dzdTime;
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

    public String getDzdSerial() {
        return dzdSerial;
    }

    public void setDzdSerial(String dzdSerial) {
        this.dzdSerial = dzdSerial;
    }

    public String getDzdPeople() {
        return dzdPeople;
    }

    public void setDzdPeople(String dzdPeople) {
        this.dzdPeople = dzdPeople;
    }

    public String getDzd_acceptance() {
        return dzd_acceptance;
    }

    public void setDzd_acceptance(String dzd_acceptance) {
        this.dzd_acceptance = dzd_acceptance;
    }

    public String getDzdTime() {
        return dzdTime;
    }

    public void setDzdTime(String dzdTime) {
        this.dzdTime = dzdTime;
    }

    public boolean search(String newText) {
        if (this.getDzdSerial().indexOf(newText) != -1 ||
                this.getDzd_acceptance().indexOf(newText) != -1 ||
                this.getDzdPeople().indexOf(newText) != -1) {
            return true;
        } else {
            return false;
        }

    }
}
