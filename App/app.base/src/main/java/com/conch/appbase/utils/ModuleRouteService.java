package com.conch.appbase.utils;

import com.alibaba.android.arouter.launcher.ARouter;

public class ModuleRouteService {
    public static String getUserAddress(String userId) {

        IUserModuleService userModuleService = ARouter.getInstance().
                navigation(IUserModuleService.class);
        if (userModuleService != null)
            return userModuleService.getUserAddress(userId);

        return "";
    }
}
