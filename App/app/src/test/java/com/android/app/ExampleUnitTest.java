package com.android.app;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        long t = new Date().getTime();
        long t2=System.currentTimeMillis();

        System.out.println(t);
        System.out.println(t2);

        assertEquals(4, 2 + 2);
    }
}