package com.lhr.frame;

public interface ICommonView<D> {

    void onSuccess(int whichApi,int loadType,D... pD);
    void onFailed(int whichApi,Throwable pThrowable);
}
