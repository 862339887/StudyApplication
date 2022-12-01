package com.example.studyapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.studyapplication.R;

public class SwitchTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_test);
        int index=0;
        while (index<4){
            switch (index){
                case 0:
                    Log.e("zhouxin--ff","0");
                    index++;
                    break;
                case 1:
                    Log.e("zhouxin--ff","1");
                    index++;
                    break;
                case 2:
                    Log.e("zhouxin--ff","2");
                    index++;
                    break;
                case 3:
                    Log.e("zhouxin--ff","3");
                    index++;
                    break;
            }
        }
    }
}