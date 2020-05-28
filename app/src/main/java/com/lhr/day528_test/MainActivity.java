 package com.lhr.day528_test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lhr.data.TestInfo;
import com.lhr.frame.ApliConfig;
import com.lhr.frame.CommonPresenter;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonPresent3er;
import com.lhr.frame.ICommonView;
import com.lhr.frame.LoadTypeConfig;
import com.lhr.frame.ParamHashMap;
import com.lhr.frame.TestModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 public class MainActivity extends AppCompatActivity implements ICommonView {

     private RecyclerView recycler;
     private TestAdapter infoAdapter;
     private TestModel testModel;
     private CommonPresenter commonPresenter;
     int pageId = 0;
     private SmartRefreshLayout smart;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main);
         recycler = (RecyclerView) findViewById(R.id.recycler);
         smart = (SmartRefreshLayout) findViewById(R.id.smart);
         recycler.setLayoutManager(new LinearLayoutManager(this));

         TestModel testModel = new TestModel();
         final CommonPresenter commonPresenter = new CommonPresenter(this, testModel);

         infoAdapter = new TestAdapter(this);
         recycler.setAdapter(infoAdapter);


         final Map<String, Object> params = new ParamHashMap().add("c", "api").add("a", "getList");

         recycler.setAdapter(infoAdapter);

         commonPresenter.getData(ApliConfig.TEST_GET, LoadTypeConfig.NORMAL,params,pageId);

         smart.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
             @Override
             public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                 pageId = 1;
                 commonPresenter.getData(ApliConfig.TEST_GET, LoadTypeConfig.MORE,params,pageId);
             }

             @Override
             public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                 pageId++;
                 commonPresenter.getData(ApliConfig.TEST_GET, LoadTypeConfig.MORE,params,pageId);
             }
         });

     }

     @Override
     public void onSuccess(int whichApi, int loadType, Object[] pD) {
         TestInfo infoBean = (TestInfo) pD[0];
         switch (whichApi){
             case ApliConfig.TEST_GET:
                 if(loadType == LoadTypeConfig.MORE){
                     smart.finishLoadMore();
                     infoAdapter.addlist(infoBean.datas);
                 }else if(loadType == LoadTypeConfig.REFRESH){
                     smart.finishRefresh();
                     infoAdapter.finish(infoBean.datas);
                 }
                 infoAdapter.notifyDataSetChanged();
         }

     }

     @Override
     public void onFailed(int whichApi, Throwable throwable) {
         Toast.makeText(MainActivity.this, throwable.getMessage()!=null ? throwable.getMessage():"网络请求发生错误", Toast.LENGTH_SHORT).show();
     }
}
