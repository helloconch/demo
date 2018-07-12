package com.conch.appmvvm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.conch.appbase.utils.RouteUtils;
import com.conch.appmvvm.R;
import com.conch.appmvvm.presenter.ILoginPresenter;
import com.conch.appmvvm.presenter.LoginPresenterImpl;
import com.conch.appmvvm.view.ILoginView;

@Route(path = RouteUtils.MVP_MAIN)
public class MainActivity extends AppCompatActivity implements ILoginView {

    ILoginPresenter loginPresenter;
    TextView movieName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movieName = findViewById(R.id.movieName);
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.showData();
    }

    @Override
    public void show() {
        if (movieName != null) {
            movieName.setText("hello mvp");
        }
    }

    @Override
    public void onLoginResult(Boolean result, int code) {

    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}


