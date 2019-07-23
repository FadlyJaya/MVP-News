package com.example.mvpnews;

public interface Presenter<T extends View> {

    void onAttach(T View);

    void onDetach();

}
