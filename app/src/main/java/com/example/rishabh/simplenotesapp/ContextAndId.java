package com.example.rishabh.simplenotesapp;

import android.content.Context;

/**
 * Created by Rishabh on 1/26/17.
 */
public class ContextAndId {
    Context mContext;
    int id;

    public ContextAndId(Context mContext, int id) {
        this.mContext = mContext;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
