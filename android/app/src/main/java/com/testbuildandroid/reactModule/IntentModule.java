package com.testbuildandroid.reactModule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * desc：跳转
 * author：fox
 * date：2017/12/5
 */
public class IntentModule extends ReactContextBaseJavaModule {

    public static final String REACTCLASSNAME = "IntentModule";
    private Context mContext;

    public IntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return REACTCLASSNAME;
    }

    /**
     * js页面跳转到activity 并传数据
     *
     * @param name
     */
    @ReactMethod
    public void startActivityByClassname(String name) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (null != currentActivity) {
                Class aimActivity = Class.forName(name);
                Intent intent = new Intent(currentActivity, aimActivity);
                currentActivity.startActivity(intent);
                currentActivity.finish();
            }
        } catch (Exception e) {
            throw new JSApplicationIllegalArgumentException(
                    "无法打开activity页面: " + e.getMessage());
        }
    }


    /**
     * 从js跳转页面到原生的,并且传递数据
     *
     * @param name
     * @param params
     */
    @ReactMethod
    public void startActivity(String name, String params) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (null != currentActivity) {
                Class toActivity = Class.forName(name);
                Intent intent = new Intent(currentActivity, toActivity);
                intent.putExtra("params", params);
                currentActivity.startActivity(intent);
                currentActivity.finish();
            }
        } catch (Exception e) {
            throw new JSApplicationIllegalArgumentException(
                    "不能打开Activity : " + e.getMessage());
        }
    }

    /**
     * Activtiy跳转到JS页面，传输数据
     *
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void dataToJS(Callback successBack, Callback errorBack) {
        try {
            Activity currentActivity = getCurrentActivity();
            String result = currentActivity.getIntent().getStringExtra("data");
            if (TextUtils.isEmpty(result)) {
                result = "没有数据";
            }
            successBack.invoke(result);
        } catch (Exception e) {
            errorBack.invoke(e.getMessage());
        }
    }

    /**
     * 从JS页面跳转到Activity界面，并且等待从Activity返回的数据给JS
     *
     * @param className
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void startActivityFromJSGetResult(String className, int requestCode, Callback successBack, Callback errorBack) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                Class toActivity = Class.forName(className);
                Intent intent = new Intent(currentActivity, toActivity);
                currentActivity.startActivityForResult(intent, requestCode);
                //进行回调数据
//                successBack.invoke(App.mQueue.take());
            }
        } catch (Exception e) {
            errorBack.invoke(e.getMessage());
            e.printStackTrace();
        }

    }


}