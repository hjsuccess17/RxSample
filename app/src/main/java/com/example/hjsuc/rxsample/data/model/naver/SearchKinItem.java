package com.example.hjsuc.rxsample.data.model.naver;

import com.example.hjsuc.rxsample.data.model.NaverSearchItem;

/**
 * Created by hjsuc on 2017-05-09.
 */

public class SearchKinItem implements NaverSearchItem{

    String total;
    String display;
    String title;
    String link;
    String description;

    public String getTotal() {
        return total;
    }

    public String getDisplay() {
        return display;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
