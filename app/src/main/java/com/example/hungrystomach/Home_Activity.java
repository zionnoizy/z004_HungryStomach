package com.example.hungrystomach;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.hungrystomach.Adapter.FoodAdapter;
import com.example.hungrystomach.Adapter.TabPagerAdapter;
import com.example.hungrystomach.Model.Food;
import com.google.android.gms.actions.ItemListIntents;
import com.google.android.gms.common.internal.Constants;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Home_Activity extends AppCompatActivity {
    public static final String Database_Path = "All_Image_Uploads_Database";

    FirebaseAuth m_auth;
    DatabaseReference rootRef;

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> mUploads;
    ImageView mImageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        configureTabLayout();

        //////////////////////////////////////////////////////////////////////////////////
        mUploads = new ArrayList<>();

        //set recycler adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);

        //rootRef  = FirebaseDatabase.getInstance().getReference("image");
        mImageIv = findViewById(R.id.thumbnail);

        loadLogoImgData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.it_upload:
                Toast.makeText(this,"Redirected to Upload Photos Section", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home_Activity.this, Upload_Activity.class);
                startActivity(i);
                return true;
            case R.id.it_shoppingcart:
                Toast.makeText(this,"Shopping Cart Coming Soon..", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_chat:
                Toast.makeText(this,"Shopping Cart Coming Soon..", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_settings:
                Toast.makeText(this,"Redirected to Setting Section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Activity.this, Setting_Activity.class);
                startActivity(intent);
                return true;
            case R.id.it_logout:
                userLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void userLogout(){
        m_auth.signOut();
    }


    private void configureTabLayout(){
        TabLayout tab_lyout = (TabLayout) findViewById(R.id.tab_layout);

        tab_lyout.addTab(tab_lyout.newTab().setText("All Food"));
        tab_lyout.addTab(tab_lyout.newTab().setText("American()"));
        tab_lyout.addTab(tab_lyout.newTab().setText("Chinese()"));
//        tab_lyout.addTab(tab_lyout.newTab().setText("Indian"));
//        tab_lyout.addTab(tab_lyout.newTab().setText("Japanese"));
//        tab_lyout.addTab(tab_lyout.newTab().setText("Korean"));
//        tab_lyout.addTab(tab_lyout.newTab().setText("Mediterranean"));
//        tab_lyout.addTab(tab_lyout.newTab().setText("Dessert"));
//        tab_lyout.addTab(tab_lyout.newTab().setText("Drink"));

        final ViewPager view_pager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tab_lyout.getTabCount());
        view_pager.setAdapter(adapter);
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_lyout));

        tab_lyout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                view_pager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){
                //
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){
                //
            }

        });
    }
    //////////////////////////////////////////////////////////////////////////////////

    /*
    private void showRecyclerViewGrid(){
        rv.setLayoutManager(new GridLayoutManager(this,2));
        FoodAdapter fa = new FoodAdapter(list, m_context);
        fa.setListFood(list);
        rv.setAdapter(fa);
    }
    */


    private void startRecyclerView() {
        foodAdapter = new FoodAdapter(mUploads,this);
        recyclerView.setAdapter(foodAdapter);
    }


    private void loadLogoImgData(){
        //mUploads.clear();

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                //Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) { //or messageSnapshot
                    Food upload = postSnapshot.getValue(Food.class);
                    //String name = upload.get_imgurl();
                    //String des = upload.get_description();
                    //String price = upload .get_price();
                    //String url = upload.get_imgurl();
                    //Food fire = new Food(name,des,price,url);

                    mUploads.add(upload);
//                    Log.e("Get Url", );
//                    Log.e("Get Des", upload.get_description());
                }
                startRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Home_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + foodAdapter.getItemId(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}