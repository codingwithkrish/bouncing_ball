package com.example.bouncingball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import static android.service.controls.ControlsProviderService.TAG;

public class MainActivity extends AppCompatActivity {
    Button bounceball;
    ImageView ballimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bounceball = findViewById(R.id.bounceBallButton);
        ballimage = findViewById(R.id.bounceBallImage);

        bounceball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ballimage.clearAnimation();
                TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,getDisplayHeight()/2);
                translateAnimation.setStartOffset(500);
                translateAnimation.setDuration(5000);
                translateAnimation.setInterpolator(new BounceInterpolator());
                translateAnimation.setFillAfter(true);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i(TAG, "Starting button dropdown animation");

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.i(TAG,
                                "Ending button dropdown animation. Clearing animation and setting layout");
                        ballimage.clearAnimation();
                        final int left = ballimage.getLeft();
                        final int top = ballimage.getTop();
                        final int right = ballimage.getRight();
                        final int bottom = ballimage.getBottom();
                        ballimage.layout(left, top, right, bottom);

                    }
                });
                ballimage.startAnimation(translateAnimation);




            }

        });

    }

    private int getDisplayHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }
}