package com.testbuildandroid;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.testbuildandroid.reactModule.IntentModule;
import com.testbuildandroid.reactModule.MutualInformationModeule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * desc：React注册模块
 * author：fox
 * date：2017/12/5
 */
public class MReactPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new IntentModule(reactContext));
        modules.add(new MutualInformationModeule(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
