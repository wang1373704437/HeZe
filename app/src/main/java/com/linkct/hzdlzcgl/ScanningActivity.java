//package com.linkct.hzdlzcgl;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.linkct.hzdlzcgl.dao.MyDateDao;
//import com.linkct.hzdlzcgl.dao.UserDao;
//import com.linkct.hzdlzcgl.domain.DataInfo;
//import com.linkct.hzdlzcgl.utils.AppToast;
//
//import java.util.List;
//
//import cn.bingoogolapple.qrcode.core.QRCodeView;
//import cn.bingoogolapple.qrcode.zbar.ZBarView;
//
//public class ScanningActivity extends AppCompatActivity implements  QRCodeView.Delegate , View.OnClickListener {
//
//    private static final String TAG="ScanningActivity";
//    private ZBarView mQRCodeView;
//    private long exitTime = 0;
//    private ImageView tv_flash;
//    private boolean flashFlag=false;
//    private MyDateDao myDao;
//    private UserDao userDao;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
//        setContentView(R.layout.activity_scanning);
//        myDao=new MyDateDao(getApplicationContext());
//        userDao=new UserDao(getApplicationContext());
//        tv_flash=(ImageView)findViewById(R.id.tv_flash);
//        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
//
//        mQRCodeView.setDelegate(this);
//
//        tv_flash.setOnClickListener(this);
//    }
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mQRCodeView.startCamera();
//        mQRCodeView.showScanRect();
//        mQRCodeView.startSpot();
//    }
//
//    @Override
//    protected void onStop() {
//        mQRCodeView.stopCamera();
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        mQRCodeView.onDestroy();
//        super.onDestroy();
//    }
//
//    private void vibrate() {
//        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        vibrator.vibrate(200);
//    }
//
//    @Override
//    public void onScanQRCodeSuccess(String result) {
//        Log.i(TAG, "result:" + result);
//        String pid=result.trim();
//        vibrate();
//        List<DataInfo> list = userDao.listByDataInfoIdByUUid(pid);
//        if(list!=null&&list.size()>0){
//            //启动activity
//            AppToast.showShortText(getApplicationContext(),"找到匹配数据");
//            Intent intent=new Intent(ScanningActivity.this,DataListActivity.class);
//            intent.putExtra("pid",pid);
//            startActivity(intent);
//        }else{
//            AppToast.showShortText(getApplicationContext(),"无法识别此设备");
//            mQRCodeView.startSpot();
//        }
//
//
//    }
//
//    @Override
//    public void onScanQRCodeOpenCameraError() {
//        AppToast.showShortText(getApplicationContext(),"打开相机出错");
//    }
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public void exit() {
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                    Toast.LENGTH_SHORT).show();
//            exitTime = System.currentTimeMillis();
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tv_flash:
//                flashFlag=!flashFlag;
//                if(flashFlag){//开启闪光灯
//                    tv_flash.setImageResource(R.drawable.light_selected);
//                    mQRCodeView.openFlashlight();
//                }else {//关闭闪光灯
//                    tv_flash.setImageResource(R.drawable.light_def);
//                    mQRCodeView.closeFlashlight();
//                }
//                break;
//            default:
//                break;
//        }
//    }
//}
