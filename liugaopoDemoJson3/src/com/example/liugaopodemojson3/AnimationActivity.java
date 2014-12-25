package com.example.liugaopodemojson3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class AnimationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView img = (ImageView) findViewById(R.id.animation_img);

		Animation anim  = new ScaleAnimation(0, 1, 0, 1);
		anim.setDuration(3*1000);
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
				Intent inte = new Intent(AnimationActivity.this, JsonActivity.class);
				startActivity(inte);
				finish();
			}
		});
		img.startAnimation(anim);
	}

}
