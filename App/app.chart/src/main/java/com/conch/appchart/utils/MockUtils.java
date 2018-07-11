package com.conch.appchart.utils;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {

    public static List<MockData> obtainData() {
        List<MockData> datas = initData();

        List<MockData> result = new ArrayList<>();

        for (MockData item : datas) {
            List<MockData> innerDatas = item.getDatas();
            if (innerDatas.size() > 0) {
                for (MockData innerItem : innerDatas) {
                    item.setxAxis(result.size() + 1);
                    result.add(innerItem);
                }

            } else {
                item.setxAxis(result.size() + 1);
                result.add(item);
            }
        }

        return result;
    }

    private static List<MockData> initData() {
        List<MockData> datas = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            MockData mockData = new MockData();
            mockData.setyAxis((float) Math.random() * 70);
            mockData.setName(i + "号");
            datas.add(mockData);
        }

        //向第一个日期插入0-4点  12-16点
        MockData mockData = datas.get(0);
        MockData t = new MockData();
        t.setName("0-4点");
        t.setyAxis((float) Math.random() * 70);
        mockData.getDatas().add(t);
        t = new MockData();
        t.setName("12-16点");
        t.setyAxis((float) Math.random() * 70);
        mockData.getDatas().add(t);

        return datas;
    }


    public static class MockData {
        private String name;
        private float xAxis;
        private float yAxis;
        private List<MockData> datas = new ArrayList<>();

        public List<MockData> getDatas() {
            return datas;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getxAxis() {
            return xAxis;
        }

        public void setxAxis(float xAxis) {
            this.xAxis = xAxis;
        }

        public float getyAxis() {
            return yAxis;
        }

        public void setyAxis(float yAxis) {
            this.yAxis = yAxis;
        }
    }
}
