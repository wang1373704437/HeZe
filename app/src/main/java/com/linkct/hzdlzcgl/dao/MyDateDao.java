package com.linkct.hzdlzcgl.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.linkct.hzdlzcgl.db.DataDbOpenHelper;
import com.linkct.hzdlzcgl.domain.CzpInfo;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.domain.DzdInfo;
import com.linkct.hzdlzcgl.domain.GzpInfo;
import com.linkct.hzdlzcgl.domain.WxjlInfo;
import com.linkct.hzdlzcgl.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlh on 2017/6/29.
 */

public class MyDateDao {

    private static final String TAG = "CalllogDao";
    private static final String TABLE_NAME = "CalllogDao.db";
    private Context context;
    private DataDbOpenHelper helper;

    public MyDateDao(Context context) {
        this.context = context;
        helper = new DataDbOpenHelper(context);
    }

    public void insert() {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            try {
                String uuid = CommonUtils.getUUID();
                Log.e(TAG, "heheheh" + uuid);
                db.execSQL(
                        "insert into data_image(image_index,image_uuid) values(?,?)",
                        new Object[]{0, uuid});
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
    }

    public boolean finddataByUUid(String pid) {
        boolean flag = false;
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from equipment where pid = ?",
                    new String[]{pid});
            if (cursor.moveToNext()) {
                flag = true;
            }
            cursor.close();
        }
        return flag;
    }



    public DataInfo findAllDateByUUID(String uuid) {
        DataInfo data = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from equipment where uuid = ?",
                    new String[]{uuid});
            if (cursor.moveToNext()) {
                data = new DataInfo();
                data.setUuid(cursor.getString(cursor
                        .getColumnIndex("uuid")));
                data.setSupplierphone(cursor.getString(cursor
                        .getColumnIndex("supplierphone")));
                data.setSuppliercontact(cursor.getString(cursor
                        .getColumnIndex("suppliercontact")));

                Cursor cursorImage3 = db.rawQuery("select * from data_image where image_uuid = ? and type=?",
                        new String[]{uuid,DataInfo.imageType3+""});
                List<String> ImageList3=new ArrayList<>();
                while (cursorImage3.moveToNext()) {
                    ImageList3.add(cursorImage3.getString(cursorImage3
                            .getColumnIndex("image_name")));
                }
                data.setSupplierImage(ImageList3);
                cursorImage3.close();


                data.setDzdList(findDzdByuuid(uuid));//定值单相关信息
//                data.setGzpList(findGzpByuuid(uuid));//工作票相关信息
                data.setCzpList(findCZPByuuid(uuid));//操作票相关信息
                data.setWxList(findWXJLByuuid(uuid));//维修记录相关信息
            }

            cursor.close();
        }
        return data;
    }

    /**
     * 根据设备id查询定值单信息
     * @param uuid
     * @return
     */
    public List<DzdInfo> findDzdByuuid(String uuid) {
        List<DzdInfo> dzdList=new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from dzd where uuid = ? order by dzd_time",
                    new String[]{uuid});
            while (cursor.moveToNext()) {
                DzdInfo data=new DzdInfo();
                String dzduuid=cursor.getString(cursor
                        .getColumnIndex("dzd_uuid"));//数据库中生成的uuid用于关联查询

                data.setDzdSerial(cursor.getString(cursor
                        .getColumnIndex("dzd_erial")));//定值单编号，用于显示
                data.setDzdTime(cursor.getString(cursor
                        .getColumnIndex("dzd_time")));
                data.setDzdPeople(cursor.getString(cursor
                        .getColumnIndex("dzd_people")));

                Cursor cursorImage3 = db.rawQuery("select * from data_image where image_uuid = ? and type=?",
                        new String[]{dzduuid,DataInfo.imageType1+""});
                List<String> ImageList3=new ArrayList<>();
                while (cursorImage3.moveToNext()) {
                    ImageList3.add(cursorImage3.getString(cursorImage3
                            .getColumnIndex("image_name")));
                }
                cursorImage3.close();

                dzdList.add(data);
            }

            cursor.close();
        }
        return dzdList;
    }
    /**
     * 根据设备id查工作工作票
     * @param uuid
     * @return
     */
    public List<GzpInfo> findGzpByuuid(String uuid) {
        List<GzpInfo> gzpList=new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from gzp where uuid = ? order by gzp_time",
                    new String[]{uuid});
            while (cursor.moveToNext()) {
                GzpInfo data=new GzpInfo();
                String dzduuid=cursor.getString(cursor
                        .getColumnIndex("gzp_uuid"));//数据库中生成的uuid用于关联查询

                data.setGzpPeople(cursor.getString(cursor
                        .getColumnIndex("gzp_people")));//定值单编号，用于显示
                data.setGzpTime(cursor.getString(cursor
                        .getColumnIndex("gzp_time")));
                data.setGzpContent(cursor.getString(cursor
                        .getColumnIndex("gzp_content")));

                Cursor cursorImage3 = db.rawQuery("select * from data_image where image_uuid = ? and type=?",
                        new String[]{dzduuid,DataInfo.imageType2+""});
                List<String> ImageList3=new ArrayList<>();
                while (cursorImage3.moveToNext()) {
                    ImageList3.add(cursorImage3.getString(cursorImage3
                            .getColumnIndex("image_name")));
                }
                data.setGzpImage(ImageList3);
                cursorImage3.close();

                gzpList.add(data);
            }

            cursor.close();
        }
        return gzpList;
    }
    /**
     * 根据设备id查操作票记录
     * @param uuid
     * @return
     */
    public List<CzpInfo> findCZPByuuid(String uuid) {
        List<CzpInfo> gzpList=new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from czp where uuid = ? order by czp_time",
                    new String[]{uuid});
            while (cursor.moveToNext()) {
                CzpInfo data=new CzpInfo();

                data.setCzpPeople(cursor.getString(cursor
                        .getColumnIndex("czp_executor")));
                data.setCzpTime(cursor.getString(cursor
                        .getColumnIndex("czp_time")));
                data.setCzpContent(cursor.getString(cursor
                        .getColumnIndex("czp_content")));

                gzpList.add(data);
            }

            cursor.close();
        }
        return gzpList;
    }
    /**
     * 根据设备id查维修记录
     * @param uuid
     * @return
     */
    public List<WxjlInfo> findWXJLByuuid(String uuid) {
        List<WxjlInfo> gzpList=new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from jxjl where uuid = ? order by jxjl_time",
                    new String[]{uuid});
            while (cursor.moveToNext()) {
                WxjlInfo data=new WxjlInfo();

                data.setWxPeople(cursor.getString(cursor
                        .getColumnIndex("jxjl_executor")));
                data.setWxTime(cursor.getString(cursor
                        .getColumnIndex("jxjl_time")));
                data.setWxContent(cursor.getString(cursor
                        .getColumnIndex("jxjl_content")));

                gzpList.add(data);
            }

            cursor.close();
        }
        return gzpList;
    }

    /**
     * 插入定值单
     * @param list
     */
    public void insertdzd(List<DzdInfo> list) {

        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            try {
                for (DzdInfo data : list) {
                    db.execSQL(
                            "insert or ignore into dzd(uuid,dzd_uuid,dzd_erial,dzd_time,dzd_people" +
                                    ") values(?,?,?,?,?)",
                            new Object[]{
                                    data.getUuid(), data.getDzdSerial(),
                                    data.getDzdTime(), data.getDzdPeople(),
                            });

                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
    }

    /**
     * 插入工作票
     * @param list
     */
    public void insertGZP(List<GzpInfo> list) {

        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            try {
                for (GzpInfo data : list) {
                    db.execSQL(
                            "insert or ignore into gzp(uuid,gzp_uuid,gzp_people,gzp_time,gzp_content" +
                                    ") values(?,?,?,?,?)",
                            new Object[]{
                                    data.getUuid(), data.getGzp_uuid(), data.getGzpPeople(),
                                    data.getGzpTime(), data.getGzpContent(),
                            });
                    List<String> ImageList1 = data.getGzpImage();
                    if (ImageList1 != null && ImageList1.size() > 0) {//设备资料
                        for (String ImageName : ImageList1) {
                            db.execSQL(
                                    "insert or ignore into data_image(type,image_name,image_uuid" +
                                            ") values(?,?,?)",
                                    new Object[]{
                                            DataInfo.imageType2, ImageName, data.getGzp_uuid()
                                    });
                        }
                    }

                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
    }

    /**
     * 插入操作票
     * @param list
     */
    public void insertCZP(List<CzpInfo> list) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            try {
                for (CzpInfo data : list) {
                    db.execSQL(
                            "insert or ignore into czp(uuid,czp_uuid,czp_guardian,czp_executor,czp_time" +
                                    ",czp_content) values(?,?,?,?,?,?)",
                            new Object[]{
                                    data.getUuid(),
                                    data.getCzpPeople(), data.getCzpTime(),data.getCzpContent()
                            });

                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
    }

    /**
     * 插入维修记录
     * @param list
     */
    public void insertWX(List<WxjlInfo> list) {

        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.beginTransaction();
            try {
                for (WxjlInfo data : list) {
                    db.execSQL(
                            "insert or ignore into jxjl(uuid,jxjl_uuid,jxjl_executor,jxjl_time,jxjl_content" +
                                    ") values(?,?,?,?,?)",
                            new Object[]{
                                    data.getUuid(), data.getWxPeople(),
                                    data.getWxTime(), data.getWxContent()
                            });

                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
    }
    //本地数据版本
    public String findVersion() {
        String version = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select version_id from version_tb",
                    new String[]{});
            if (cursor.moveToNext()) {
                version = cursor.getString(0);
            }
            cursor.close();
        }
        return version;
    }

    //更新本地数据版本
    public String updateVersion(String versionId) {
        String version = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            db.execSQL("update version_tb set version_id=?",
                    new Object[]{versionId});
        }
        return version;
    }
}
