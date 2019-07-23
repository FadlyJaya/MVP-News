package com.example.mvpnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.example.mvpnews.models.Article;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, ItemListener{

    MainPresenter presenter;
    private Adapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        onAttachView();

        recyclerView = findViewById(R.id.rv_category);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        presenter.onShowNews();
        adapter.setItemListener(this);
    }

    @Override
    public void onShowNews(List<Article> articles) {
        adapter.setArticles(articles);

    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    public void initPresenter(){
        presenter = new MainPresenter();
    }

    @Override
    public void onItemClickListener(Article article) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("url", article.getUrl());
        intent.putExtra("title", article.getTitle());
        intent.putExtra("img", article.getUrlToImage());
        intent.putExtra("date", article.getPublishedAt());
        intent.putExtra("source", article.getSource().getName());
        intent.putExtra("author", article.getAuthor());

        startActivity(intent);
    }
}
