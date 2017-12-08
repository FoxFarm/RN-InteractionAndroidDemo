package com.testbuildandroid.reactModule;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * desc：原生和RN的信息传递
 * author：fox
 * date：2017/12/5
 */

public class MutualInformationModeule extends ReactContextBaseJavaModule {

    public static final String REACTCLASSNAME = "MutualInformationModeule";
    private ReactApplicationContext mContext;

    public MutualInformationModeule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return REACTCLASSNAME;
    }

    /**
     * Callback 方式 RN调用原生回调的方式 jS调用一次，Native返回一次	CallBack为异步操作，返回时机不确定
     * rn调用Native,并获取返回值
     *
     * @param msg
     * @param callback
     */
    @ReactMethod
    public void rnCallNativeFromCallback(String msg, Callback callback) {

        // 1.处理业务逻辑...
        String result = "处理结果：" + msg;
        // 2.回调RN,即将处理结果返回给RN
        callback.invoke(result);
    }

    /**
     * Promise RN调用原生回调的方式 JS调用一次，Native返回一次	每次使用需要JS调用一次
     *
     * @param msg
     * @param promise
     */
    @ReactMethod
    public void rnCallNativeFromPromise(String msg, Promise promise) {
        try {
            //业务逻辑处理
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            String componentName = getCurrentActivity().getComponentName().toString();
            promise.resolve(componentName);
        }catch (Exception e){
            promise.reject("100",e.getMessage());//promise 失败
        }
    }



}