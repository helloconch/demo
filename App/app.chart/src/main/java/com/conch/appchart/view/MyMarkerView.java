package com.conch.appchart.view;

import android.content.Context;
import android.widget.TextView;

import com.conch.appchart.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

public class MyMarkerView extends MarkerView {
    private DecimalFormat format = new DecimalFormat("##0");

    private TextView content;

    public MyMarkerView(Context context) {
        super(context, R.layout.layout_markerview);
        content = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        content.setText(format.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }
}
