package com.example.apppickerimageintentexplicit;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
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
        mImgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("arr_drawable", mArrDrawable);
                someActivityResultLauncher.launch(intent);
            }
        });
    }

    private ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        int resourceData = data.getIntExtra("resourceData", -1);
                        mImgPick.setImageResource(resourceData);

                        if (resourceData == mResourceRandom){
                            Toast.makeText(MainActivity.this, "Bạn chọn chính xác", Toast.LENGTH_SHORT).show();
                            mResourceRandom = randomImage(mArrDrawable);
                            mImgRandom.setImageResource(mResourceRandom);
                            mImgPick.setImageResource(R.drawable.nophoto);
                        }else{
                            Toast.makeText(MainActivity.this, "Bạn chọn chưa chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Bạn chưa chọn hình", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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

    private int randomImage(String[] arrDrawable) {
        int index = new Random().nextInt(arrDrawable.length);
        String name = arrDrawable[index];
        int resourceImage = getResources().getIdentifier(name, "drawable", getPackageName());
        return resourceImage;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_refresh:
                mResourceRandom = randomImage(mArrDrawable);
                mImgRandom.setImageResource(mResourceRandom);
                break;
        }
        return true;
    }
}
© 2021 GitHub, Inc.