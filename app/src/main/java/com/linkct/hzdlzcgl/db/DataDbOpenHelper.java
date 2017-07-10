package com.linkct.hzdlzcgl.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wlh on 2017/6/29.
 */

public class DataDbOpenHelper extends SQLiteOpenHelper {
    private static final String name = "data.db"; // 数据库名称
    private static final int version = 1; // 数据库版本

    public DataDbOpenHelper(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        /**
         * pid 父节点id
         * uuid当前节点id
         * equipment_name设备名称
         * model 设备型号
         *productiontime生产日期
         * opttime投运日期
         * suppliermf 厂家名称
         * supplierphone 厂家电话
         * suppliercontact 厂家联系人
         * note 其他
         * 扫描节点
         */
        db.execSQL("create table group_emt(_id integer primary key autoincrement,"
                + "pid text,pnmae text,uuid text,equipment_name text,model text,productiontime text,opttime text," +
                "suppliermf text,supplierphone text,suppliercontact text,note text,unique(pid,uuid))");
        /**
         * 子设备名称
         * pid 上一级名称
         * uuid 唯一id
         * equipment_name 设备名称
         *suppliername 厂家名称
         * supplieraddress 厂家地址
         *supplierphone 联系方式
         *suppliercontact 联系人
         * note 其他
         */
        db.execSQL("create table equipment(_id integer primary key autoincrement,"
                + "pid text,uuid text,equipment_name text,model text,productiontime text,opttime text," +
                "suppliermf text,supplierphone text,suppliercontact text,note text,unique(pid,uuid))");


        /**
         * 检修记录：
         * jxjl_uuid 唯一id
         *jxjl_executor 执行人
         *jxjl_time 时间
         *jxjl_content 内容
         *jxjl_workid 工作票号
         */
        db.execSQL("create table jxjl(_id integer primary key autoincrement,"
                + "uuid text,jxjl_uuid text,jxjl_executor text,jxjl_time text," +
                "jxjl_content text,jxjl_workid,unique(uuid,jxjl_uuid))");


        /**
         * 操作记录
         * czp_uuid 唯一id
         *czp_executor 执行人
         *czp_time 时间
         *czp_content 内容
         *
         */
        db.execSQL("create table czp(_id integer primary key autoincrement,"
                + "uuid text,czp_uuid text,czp_executor text,czp_time text," +
                "czp_content text,unique(uuid,czp_uuid))");

        /**
         * 定值单：
         * uuid 唯一id设备的
         * dzd_uuid 定值单的唯一uuid
         * dzd_erial 定值单编号
         *dzd_time 定值单执行时间
         *dzd_people 定值单执行人
         *dzd_acceptance 定值单验收人
         *
         */
        db.execSQL("create table dzd(_id integer primary key autoincrement,"
                + "uuid text,dzd_uuid text,dzd_erial text,dzd_time text,dzd_people text," +
                "dzd_acceptance text,unique(uuid,dzd_uuid))");




        //图片数据表
        /**
         * type 1定值单图片
         *       2工作票图片
         *       3设备资料
         *  image_index 图片的下标，排序规则
         *  image_title 图片介绍
         *  image_uuid 设备的uuid
         *  image_name 图片的名字
         *
         */
        db.execSQL("create table data_image(_id integer primary key autoincrement,"
                + "image_index integer,type integer,image_uuid text,image_name text," +
                "image_title text,unique(image_uuid,image_name,type))");

        db.execSQL("create table version_tb(_id integer primary key autoincrement,"
                + "version_id text)");

        db.execSQL("insert into version_tb(version_id) values(?)",
                new Object[]{"-1"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
