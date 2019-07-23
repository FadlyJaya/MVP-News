package com.example.mvpnews;


import com.example.mvpnews.models.Article;

import java.util.List;

public interface MainView extends View {

    void onShowNews(List<Article> articles);
    void onShowToast(String message);

}
