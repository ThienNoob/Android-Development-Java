package com.example.lab3;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivUitLogo = findViewById(R.id.iv_uit_logo);
        Button btnFadeInXml = findViewById(R.id.btn_fade_in_xml);
        Button btnFadeOutXml = findViewById(R.id.btn_fade_out_xml);
        Button btnBlinkXml = findViewById(R.id.btn_blink_xml);
        Button btnZoomInXml = findViewById(R.id.btn_zoom_in_xml);
        Button btnZoomOutXml = findViewById(R.id.btn_zoom_out_xml);
        Button btnMoveXml = findViewById(R.id.btn_move_xml);
        Button btnSlideUpXml = findViewById(R.id.btn_slide_up_xml);
        Button btnBounceXml = findViewById(R.id.btn_bounce_xml);
        Button btnRotateXml=findViewById(R.id.btn_rotate_xml);
        Button btnCombineXml = findViewById(R.id.btn_combine_xml);
        Button btnFadeInCode = findViewById(R.id.btn_fade_in_code);
        Button btnFadeOutCode = findViewById(R.id.btn_fade_out_code);
        Button btnBlinkCode = findViewById(R.id.btn_blink_code);
        Button btnZoomInCode = findViewById(R.id.btn_zoom_in_code);
        Button btnZoomOutCode = findViewById(R.id.btn_zoom_out_code);
        Button btnRotateCode = findViewById(R.id.btn_rotate_code);
        Button btnMoveCode = findViewById(R.id.btn_move_code);
        Button btnSlideUpCode = findViewById(R.id.btn_slide_up_code);
        Button btnBounceCode = findViewById(R.id.btn_bounce_code);
        Button btnCombineCode = findViewById(R.id.btn_combine_code);
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        handleClickAnimationXml(btnFadeInXml,R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml,R.anim.anim_fade_out);
        handleClickAnimationXml(btnZoomInXml,R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml,R.anim.anim_zoom_out);
        handleClickAnimationXml(btnBlinkXml,R.anim.anim_blink);
        handleClickAnimationXml(btnMoveXml,R.anim.anim_move);
        handleClickAnimationXml(btnCombineXml,R.anim.anim_combine);
        handleClickAnimationXml(btnSlideUpXml,R.anim.anim_slide_up);
        handleClickAnimationXml(btnRotateXml,R.anim.anim_rotate);
        handleClickAnimationXml(btnBounceXml,R.anim.anim_bounce);
        handleClickAnimationCode(btnFadeInCode,fadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode,fadeOutAnimation());
        handleClickAnimationCode(btnZoomInCode,zoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode,zoomOutAnimation());
        handleClickAnimationCode(btnBlinkCode,blinkAnimation());
        handleClickAnimationCode(btnMoveCode,moveAnimation());
        handleClickAnimationCode(btnSlideUpCode,slideUpAnimation());
        handleClickAnimationCode(btnBounceCode,bounceAnimation());
        handleClickAnimationCode(btnCombineCode,combineAnimation());
        handleClickAnimationCode(btnRotateCode, rotateAnimation());
        ivUitLogo.setOnClickListener(v->{
            Intent iNewActivity = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(iNewActivity);
            overridePendingTransition(R.anim.push_left_in,0);
        });
    }

    private void handleClickAnimationXml(Button btn,int animId){
        btn.setOnClickListener(v->{
            final Animation animation=AnimationUtils.loadAnimation(MainActivity.this,animId);
            animation.setAnimationListener(animationListener);
            ivUitLogo.startAnimation(animation);
        });
    }
    private void handleClickAnimationCode(Button btn,final Animation animation){
        btn.setOnClickListener(v->{
            animation.setAnimationListener(animationListener);
            ivUitLogo.startAnimation(animation);
        });
    }
    private Animation fadeInAnimation(){
        Animation animation= new AlphaAnimation(0f,1f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        return animation;
    }
    private Animation fadeOutAnimation(){
        Animation animation =new AlphaAnimation(1f,0f);
        animation.setDuration(1000);
        return animation;
    }
    private Animation zoomInAnimation(){
        Animation animation=new ScaleAnimation(1.0f,3.0f,1.0f,3.0f,
                Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1000);

        return animation;
    }
    private Animation zoomOutAnimation(){
        Animation animation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        return animation;
    }
    private  Animation rotateAnimation(){
        Animation animation=new RotateAnimation(0.0f,360.0f,
                Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        animation.setDuration(600);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(2);
        return animation;
    }
    private Animation blinkAnimation(){
        Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(300);
        animation.setRepeatCount(4);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }
    private Animation moveAnimation(){
        Animation animation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.75f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(800);
        return animation;
    }
    private Animation slideUpAnimation(){
        Animation animation = new ScaleAnimation(1.0f,1.0f,1.0f,0.0f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        return animation;
    }
    private Animation bounceAnimation(){
        Animation animation =new ScaleAnimation(1.0f,1.0f,0.0f,1.0f);
        animation.setDuration(500);
        animation.setInterpolator(new BounceInterpolator());
        return animation;
    }
    private Animation combineAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        Animation animation1=new ScaleAnimation(1.0f,3.0f,1.0f,3.0f,
                Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        animation1.setDuration(4000);
        Animation animation2= new RotateAnimation(0.0f,360.0f,Animation.RELATIVE_TO_PARENT,0.5f,
                Animation.RELATIVE_TO_PARENT,0.5f);
        animation2.setDuration(500);
        animation2.setRepeatCount(2);
        animation2.setRepeatMode(Animation.RESTART);
        animationSet.addAnimation(animation1);
        animationSet.addAnimation(animation2);
        return animationSet;
    }

}