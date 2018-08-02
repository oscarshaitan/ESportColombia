package com.allegorit.e_sportcolombia;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.allegorit.e_sportcolombia.Lib.ViewAnimator;
import com.allegorit.e_sportcolombia.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class Main2Activity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, drawerLayout, this);
    }

    private void createMenuList() {
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.HOME, R.drawable.home_pink);
        list.add(menuItem2);
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.MSN, R.drawable.fb_msn_pink);
        list.add(menuItem0);
        SlideMenuItem menuItema = new SlideMenuItem(ContentFragment.FACEBOOK, R.drawable.fb_pink);
        list.add(menuItema);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.MAPS, R.drawable.maps_pink);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.STAFF, R.drawable.staff_black);
        list.add(menuItem4);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.LOGOUT, R.drawable.info_pink);
        list.add(menuItem6);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.LOGOUT, R.drawable.logout_pink);
        list.add(menuItem5);
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSwitch(Resourceble slideMenuItem, int position) {
        Handler handler = new Handler();
        Intent intent = null;

        Toast.makeText(getApplicationContext(), slideMenuItem.getName(), Toast.LENGTH_SHORT).show();

        if (slideMenuItem.getName().equals(ContentFragment.CLOSE)) {
        } else if (slideMenuItem.getName().equals(ContentFragment.STAFF)) {
            intent = new Intent(getApplication(), Staff.class);
        }else if (slideMenuItem.getName().equals(ContentFragment.MAPS)) {
            intent = new Intent(getApplication(), MapsActivity.class);
        }
        else if (slideMenuItem.getName().equals(ContentFragment.HOME)) {
            intent = new Intent(getApplication(), Main2Activity.class);
        }
        else if (slideMenuItem.getName().equals(ContentFragment.FACEBOOK)) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1069712796440708/")));
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }catch (Exception e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/esportscolombiacali/")));
                overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            }
        }
        else if (slideMenuItem.getName().equals(ContentFragment.MSN)) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/1069712796440708/")));
            }catch (Exception e){
                try{
                    Toast.makeText(getApplicationContext(),"Need Messenger installed to do that!!!!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.orca")));
                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                }
                catch (Exception e2){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.orca")));
                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                }
            }
        }

        final Intent finalIntent = intent;
        if (intent != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(finalIntent);
                    overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
                }
            }, 800);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

}





