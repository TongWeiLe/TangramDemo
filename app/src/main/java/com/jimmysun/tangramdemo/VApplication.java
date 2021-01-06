package com.jimmysun.tangramdemo;

import android.app.Application;
import android.content.Context;

import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.ViewManager;

public class VApplication extends Application {


    private VafContext sVafContext;

    private ViewManager sViewManager;
    @Override
    public void onCreate() {
        super.onCreate();

        if (sVafContext == null) {
            sVafContext = new VafContext(this.getApplicationContext());

            sViewManager = sVafContext.getViewManager();
        }
    }

    public VafContext getVafContext() {
        return sVafContext;
    }

    public ViewManager getViewManager() {
        return sViewManager;
    }
}
