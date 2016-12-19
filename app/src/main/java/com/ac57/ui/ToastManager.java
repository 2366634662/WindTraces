package com.ac57.ui;

import com.ac57.framework.base.BaseActivity;
import com.ac57.framework.tools.AppManager;
import com.ac57.framework.utils.sign.Constance;
import com.ac57.ui.entity.ToastBean;
import com.ac57.ui.view.MyToast;

import java.util.LinkedList;
import java.util.Queue;

public class ToastManager {
    private static ToastManager toastManager;
    private Queue<MyToast> toasts;

    public static ToastManager getToastManager() {
        if (toastManager == null) {
            toastManager = new ToastManager();
        }
        return toastManager;
    }

    private ToastManager() {
        toasts = new LinkedList<MyToast>();
    }

    public void showToast(String title, MyToast.Types type, int flag, Object obj, ToastManagerListener lister) {
        addToast(new ToastBean(title, type, flag, obj), lister);
    }

    public void showOkToast(String title, int flag, Object obj, ToastManagerListener lister) {
        addToast(new ToastBean(title, MyToast.Types.OK, flag, obj), lister);
    }

    public void showErrToast(String title, int flag, Object obj, ToastManagerListener lister) {
        addToast(new ToastBean(title, MyToast.Types.ERREY, flag, obj), lister);
    }

    public void showGOToast(int flag, ToastManagerListener lister) {
        addToast(new ToastBean(null, MyToast.Types.GO, flag, null), lister);
    }

    public void showNotiToast(String title, int flag, Object obj, ToastManagerListener lister) {
        addToast(new ToastBean(title, MyToast.Types.NOTI, flag, obj), lister);
    }

    public void showToast(ToastBean bean, final ToastManagerListener lister) {
        addToast(bean, lister);
    }

    private void addToast(ToastBean bean, final ToastManagerListener lister) {
        BaseActivity activity = AppManager.getInstance().currentActivity();
        MyToast.ToastCallBackLister backLister = new MyToast.ToastCallBackLister() {
            @Override
            public void forwordclick(ToastBean beans) {
                MyToast toast = toasts.poll();
                if (toast != null) {
                    toast.dissmiss();
                    toast = null;
                }
                if (beans.getFlag() != Constance.MYTOAST_ER) {
                    lister.stop(beans);
                }
            }

            @Override
            public void beginClick(ToastBean beans) {
                if (beans.getFlag() != Constance.MYTOAST_ER) {
                    lister.start(beans);
                }
            }
        };
        MyToast myToast = new MyToast(activity, bean, backLister);
        toasts.offer(myToast);
        myToast.show();

    }

    public MyToast getCurrent() {
        return toasts.peek();
    }

    public void finisCurrent() {
        MyToast toast = toasts.poll();
        if (toast != null) {
            toast.dissmiss();
        }
    }

    public interface ToastManagerListener {
        void start(ToastBean bean);

        void stop(ToastBean bean);
    }
}
