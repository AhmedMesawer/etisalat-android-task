package com.etisalat.sampletask.bases;

public interface BasePresenterListener {
    void showProgress();
    void hideProgress();
    void showErrorMessage(String message);
}
