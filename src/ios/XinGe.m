/********* XinGe.m Cordova Plugin Implementation *******/

#import "XinGe.h"
#import <IMSDK/LTIM.h>


@implementation XinGe

- (void)login:(CDVInvokedUrlCommand*)command
{
    NSMutableDictionary *args = [command argumentAtIndex:0];
    NSString   *username  = [args objectForKey:@"username"];
    NSString   *token  = [args objectForKey:@"token"];
    [[WWIMManager shareManager] ww_loginWithUserName:username token:token];
}
- (void)logout:(CDVInvokedUrlCommand *)command {
    [[WWIMManager shareManager]ww_logout];
}
#pragma mark - 注册通知 消息推送
///无参数
- (void)didFinishLaunchingWithOptions:(CDVInvokedUrlCommand *)command {
    [[WWIMManager shareManager]ww_application:nil didFinishLaunchingWithOptions:nil];
}
///无参数
- (void)applicationDidEnterBackground:(CDVInvokedUrlCommand *)command {
    [[WWIMManager shareManager]ww_applicationDidEnterBackground:nil];
}
///无参数
- (void)applicationWillEnterForeground:(CDVInvokedUrlCommand *)command {
    [[WWIMManager shareManager]ww_applicationWillEnterForeground:nil];
}
///参数{"deviceToken" : 推送时用的deviceToken}
- (void)didRegisterForRemoteNotificationsWithDeviceToken:(CDVInvokedUrlCommand *)command {
    NSMutableDictionary *args = [command argumentAtIndex:0];
    NSData *deviceToken  = [args objectForKey:@"deviceToken"];
    [[WWIMManager shareManager]ww_application:nil didRegisterForRemoteNotificationsWithDeviceToken:deviceToken];
}
///参数{"application" : UIApplication, "userInfo" : NSDictionary推送信息}
- (void)didReceiveRemoteNotification:(CDVInvokedUrlCommand *)command {
    NSMutableDictionary *args = [command argumentAtIndex:0];
    UIApplication   *application  = [args objectForKey:@"application"];
    NSDictionary   *userInfo  = [args objectForKey:@"userInfo"];
    [[WWIMManager shareManager]ww_application:application didReceiveRemoteNotification:userInfo];
}
@end
