package com.lhr.frame;

public class CommonPresenter implements ICommonPresent3er {

    ICommonView mCommonView;
    ICommonModel mCommonModel;
    public CommonPresenter(ICommonView pCommonView,ICommonModel pCommonModel){
        this.mCommonView = pCommonView;
        this.mCommonModel = pCommonModel;
    }

    @Override
    public void getData(int whichApi, Object... pD) {
        mCommonModel.getData(this,whichApi,pD);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        mCommonView.onSuccess(whichApi,loadType,pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable throwable) {
        mCommonView.onFailed(whichApi,throwable);
    }
}
