package com.example.hatem.tick_toc_app;

import android.widget.TextView;

/**
 * Created by baselabdallah on 12/5/16.
 */

public class ViewHolder {
    TextView text;

    public ViewHolder(TextView text) {
        this.text = text;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }
}
