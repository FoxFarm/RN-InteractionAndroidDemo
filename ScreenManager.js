/**
 * desc：跳转页面的地方
 * author：fox
 * date： 2017/12/5
 */

import {
    NativeModules,
} from 'react-native';
/**
 *跳转到原生并且传递一个信息
 */
export const startActivityByClassname = (isture) => {
    NativeModules.IntentModule.startActivityByClassname(isture);

};
/**
 *跳转到原生并且传递一个信息,和一个整体object的信息
 */
export const startActivity = (isture,param) => {
    NativeModules.IntentModule.startActivity(isture,param);
};
/**
 * Callback 方式 RN调用原生回调的方式 jS调用一次，Native返回一次	CallBack为异步操作，返回时机不确定
 * rn调用Native,并获取返回值
 */
export const rnCallNativeFromCallback = (isture,callback) => {
    NativeModules.MutualInformationModeule.rnCallNativeFromCallback(isture,callback);
};
/**
 Promise RN调用原生回调的方式 JS调用一次，Native返回一次	每次使用需要JS调用一次
 */

