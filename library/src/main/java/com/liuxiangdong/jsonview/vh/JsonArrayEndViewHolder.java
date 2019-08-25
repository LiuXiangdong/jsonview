package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonArrayEndViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonArrayEndViewModel}.
 */
public class JsonArrayEndViewHolder extends ValidViewHolder<JsonArrayEndViewModel> {
    public JsonArrayEndViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        TextView rightBracket = elementProvider.createClosingBracketView(linearLayout);
        if (rightBracket != null) {
            rightBracket.setText(R.string.json_view_closing_bracket);
            linearLayout.addView(rightBracket);
        }
    }
}
