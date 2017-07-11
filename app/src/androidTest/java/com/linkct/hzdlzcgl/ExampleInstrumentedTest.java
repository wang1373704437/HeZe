package com.linkct.hzdlzcgl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.linkct.hzdlzcgl.dao.MyDateDao;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.CzpInfo;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.domain.DzdInfo;
import com.linkct.hzdlzcgl.domain.ImageInfo;
import com.linkct.hzdlzcgl.domain.WxjlInfo;
import com.linkct.hzdlzcgl.utils.CommonUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.linkct.hzdlzcgl", appContext.getPackageName());

        MyDateDao mDao = new MyDateDao(appContext);
        mDao.findAllDateByUUID("1");

        UserDao user=new UserDao(appContext);

        user.cleanALL();

        for (int i=0;i<11;i++){
            DataInfo data=new DataInfo();
            data.setPid("g_1");
            data.setUuid("102"+i);
            data.setEquipment_name("RRETTY");
            data.setEquipment_model("AMP-L"+i);
            data.setProductiontime("2017"+i);
            data.setOpttime("2018"+i);
            data.setSuppliermf("设备厂家"+i);
            data.setSuppliercontact("张三"+i);
            data.setSupplierphone("100860"+i);

            user.add(data);
        }


        DataInfo data2=new DataInfo();
        data2.setPid("g_12");
        data2.setUuid("g_1");
        data2.setPname("变电站1");
        data2.setEquipment_name("RRETTY11111111111");
        data2.setEquipment_model("AMP-L");
        data2.setProductiontime("2017-05-06 12:35:65");
        data2.setOpttime("2017-05-06 12:35:65");
        data2.setSuppliermf("设备厂家222222222222222222222222222222222222222222222222");
        data2.setSuppliercontact("张三");
        data2.setSupplierphone("1008602222222222");
        user.add(data2);
        List<DataInfo> list = user.listByDataInfoIdByUUid("g_1");
        Log.e("ExampleInstrumentedTest","============"+list.get(0).toString());


       List<WxjlInfo> wxList =new ArrayList<>();
        for (int i=0;i<110;i++){
            WxjlInfo wx=new WxjlInfo();
            wx.setUuid("g_1");
            wx.setWxTime("2017-06-05 12:30"+i);
            wx.setWxPeople("张三"+i);
            wx.setWxSerial("0x000"+i);
            wx.setWxContent("内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+
                    "内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+"");
            wxList.add(wx);
        }
        user.addWxjl(wxList);


        List<ImageInfo> imageList =new ArrayList<>();
        for (int i=0;i<110;i++){
            ImageInfo image=new ImageInfo();
            image.setUuid("g_1");
            image.setIndex(i);
            image.setPath((i%17)+".jpg");
            imageList.add(image);
        }
        user.addImageList(imageList);

        List<CzpInfo> czList =new ArrayList<>();
        for (int i=0;i<110;i++){
            CzpInfo cz=new CzpInfo();
            cz.setUuid("g_1");
            cz.setCzpTime("2017-06-05 12:30"+i);
            cz.setCzpPeople("张三"+i);
            cz.setCzpContent("操作内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+
                    "内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+""+"内容123123"+ CommonUtils.getUUID()+"");
            czList.add(cz);
        }
        user.addczpList(czList);



        List<DzdInfo> dzdList =new ArrayList<>();
        for (int i=0;i<20;i++){
            DzdInfo cz=new DzdInfo();
            cz.setUuid("g_1");
            cz.setDzdTime("2017-06-05 12:30"+i);
            cz.setDzdSerial("0x000"+i);
            cz.setDzdPeople("张三"+i);
            cz.setDzd_acceptance("李四"+i);
            dzdList.add(cz);
        }
        user.addDzdList(dzdList);

    }
}
