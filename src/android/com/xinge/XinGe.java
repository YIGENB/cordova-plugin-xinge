package com.android.xinge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.mogujie.tt.IMTT;
import com.mogujie.tt.ui.activity.LoginJsActivity;

import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JasonYang on 2019/11/27.
 */
public class XinGe extends CordovaPlugin {

    public static final int REQUEST_CODE = 0x0ba7c0de;

    static final int ERR_CODE_PARAMETER = 1;
    static final int ERR_CODE_CONVERSATION = 2;

    static final String ERR_MSG_PARAMETER = "Parameters error";
    static final String ERR_MSG_CONVERSATION = "Can't get the conversation";

    private static final String LOG_TAG = "XinGe";
    private String [] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE };

    private JSONArray requestArgs;
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        this.requestArgs = args;

        //android permission auto add
        if(!hasPermisssion()) {
            requestPermissions(0);
        } else {
            if (action.equalsIgnoreCase("login")) {
                this.Open(args,callbackContext);
                return true;
            }

            if (action.equalsIgnoreCase("logout")) {
                this.Logout();
                return true;
            }
        }

//        if (action.equalsIgnoreCase("login")) {
//            this.Open(args,callbackContext);
//            return true;
//        }
//
//        if (action.equalsIgnoreCase("logout")) {
//            this.Logout();
//            return true;
//        }
//
//        return false;
        return true;
    }

    /**
     * 拉起界面
     * @param args
     */
    public void Open(final JSONArray args,final CallbackContext callback) {

        try {

            JSONObject jsonObject = args.getJSONObject(0);

            //用户名
            String username="";
            if(jsonObject.has("username")) {
                username=jsonObject.getString("username");
            }
            else {
                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("isSuccess", false);
                resultJsonObj.put("msg", "缺少用户名");
                callback.error(resultJsonObj);
                return;
            }

            //token
            String token="";
            if(jsonObject.has("token")) {
                token=jsonObject.getString("token");
            }
            else {
                JSONObject resultJsonObj = new JSONObject();
                resultJsonObj.put("isSuccess", false);
                resultJsonObj.put("msg", "缺少token");
                callback.error(resultJsonObj);
                return;
            }

            final String user_name=username;
            final String to_ken=token;

            final CordovaPlugin that = this;

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {

                    IMTT.startIM(that.cordova.getActivity().getBaseContext(),user_name,to_ken);
//                    Intent intent = new Intent(that.cordova.getActivity().getBaseContext(), LoginJsActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                    // add
//                    intent.putExtra("username",user_name);
//                    intent.putExtra("token",to_ken);
//
//                    that.cordova.startActivityForResult(that, intent, REQUEST_CODE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            handleResult(ERR_CODE_PARAMETER, ERR_MSG_PARAMETER, callback);
            return;
        }
    }


    /**
    * 退出
    */
    public void Logout() {
        IMTT.quitIm();
    }

    static void handleResult(int status, String desc, CallbackContext callback) {
        if (status == 0) {
            callback.success();
        } else {
            try {
                callback.error(getErrorObject(status, desc));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONObject getErrorObject(int code, String description) throws JSONException {
        JSONObject error = new JSONObject();
        error.put("code", code);
        error.put("description", description);
        return error;
    }

    /**
     * check application's permissions
     */
    public boolean hasPermisssion() {
        for(String p : permissions)
        {
            if(!PermissionHelper.hasPermission(this, p))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * We override this so that we can access the permissions variable, which no longer exists in
     * the parent class, since we can't initialize it reliably in the constructor!
     *
     * @param requestCode The code to get request action
     */
    public void requestPermissions(int requestCode)
    {
        PermissionHelper.requestPermissions(this, requestCode, permissions);
    }

    /**
     * processes the result of permission request
     *
     * @param requestCode The code to get request action
     * @param permissions The collection of permissions
     * @param grantResults The result of grant
     */
    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException
    {
        PluginResult result;
        for (int r : grantResults) {
            if (r == PackageManager.PERMISSION_DENIED) {
                Log.d(LOG_TAG, "Permission Denied!");
                result = new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION);
                this.callbackContext.sendPluginResult(result);
                return;
            }
        }

        switch(requestCode)
        {
            case 0:
                this.Open(this.requestArgs,this.callbackContext);
                break;
        }
    }

}
