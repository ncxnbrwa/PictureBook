package com.example.picturebook;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ncx on 2021/4/26
 */
public class BaseExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> params = new HashMap<>();

    private static BaseExceptionHandler instance = new BaseExceptionHandler();

    public static BaseExceptionHandler getInstance() {
        return instance;
    }

    public void init(Context mContext) {
        this.mContext = mContext;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            //如果自己没处理就交给系统处理
            mDefaultHandler.uncaughtException(t, e);
        } else {
            //自己处理,可以收集手机信息
            try {
                PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
                params.put("versionCode", String.valueOf(pi.versionCode));
                params.put("versionName", pi.versionName);
            } catch (PackageManager.NameNotFoundException ex) {
                //获取所有系统信息
                Field[] fields = Build.class.getDeclaredFields();
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        params.put(field.getName(), field.get(null).toString());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }

    private void saveInfo2File(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("==").append(entry.getValue()).append("\n");
        }
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        pw.close();

        sb.append(writer.toString());
        //然后IO流操作写入文件
    }

    private boolean handleException(Throwable e) {
        return false;
    }
}
