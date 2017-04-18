package com.goach.client.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.goach.client.R;
import com.goach.client.adapter.NewsAdapter;
import com.goach.client.api.INewsService;
import com.goach.client.model.NewItem;
import com.goach.client.model.News;
import com.goach.client.util.BaseResp;
import com.goach.client.util.HRetrofitNetHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class NewsActivity extends BaseActivity implements HRetrofitNetHelper.RetrofitCallBack<News>{
    private String mUserId;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<NewItem> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mUserId = getIntent().getStringExtra("intent_user_id");
        mDataList = new ArrayList<>();
        mRecyclerView = (RecyclerView)findViewById(R.id.id_news_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(NewsActivity.this);
        mRecyclerView.setLayoutManager(manager);
        mNewsAdapter = new NewsAdapter(NewsActivity.this,mDataList);
        mRecyclerView.setAdapter(mNewsAdapter);
        loadData();
    }
    private void loadData(){
        mDialog.setMessage("正在加载中，请稍后...");
        mDialog.show();
        INewsService newService = retrofitNetHelper.getAPIService(INewsService.class);
        Log.d("zgx","mUserId====="+mUserId);
            final Call<BaseResp<News>> repos = newService.userNews(mUserId);
            retrofitNetHelper.enqueueCall(repos,this);
    }

    @Override
    public void onSuccess(BaseResp<News> baseResp) {
        mDialog.dismiss();
        mDataList.clear();
        mDataList.addAll(baseResp.getData().getNewsItem());
        mNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String error) {
        mDialog.dismiss();
        Toast.makeText(NewsActivity.this,"请求出现异常"+error,Toast.LENGTH_SHORT).show();
    }
}
