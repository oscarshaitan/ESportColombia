package com.allegorit.e_sportcolombia;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.allegorit.e_sportcolombia.Lib.ViewAnimator;
import com.allegorit.e_sportcolombia.fragment.ContentFragment;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private ArrayList<PcObj> pcObjs = new ArrayList<>();

    final Handler handler =new Handler();
    SyncThread syncThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        syncThread = new SyncThread(getApplicationContext());
        mToastRunnable.run();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {

            //TODO
            pcObjs.clear();
            pcObjs.addAll(syncThread.getStatus());
            refreshPc();

            handler.postDelayed(this, 180000);

        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void refreshPc(){
        System.out.println(pcObjs.toString());
        int[]pcs={R.id.pc1,R.id.pc2,R.id.pc3,R.id.pc4,R.id.pc5,R.id.pc6,R.id.pc7,R.id.pc8,R.id.pc9,R.id.pc10,
                R.id.pc11,R.id.pc12,R.id.pc13,R.id.pc14,R.id.pc15,R.id.pc16,R.id.pc17,R.id.pc18,R.id.pc19,
                R.id.pc20,R.id.pc21,R.id.pc22,R.id.pc23,R.id.pc24,R.id.pc25,R.id.pc26,R.id.pc27,R.id.pc28,
                R.id.pc29,R.id.pc30,R.id.pc31,R.id.pc32,R.id.pc33,R.id.pc34,R.id.pc35,R.id.pc36,R.id.pc37,
                R.id.pc38, R.id.pc39,R.id.pc40,R.id.pc41,R.id.pc42,R.id.pc43,R.id.pc44,R.id.pc45,R.id.pc46,
                R.id.pc47,R.id.pc48, R.id.pc49,R.id.pc50};
        int i = 0;

        if(pcObjs.isEmpty()){

            Toast.makeText(getApplicationContext(),"SERVER NOT RESPOND NEXT TRY IN 5 SEC...",Toast.LENGTH_SHORT).show();

        }
        else {
            //vertical left 3
            View VL3 = findViewById(R.id.vertical_left_3);
            for(i=0; i<=3;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)VL3.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) VL3.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) VL3.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

            //vertical left 2
            View VL2 = findViewById(R.id.vertical_left_2);
            for(i=4; i<=5;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)VL2.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) VL2.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) VL2.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

            //vertical left 1
            View VL1 = findViewById(R.id.vertical_left_1);
            for(i=6; i<=10;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)VL1.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }
                else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) VL1.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) VL1.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

            //diagonal left
            View DL = findViewById(R.id.diagonal_left);
            for(i=11; i<=14;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)DL.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }
                else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) DL.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) DL.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

            //vertical center left
            View VCL = findViewById(R.id.vertical_central_left);
            for(i=15; i<=19;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)VCL.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }
                else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) VCL.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) VCL.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

            //pentagons

            View PTG = findViewById(R.id.pentagon1);
            for(i=20; i<=39;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)PTG.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }
                else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) PTG.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) PTG.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }

            }

            //vertical right

            View VR = findViewById(R.id.vertical_right);
            for(i=40; i<=44;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)VR.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }
                else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) VR.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) VR.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

            //diagonal right

            View DR = findViewById(R.id.diagonal_right);
            for(i=45; i<=49;i++){
                if(pcObjs.get(i).getPing().equals("0")){
                    ((ImageView)DR.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc));
                }
                else if(pcObjs.get(i).getPing().equals("1")){
                    ((ImageView) DR.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_off));
                }
                else if(pcObjs.get(i).getPing().equals("2")){
                    ((ImageView) DR.findViewById(pcs[i])).setImageDrawable(getResources().getDrawable(R.drawable.pc_supp));
                }
            }

        }

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

    private void createMenuList() {
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.HOME, R.drawable.home_pink_50);
        list.add(menuItem2);
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.MSN, R.drawable.fb_msn_pink_50);
        list.add(menuItem0);
        SlideMenuItem menuItema = new SlideMenuItem(ContentFragment.FACEBOOK, R.drawable.fb_pink_50);
        list.add(menuItema);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.MAPS, R.drawable.maps_pink_50);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.STAFF, R.drawable.staff_pink_50);
        list.add(menuItem4);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.INFO, R.drawable.info_pink_50);
        list.add(menuItem6);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.LOGOUT, R.drawable.logout_pink_50);
        list.add(menuItem5);
    }

    @Override
    public void onSwitch(Resourceble slideMenuItem, int position) {
        Handler handler = new Handler();
        Intent intent = null;

        if (slideMenuItem.getName().equals(ContentFragment.CLOSE)) {
        } else if (slideMenuItem.getName().equals(ContentFragment.STAFF)) {
            intent = new Intent(getApplication(), Staff.class);
        }else if (slideMenuItem.getName().equals(ContentFragment.MAPS)) {
            intent = new Intent(getApplication(), MapsActivity.class);
        }
        else if (slideMenuItem.getName().equals(ContentFragment.HOME)) {
            intent = new Intent(getApplication(), MainActivity.class);
        }
        else if (slideMenuItem.getName().equals(ContentFragment.INFO)) {
            intent = new Intent(getApplication(), Info.class);
        }
        else if (slideMenuItem.getName().equals(ContentFragment.LOGOUT)) {
            LoginManager.getInstance().logOut();
            intent = new Intent(getApplication(), Login.class);
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





