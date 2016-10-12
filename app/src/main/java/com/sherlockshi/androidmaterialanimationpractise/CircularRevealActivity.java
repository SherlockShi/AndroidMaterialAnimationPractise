package com.sherlockshi.androidmaterialanimationpractise;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;

/**
 * Author: SherlockShi
 * Date:   2016-10-08 14:55
 * Description:
 */

public class CircularRevealActivity extends AppCompatActivity {

    private static final int DELAY = 100;
    private RelativeLayout bgViewGroup;
    private Toolbar mToolbar;
    private Interpolator interpolator;
    private View ivSquareRed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_circular_reveal);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.orange));

        setupLayout();

        setupWindowAnimations();
    }

    private void setupLayout() {
        initToolbar();

        bgViewGroup = (RelativeLayout) findViewById(R.id.reveal_root);

        findViewById(R.id.iv_square_orange).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.iv_square_orange) {
                    revealOrangeAtPoint(event.getRawX(), event.getRawY());
                }
                return false;
            }
        });

        ivSquareRed = findViewById(R.id.iv_square_red);
        ivSquareRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealRedAtCenter();
            }
        });
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        }
        setupEnterAnimations();
        setupExitAnimations();
    }

    private void setupEnterAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
            getWindow().setSharedElementEnterTransition(transition);
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    hideTarget();
                    animateRevealShow(mToolbar);
                    animateButtonsIn();
                }

                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }

    private void setupExitAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade returnTransition = new Fade();
            getWindow().setReturnTransition(returnTransition);
            returnTransition.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
            returnTransition.setStartDelay(getResources().getInteger(android.R.integer.config_mediumAnimTime));
            returnTransition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    transition.removeListener(this);
                    animateButtonsOut();
                    animateRevealHide(bgViewGroup);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                }

                @Override
                public void onTransitionPause(Transition transition) {
                }

                @Override
                public void onTransitionResume(Transition transition) {
                }
            });
        }
    }

    private void animateRevealHide(final View viewRoot) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
            int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
            int initialRadius = viewRoot.getWidth();

            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    viewRoot.setVisibility(View.INVISIBLE);
                }
            });
            anim.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
            anim.start();
        }
    }

    private void hideTarget() {
        findViewById(R.id.shared_target).setVisibility(View.GONE);
    }

    private void animateButtonsIn() {
        for (int i = 0; i < bgViewGroup.getChildCount(); i++) {
            View child = bgViewGroup.getChildAt(i);
            child.animate()
                    .setStartDelay(100 + i*DELAY)
                    .setInterpolator(interpolator)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1);
        }
    }

    private void animateButtonsOut() {
        for (int i = 0; i < bgViewGroup.getChildCount(); i++) {
            View child = bgViewGroup.getChildAt(i);
            child.animate()
                    .setStartDelay(i)
                    .setInterpolator(interpolator)
                    .alpha(0)
                    .scaleX(0f)
                    .scaleY(0f);
        }
    }

    private void animateRevealShow(View viewRoot) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
            int centerY = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
            int endRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

            Animator animator = ViewAnimationUtils.createCircularReveal(viewRoot, centerX, centerY, 0, endRadius);
            viewRoot.setVisibility(View.VISIBLE);
            animator.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
            animator.setInterpolator(new AccelerateInterpolator());
            animator.start();
        }
    }

    public void revealGreenAtMiddle(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = (bgViewGroup.getLeft() + bgViewGroup.getRight()) / 2;
            int centerY = (bgViewGroup.getTop() + bgViewGroup.getBottom()) / 2;
            int endRadius = (int) Math.hypot(bgViewGroup.getWidth(), bgViewGroup.getHeight());

            Animator animator = ViewAnimationUtils.createCircularReveal(bgViewGroup, centerX, centerY, 0, endRadius);
            bgViewGroup.setBackgroundResource(R.color.green);
            animator.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }
    }

    public void revealBlueAtTop(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateButtonsOut();

            int centerX = (bgViewGroup.getLeft() + bgViewGroup.getRight()) / 2;
            int centerY = 0;
            int endRadius = (int) Math.hypot(bgViewGroup.getWidth(), bgViewGroup.getHeight());

            Animator animator = ViewAnimationUtils.createCircularReveal(bgViewGroup, centerX, centerY, 0, endRadius);
            bgViewGroup.setBackgroundResource(R.color.blue);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animateButtonsIn();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            animator.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }
    }


    private void revealOrangeAtPoint(float rawX, float rawY) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = (int) rawX;
            int centerY = (int) rawY;
            int endRadius = (int) Math.hypot(bgViewGroup.getWidth(), bgViewGroup.getHeight());

            Animator animator = ViewAnimationUtils.createCircularReveal(bgViewGroup, centerX, centerY, 0, endRadius);
            bgViewGroup.setBackgroundResource(R.color.orange);
            animator.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }
    }

    private void revealRedAtCenter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            hideAllCircle();
            final ViewGroup.LayoutParams originalParams = ivSquareRed.getLayoutParams();

            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int centerX = (bgViewGroup.getLeft() + bgViewGroup.getRight()) / 2;
                        int centerY = (bgViewGroup.getTop() + bgViewGroup.getBottom()) / 2;
                        int endRadius = (int) Math.hypot(bgViewGroup.getWidth(), bgViewGroup.getHeight());

                        Animator animator = ViewAnimationUtils.createCircularReveal(bgViewGroup, centerX, centerY, 0, endRadius);
                        bgViewGroup.setBackgroundResource(R.color.red);
                        animator.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.start();

                        ivSquareRed.setLayoutParams(originalParams);
                        showAllCircle();
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });

            TransitionManager.beginDelayedTransition(bgViewGroup, transition);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            ivSquareRed.setLayoutParams(params);
        }
    }

    private void hideAllCircle() {
        findViewById(R.id.iv_square_green).setVisibility(View.GONE);
        findViewById(R.id.iv_square_blue).setVisibility(View.GONE);
        findViewById(R.id.iv_square_orange).setVisibility(View.GONE);
    }

    private void showAllCircle() {
        findViewById(R.id.iv_square_green).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_square_blue).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_square_orange).setVisibility(View.VISIBLE);
    }
}
