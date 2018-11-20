package com.he.hack;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * ================================================
 * 作    者：何云超
 * 版    本：
 * 创建日期：2018/11/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class RobServer extends BaseAccessibilityService {

    private List<AccessibilityNodeInfo> accessibilityNodeInfosByViewId;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:


                //界面点击
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                //界面文字改动
                break;

            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
                Log.e("当前的路径==",event.getClassName().toString());
                boolean equals = event.getClassName().equals("com.uc.browser.InnerUCMobile");
                if (equals) {


                    performScrollForward();
                }



                break;
        }
    }



    @Override
    public void onInterrupt() {

    }


}
