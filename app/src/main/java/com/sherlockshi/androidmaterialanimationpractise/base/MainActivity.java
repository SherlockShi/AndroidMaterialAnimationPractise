package com.sherlockshi.androidmaterialanimationpractise.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sherlockshi.androidmaterialanimationpractise.ContentTransitionActivityA;
import com.sherlockshi.androidmaterialanimationpractise.R;

public class MainActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToContentTransitionActivity(View view) {
        startActivity(new Intent(MainActivity.this, ContentTransitionActivityA.class));
    }
}
