package com.jdamiancabello.base;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void onSucces();
    void onGenericError(String errorMessage);
}
