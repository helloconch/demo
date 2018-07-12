package com.conch.appmvvm.presenter;

public interface ILoginPresenter {
    void showData();

    void doLogin(String name, String passwd);

    void setProgressBarVisiblity(int visiblity);

}
