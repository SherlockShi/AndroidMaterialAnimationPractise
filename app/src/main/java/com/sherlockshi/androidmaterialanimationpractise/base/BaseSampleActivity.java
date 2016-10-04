package com.sherlockshi.androidmaterialanimationpractise.base;

/**
 * Author: SherlockShi
 * Date:   2016-09-17 21:48
 * Description:
 */
public abstract class BaseSampleActivity extends BaseActivity {

    @Override
    protected void customSetContentView() {
        initView();

        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

}
