package com.ac57.framework.utils.sign;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;

import com.ac57.ui.AppContext;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class ProjectUntil {

    public static String toMd5String(String str) {
        return stringMD5(str).toUpperCase();

    }

    public static String stringMD5(String pw) {
        try {

            // 拿到�?个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数�?
            byte[] inputByteArray = pw.getBytes();
            // inputByteArray是输入字符串转换得到的字节数�?
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包�?16个元�?
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    public  static  String randString(int length){//8位随机字符串
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String sortString(String... str) {
        Arrays.sort(str);
        StringBuffer bf = new StringBuffer();
        for (String s : str) {
            bf.append(s);
            Log.i("user", s);
        }
        Log.i("user", bf.toString());
        return bf.toString();
    }

    /**
     * SHA1 安全加密算法
     *
     * @return
     * @throws DigestException
     */
    public static String SHA1(String... str) {
        //获取信息摘要 - 参数字典排序后字符串
        String decrypt = sortString(str);
        try {
            //指定sha1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getClientInfo() {
        return "android_" + getSystemVersion() + "_w" + getWidth() + "_h" + getHeight();
    }


    public static String getVersionName()//应用程序版本
    {
        try {
            PackageManager packageManager = AppContext.getAppContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(AppContext.getAppContext().getPackageName(), 0);
            String version = packInfo.versionName;
            return version;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSystemCode() {//android系统版本名:4.3对应数值18
        return Build.VERSION.SDK;
    }

    public static String getSystemVersion() {//android系统版本名:4.3
        return Build.VERSION.RELEASE;
    }

    public static String getProductName() {//手机型号
        return Build.MODEL + "";
    }

    public static int getHeight() {//分辨率高
        WindowManager wm = (WindowManager) AppContext.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        android.view.Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }

    public static int getWidth() {//分辨率宽
        WindowManager wm = (WindowManager) AppContext.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        android.view.Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }

    public static String getUuid(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }
}

