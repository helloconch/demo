package com.android.proxy;


/**
 * 真正起诉
 */

public class Civilian implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("起诉");
    }

    @Override
    public void burden() {
        System.out.println("举证");
    }

    @Override
    public void defend() {
        System.out.println("辩护");
    }

    @Override
    public void finish() {
        System.out.println("胜诉");
    }
}
