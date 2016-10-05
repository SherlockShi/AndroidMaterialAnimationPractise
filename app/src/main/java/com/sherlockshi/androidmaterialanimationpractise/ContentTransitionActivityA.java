package com.sherlockshi.androidmaterialanimationpractise;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;

import com.sherlockshi.androidmaterialanimationpractise.base.BaseSampleActivity;
import com.sherlockshi.androidmaterialanimationpractise.base.Config;
import com.sherlockshi.androidmaterialanimationpractise.base.Constants;

/**
 * Author: SherlockShi
 * Date:   2016-10-03 23:33
 * Description:
 */

public class ContentTransitionActivityA extends BaseSampleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_trnasition_a);

        mToolbar.setBackgroundColor(getResources().getColor(R.color.blue));

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


                getWindow().setExitTransition(fade);
//                getWindow().setReenterTransition(fade);
            } else if (Config.TRANSITION_TYPE == Constants.TransitionType.SLIDE) {
                // 1. By code
//                Slide slide = new Slide();
//                slide.setDuration(Config.TRANSITION_TIME);
//                slide.setSlideEdge(Gravity.RIGHT);

                // 2. or by xml
                Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.slide);
                slide.setSlideEdge(Gravity.LEFT);

                getWindow().setExitTransition(slide);
//                getWindow().setReenterTransition(slide);
            } else if (Config.TRANSITION_TYPE == Constants.TransitionType.EXPLODE) {
                // 1. By code
//                Explode explode = new Explode();
//                explode.setDuration(Config.TRANSITION_TIME);

                // 2. or by xml
                Explode explode = (Explode) TransitionInflater.from(this).inflateTransition(R.transition.explode);

                getWindow().setExitTransition(explode);
//                getWindow().setReenterTransition(explode);
            }
        }
    }

    public void jumpToContentTransitionBctivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(new Intent(ContentTransitionActivityA.this, ContentTransitionActivityB.class),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(ContentTransitionActivityA.this).toBundle());
        } else {
            startActivity(new Intent(ContentTransitionActivityA.this, ContentTransitionActivityB.class));
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
