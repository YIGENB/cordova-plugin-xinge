//
//  WWIMManager.h
//  LTIM
//
//  Created by LT on 2019/11/13.
//  Copyright © 2019 LTIM. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <SystemConfiguration/SystemConfiguration.h>
#import <MobileCoreServices/MobileCoreServices.h>

NS_ASSUME_NONNULL_BEGIN
@interface WWIMManager : NSObject
+ (instancetype)shareManager;
/// 登录
/// @param userName 用户名
/// @param token token
- (void)ww_loginWithUserName:(NSString *)userName token:(NSString *)token;

///退出登录
- (void)ww_logout;


#pragma mark - 推送相关配置
- (void)ww_application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions;
- (void)ww_applicationDidEnterBackground:(UIApplication *)application;
- (void)ww_applicationWillEnterForeground:(UIApplication *)application;
- (void)ww_application:(UIApplication *)app didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken;
- (void)ww_application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo;
@end

NS_ASSUME_NONNULL_END
