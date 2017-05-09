package com.example.hjsuc.rxsample.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hjsuc.rxsample.R;
import com.example.hjsuc.rxsample.data.model.NaverSearchItem;
import com.example.hjsuc.rxsample.data.model.naver.SearchBlogItem;
import com.example.hjsuc.rxsample.data.model.naver.SearchKinItem;
import com.example.hjsuc.rxsample.data.model.naver.SearchNewsItem;
import com.example.hjsuc.rxsample.data.model.naver.SearchTitleItem;
import com.example.hjsuc.rxsample.util.TextUtil;

import java.util.ArrayList;

/**
 * Created by hjsuc on 2017-05-09.
 */

public class NaverSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_EMPITY     = 0;
    public static final int TYPE_TITLE      = 1;
    public static final int TYPE_BLOG       = 2;
    public static final int TYPE_NEWS       = 3;
    public static final int TYPE_KIN        = 4;

    private ArrayList<NaverSearchItem> mData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_BLOG:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_naver_blog, parent, false);
                return new SearchBlogHolder(view);

            case TYPE_NEWS:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_naver_news, parent, false);
                return new SearchNewsHolder(view);

            case TYPE_KIN:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_naver_kin, parent, false);
                return new SearchKinHolder(view);

            case TYPE_TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_naver_title, parent, false);
                return new SearchTitleHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NaverSearchItem data = mData.get(position);
        if (holder instanceof SearchBlogHolder) {
            SearchBlogHolder searchBlogHolder = (SearchBlogHolder) holder;
            SearchBlogItem item = (SearchBlogItem) data;
            TextUtil.setHtmlTag(searchBlogHolder.title, item.getTitle());
            TextUtil.setHtmlTag(searchBlogHolder.desc, item.getDescription());
            searchBlogHolder.name.setText(item.getBloggername());

        } else if (holder instanceof SearchNewsHolder) {
            SearchNewsHolder searchNewsHolder = (SearchNewsHolder) holder;
            SearchNewsItem item = (SearchNewsItem) data;
            TextUtil.setHtmlTag(searchNewsHolder.title, item.getTitle());
            TextUtil.setHtmlTag(searchNewsHolder.desc, item.getDescription());

        } else if (holder instanceof SearchKinHolder) {
            SearchKinHolder searchKinHolder = (SearchKinHolder) holder;
            SearchKinItem item = (SearchKinItem) data;
            TextUtil.setHtmlTag(searchKinHolder.title, item.getTitle());
            TextUtil.setHtmlTag(searchKinHolder.desc, item.getDescription());

        } else if (holder instanceof SearchTitleHolder) {
            SearchTitleHolder searchBlogHolder = (SearchTitleHolder) holder;
            SearchTitleItem blog = (SearchTitleItem) data;
            searchBlogHolder.title.setText(blog.getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        NaverSearchItem model = mData.get(position);
        if (model instanceof SearchBlogItem) {
            return TYPE_BLOG;
        } else if (model instanceof SearchNewsItem) {
            return TYPE_NEWS;
        } else if (model instanceof SearchKinItem) {
            return TYPE_KIN;
        } else if (model instanceof SearchTitleItem) {
            return TYPE_TITLE;
        } else {
            return TYPE_EMPITY;
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }
    }

    public void setData(ArrayList<NaverSearchItem> datas) {
        mData = datas;
        notifyDataSetChanged();
    }

    class SearchBlogHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView name;


        public SearchBlogHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    class SearchNewsHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView name;


        public SearchNewsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    class SearchKinHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView name;


        public SearchKinHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    class SearchTitleHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public SearchTitleHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
