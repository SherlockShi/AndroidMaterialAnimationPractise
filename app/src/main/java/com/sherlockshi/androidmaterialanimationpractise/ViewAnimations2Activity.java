package com.sherlockshi.androidmaterialanimationpractise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;
import com.sherlockshi.androidmaterialanimationpractise.base.BaseSampleActivity;

/**
 * Author: SherlockShi
 * Date:   2016-10-07 18:07
 * Description:
 */

public class ViewAnimations2Activity extends BaseSampleActivity {

    private FrameLayout flytRoot;

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_animation2);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.green));

        getToolbar().setBackgroundColor(getResources().getColor(R.color.green));
        getToolbar().setTitle(R.string.activity_name_scenes);
    }

    @Override
    protected void initView() {
        flytRoot = (FrameLayout) findViewById(R.id.flyt_root);

        scene1 = Scene.getSceneForLayout(flytRoot, R.layout.activity_animations_scene1, this);
        scene2 = Scene.getSceneForLayout(flytRoot, R.layout.activity_animations_scene2, this);
        scene3 = Scene.getSceneForLayout(flytRoot, R.layout.activity_animations_scene3, this);
        scene4 = Scene.getSceneForLayout(flytRoot, R.layout.activity_animations_scene4, this);
    }

    public void goSceneOne(View view) {
        TransitionManager.go(scene1, new ChangeBounds());
    }

    public void goSceneTwo(View view) {
        TransitionManager.go(scene2, new ChangeBounds());
    }

    public void goSceneThree(View view) {
        TransitionManager.go(scene3, new ChangeBounds());
    }

    public void goSceneFour(View view) {
        TransitionManager.go(scene4, new ChangeBounds());
    }
}
