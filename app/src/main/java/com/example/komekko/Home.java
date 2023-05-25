package com.example.komekko;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.komekko.model.Lesson;
import com.example.komekko.model.Practice;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity {

    public static int CategoryClicked = -10;
    ImageSlider imageSlider;
    ExpandableHeightGridView mainGrid;
    //    RelativeLayout cseDept;
    Intent targetActivity;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Bottom Nav Menu Start
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                return true;
            } else if (item.getItemId() == R.id.dictionary) {
                startActivity(new Intent(getApplicationContext(), Dictionary.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
        //End Bottom Nav Menu


        imageSlider = findViewById(R.id.image_slider);
        mainGrid = findViewById(R.id.mainGrid);
//        cseDept = findViewById(R.id.cseDept);

        createSlider();
        MakeVideoList.createMyAlbums();
//        rateUs();

        MyAdapter adapter = new MyAdapter();
        mainGrid.setExpanded(true);
        mainGrid.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    } //------------------------------onCreate (bundle) ENDS here


    // ================================================================

    private void createSlider() {

        ArrayList<SlideModel> imageList = new ArrayList<>();
        //imageList.add(new SlideModel(R.drawable.slide_1, null));
        imageList.add(new SlideModel(R.drawable.learn_english1, "Opportunity", null));
        imageList.add(new SlideModel(R.drawable.learn_english2, "Learning", null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

    }
    // ================================================================


    private class MyAdapter extends BaseAdapter {
        private final LayoutInflater inflater;

        public MyAdapter() {
            this.inflater = (LayoutInflater) Home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return MakeVideoList.catArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);

            ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
            LinearLayout layItem = convertView.findViewById(R.id.layItem);

            HashMap<String, String> mHashMap = MakeVideoList.catArrayList.get(position);
            String catName = mHashMap.get("category_name");
            String img = mHashMap.get("img");
            String url = mHashMap.get("url");
            String pdfAssetName = mHashMap.get("pdfAssetName");


            if (tvTitle != null) tvTitle.setText(catName);
            if (imgIcon != null && img != null) {
                int drawable = Integer.parseInt(img);
                imgIcon.setImageResource(drawable);
            }

            Animation animation = AnimationUtils.loadAnimation(Home.this, R.anim.anim_grid);
            animation.setStartOffset(position * 300L);
            convertView.startAnimation(animation);


            if (layItem != null) {
                layItem.setOnClickListener(v -> {

                    // Check the item is a website link
                    if (url != null && url.length() > 5) {
                        WebBrowser.WEBSITE_LINK = url;
                        WebBrowser.WEBSITE_TITLE = catName;
                        //startActivity(new Intent(Home.this, WebBrowser.class));
                        targetActivity = new Intent(Home.this, WebBrowser.class);
                        startActivity(targetActivity);
                    }

                    // Check the item is a PDF FILE OR NOT
                    else if (pdfAssetName != null && pdfAssetName.length() > 3) {
                        MyPDFViewer.PDF_ASSET_NAME = pdfAssetName;
                        MyPDFViewer.PDF_TITLE = catName;
                        //startActivity(new Intent(Home.this, MyPDFViewer.class));
                        targetActivity = new Intent(Home.this, MyPDFViewer.class);
                        startActivity(targetActivity);
                    }else if (catName.equalsIgnoreCase("Vocabulary")){
                        Practice practice = new Practice(1,"Vocabulary","Vocabulary");
                        targetActivity = new Intent(Home.this, VocabularyActivity.class);
                        targetActivity.putExtra("practice",practice);
                        startActivity(targetActivity);
                    }
                    // None of the above is true. So the category is video collection type
                    else {
                        CategoryClicked = position;
                        MainActivity.arrayList = MakeVideoList.rootArrayList.get(position);
                        targetActivity = new Intent(Home.this, MainActivity.class);
                        startActivity(targetActivity);
                    }

                });
            }

            return convertView;
        }
    }



    ///====================================================
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired
    private long mBackPressed;

    // When user click backpress button this method is called
    @Override
    public void onBackPressed() {
        // When user press back button

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {

            Toast.makeText(getBaseContext(), "Press again to exit",
                    Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();


    } // end of onBackpressed method

    //#############################################################################################


}