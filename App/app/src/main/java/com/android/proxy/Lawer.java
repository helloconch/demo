package com.android.proxy;

/**
 * 找到律师--静态代理
 */

public class Lawer implements ILawsuit {

    private ILawsuit civilian;

    public Lawer(ILawsuit civilian) {
        this.civilian = civilian;
    }

    @Override
    public void submit() {
        this.civilian.submit();
    }

    @Override
    public void burden() {
        this.civilian.burden();
    }

    @Override
    public void defend() {
        this.civilian.defend();
    }

    @Override
    public void finish() {
        this.civilian.finish();
    }
}
