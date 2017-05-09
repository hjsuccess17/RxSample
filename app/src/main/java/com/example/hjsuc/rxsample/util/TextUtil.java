package com.example.hjsuc.rxsample.util;

import android.os.Build;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by hjsuc on 2017-05-09.
 */

public class TextUtil {

    public static void setHtmlTag(TextView textView, String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(html));
        }
    }

}
