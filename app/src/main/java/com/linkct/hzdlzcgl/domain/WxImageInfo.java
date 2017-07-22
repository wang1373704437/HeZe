package com.linkct.hzdlzcgl.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlh on 2017/7/5.
 */
@DatabaseTable(tableName = "tb_wx_image")
public class WxImageInfo implements Serializable {

    public WxImageInfo() {
    }

    public WxImageInfo(int _id, String uuid, String path, String title, int index) {
        this._id = _id;
        this.uuid = uuid;
        this.path = path;
        this.title = title;
        this.index = index;
    }

    @DatabaseField(generatedId = true)
    int _id;

    @DatabaseField(columnName = "wximageid")
    String wximageid;//记录id
    @DatabaseField(columnName = "uuid")
    String uuid;//归属设备的uuid
    @DatabaseField(columnName = "path")
    String path;//本地路径
    @DatabaseField(columnName = "title")
    String title;//描述的文字
    @DatabaseField(columnName = "index")
    int index;//排序规则

    @DatabaseField(columnName = "date")
    String date;//描述的文字

    @DatabaseField(columnName = "peopleName")
    String peopleName;//描述的文字

    List<WxImageInfo> imageList=new ArrayList<>();

    public List<WxImageInfo> getImageList() {
        return imageList;
    }

    public void setImageList(List<WxImageInfo> imageList) {
        this.imageList = imageList;
    }

    public String getWximageid() {
        return wximageid;
    }

    public void setWximageid(String wximageid) {
        this.wximageid = wximageid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
