package com.allegorit.e_sportcolombia.Lib;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.R;
import yalantis.com.sidemenu.animation.FlipAnimation;
import yalantis.com.sidemenu.interfaces.Resourceble;

public class ViewAnimator<T extends Resourceble> {
    private Activity actionBarActivity;
    private ViewAnimatorListener animatorListener;
    private DrawerLayout drawerLayout;
    private List<T> list;
    private ArrayList<View> viewList = new ArrayList<>();

    public interface ViewAnimatorListener {
        void addViewToContainer(View view);

        void disableHomeButton();

        void enableHomeButton();

        void onSwitch(Resourceble resourceble,int i);
    }

    public ViewAnimator(Activity activity, List<T> items, DrawerLayout drawerLayout, ViewAnimatorListener animatorListener) {
        this.actionBarActivity = activity;
        this.list = items;
        this.drawerLayout = drawerLayout;
        this.animatorListener = animatorListener;
    }

    public void showMenuContent() {
        setViewsClickable(false);
        this.viewList.clear();
        double size = (double) this.list.size();
        for (int i = 0; ((double) i) < size; i++) {
            View viewMenu = this.actionBarActivity.getLayoutInflater().inflate(R.layout.menu_list_item, null);

            final int finalI = i;
            viewMenu.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    int[] location = new int[]{0, 0};
                    v.getLocationOnScreen(location);
                    ViewAnimator.this.switchItem(ViewAnimator.this.list.get(finalI), location[1] + (v.getHeight() / 2));
                }
            });
            ((ImageView) viewMenu.findViewById(R.id.menu_item_image)).setImageResource(this.list.get(i).getImageRes());
            viewMenu.setVisibility(View.GONE);
            viewMenu.setEnabled(false);
            this.viewList.add(viewMenu);
            this.animatorListener.addViewToContainer(viewMenu);
            final double position = (double) i;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (position < ((double) ViewAnimator.this.viewList.size())) {
                        ViewAnimator.this.animateView((int) position);
                    }
                    if (position == ((double) (ViewAnimator.this.viewList.size() - 1))) {
                        ViewAnimator.this.setViewsClickable(true);
                    }
                }
            }, (long) (525.0d * (position / size)));
        }
    }

    private void hideMenuContent() {
        setViewsClickable(false);
        double size = (double) this.list.size();
        for (int i = this.list.size(); i >= 0; i--) {
            final double position = (double) i;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (position < ((double) ViewAnimator.this.viewList.size())) {
                        ViewAnimator.this.animateHideView((int) position);
                    }
                }
            }, (long) (525.0d * (position / size)));
        }
    }

    private void setViewsClickable(boolean clickable) {
        this.animatorListener.disableHomeButton();
        for (View view : this.viewList) {
            view.setEnabled(clickable);
        }
    }

    private void animateView(int position) {
        final View view = this.viewList.get(position);
        view.setVisibility(View.VISIBLE);
        FlipAnimation rotation = new FlipAnimation(90.0f, 0.0f, 0.0f, ((float) view.getHeight()) / 2.0f);
        rotation.setDuration(175);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(rotation);
    }

    private void animateHideView(final int position) {
        final View view = this.viewList.get(position);
        FlipAnimation rotation = new FlipAnimation(0.0f, 90.0f, 0.0f, ((float) view.getHeight()) / 2.0f);
        rotation.setDuration(175);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setVisibility(View.INVISIBLE);
                if (position == ViewAnimator.this.viewList.size() - 1) {
                    ViewAnimator.this.animatorListener.enableHomeButton();
                    ViewAnimator.this.drawerLayout.closeDrawers();
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(rotation);
    }

    private void switchItem(Resourceble slideMenuItem, int topPosition) {
        this.animatorListener.onSwitch(slideMenuItem,topPosition);
        hideMenuContent();
    }


}