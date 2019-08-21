package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonObjectEndViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonObjectEndViewModel}.
 */
public class JsonObjectEndViewHolder extends ValidViewHolder<JsonObjectEndViewModel> {
    public JsonObjectEndViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        TextView rightBrace = elementProvider.createClosingBraceView(linearLayout);
        if (rightBrace != null) {
            rightBrace.setText(R.string.json_view_closing_brace);
            linearLayout.addView(rightBrace);
        }
    }
}
