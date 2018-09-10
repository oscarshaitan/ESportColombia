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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allegorit.e_sportcolombia.Lib.ViewAnimator;
import com.allegorit.e_sportcolombia.fragment.ContentFragment;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class Staff extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    TextView name;

    private RecyclerView myRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

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

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view_staff);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapterStaff(getDataSet(),this);
        myRecycler.setAdapter(mAdapter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private ArrayList<StaffObj>getDataSet(){
        ArrayList<StaffObj> arrayList = new ArrayList<>();
        StaffObj alejandroG = new StaffObj(R.drawable.lol35,R.drawable.pubg35,R.drawable.gow35,R.drawable.mario35,
                R.drawable.alejandro_g,"Alejandro","Garcia","26","2017","Tier 1");
        StaffObj mauricioM = new StaffObj(R.drawable.lol35,R.drawable.pubg35,R.drawable.ow35,R.drawable.null35,
                R.drawable.mauricio_m,"Mauricio","Montoya","35","2018","Tier 2");
        StaffObj danielaG = new StaffObj(R.drawable.lol35,R.drawable.pubg35,R.drawable.ow35,R.drawable.fn35,
                R.drawable.daniela_g,"Daniela","Giraldo","21","2017","Tier 1");
        StaffObj juanO = new StaffObj(R.drawable.dota35,R.drawable.pubg35,R.drawable.wow35,R.drawable.lol35,
                R.drawable.sebastian_t,"Juan","Ospina","21","2016","Tier 1");
        StaffObj zoro = new StaffObj(R.drawable.dota35,R.drawable.pubg35,R.drawable.wow35,R.drawable.lol35,
                R.drawable.zoro,"Diego","Chicango aka Zoro","30","2016","Tier 4");
        StaffObj pingu = new StaffObj(R.drawable.ow35,R.drawable.pubg35,R.drawable.dbd352,R.drawable.lol35,
                R.drawable.pingu,"Felipe","Giraldo aka Pingu","25","2016","Tier 3");
        StaffObj jaxmin = new StaffObj(R.drawable.lol35,R.drawable.fn35,R.drawable.null35,R.drawable.null35,
                R.drawable.jazmin_p,"Jazmin","Marquez","20","2018","Tier 1");

        arrayList.add(zoro);
        arrayList.add(pingu);
        arrayList.add(alejandroG);
        arrayList.add(mauricioM);
        arrayList.add(danielaG);
        arrayList.add(juanO);
        arrayList.add(jaxmin);

        return arrayList;
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
