package com.example.cheng.parabola;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view =findViewById(R.id.view);


        //方法1====start=====
        final Animator animator1=ObjectAnimator.ofFloat(view,"translationX",0.0f,-160.0f);
        animator1.setDuration(2000);
        animator1.setInterpolator(new LinearInterpolator());

        final Animator animator2=ObjectAnimator.ofFloat(view,"translationY",0.0f,-100.0f);
        animator2.setDuration(2000);
        animator2.setInterpolator(new DecelerateInterpolator());


        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                final Animator animator3=ObjectAnimator.ofFloat(view,"translationX",-160.0f,-320.0f);
                animator3.setDuration(2000);
                animator3.setInterpolator(new LinearInterpolator());

                Animator animator4=ObjectAnimator.ofFloat(view,"translationY",-100.0f,0.0f);
                animator4.setDuration(2000);
                animator4.setInterpolator(new AccelerateInterpolator());

                animator3.start();
                animator4.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        Button button=(Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                animator1.start();
                animator2.start();

            }
        });

        //方法1====end=====


        //方法2====start=====

        final Animator animator11=ObjectAnimator.ofFloat(view,"translationX",0.0f,-320.0f);
        animator11.setDuration(4000);
        animator11.setInterpolator(new LinearInterpolator());

        final ObjectAnimator animator22=ObjectAnimator.ofFloat(view,"translationY",0.0f,0.0f);
        animator22.setDuration(4000);
        animator22.setInterpolator(new LinearInterpolator());

        final float timeTotal=animator22.getDuration();
        float maxHeight=-200.0f;
        //vt=v0+at;   //vt=0 最高点
        //s=v0*t+0.5*a*t*t ; //s=maxHeight

        float t=timeTotal/2;
        final float a=-2*maxHeight/t/t;
        final float v0=-a*t;
        //计算加速度 在最高点。
        animator22.setEvaluator(new TypeEvaluator<Float>() {
            @Override
            public Float evaluate(float fraction, Float startValue, Float endValue) {

                float timeAlready=timeTotal*fraction;

                float s=(float)(v0*timeAlready+0.5*a*timeAlready*timeAlready);

                System.out.println(s+"====="+timeAlready);

                return s;

            }
        });

        Button button2=(Button)findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                animator11.start();
                animator22.start();
            }
        });

        //方法2====end=====
    }
}
