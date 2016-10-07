package com.sherlockshi.androidmaterialanimationpractise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.sherlockshi.androidmaterialanimationpractise.base.BaseSampleActivity;

/**
 * Author: SherlockShi
 * Date:   2016-10-06 10:37
 * Description:
 */

public class ViewAnimations1Activity extends BaseSampleActivity {

    private LinearLayout llytRoot;
    private ImageView ivSquareGreen;

    private boolean isSizeChanged = false;
    private int savedWidth;

    private boolean isPositionChanged = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_animation1);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.green));

        getToolbar().setBackgroundColor(getResources().getColor(R.color.green));
        getToolbar().setTitle(R.string.activity_name_view_animations);
    }

    @Override
    protected void initView() {
        llytRoot = (LinearLayout) findViewById(R.id.llyt_root);
        ivSquareGreen = (ImageView) findViewById(R.id.iv_square_green);

        findViewById(R.id.btn_change_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSize();
            }
        });

        findViewById(R.id.btn_change_position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePosition();
            }
        });
    }

    private void changeSize() {
        TransitionManager.beginDelayedTransition(llytRoot);

        ViewGroup.LayoutParams params = ivSquareGreen.getLayoutParams();

        if (isSizeChanged) {
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 100;
        }

        isSizeChanged = !isSizeChanged;
        ivSquareGreen.setLayoutParams(params);
    }

    private void changePosition() {
        TransitionManager.beginDelayedTransition(llytRoot);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivSquareGreen.getLayoutParams();

        if (isPositionChanged) {
            params.gravity = Gravity.CENTER_HORIZONTAL;
        } else {
            params.gravity = Gravity.LEFT;
        }

        isPositionChanged = !isPositionChanged;
        ivSquareGreen.setLayoutParams(params);
    }

    public void jumpToViewAnimations2Activity(View view) {
        startActivity(new Intent(ViewAnimations1Activity.this, ViewAnimations2Activity.class));
    }
}
