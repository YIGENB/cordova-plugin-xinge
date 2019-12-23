/********* XinGe.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface XinGe : CDVPlugin {
  // Member variables go here.
}
///登录 参数{"username" : 用户名, "token" : token}
- (void)login:(CDVInvokedUrlCommand*)command;
///退出登录 无参数
- (void)logout:(CDVInvokedUrlCommand *)command;

#pragma mark - 注册通知 消息推送
///无参数
- (void)didFinishLaunchingWithOptions:(CDVInvokedUrlCommand *)command;
///无参数 解决锁屏后崩溃问题 必须添加
- (void)applicationDidEnterBackground:(CDVInvokedUrlCommand *)command;
///无参数 解决锁屏后崩溃问题 必须添加
- (void)applicationWillEnterForeground:(CDVInvokedUrlCommand *)command;
///参数{"deviceToken" : 推送时用的deviceToken}
- (void)didRegisterForRemoteNotificationsWithDeviceToken:(CDVInvokedUrlCommand *)command;
///参数{"application" : UIApplication, "userInfo" : NSDictionary推送信息}
- (void)didReceiveRemoteNotification:(CDVInvokedUrlCommand *)command;

@end

