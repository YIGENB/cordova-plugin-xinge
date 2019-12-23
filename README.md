# cordova-plugin-xinge

拉起页面

cordova.plugins.XinGe.login({ "username": YZSoft.LoginUser.Account, "token": YZSoft.LoginUser.IMToken },
                        function (res) { 
                            console.log("IM跳转成功了") 
                        }, 
                        function (res) {
                            console.log("IM跳转失败了") 
                        }
                        
                        
                        
                        
退出登录

cordova.plugins.XinGe.logout()



IOS需调用

程序启动时调用，注册推送，无参数
cordova.plugins.XinGe.didFinishLaunchingWithOptions（）



程序进入后台时调用，无参数
cordova.plugins.XinGe.applicationDidEnterBackground（）


程序回到前台时调用
cordova.plugins.XinGe.applicationWillEnterForeground（）
