package com.conch.appmvvm.view;

public interface ILoginView {
    void show();

    void onLoginResult(Boolean result, int code);

    void onSetProgressBarVisibility(int visibility);
}
