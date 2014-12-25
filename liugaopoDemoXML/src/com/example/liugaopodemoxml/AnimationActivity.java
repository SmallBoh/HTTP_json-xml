package com.example.liugaopodemoxml;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class AnimationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView img = (ImageView) findViewById(R.id.anim_img);
		Animation anim = new ScaleAnimation(1, 0, 1, 0);
		anim.setDuration(5*1000);
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(AnimationActivity.this, XMLActivity.class);
				startActivity(inte);
				finish();
			}
		});
		img.startAnimation(anim);
	}


}
