package com.he.hack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    public MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView viewById = (TextView) findViewById(R.id.tv);

        myHandler = new MyHandler(this);

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Flowable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                if (aLong < 6) {
                                    viewById.setText((int) (5 -aLong)+"秒后");
                                }
                                if (aLong == 5) {

                                    Intent intent = new Intent();
//                                    intent.setClassName("com.jifen.qukan","com.jifen.qkbase.main.MainActivity");
                                    intent.setClassName("com.expflow.reading","com.expflow.reading.activity.MainActivity");
//                                    intent.setClassName("com.tencent.mm","com.tencent.mm..plugin.setting.ui.setting.SettingsAccountInfoUI");
                                    startActivity(intent);
//                                    execShell("am start com.tencent.mm/com.tencent.mm.ui.LauncherUI");
                                    myHandler.sendEmptyMessageDelayed(0,2000);
//                                    myHandler.sendEmptyMessageDelayed(7,2000);


                                }
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                            }
                        })
                        .subscribe();
            }
        });






    }


    public static class MyHandler extends Handler {


        public static void execShell(String cmd) {
            try {
                //权限设置
                Process p = Runtime.getRuntime().exec("su");  //开始执行shell脚本
                //获取输出流
                OutputStream outputStream = p.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                //将命令写入
                dataOutputStream.writeBytes(cmd);
                //提交命令
                dataOutputStream.flush();
                //关闭流操作
                dataOutputStream.close();
                outputStream.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }


        private WeakReference<MainActivity> mActivity ;

        public MyHandler(MainActivity activity){
            mActivity   = new WeakReference<MainActivity>(activity);

        }


private int count = 0;
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            Log.e("消息==", msg.what+"");
            Log.e("消息count==", count+"");
            switch (msg.what) {

                case 0:
                    execShell("input swipe 566 1670 740 436");
                    if (count == 999) {
                        mActivity.get().myHandler.sendEmptyMessageDelayed(0,1000);
                        count = 0;
                    } else {

                        mActivity.get().myHandler.sendEmptyMessageDelayed(1,2000);
                    }
                    break;

                case 1:
                    execShell("input tap 531 1537");
                    mActivity.get().myHandler.sendEmptyMessageDelayed(2,4000);
                    break;

                case 2:
                    execShell("input swipe 550 1578 395 776");

                    if (count <= 16) {

                        mActivity.get().myHandler.sendEmptyMessageDelayed(2,4000);

                           count = count + 4;

                    } else {
                        mActivity.get().myHandler.sendEmptyMessageDelayed(3,1000);
                    }

                    break;

                case 3:
                    execShell("input swipe 395 776 550 1578 ");
                    if (count <=32) {
                        mActivity.get().myHandler.sendEmptyMessageDelayed(3,4000);
                        count = count + 4;
                    } else {
                        Intent intent = new Intent();
                        intent.setClassName("com.expflow.reading","com.expflow.reading.activity.MainActivity");
                        mActivity.get().startActivity(intent);
                        mActivity.get().myHandler.sendEmptyMessageDelayed(0,1000);
                        count = 999;
                    }


                    break;

                    case 7:
                execShell("input tap 588 474");
                        mActivity.get().myHandler.sendEmptyMessageDelayed(8,1000);

                break;

                case 8:
                execShell("input tap 437 1865");
                        mActivity.get().myHandler.sendEmptyMessageDelayed(9,2000);

                break;

                case 9:
                execShell("input text hello");
                        mActivity.get().myHandler.sendEmptyMessageDelayed(10,2000);

                break;

                case 10:
                    execShell("input tap 1011 1031");

                    break;

            }



        }



    }
}
