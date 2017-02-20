package com.example.tusharking.systemfreeze;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.view.ViewHelper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class LoginAnimation {

	private static Context context;
	static int max = 5500;
	static int min = 5400;
	static Integer[] images = { R.id.imageView2, R.id.imageView3,
			R.id.imageView4, R.id.imageView5, R.id.imageView6, R.id.imageView7,
			R.id.imageView8, R.id.imageView9, R.id.imageView10,
			R.id.imageView11, R.id.imageView12, R.id.imageView13 };

	static Integer[] points = { R.drawable.point, R.drawable.point2,
			R.drawable.point3 };
	static Timer myTimer;

	public static void startAnimation(final Context context,
			final Activity activity) {
		LoginAnimation.context = context;

		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// If you want to modify a view in your Activity
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						int index = getRandom(0, images.length - 1);
						ImageView animated = (ImageView) activity
								.findViewById(images[index]);
						LoginAnimation.Animate(getRandom(min, max), animated);
						//
						index = getRandom(0, images.length - 1);
						animated = (ImageView) activity
								.findViewById(images[index]);
						LoginAnimation.Animate(getRandom(min, max), animated);
						//
						index = getRandom(0, images.length - 1);
						animated = (ImageView) activity
								.findViewById(images[index]);
						LoginAnimation.Animate(getRandom(min, max), animated);
						//
						index = getRandom(0, images.length - 1);
						animated = (ImageView) activity
								.findViewById(images[index]);
						LoginAnimation.Animate(getRandom(min, max), animated);
						//
						index = getRandom(0, images.length - 1);
						animated = (ImageView) activity
								.findViewById(images[index]);
						LoginAnimation.Animate(getRandom(min, max), animated);
						//
						index = getRandom(0, images.length - 1);
						animated = (ImageView) activity
								.findViewById(images[index]);
						LoginAnimation.Animate(getRandom(min, max), animated);
					}
				});
			}
		}, 10, 1300); // initial delay 1 second, interval 1 second

	}

	private static int getRandom(int min, int max) {
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	private static void Animate(int duration, final ImageView imageView) {
		animate(imageView).y(getHight())
				.setListener(new AnimatorListener() {

					@Override
					public void onAnimationStart(Animator arg0) {
						imageView.setVisibility(View.VISIBLE);
					}

					@Override
					public void onAnimationRepeat(Animator arg0) {

					}

					@Override
					public void onAnimationEnd(Animator arg0) {
						imageView.setVisibility(View.INVISIBLE);
						ViewHelper.setY(imageView, 0);
					}

					@Override
					public void onAnimationCancel(Animator arg0) {

					}
				}).alphaBy(0.4f).setDuration(duration).start();
	}

	private static int getHight() {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return manager.getDefaultDisplay().getHeight();
	}

}
