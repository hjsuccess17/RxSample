package com.example.hjsuc.rxsample.data.model.naver;

import com.example.hjsuc.rxsample.data.model.NaverSearchItem;

/**
 * Created by hjsuc on 2017-05-09.
 */

public class SearchBlogItem implements NaverSearchItem {

    String title;
    String link;
    String description;
    String bloggername;
    String bloggerlink;
    String postdate;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getBloggername() {
        return bloggername;
    }

    public String getBloggerlink() {
        return bloggerlink;
    }

    public String getPostdate() {
        return postdate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", bloggername='" + bloggername + '\'' +
                ", bloggerlink='" + bloggerlink + '\'' +
                ", postdate='" + postdate + '\'' +
                '}';
    }
}
