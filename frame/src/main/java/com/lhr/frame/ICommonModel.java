package com.lhr.frame;

public interface ICommonModel<T>  {
    void getData(ICommonPresent3er pPresenter,int whichApi,T... params);

}
