package com.ac57.framework.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Untils {
    public static ArrayList getBankArr(List<Object> list) {
        ArrayList lists=new ArrayList();
        ArrayList<String> items1=new ArrayList<>();
        ArrayList<String> items2=new ArrayList<>();
        for(Object obj:list){
            Map<String,Object> map= (Map<String, Object>) obj;
            items1.add(map.get("id")+"");
            items2.add(map.get("bank_name")+"");
        }
        lists.add(items1); lists.add(items2);
        return lists;
    }

    public static ArrayList getJobArr(List<Object> list) {
        ArrayList lists=new ArrayList();
        ArrayList<String> items1=new ArrayList<>();
        ArrayList<String> items2=new ArrayList<>();
        for(Object obj:list){
            Map<String,Object> map= (Map<String, Object>) obj;
            items1.add(map.get("id")+"");
            items2.add(map.get("profession_name")+"");
        }
        lists.add(items1); lists.add(items2);
        return lists;
    }
//    public static List<ShareDialog.ShareItem> getShareItemList(){
//        List<ShareDialog.ShareItem> list=new ArrayList<>();
//        list.add(new ShareItem("微信", R.mipmap.wechat, SHARE_MEDIA.WEIXIN));
//        list.add(new ShareItem("朋友圈", R.mipmap.wechat_friends, SHARE_MEDIA.WEIXIN_CIRCLE));
//        list.add(new ShareItem("QQ", R.mipmap.share_qq, SHARE_MEDIA.QQ));
//        list.add(new ShareItem("QQ空间", R.mipmap.qqzone, SHARE_MEDIA.QZONE));
//        list.add(new ShareItem("复制链接", R.mipmap.copy,null));
//        list.add(new ShareItem("浏览器", R.mipmap.qqbrowser,null));
//        return list;
//    }
//
//    public static void toWingInfo(Context context, String content_type, String art_id, String art_type){
//        if(content_type.equals("101")||content_type.equals("102")){
//            Intent in=new Intent(context,InteractItemInfoActivity.class);
//            in.putExtra("art_type", art_type);
//            in.putExtra("art_id",art_id);
//            in.putExtra("content_type", content_type);
//            in.putExtra("type",1);
//            context.startActivity(in);
//
//        }else if(content_type.equals("103")){
//            Intent in=new Intent(context, OtherInfomationItemActivity.class);
//            in.putExtra("content_type",content_type);
//            in.putExtra("art_id",art_id);
//            in.putExtra("art_type",art_type);
//            context.startActivity(in);
//        }else if(content_type.equals("104")){
//            //打开浏览器
//        }
//    }
    public  static String getTimes(String tm){
        long now_time=new Date().getTime();
        long dtime= Long.parseLong(tm)*1000;
       long delay=(now_time-dtime)/1000;
        if(delay>0&&delay<=60){
            return delay+"秒前";
        }else if(delay<=0){
            return "刚刚";
        }else if(delay>60&&delay<3600){
            return (delay/60)+"分钟前";
        }else {
            String st = TimeUntil.timeStampT(dtime);
            return st.substring(st.indexOf("-")+1);
        }
    }


    public  static String getTimeStr(String tm){
        long now_time=new Date().getTime();
        long dtime= Long.parseLong(tm)*1000;
        long delay=(now_time-dtime)/1000;
        if(delay>0&&delay<=60){
            return delay+"秒前";
        }else if(delay<0){
            return "刚刚";
        }else if(delay>60&&delay<=180){
            return (delay/60)+"刚刚";
        }else if(delay>180&&delay<=3600){
            return (delay/60)+"分钟前";
        }else if(delay>3600&&delay<=3600*12){
            return  (delay/3600)+"小时前";
        }else {
            String st = TimeUntil.timeStampT(dtime);
            return st.substring(st.indexOf("-")+1);
        }
    }
    public  static String getMonth(String month){
       
       if(month.equals("1")||month.equals("01")){
           return "JAN";
       }else if(month.equals("2")||month.equals("02")){
           return "FEB";
       }else if(month.equals("3")||month.equals("03")){
            return "MAR";
        }else if(month.equals("4")||month.equals("04")){
            return "APR";
        }else if(month.equals("5")||month.equals("05")){
            return "MAY";
        }else if(month.equals("6")||month.equals("06")){
            return "JUN";
        }else if(month.equals("7")||month.equals("07")){
            return "JUL";
        }else if(month.equals("8")||month.equals("08")){
            return "AUG";
        }else if(month.equals("9")||month.equals("09")){
            return "SEP";
        }else if(month.equals("10")){
            return "OCT";
        }else if(month.equals("11")){
            return "NOV";
        }else if(month.equals("12")){
            return "DEC";
        }
        return "";
    }
    
    
}
