package com.ac57.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ac57.R;
import com.ac57.ui.entity.ToastBean;

import java.util.Timer;
import java.util.TimerTask;

public class MyToast implements View.OnClickListener {
    private TextView mytoast_tv;
    ImageView mytoast_img;
    private ImageView mytoast_canel;
    private ProgressBar mytoast_pro;
    private int defalut;
    private Context context;
    private ToastCallBackLister backLister;
    private LayoutInflater inflater;
    private Dialog dialog;
    private ToastBean bean;
    int[] imgs;

    public MyToast(Context context, ToastBean bean, ToastCallBackLister backLister) {
        imgs = new int[]{R.mipmap.ok, R.mipmap.cenel, R.mipmap.noti};
        this.bean = bean;
        if (bean.getType().indexs != 3) {
            this.defalut = imgs[bean.getType().getIndexs()];
        }
        this.context = context;
        this.backLister = backLister;
        inflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        dialog = new Dialog(context, R.style.EvaluateDialogStyle);
        View view = inflater.inflate(R.layout.mytoast, null);
        mytoast_img = (ImageView) view.findViewById(R.id.mytoast_img);
        mytoast_tv = (TextView) view.findViewById(R.id.mytoast_tv);
        mytoast_pro = (ProgressBar) view.findViewById(R.id.mytoast_pro);
        mytoast_canel = (ImageView) view.findViewById(R.id.mytoast_canel);
        mytoast_canel.setOnClickListener(this);

        if (bean.getType().getIndexs() != 3) {
            mytoast_pro.setVisibility(View.GONE);
            mytoast_img.setVisibility(View.VISIBLE);
            mytoast_canel.setVisibility(View.GONE);
            mytoast_img.setImageResource(defalut);
            mytoast_tv.setText(bean.getTitle());
        } else {
            mytoast_tv.setText(bean.getTitle());
            mytoast_canel.setVisibility(View.VISIBLE);
            mytoast_img.setVisibility(View.GONE);
            mytoast_pro.setVisibility(View.VISIBLE);
        }
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
    }

    public interface ToastCallBackLister {
        public void beginClick(ToastBean bean);

        public void forwordclick(ToastBean bean);
    }

    public enum Types {
        OK(1000, 0), ERREY(1500, 1), NOTI(1500, 2), GO(0, 3);

        private Types(int time, int indexs) {
            this.indexs = indexs;
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        private int time;
        private int indexs;

        public int getIndexs() {
            return indexs;
        }

        public void setIndexs(int indexs) {
            this.indexs = indexs;
        }

    }

    public void show() {
        if (backLister != null) {
            backLister.beginClick(bean);
        }
        dialog.show();
        if (bean.getType().indexs != 3) {
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    dissmiss();
                    if (backLister != null) {
                        backLister.forwordclick(bean);
                    }
                }
            }, bean.getType().time);
        }
    }

    public void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (dialog.isShowing() && backLister != null) {
            backLister.forwordclick(bean);
        }
    }

    public boolean isShow() {
        return dialog.isShowing();
    }
}
