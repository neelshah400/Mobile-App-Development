package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
            
    static TextView textMoney, textIncome, textTractor, textTractorBenefit, textTractorQuantity;
    ImageView imageCorn, imageTractor;
    static Button buttonTractor;

    TextView textPlus;

    static AtomicInteger money = new AtomicInteger();
    static AtomicInteger income = new AtomicInteger();
    static AtomicInteger benefit = new AtomicInteger();
    static AtomicInteger quantity = new AtomicInteger();
    static AtomicInteger cost = new AtomicInteger();

    final static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.id_layout);
        
        textMoney = findViewById(R.id.id_textMoney);
        textIncome = findViewById(R.id.id_textIncome);
        textTractor = findViewById(R.id.id_textTractor);
        textTractorBenefit = findViewById(R.id.id_textTractorBenefit);
        textTractorQuantity = findViewById(R.id.id_textTractorQuantity);

        imageCorn = findViewById(R.id.id_imageCorn);
        imageTractor = findViewById(R.id.id_imageTractor);

        buttonTractor = findViewById(R.id.id_buttonTractor);

        money.set(0);
        income.set(0);
        benefit.set(1);
        quantity.set(0);
        cost.set(50);

        buttonTractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money.get() >= cost.get()) {
                    money.getAndAdd(-1 * cost.get());
                    quantity.getAndAdd(1);
                    income.getAndAdd(benefit.get());
//                    benefit.getAndAdd(1);
                    cost.set((int) (cost.get() * 1.15));
                    setFields();
                }
            }
        });

        imageCorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                money.getAndAdd(1);
                setFields();

                ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(400);
                imageCorn.startAnimation(scaleAnimation);

                textPlus = new TextView(MainActivity.this);
                textPlus.setId(View.generateViewId());
                textPlus.setText("\uD83C\uDF3D");
                textPlus.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                textPlus.setLayoutParams(params);

                constraintLayout.addView(textPlus);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(textPlus.getId(), ConstraintSet.TOP, imageCorn.getId(), ConstraintSet.TOP);
                constraintSet.connect(textPlus.getId(), ConstraintSet.BOTTOM, imageCorn.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(textPlus.getId(), ConstraintSet.LEFT, imageCorn.getId(), ConstraintSet.LEFT);
                constraintSet.connect(textPlus.getId(), ConstraintSet.RIGHT, imageCorn.getId(), ConstraintSet.RIGHT);

                constraintSet.setHorizontalBias(textPlus.getId(), (float) Math.random());
                constraintSet.setVerticalBias(textPlus.getId(), (float) Math.random());

                constraintSet.applyTo(constraintLayout);

                AnimationSet animationSet = new AnimationSet(false);

                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -600);
                translateAnimation.setDuration(1000);
                animationSet.addAnimation(translateAnimation);

                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setInterpolator(new AccelerateInterpolator());
                alphaAnimation.setDuration(1000);
                animationSet.addAnimation(alphaAnimation);

                textPlus.startAnimation(animationSet);
                textPlus.setVisibility(View.INVISIBLE);

            }
        });

        handler.postDelayed(new BackgroundThread(), 1000);

    }

    public static void setFields() {

        textMoney.setText(money.get() + " cobs");
        textIncome.setText(income.get() + " cobs/sec");
        textTractorBenefit.setText("+" + benefit.get() + " cobs/sec");
        textTractorQuantity.setText(quantity.get() + " owned");
        buttonTractor.setText(cost.get() + " cobs");

        if (money.get() < cost.get())
            buttonTractor.setEnabled(false);
        else
            buttonTractor.setEnabled(true);

    }

    public static class BackgroundThread extends Thread {

        public void run() {

            try {
                money.getAndAdd(income.get());
                setFields();
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(this, 1000);

        }

    }

}
