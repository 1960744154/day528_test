package com.lhr.frame;


import com.lhr.data.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class TestModel implements ICommonModel {


    @Override
    public void getData(final ICommonPresent3er pPresenter, final int whichApi, Object[] params) {
        final int loadType = (int) params[0];
        Map param = (Map) params[1];
        int pageId = (int) params[2];

        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://static.owspace.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Observable listData = build.create(IService.class).getTestData(param, pageId);
        listData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        pPresenter.onSuccess(whichApi,loadType,value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pPresenter.onFailed(whichApi,e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
