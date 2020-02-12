package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
            
    TextView textMoney, textIncome;
    ImageView imageCorn;
    
    TextView textPlus;

    int money = 0;
    int income = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.id_constraintLayout);
        
        textMoney = findViewById(R.id.id_textMoney);
        textIncome = findViewById(R.id.id_textIncome);
        imageCorn = findViewById(R.id.id_imageCorn);
        
        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(400);

        imageCorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                v.startAnimation(scaleAnimation);
                
                money++;
                textMoney.setText(money + " cobs");

                textPlus = new TextView(MainActivity.this);
                textPlus.setId(View.generateViewId());
                textPlus.setText("+1");
                textPlus.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                textPlus.setLayoutParams(params);

                constraintLayout.addView(textPlus);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(textPlus.getId(), ConstraintSet.TOP, imageCorn.getId(), ConstraintSet.TOP);
                constraintSet.connect(textPlus.getId(), ConstraintSet.BOTTOM, imageCorn.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(textPlus.getId(), ConstraintSet.LEFT, imageCorn.getId(), ConstraintSet.LEFT);
                constraintSet.connect(textPlus.getId(), ConstraintSet.RIGHT, imageCorn.getId(), ConstraintSet.RIGHT);

                constraintSet.setHorizontalBias(textPlus.getId(), (float) Math.random());
                constraintSet.setVerticalBias(textPlus.getId(), (float) Math.random());

                constraintSet.applyTo(constraintLayout);

                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -600);
                translateAnimation.setDuration(1000);
                textPlus.startAnimation(translateAnimation);
                textPlus.setVisibility(View.INVISIBLE);
                
            }
        });

    }

}
