package com.testbuildandroid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * desc：RN初始化
 * author：fox
 * date：2017/12/5
 */
public class MainActivity extends ReactActivity {
    public static final String EVENT_NAME = "nativeCallRn";
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "testBuildAndroid";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int type = getIntent().getIntExtra("type",0);
       if(type == 1){
            nativeCallRn(getIntent().getStringExtra("message"));
        }
    }

    /**
     * Native调用RN 可任意时刻传递，Native主导控制	个人觉得此种方式缺点小
     * @param msg
     */
    public  void nativeCallRn( String msg) {
        this.getReactNativeHost().getReactInstanceManager().getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(EVENT_NAME, msg);
    }
}
