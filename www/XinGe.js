var exec = require('cordova/exec');
//登录,参数{"username" : 用户名, "token" : token}
exports.login = function (arg0, success, error) {
    exec(success, error, 'XinGe', 'login', [arg0]);
};
//退出登录，无参数
exports.logout = function (arg0, success, error) {
    exec(success, error, 'XinGe', 'logout', [arg0]);
};
//程序启动时调用，注册推送，无参数
exports.didFinishLaunchingWithOptions = function (arg0, success, error) {
    exec(success, error, 'XinGe', 'didFinishLaunchingWithOptions', [arg0]);
};
//程序进入后台时调用，无参数
exports.applicationDidEnterBackground = function (arg0, success, error) {
    exec(success, error, 'XinGe', 'applicationDidEnterBackground', [arg0]);
};
//程序回到前台时调用，无参数
exports.applicationWillEnterForeground = function (arg0, success, error) {
    exec(success, error, 'XinGe', 'applicationWillEnterForeground', [arg0]);
};