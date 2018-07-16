package com.allegorit.e_sportcolombia;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Staff extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    CircleImageView profileImg;
    TextView name;

    private RecyclerView myRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setItemTextAppearance(R.style.list_item_appearance);
        View headerView = navigationView.getHeaderView(0);
        profileImg = (CircleImageView)headerView.findViewById(R.id.imageView);
        name = (TextView)headerView.findViewById(R.id.name);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        graphFBData(accessToken);

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view_staff);
        myRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapterStaff(getDataSet(),this);
        myRecycler.setAdapter(mAdapter);
    }

    private ArrayList<StaffObj>getDataSet(){
        ArrayList<StaffObj> arrayList = new ArrayList<>();
        StaffObj staffObj = new StaffObj(R.drawable.ow35,R.drawable.lol35,R.drawable.csgo35,R.drawable.pubg35,
                R.drawable.zoro2r,"Oscar","Tigreros","28","2013","System Ing.");
        arrayList.add(staffObj);
        arrayList.add(staffObj);
        arrayList.add(staffObj);
        arrayList.add(staffObj);

        return arrayList;
    }

    private void graphFBData(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                getFacebookData(object);
            }
        });
        request.executeAsync();
    }

    private void getFacebookData(JSONObject object){
        if(object!=null){
            try {
                URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=150&heigth=150");
                Picasso.get()
                        .load(String.valueOf(profile_picture))
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(profileImg);
                String Name = object.getString("name");
                name.setText(Name);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout) {

            System.out.println("iniciando el logout");
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
            // Handle the camera action
        } else if (id == R.id.map) {

            System.out.println("iniciando el map");
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        } else if (id == R.id.home) {

            System.out.println("iniciando el home");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        } else if (id == R.id.staff) {

            System.out.println("iniciando el staff");
            Intent intent = new Intent(this,Staff.class);
            startActivity(intent);
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        } else if (id == R.id.messenger) {
            System.out.println("iniciando el messenger");
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/1069712796440708/")));
            }catch (Exception e){
                try{
                    Toast.makeText(getApplicationContext(),"Need Messenger installed to do that!!!!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.orca")));
                }
                catch (Exception e2){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.orca")));
                }
            }
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        } else if (id == R.id.facebook) {
            System.out.println("iniciando el facebook");
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1069712796440708/")));
            }catch (Exception e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/esportscolombiacali/")));
            }
            overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
