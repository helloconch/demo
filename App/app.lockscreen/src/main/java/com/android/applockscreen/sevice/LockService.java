package com.android.applockscreen.sevice;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.conch.appbase.utils.IUserModuleService;
import com.conch.appbase.utils.RouteUtils;

@Route(path = RouteUtils.LOCK_SERVICE)
public class LockService implements IUserModuleService {
    @Override
    public String getUserAddress(String userId) {
        //return UserService.getUserAddress(userId);
        return "AAA";
    }

    @Override
    public void init(Context context) {

    }
}
