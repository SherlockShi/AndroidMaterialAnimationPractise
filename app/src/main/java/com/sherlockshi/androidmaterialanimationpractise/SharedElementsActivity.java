package com.sherlockshi.androidmaterialanimationpractise;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.sherlockshi.androidmaterialanimationpractise.base.BaseSampleActivity;

/**
 * Author: SherlockShi
 * Date:   2016-10-04 22:35
 * Description:
 */

public class SharedElementsActivity extends BaseSampleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shared_elements);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.blue));

        getToolbar().setBackgroundColor(getResources().getColor(R.color.blue));

        getToolbar().setTitle(R.string.activity_name_shared_elements);
    }
}
