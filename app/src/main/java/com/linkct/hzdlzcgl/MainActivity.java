package com.linkct.hzdlzcgl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkct.hzdlzcgl.dao.MyDateDao;
import com.linkct.hzdlzcgl.utils.CsvUtils;
import com.linkct.hzdlzcgl.utils.FileUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ImageView tv_scanning;
    public static final int APPLY_READ_EXTERNAL_STORAGE = 0x111;
    private boolean updateDate;
    private TextView tv_tips;
    private MyDateDao mDao;
    private String dbPath;//资源文件路径
    private String mtLogPath;
    private String optPath;
    private String svmPath;
    private String wxImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initView();
        dbPath=FileUtils.DB_PATH+"equipment.csv";//设备信息
        mtLogPath=FileUtils.DB_PATH+"mtLog.csv";//维修记录
        optPath=FileUtils.DB_PATH+"opt.csv";//操作记录
        svmPath=FileUtils.DB_PATH+"svm.csv";//定值单
        wxImage=FileUtils.DB_PATH+"wt.csv";//维修记录图片模式

        updateDate = CsvUtils.getVersion(getApplicationContext(),dbPath);
        Log.e("CsvUtils","资源文件****====="+dbPath);
        if (updateDate) {
//            rl_update_data.setVisibility(View.GONE);
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    FileUtils.initPath();
                    clearImageDiskCache(getApplication());

                    CsvUtils.getCSV(getApplicationContext(),dbPath,mtLogPath,optPath,svmPath,wxImage);
                    Log.e(TAG, "运行在什么线程" + Thread.currentThread().getName());
                    e.onNext("action");
                    Thread.sleep(1500);
                    e.onComplete();
                }
            }).subscribeOn(Schedulers.newThread())               //线程调度器,将发送者运行在子线程
                    .observeOn(AndroidSchedulers.mainThread())          //接受者运行在主线程
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "onSubscribe: ");
                            Log.e(TAG, "接收在什么线程" + Thread.currentThread().getName());

                        }

                        @Override
                        public void onNext(String value) {
                            Log.e(TAG, "onNext: " + value);

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: ");
                            finish();
                            startActivity(new Intent(MainActivity.this, NewScanningActivity.class));
                            overridePendingTransition(0, 0);
                        }
                    });
        } else {
            tv_tips.setVisibility(View.GONE);
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    FileUtils.initPath();
                    Log.e(TAG, "运行在什么线程" + Thread.currentThread().getName());
                    e.onNext("action");
                    Thread.sleep(1000);
                    e.onComplete();
                }
            }).subscribeOn(Schedulers.newThread())               //线程调度器,将发送者运行在子线程
                    .observeOn(AndroidSchedulers.mainThread())          //接受者运行在主线程
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "onSubscribe: ");
                            Log.e(TAG, "接收在什么线程" + Thread.currentThread().getName());

                        }

                        @Override
                        public void onNext(String value) {
                            Log.e(TAG, "onNext: " + value);

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: ");
                            finish();
                            startActivity(new Intent(MainActivity.this, NewScanningActivity.class));
                            overridePendingTransition(0, 0);
                        }
                    });

        }
    }


    private void initView() {
        tv_scanning = (ImageView) findViewById(R.id.tv_scanning);
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        tv_scanning.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_scanning://扫描二维码
//                finish();
//                startActivity(new Intent(MainActivity.this, ScanningActivity.class));
                break;
        }
    }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
