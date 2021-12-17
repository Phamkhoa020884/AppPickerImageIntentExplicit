package com.example.apppickerimageintentexplicit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] mArrDrawable;
    private ImageView mImgPick, mImgRandom;
    private int mResourceRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        event();

    }

    private void event() {
    }

    private void init() {
        // Ánh xạ
        mImgPick = findViewById(R.id.imgPick);
        mImgRandom = findViewById(R.id.imgRandom);

        // Tạo mảng chứa tên hình
        mArrDrawable = getResources().getStringArray(R.array.ArrayDrawable);

        // Xử lý việc gán hình cho hình random
        mResourceRandom = randomImage(mArrDrawable);
        mImgRandom.setImageResource(mResourceRandom);
    }
    private int randomImage(String[] arrDrawable){
        int index = new Random().nextInt(arrDrawable.length);
        String name = arrDrawable[index];
        int resourceImage = getResources().getIdentifier(name,"drawable",getPackageName());
        return resourceImage;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_refresh:
                mResourceRandom = randomImage(mArrDrawable);
                mImgRandom.setImageResource(mResourceRandom);
                break;
        }
        return true;
    }
}
© 2021 GitHub, Inc.}