package com.sherlockshi.androidmaterialanimationpractise;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.sherlockshi.androidmaterialanimationpractise.base.BaseSampleActivity;
import com.sherlockshi.androidmaterialanimationpractise.base.Config;
import com.sherlockshi.androidmaterialanimationpractise.base.Constants;

/**
 * Author: SherlockShi
 * Date:   2016-10-03 23:33
 * Description:
 */

public class ContentTransitionActivityB extends BaseSampleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_trnasition_b);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.red));

        getToolbar().setBackgroundColor(getResources().getColor(R.color.red));
        getToolbar().setTitle(R.string.activity_name_content_transition);

//        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Config.TRANSITION_TYPE == Constants.TransitionType.FADE) {
                // 1. By code
//                Fade fade = new Fade();
//                fade.setDuration(Config.TRANSITION_TIME);

                // 2. or by xml
                Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.fade);


                getWindow().setEnterTransition(fade);
//                getWindow().setReturnTransition(fade);
            } else if (Config.TRANSITION_TYPE == Constants.TransitionType.SLIDE) {
                // 1. By code
//                Slide slide = new Slide();
//                slide.setDuration(Config.TRANSITION_TIME);
//                slide.setSlideEdge(Gravity.RIGHT);

                // 2. or by xml
                Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.slide);
                slide.setSlideEdge(Gravity.RIGHT);

                getWindow().setEnterTransition(slide);
//                getWindow().setReturnTransition(slide);
            } else if (Config.TRANSITION_TYPE == Constants.TransitionType.EXPLODE) {
                // 1. By code
//                Explode explode = new Explode();
//                explode.setDuration(Config.TRANSITION_TIME);

                // 2. or by xml
                Explode explode = (Explode) TransitionInflater.from(this).inflateTransition(R.transition.explode);

                getWindow().setEnterTransition(explode);
//                getWindow().setReturnTransition(explode);
            }
        }
    }

    public void returnToContentTransitionActivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
