package com.example.studyapplication.others;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zhouxin on 2022/10/1
 *
 * @author zhouxin.3012143@bytedance.com
 */
 class AcessCheck {
    private void printStuff(String src, ArrayList<String> list) {
        Log.e("zhouxin-src", src );
        for(int i=0 ;i<list.size();i++){
            Log.e("zhouxin-list", list.get(i) );

        }
    }
}
