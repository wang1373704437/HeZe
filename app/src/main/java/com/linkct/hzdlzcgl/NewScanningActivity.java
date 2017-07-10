package com.linkct.hzdlzcgl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.utils.AppToast;

import java.util.List;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class NewScanningActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;
    private final static String TAG="NewScanningActivity";
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        userDao=new UserDao(getApplicationContext());
//        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
//        mScannerView.setBorderColor(Color.parseColor("#1bb386"));//角线颜色
        //        mScannerView.setBorderStrokeWidth(50); //角线的宽度
//        mScannerView.setBorderLineLength(200);//角线的长度
//        mScannerView.setMaskColor(Color.parseColor("#33ffffff"));//整体背景颜色
//        mScannerView.setLaserColor(Color.parseColor("#1bb386"));//扫描线的颜色
        setContentView(R.layout.activity_new_scanning);
        mScannerView= (ZBarScannerView) findViewById(R.id.zbar_sv1);
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
    @Override
    public void handleResult(Result rawResult) {
        String pid= rawResult.getContents().trim();
        vibrate();

        List<DataInfo> list = userDao.listByDataInfoIdByUUid(pid);
        if(list!=null&&list.size()>0){
            //启动activity
            AppToast.showShortText(getApplicationContext(),"找到匹配数据");
            Intent intent=new Intent(NewScanningActivity.this,DataListActivity.class);
            intent.putExtra("pid",pid);
            startActivity(intent);
        }else{
            AppToast.showShortText(getApplicationContext(),"无法识别此设备");
        }
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
