package com.example.ahoreader;

import android.content.Context;
import android.widget.TextView;

import io.nlopez.smartadapters.views.BindableRelativeLayout;

/**
 * Created by A455LF-I3 on 12/30/2016.
 */

public class NovelView extends BindableRelativeLayout<Novel> {
    public NovelView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.novel_view;
    }

    TextView tv;

    @Override
    public void bind(Novel novel) {
        tv = (TextView) findViewById(R.id.Text2);
        tv.setText(novel.getJudul());
    }

}
