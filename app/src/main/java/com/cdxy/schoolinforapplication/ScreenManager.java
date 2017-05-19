package com.cdxy.schoolinforapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

/**
 * Created by huihui on 2016/12/25.
 */
//应用程序activity管理类，主要用于activity的管理和应用程序的退出
public class ScreenManager {
    private static Stack<Activity> activityStack;
    private static ScreenManager instance;

    public static ScreenManager getScreenManager() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    //将activity添加到堆栈中
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    //移除栈顶activity
    public  void popActivty(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    //移除堆栈中的所有activity
    public  void popAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();

    }
    //退出程序
    public  void appExit(Context context){
        try{
            popAllActivity();
            ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            //杀死所有后台进程
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        }catch (Exception e){

        }


    }
}
