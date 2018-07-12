package com.conch.appmvvm.presenter;

import com.conch.appmvvm.model.IUser;
import com.conch.appmvvm.model.UserModel;
import com.conch.appmvvm.view.ILoginView;

public class LoginPresenterImpl implements ILoginPresenter {

    ILoginView loginView;
    IUser user;

    public LoginPresenterImpl(ILoginView loginView) {
        this.loginView = loginView;
        user = new UserModel();
    }

    @Override
    public void showData() {
        this.loginView.show();
    }

    @Override
    public void doLogin(String name, String passwd) {
        boolean result = true;
        int code = 200;
        this.loginView.onLoginResult(result, code);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        this.loginView.onSetProgressBarVisibility(visiblity);
    }
}
