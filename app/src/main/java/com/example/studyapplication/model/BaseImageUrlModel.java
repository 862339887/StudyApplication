package com.example.studyapplication.model;

import android.text.TextUtils;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


/**
 * description :
 * author      : liang.tong
 * create on   : 2019-08-27
 */

public class BaseImageUrlModel {

    private List<String> mUrls = new ArrayList<>();

    public BaseImageUrlModel(List<String> urls) {
        if (urls == null || urls.isEmpty()) {
            return;
        }

        for (String url : urls) {
            if (!TextUtils.isEmpty(url)) {
                mUrls.add(url);
            }
        }
    }

    public List<String> getUrls() {
        return mUrls;
    }

    public void setUrls(List<String> urls) {
        mUrls = urls;
    }

    public boolean isEmpty() {
        return mUrls == null || mUrls.isEmpty();
    }

    @Override
    public String toString() {
        return "BaseImageUrlModel{" +
                "mUrls=" + mUrls +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return true;
    }
}
