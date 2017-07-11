package com.linkct.hzdlzcgl.utils;

import android.content.Context;
import android.util.Log;

import com.linkct.hzdlzcgl.dao.MyDateDao;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.CzpInfo;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.domain.DzdInfo;
import com.linkct.hzdlzcgl.domain.ImageInfo;
import com.linkct.hzdlzcgl.domain.WxjlInfo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by wlh on 2017/6/30.
 */

public class CsvUtils {
    public static String TAG = "CsvUtils";
    public static String CodingFormat="utf-8";
//    public static String CodingFormat="gb2312";
    public static boolean getVersion(Context context, String csvPath) {
        ArrayList<DataInfo> dataLists = new ArrayList<DataInfo>();
        boolean flag = false;
        MyDateDao myDao = new MyDateDao(context);
        //_______________________导入到数据库，未成功______________________________
        try {
            File csvFile = new File(csvPath);
            if (!csvFile.exists()) {
                return false;
            }
            DataInputStream in = new DataInputStream(new FileInputStream(csvFile));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, CodingFormat));
            String line = "";
            int i = 0;
            if ((line = br.readLine()) != null) {//一次一行，lists.size()=14,28,42...
                // 把一行数据分割成多个字段
                ArrayList<String> lists = new ArrayList<String>();
                DataInfo data = new DataInfo();
                StringTokenizer st = new StringTokenizer(line, ",");
                if (st.hasMoreTokens()) {//一次一个 lists.size()=1
                    String str = st.nextToken().trim();
                    String db_version = myDao.findVersion();
                    Log.e("CsvUtils", "资源文件****=====" + str);
                    Log.e("CsvUtils", "本地数据库****===" + db_version);
                    if (str.equals(db_version)) {
                        flag = false;
                    } else {
                        //更新本地数据库标志位
                        myDao.updateVersion(str);
                        flag = true;
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void getCSV(Context context, String csvPath, String mtLogPath, String optPath, String svmPath) {
        ArrayList<DataInfo> dataLists = new ArrayList<DataInfo>();
        UserDao user = new UserDao(context);
        user.cleanALL();
        try {
            File csvFile = new File(csvPath);//设备信息
            File mtLogFile = new File(mtLogPath);//检修记录
            File optFile = new File(optPath);//操作记录
            File svmFile = new File(svmPath);//定值单

            if (!csvFile.exists()) {
                return;
            }
            DataInputStream in = new DataInputStream(new FileInputStream(csvFile));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, CodingFormat));
            String line = "";
            int i = 0;
            ArrayList<DataInfo> lists = new ArrayList<DataInfo>();//设备列表
            while ((line = br.readLine()) != null) {//一次一行，lists.size()=14,28,42...
                // 把一行数据分割成多个字段
                String[] arr = line.split(",");
                for (int w = 0; w < arr.length; w++) {
                    if (arr[w].equals("*")) {
                        arr[w] = "";
                    }
                }
                if (arr.length >= 10) {
                    DataInfo data = new DataInfo();
                    data.setUuid(arr[0]);
                    data.setEquipment_name(arr[1]);
                    data.setPid(arr[2]);
                    data.setEquipment_model(arr[3]);
                    data.setSuppliermf(arr[4]);
                    data.setSupplierphone(arr[5]);
                    data.setSuppliercontact(arr[6]);
                    String time = arr[7];
                    if (time.length() > 2)
                        data.setProductiontime(time.substring(0, time.length() - 2));

                    time = arr[8];
                    if (time.length() > 2)
                        data.setOpttime(time.substring(0, time.length() - 2));
                    data.setNote(arr[9]);
                    data.setPname("");
                    data.setSupptextAll("");

                    List<ImageInfo>  mImageList=new ArrayList<>();
                    for(int m=10;m<arr.length;m++){
                        ImageInfo image=new ImageInfo();
                        image.setUuid(data.getUuid());
                        String path=arr[m];
                        if (path.equals("*"))
                            continue;
                        image.setPath(path);
                        mImageList.add(image);
                    }
                    user.addImageList(mImageList);
                    lists.add(data);
                }
            }
            user.addDataList(lists);

            if (mtLogFile.exists()) {//维修记录
                insertmtLog(user,mtLogFile);
            }
            if (optFile.exists()) {//操作记录
                inserOpt(user,optFile);
            }
            if (svmFile.exists()) {//定值单
                insersvm(user,svmFile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入定值单
     * @param user
     * @param svmFile
     */
    private static void insersvm(UserDao user, File svmFile) throws Exception {

        DataInputStream in = new DataInputStream(new FileInputStream(svmFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, CodingFormat));
        String line = "";
        ArrayList<DzdInfo> lists = new ArrayList<DzdInfo>();//设备列表
        while ((line = br.readLine()) != null) {//一次一行，lists.size()=14,28,42...
            // 把一行数据分割成多个字段
            String[] arr = line.split(",");
            for (int w = 0; w < arr.length; w++) {
                if (arr[w].equals("*")) {
                    arr[w] = "";
                }
            }
            if (arr.length >= 6) {
                DzdInfo data = new DzdInfo();
                data.setDzdSerial(arr[1]);
                data.setDzdPeople(arr[2]);
                String time=arr[3];
                if (time.length() > 2)
                    data.setDzdTime(time.substring(0, time.length() - 2));
                data.setDzd_acceptance(arr[4]);
                data.setUuid(arr[5]);
                lists.add(data);
            }
        }
        user.addDzdList(lists);
    }

    /**
     * 插入操作记录
     * @param user
     * @param optFile
     */
    private static void inserOpt(UserDao user, File optFile) throws Exception {


        DataInputStream in = new DataInputStream(new FileInputStream(optFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, CodingFormat));
        String line = "";
        int i = 0;
        ArrayList<CzpInfo> lists = new ArrayList<CzpInfo>();//设备列表
        while ((line = br.readLine()) != null) {//一次一行，lists.size()=14,28,42...
            // 把一行数据分割成多个字段
            String[] arr = line.split(",");
            for (int w = 0; w < arr.length; w++) {
                if (arr[w].equals("*")) {
                    arr[w] = "";
                }
            }
            if (arr.length >= 5) {
                CzpInfo data = new CzpInfo();

                data.setCzpPeople(arr[1]);
                String time=arr[2];
                if (time.length() > 2)
                    data.setCzpTime(time.substring(0, time.length() - 2));
                data.setCzpContent(arr[3]);
                data.setUuid(arr[4]);
                lists.add(data);
            }
        }
        user.addczpList(lists);
    }

    /**
     * 插入维修记录
     * @param user
     * @param mtLogFile
     * @throws Exception
     */
    private static void insertmtLog(UserDao user,File mtLogFile) throws Exception {

        DataInputStream in = new DataInputStream(new FileInputStream(mtLogFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, CodingFormat));
        String line = "";
        int i = 0;
        ArrayList<WxjlInfo> lists = new ArrayList<WxjlInfo>();//设备列表
        while ((line = br.readLine()) != null) {//一次一行，lists.size()=14,28,42...
            // 把一行数据分割成多个字段
            String[] arr = line.split(",");
            for (int w = 0; w < arr.length; w++) {
                if (arr[w].equals("*")) {
                    arr[w] = "";
                }
            }
            if (arr.length >= 6) {
                WxjlInfo data = new WxjlInfo();

                data.setWxSerial(arr[1]);
                data.setWxPeople(arr[2]);
                String time=arr[3];

                if (time.length() > 2)
                    data.setWxTime(time.substring(0, time.length() - 2));
                data.setWxContent(arr[4]);
                data.setUuid(arr[5]);
                lists.add(data);
            }
        }
        user.addWxjl(lists);

    }

    static String[] imgs = new String[]{"2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg"
            , "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg"
            , "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg"};

//    public static void getCSVDzd(Context context) {
//        List<DzdInfo> list = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            DzdInfo data = new DzdInfo();
//            data.setDzdSerial("0x000" + i);
//            data.setDzdTime("" + System.currentTimeMillis());
//            data.setDzdPeople("张三" + i);
//            data.setUuid("" + ((i % 3) + 1));
//
//            list.add(data);
//        }
//        MyDateDao myDao = new MyDateDao(context);
//        myDao.insertdzd(list);
//
//
//        List<GzpInfo> list2 = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            GzpInfo data = new GzpInfo();
//            data.setGzp_uuid(CommonUtils.getUUID());
//            data.setGzpPeople("工作票张三" + i);
//            data.setGzpTime("" + System.currentTimeMillis());
//            data.setGzpContent("工作票内容我有很多个字？？代码昆明…………*&&…" +
//                    "…&……&……&……&··，，,,,,dasd" + i);
//            data.setUuid("" + ((i % 3) + 1));
//            List<String> imagelist = new ArrayList<>();
//            for (int k = 0; k < i % 18; k++) {
//                imagelist.add(imgs[k]);
//            }
//            data.setGzpImage(imagelist);
//
//            list2.add(data);
//        }
//        myDao.insertGZP(list2);
//
//
//        List<CzpInfo> list3 = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            CzpInfo data = new CzpInfo();
//            data.setCzpPeople("操作票李四" + i);
//            data.setCzpTime(System.currentTimeMillis() + "");
//            data.setCzpContent("操作票内容我有很多个字？？代码昆明…………*&&…" +
//                    "…&……&……&……&··，，,,,,dasd" + i);
//            data.setUuid("" + ((i % 3) + 1));
//            list3.add(data);
//        }
//        myDao.insertCZP(list3);
//
//
//        List<WxjlInfo> list4 = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            WxjlInfo data = new WxjlInfo();
//            data.setWxPeople("维修员周星驰" + i);
//            data.setWxTime(System.currentTimeMillis() + "");
//            data.setWxContent("操作票内容我有很多个字？？代码昆明…………*&&…" +
//                    "…&……&……&……&··，，,,,,dasd" + i);
//            data.setUuid("" + ((i % 3) + 1));
//            list4.add(data);
//        }
//        myDao.insertWX(list4);
//
//
//    }


}
