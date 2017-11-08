package com.dyhdyh.helper.wechat.control;

import android.content.Context;

import com.dyhdyh.helper.wechat.client.GreenDaoClient;
import com.dyhdyh.helper.wechat.database.WeChatMessageEntity;
import com.dyhdyh.helper.wechat.database.WeChatMessageEntityDao;
import com.dyhdyh.helper.wechat.util.Constants;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author dengyuhan
 * @created 2017/11/8 09:47
 */
public class WeChatMessageDatabaseController {

    public static Observable<Long> insertMessageAsync(Context context, WeChatMessageEntity insertEntity) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    long id = insertMessage(context, insertEntity);
                    subscriber.onNext(id);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static long insertMessage(Context context, WeChatMessageEntity insertEntity) throws Exception {
        try {
            WeChatMessageEntityDao dao = GreenDaoClient.get(context).getSession().getWeChatMessageEntityDao();
            long minRetentionTime = System.currentTimeMillis() - Constants.MESSAGE_RETENTION_TIME;
            List<WeChatMessageEntity> entities = dao.queryBuilder().where(WeChatMessageEntityDao.Properties.Timestamp.lt(minRetentionTime)).list();
            dao.deleteInTx(entities);
            //先清空n过期的消息再插入新消息
            return dao.insert(insertEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
