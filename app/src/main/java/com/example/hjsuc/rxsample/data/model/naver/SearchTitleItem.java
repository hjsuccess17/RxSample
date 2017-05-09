package com.example.hjsuc.rxsample.data.model.naver;

import com.example.hjsuc.rxsample.data.model.NaverSearchItem;

/**
 * Created by hjsuc on 2017-05-09.
 */

public class SearchTitleItem implements NaverSearchItem{

    String title;

    public SearchTitleItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
