package com.sherlockshi.androidmaterialanimationpractise.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.sherlockshi.androidmaterialanimationpractise.CircularRevealActivity;
import com.sherlockshi.androidmaterialanimationpractise.ContentTransitionActivityA;
import com.sherlockshi.androidmaterialanimationpractise.R;
import com.sherlockshi.androidmaterialanimationpractise.SharedElementsActivity;
import com.sherlockshi.androidmaterialanimationpractise.ViewAnimations1Activity;

public class MainActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getToolbar().setTitle(R.string.app_name);
    }

    public void jumpToContentTransitionActivity(View view) {
        startActivity(new Intent(MainActivity.this, ContentTransitionActivityA.class));
    }

    public void jumpToSharedElementsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SharedElementsActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View ivSquareBlue = findViewById(R.id.iv_square_blue);
            String squareBlueTransitionName = getString(R.string.transition_name_square_blue);

            // only one shared elements
//            ActivityOptionsCompat activityOptionsCompat =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(this, ivSquareBlue, squareBlueTransitionName);

            View tvElementTitle = findViewById(R.id.tv_element_title);
            String elementTitleTransitionName = getString(R.string.transition_name_element_title);

            // multiple shared elements
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    Pair.create(ivSquareBlue, squareBlueTransitionName),
                    Pair.create(tvElementTitle, elementTitleTransitionName));

            startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void jumpToViewAnimations1Activity(View view) {
        startActivity(new Intent(MainActivity.this, ViewAnimations1Activity.class));
    }

    public void jumpToCircularRevealActivity(View view) {
        Intent intent = new Intent(MainActivity.this, CircularRevealActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View ivSquareOrange = findViewById(R.id.iv_square_orange);
            String squareBlueTransitionName = getString(R.string.transition_name_circular_reveal);

            // only one shared elements
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this, ivSquareOrange, squareBlueTransitionName);

            startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
