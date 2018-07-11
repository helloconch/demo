package com.conch.appchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.conch.appbase.utils.RouteUtils;
import com.conch.appchart.model.CompositeIndexBean;
import com.conch.appchart.utils.MockUtils;
import com.conch.appchart.view.MyMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;
import java.util.List;

import static com.conch.appchart.utils.MockUtils.obtainData;

@Route(path = RouteUtils.CHART_DYMIC_MAIN)
public class DymicChartActivity extends AppCompatActivity implements OnChartGestureListener {
    private final String TAG = "CHARTCHAA";
    int start = 0;
    int end = 30;
    LineChart chart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


        chart = findViewById(R.id.chart);
        findViewById(R.id.test)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // change();
                        addData();
                    }
                });

        //显示边界线
        //chart.setDrawBorders(true);
        List<Entry> entries = new ArrayList<>();
        for (int i = start; i < end; i++) {
            float x = i;
            float y = (float) Math.random() * 80;
            entries.add(new Entry(x, y));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        //设置曲线值圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        //设置曲线上字体的大小
        lineDataSet.setValueTextSize(9f);
        //设置曲线为圆滑曲线(默认折线)
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);

        //设置X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //设置x轴坐标之间最小的间距
        xAxis.setGranularity(1f);

        //设置x轴最小值最大值
        //xAxis.setAxisMinimum(0f);
        //xAxis.setAxisMaximum(20f);

        //设置X轴刻度数量
        //xAxis.setLabelCount(12, true);

        //设置在曲线图中显示的最大数量
        chart.setVisibleXRangeMaximum(7);
        //设置X轴为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                float scaleX = chart.getScaleX();
                float scaleY = chart.getScaleY();
                Log.i(TAG, "getFormattedValue>>>" + value);
                Log.i(TAG, "scaleX>>>" + scaleX + "---scaleY>>>" + scaleY);
                return String.valueOf((int) (value)) + "日";
            }
        });

        //设置曲线显示的值为整数

//        lineDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return null;
//            }
//        });


        //操作Y轴
        YAxis leftYAxis = chart.getAxisLeft();
        YAxis rightYAxis = chart.getAxisRight();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(100f);
        rightYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMaximum(100f);

        //设置右侧Y周不显示
        rightYAxis.setEnabled(false);

        //设置左侧Y轴
        //网格线颜色
        //leftYAxis.setGridColor(Color.RED);
        //y轴刻度颜色
        //leftYAxis.setTextColor(Color.BLUE);
        //y轴颜色
        //leftYAxis.setAxisLineColor(Color.RED);

        //添加限制线
        if (false) {
            LimitLine limitLine = new LimitLine(95, "限制线");
            limitLine.setLineWidth(3f);
            limitLine.setTextSize(10f);
            limitLine.setTextColor(Color.RED);
            limitLine.setLineColor(Color.BLUE);
            leftYAxis.addLimitLine(limitLine);
        }
        //图例
        if (false) {
            Legend legend = chart.getLegend();
            legend.setTextColor(Color.GRAY);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            //标签是否换行
            legend.setWordWrapEnabled(true);
            //隐藏Legend
            legend.setEnabled(false);
        }

        if (true) {
            Description description = new Description();
            //显示描述
            description.setEnabled(true);
            description.setText("测温");
            description.setTextColor(Color.RED);
            chart.setDescription(description);
        }

        if (true) {
            MyMarkerView myMarkerView = new MyMarkerView(this);
            myMarkerView.setChartView(chart);
            chart.setMarker(myMarkerView);
        }


    }

    private void addData() {
        if (chart != null) {
            LineData lineData = chart.getLineData();
            float x = lineData.getDataSetCount();
            float y = (float) Math.random() * 80;
            Entry entry = new Entry(x, y);
            lineData.addEntry(entry, 0);
            lineData.notifyDataChanged();
            chart.notifyDataSetChanged();
            //设置在曲线图中显示的最大数量
            chart.setVisibleXRangeMaximum(10);
            //移到某个位置
           // chart.moveViewToX(lineData.getEntryCount() - 2);
        }
    }

    private void change() {
        if (chart != null) {
            LineData lineData = chart.getLineData();

            List<ILineDataSet> list = lineData.getDataSets();
            if (list.size() == 0) {
                return;
            }

            final List<MockUtils.MockData> datas = MockUtils.obtainData();

            List<Entry> entries = new ArrayList<>();
            for (int i = start; i < datas.size(); i++) {
                MockUtils.MockData data = datas.get(i);
                float x = data.getxAxis();
                float y = data.getyAxis();
                Entry entry = new Entry(x, y);
                entries.add(entry);
            }

            LineDataSet lineDataSet = new LineDataSet(entries, "change");
            //设置曲线值圆点是实心还是空心
            lineDataSet.setDrawCircleHole(true);
            //设置曲线上字体的大小
            lineDataSet.setValueTextSize(9f);
            lineDataSet.setColor(Color.RED);
            //设置曲线为圆滑曲线(默认折线)
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineData.getDataSets().set(0, lineDataSet);
            //lineData.getDataSets().add(lineDataSet);
            chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    Log.i(TAG, "getFormattedValue>>>change:" + value);
                    int index = (int) value;
                    if (index < datas.size()) {
                        return datas.get(index).getName();
                    }
                    return index + "号";
                }
            });
            //移到某个位置
            //chart.moveViewToX(lineData.getEntryCount() - 2);
            chart.invalidate();


        }

    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureEnd>>>");
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed>>>");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped>>>");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG, "onChartSingleTapped>>>");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling>>>");
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale>>>");
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate>>>");
    }
}
