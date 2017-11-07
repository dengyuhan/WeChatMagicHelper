package com.dyhdyh.helper.wechat.client;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dyhdyh.helper.wechat.database.DaoMaster;
import com.dyhdyh.helper.wechat.database.DaoSession;

/**
 * @author dengyuhan
 * @created 2017/11/6 11:04
 */
public class GreenDaoClient {
    private static GreenDaoClient mInstance;

    private DaoSession mDaoSession;

    private GreenDaoClient() {

    }

    public static GreenDaoClient get(Context context) {
        if (mInstance == null) {
            mInstance = new GreenDaoClient();
            DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(context, "wechat-helper-db", null);
            SQLiteDatabase db = mHelper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            mInstance.mDaoSession = daoMaster.newSession();
        }
        return mInstance;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }
}
