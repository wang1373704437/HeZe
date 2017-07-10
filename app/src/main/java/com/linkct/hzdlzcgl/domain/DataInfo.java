package com.linkct.hzdlzcgl.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

/**
 * 设备信息主表
 * Created by wlh on 2017/6/30.
 */
@DatabaseTable(tableName = "tb_data")
public class DataInfo implements Serializable {

    public static final int imageType1 = 1;//定值单图片
    public static final int imageType2 = 2;//工作票图片
    public static final int imageType3 = 3;//设备资料

    public static final int Type1 = 1;//1级别目录
    public static final int Type2 = 2;//2级目录
    public static final int Type3 = 3;//3级目录

    public DataInfo() {

    }

    public DataInfo(String pid, String pname, String uuid, String equipment_name, String equipment_model, String productiontime, String opttime, String suppliermf, String suppliercontact, String supplierphone, String note, List<String> supplierImage, List<String> supplierText, List<DzdInfo> dzdList, List<CzpInfo> czpList, List<WxjlInfo> wxList) {
        this.pid = pid;
        this.pname = pname;
        this.uuid = uuid;
        this.equipment_name = equipment_name;
        this.equipment_model = equipment_model;
        this.productiontime = productiontime;
        this.opttime = opttime;
        this.suppliermf = suppliermf;
        this.suppliercontact = suppliercontact;
        this.supplierphone = supplierphone;
        this.note = note;
        this.supplierImage = supplierImage;
        this.supplierText = supplierText;
        this.dzdList = dzdList;
        this.czpList = czpList;
        this.wxList = wxList;
    }

//    @DatabaseField(generatedId = true)
    int _id;//主键
    @DatabaseField(columnName = "pid")
    String pid;//父id
    @DatabaseField(columnName = "pname")
    String pname;//上一级列表的名字
    @DatabaseField(id=true,columnName = "uuid")
    String uuid;//设备唯一id
    @DatabaseField(columnName = "equipment_name")
    String equipment_name;//设备名称
    @DatabaseField(columnName = "equipment_model")
    String equipment_model;//设备型号
    @DatabaseField(columnName = "productiontime")
    String productiontime;//生产日期
    @DatabaseField(columnName = "opttime")
    String opttime;//投运日期
    @DatabaseField(columnName = "suppliermf")
    String suppliermf; //设备厂家
    @DatabaseField(columnName = "suppliercontact")
    String suppliercontact; //厂家联系人
    @DatabaseField(columnName = "supplierphone")
    String supplierphone; //厂家联系方式
    @DatabaseField(columnName = "note")
    String note;//备忘字段
    @DatabaseField(columnName = "supptextAll")
    String supptextAll;//图纸资料介绍文字

    List<String> supplierImage;//设备资料对应的图片
    List<String> supplierText;//设备资料对应的介绍

    List<DzdInfo> dzdList;//定值单列表
    List<CzpInfo> czpList;//操作票
    List<WxjlInfo> wxList;//检修记录

    public String getSupptextAll() {
        return supptextAll;
    }

    public void setSupptextAll(String supptextAll) {
        this.supptextAll = supptextAll;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipment_model() {
        return equipment_model;
    }

    public void setEquipment_model(String equipment_model) {
        this.equipment_model = equipment_model;
    }

    public String getProductiontime() {
        return productiontime;
    }

    public void setProductiontime(String productiontime) {
        this.productiontime = productiontime;
    }

    public String getOpttime() {
        return opttime;
    }

    public void setOpttime(String opttime) {
        this.opttime = opttime;
    }

    public String getSuppliermf() {
        return suppliermf;
    }

    public void setSuppliermf(String suppliermf) {
        this.suppliermf = suppliermf;
    }

    public String getSuppliercontact() {
        return suppliercontact;
    }

    public void setSuppliercontact(String suppliercontact) {
        this.suppliercontact = suppliercontact;
    }

    public String getSupplierphone() {
        return supplierphone;
    }

    public void setSupplierphone(String supplierphone) {
        this.supplierphone = supplierphone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getSupplierImage() {
        return supplierImage;
    }

    public void setSupplierImage(List<String> supplierImage) {
        this.supplierImage = supplierImage;
    }

    public List<String> getSupplierText() {
        return supplierText;
    }

    public void setSupplierText(List<String> supplierText) {
        this.supplierText = supplierText;
    }

    public List<DzdInfo> getDzdList() {
        return dzdList;
    }

    public void setDzdList(List<DzdInfo> dzdList) {
        this.dzdList = dzdList;
    }

    public List<CzpInfo> getCzpList() {
        return czpList;
    }

    public void setCzpList(List<CzpInfo> czpList) {
        this.czpList = czpList;
    }

    public List<WxjlInfo> getWxList() {
        return wxList;
    }

    public void setWxList(List<WxjlInfo> wxList) {
        this.wxList = wxList;
    }
}
