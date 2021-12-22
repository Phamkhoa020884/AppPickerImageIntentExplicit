package com.example.apppickerimageintentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {

    TableLayout mTbLayout;
    int mColumn, mRow, mCount;
    int mWidthScreen, mHeightScreen;
    int mResourceId;
    String[] mArrDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTbLayout = findViewById(R.id.tableLayout);

        initView();
    }

    private void initView() {
        // Get data
        Intent intent = getIntent();
        if (intent != null) {
            mArrDrawable = intent.getStringArrayExtra("arr_drawable");
            Collections.shuffle(Arrays.asList(mArrDrawable));
        }

        // Get dimension screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mHeightScreen = displayMetrics.heightPixels;
        mWidthScreen = displayMetrics.widthPixels;


        mRow = (int) Math.ceil(Double.parseDouble((mArrDrawable.length / 3f) + ""));
        mCount = 0;
        mColumn = 3;
        // 6 dong 3 cot
        for (int i = 0; i < mRow; i++) {
            //Create row
            TableRow tableRow = new TableRow(this);
            for (int y = 0; y < mColumn; y++) {
                if (mCount < mArrDrawable.length) {
                    // Tính vị trí của hình chữ nhật khi biết trục tung và hoành
//                    mIndex =  mColumn * i  + y ;
                    mResourceId = getResources().getIdentifier(mArrDrawable[mCount], "drawable", getPackageName());
                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(mWidthScreen / 3 , mWidthScreen / 3);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(mResourceId);
                    imageView.setTag(mResourceId);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intentData = new Intent();
                            intentData.putExtra("resourceData",(int) imageView.getTag());
                            setResult(Activity.RESULT_OK,intentData);
                            finish();
                        }
                    });
                    tableRow.addView(imageView);
                    mCount++;
                }
            }
            mTbLayout.addView(tableRow);
        }
    }
}