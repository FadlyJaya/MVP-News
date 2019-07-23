package com.example.mvpnews;

import com.example.mvpnews.api.ApiClient;
import com.example.mvpnews.api.ApiInterface;
import com.example.mvpnews.models.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements Presenter<MainView> {

    public static final String API_KEY = "ca5364d7cee342f2b0550da995e04fbc";
    MainView mView;

    public void onShowNews(){
        final ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String country = Utils.getCountry();

        apiInterface.getNews(country, API_KEY).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (response.isSuccessful() && response.body().getArticles() != null){
                    mView.onShowNews(response.body().getArticles());
                }else {
                    mView.onShowToast("Data tidak ada");
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAttach(MainView View) {
        mView = View;
    }

    @Override
    public void onDetach() {
        mView = null;
    }
}
