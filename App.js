/**
 * desc：RN首页
 * author：fox
 * date： 2017/12/5
 */
import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    NativeModules,
    NativeEventEmitter,
    DeviceEventEmitter
} from 'react-native';
import {
    startActivityByClassname,
    startActivity,
    rnCallNativeFromCallback,
} from './ScreenManager';

var ChivoxISE = NativeModules.MutualInformationModeule;
const myNativeEvt = new NativeEventEmitter(ChivoxISE);  //创建自定义事件接口
export default class App extends Component<{}> {

    //在组件中使用
    componentWillMount() {
        this.listener = DeviceEventEmitter.addListener('nativeCallRn', this.iseCallback.bind(this));  //对应了原生端的名字
    }

    componentWillUnmount() {
        this.listener && this.listener.remove();  //记得remove哦
        this.listener = null;
}

    render() {
        return (
            <View style={styles.container}>
                <Text>我是RN界面</Text>
                <TouchableOpacity style={styles.welcome} onPress={this.gotoNativeMessageOne.bind(this, "com.testbuildandroid.NativeActivity")}>
                    <Text>跳转到原生界面</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.welcome} onPress={this.gotoNativeMessageTwo.bind(this, "com.testbuildandroid.NativeActivity", '我很帅，第二个')}>
                    <Text>跳转到原生界面并传递一个信息</Text>
                </TouchableOpacity>

                <TouchableOpacity style={styles.welcome} onPress={this.gotoNativeMessageCallBack.bind(this, "我是第一个callback", () => {
                    alert("第一个")
                })}>
                    <Text>使用callback</Text>
                </TouchableOpacity>

                <TouchableOpacity style={styles.welcome}
                    onPress={this.CallAndroid_promise}>
                    <Text>Promise</Text>
                </TouchableOpacity>
            </View>
        );
    }

    iseCallback(data) {
        alert(data)
    }

    /**
     * 跳转到原生并传递一个信息
     */
    gotoNativeMessageOne(message) {
        startActivityByClassname(message);
    }

    /**
     * 跳转到原生并传递俩个信息
     */
    gotoNativeMessageTwo(message, param) {
        startActivity(message, param);
    }

    /**
     * 使用callback
     */
    gotoNativeMessageCallBack(message, callback) {
        rnCallNativeFromCallback(message, callback)
    }

    /**
     * 使用promise
     * @param isture
     * @param promise
     */
    CallAndroid_promise = () => {
        NativeModules.MutualInformationModeule.rnCallNativeFromPromise('rn调用原生模块的方法-成功啦').then(
            (msg) => {
                console.log('promise成功：'+msg);
            }
        ).catch(
            (err) => {
                console.log(err);
            }
        );
    }


}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {

        marginTop: 30,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});
