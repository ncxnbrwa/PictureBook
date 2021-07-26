package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AppInfoActivity extends AppCompatActivity {
    private static final String TAG = "AppInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        new getInstalledApps(AppInfoActivity.this).execute();
//        Log.d(TAG, "解密结果:" + deCiphering());
//        Log.d(TAG, "解密结果:" + deCipheringHttps());
//        Log.d(TAG, "DeviceId:" + getDeviceId());
//        StringBuilder sb = new StringBuilder();
//        String userId = UUID.randomUUID().toString();
//        //       String tel = "tel";
//        String tel = "18312064159";
//        //判断是否登录
//        if (true) {
//            try {
//                if (TextUtils.isEmpty(tel) || !isChinaMobilePhoneNum(tel)) {
//                    userId = tel;
//                } else {
//                    userId = String.valueOf(Long.valueOf(tel, 13));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            userId = "tourist_" + m1236a();
//        }
//        sb.append(userId).append(UUID.randomUUID().toString());
//        sb.append("appName");
//        sb.append("bb0f6797d45");
//        sb.append("a1000100");
////        String cToken = m794a(m152a(m869a(sb.toString().getBytes(), m796a("a093d878d07544f527e705d4a036efe7"))));
//        String cToken = m794a(m152a(m869a(sb.toString().getBytes(),"[B@c629899".getBytes())));
//        Log.d(TAG, "cToken:" + cToken);
//        Log.d(TAG, "密钥:" + m796a("a093d878d07544f527e705d4a036efe7"));//[B@c629899
    }

    public static boolean isChinaMobilePhoneNum(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.matches("(^1\\d{10}$)", str);
    }


    private static String f1342d = null;

    public String m1236a() {
        if (f1342d != null) {
            return f1342d;
        }
        f1342d = Settings.System.getString(getContentResolver(), "android_id");
        if (TextUtils.isEmpty(f1342d)) {
            f1342d = "0000000000000000";
        } else {
            for (int i = 0; i < 16 - f1342d.length(); i++) {
                f1342d += "0";
            }
        }
        return f1342d;
    }


    public static String m794a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }


    public static byte[] m796a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (m788a(charArray[i2 + 1]) | (m788a(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte m788a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] m869a(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] m152a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String encrypt(String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec("*&~@!#()^%$+w_x2".getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec("(%*^~!)$#&@a_x+r".getBytes()));
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String deCiphering() {
        try {
            String keySpec = "*&~@!#()^%$+w_x2";  //密钥
            IvParameterSpec ivSpec = new IvParameterSpec("(%*^~!)$#&@a_x+r".getBytes());
            //从服务器返回的加密字符串，需要解密的字符串
            String textDeCipher = "npggbdaVAfSqSBkSYlN4gNyJu4O+NdTTIwjAOboggOy7+Khfr41V+L/sXld8lsOEw8lfh2x11n99\n" +
                    "PVcjIgawdMcROxS8S8d8wVO6CKvZf8eAGHKf+f+84bNK4hQtkXAaf7B4Ieet0Z77i0QGdUmIcFCz\n" +
                    "66Jq5e+78jGkRy7ucVopZZhcRj9az3wO2nNnk1Yn/fpzhZ+4WO+TF8ZDd1nnGlI+iq0txa+avWkv\n" +
                    "JdH4pIt2x3HcDsfcYOhiKlOhOq70O8lSNhJGwFFXdNuO+E8l2rCjlZLkW1M3R4KpIveBWnEsuQew\n" +
                    "fmDwZGb6ng6Yra1eHRz1NRLAef/DEFGNQi3vVueXBoBEvQ3qAmGlv7X/EL4=";
            byte[] b = Base64.decode(textDeCipher, Base64.DEFAULT);       //先用Base64解码
            Key key = new SecretKeySpec(keySpec.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);               //与加密时不同MODE:Cipher.DECRYPT_MODE
            byte[] ret = cipher.doFinal(b);
            return new String(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static byte[] aes(byte[] bArr, int i) {
        try {
            String mPasswordKey = "and0123456789012ww6hrtxnehhn6q50";
            String pwd = mPasswordKey.toLowerCase();
            SecretKeySpec secretKeySpec = new SecretKeySpec(pwd.getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES");
            instance.init(i, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }


    public static String deCipheringHttps() {
        try {
//            String mPasswordKey = "and0123456789012" + getRandomString(16);
            String mPasswordKey = "and0123456789012ww6hrtxnehhn6q50";
            String pwd = mPasswordKey.toLowerCase();
            //从服务器返回的加密字符串，需要解密的字符串
            String textDeCipher = "eyJyaWQiOiI0Y2FhMGVhOWI1MGM0YTkyYmFhYmEyMGU2M2VkMjYwYSIsIlJlczIiOiIxMC43My4yMjkuMjgiLCJib3hudW1iZXIiOiI1OTkwMjgyODAyOCIsImJyYW5kIjoiQW5kcm9pZCIsImNoYW5uZWxJZCI6IjQwMzUwNzQyMjk1IiwiY2xpZW50U2Vzc2lvbklkIjoiNjFmOWY1M2ZjYTQwNGNkZmJhZDQ4NzYwYzU1MmJlYzQiLCJjbGllbnRTb3VyY2UiOiIwIiwiY29ubmVjdGlvblN0YXR1cyI6IjAiLCJkZXZpY2VJZCI6ImZjY2JlMzBkYWIzNmM4NDAiLCJnYW1lSWQiOiIyMDIwMDgyMDAwNSIsImltZWkiOiIwMDAwMDAwMDAwMDAiLCJ2aXBDaGFyZ2VJZCI6IjEwMTMiLCJsb2dpbk1vZGUiOiIxIiwibWFjIjoiNzQ6OGY6MWI6YmU6ODI6MzEiLCJtZW1iZXJHYW1lIjoiMSIsInZlcnNpb24iOiI2LjcuMS4wIiwicGxheU1vZGUiOiIxIiwicGxheU9yaWdpbiI6IuKAnOa4uOaIj+ivpuaDhemhteKAnV8yMDIwMDgyMDAwNV/lr7vmib7om4vom4siLCJyZXNvbHV0aW9uRGV2aWNlIjoiMTI4MHg3MjAiLCJzZXJ2aWNlTmFtZSI6IuWvu+aJvuibi+ibiyIsInNvdXJjZSI6IjEiLCJzeXN0ZW1WZXJzaW9uIjoiMTkiLCJ1aWQiOiJHRElQVFZfNTk5MDI4MjgwMjgiLCJ1c2VySWQiOiJHRElQVFZfNTk5MDI4MjgwMjgiLCJ1c2VyX3R5cGUiOiI0IiwibmV0d29yayI6NCwibGVmdFRpbWUiOjM0ODV9";
            //先用Base64解码,换行符必须替换成空字符串,否则会报错
            byte[] b = Base64.decode(textDeCipher.replace("\r\n", ""), Base64.NO_WRAP);
            Key key = new SecretKeySpec(pwd.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);               //与加密时不同MODE:Cipher.DECRYPT_MODE
            byte[] ret = cipher.doFinal(b);
            return new String(ret, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getRandomString(int i) {
        Random random2 = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random2.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    public String getDeviceId() {
        String deviceId = Settings.System.getString(getContentResolver(), "android_id");
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "0000000000000000";
        } else {
            for (int i = 0; i < 16 - deviceId.length(); i++) {
                deviceId += "0";
            }
        }
        return deviceId;
    }


    class getInstalledApps extends AsyncTask<Void, String, Void> {
        private WeakReference<Context> activityWeakReference;

        public getInstalledApps(Context context) {
            this.activityWeakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            final PackageManager packageManager = getPackageManager();
            List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
            for (PackageInfo packageInfo : packages) {
                if (!(packageManager.getApplicationLabel(packageInfo.applicationInfo).equals("")
                        || packageInfo.packageName.equals(""))) {
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                        try {
                            //非系统APP,sourceDir为APK所在文件夹,dataDir为资源文件夹
                            Log.d(TAG, "non-system AppInfo:" +
                                    packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
                                    + "," + packageInfo.packageName + "," + packageInfo.versionName + "," +
                                    packageInfo.applicationInfo.sourceDir + "," + packageInfo.applicationInfo.dataDir);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            // 系统 Apps
                            Log.d(TAG, "system AppInfo:" +
                                    packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
                                    + "," + packageInfo.packageName + "," + packageInfo.versionName + "," +
                                    packageInfo.applicationInfo.sourceDir + "," + packageInfo.applicationInfo.dataDir);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }
}