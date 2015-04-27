package com.kyle.recycler_anim;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private RecyclerView recycleView;
    private ImageView imageView,imageView2;
    private float mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recycleView = ((RecyclerView) findViewById(R.id.recycleView));
        imageView = ((ImageView) findViewById(R.id.imageView));
        imageView2 = ((ImageView) findViewById(R.id.imageView2));
        ((Button) findViewById(R.id.button)).setOnClickListener(this);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
    }

    @Override
    public void onClick(View v) {
        //3.1以上才可以用
        //ViewPropertyAnimator animate = imageView.animate();
        //全版本都可以用(V4支持包)
        ViewPropertyAnimatorCompat animatel = ViewCompat.animate(imageView);
//        View viewById = findViewById(R.id.layout);
//        ViewPropertyAnimatorCompat animatel = ViewCompat.animate(viewById);
        animatel.rotationXBy(1);
        animatel.rotationYBy(-1);
        animatel.setDuration(11000);
        animatel.setInterpolator(new Interpolator() {

            @Override
            public float getInterpolation(float input) {
                //intpu 的值是从 0~1的线性增长
                return (float) Math.pow(input, 3);
            }
        });
        animatel.setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                Toast.makeText(MainActivity.this,"动画开始",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(View view) {
                Toast.makeText(MainActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(View view) {

            }
        });
        animatel.start();
    }

    public void rotateyAnimRun(final View view)
    {

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }
    public void onclick2(View v){

        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight-imageView.getHeight());
        animator.setTarget(imageView);
        animator.setDuration(1000).start();
    }
}
