package com.android.proxy;


import java.lang.reflect.Proxy;

public class ProxyTest {
    /**
     * 一个代理可以代理多个类，就像这个律师可以给很多人打官司，只需要在实现一个具体的ILawsuit就行了。
     * 代理会根据传进来的被代理者调用传进来的被代理者的方法。
     */
    public static void staticProxy() {
        ILawsuit civlilian = new Civilian();
        ILawsuit lawyer = new Lawer(civlilian);
        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();
    }

    /**
     * 动态代理是相反的，通过反射动态的生成代理者对象，也就是说在写代码的时候根本不知道要代理谁，
     * 具体代理谁会在执行阶段决定
     */
    public static void dynamicProxy() {


        ILawsuit lawsuit = new Civilian();
        DynamicProxy proxy = new DynamicProxy(lawsuit);

        ClassLoader loader = lawsuit.getClass().getClassLoader();

        //动态创建代理类，需要传入一个类加载器ClassLoader,一个代理实现的接口列表
        //一个InvocationHandler的实现

        ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader,
                new Class[]{ILawsuit.class}, proxy);

        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();


    }
}
