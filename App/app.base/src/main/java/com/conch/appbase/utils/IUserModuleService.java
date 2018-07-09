package com.conch.appbase.utils;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IUserModuleService extends IProvider {

    String getUserAddress(String userId);
}
