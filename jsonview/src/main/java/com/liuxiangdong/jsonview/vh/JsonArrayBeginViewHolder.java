package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonArrayBeginViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonArrayBeginViewModel}.
 */
public class JsonArrayBeginViewHolder extends CopyJsonStringViewHolder<JsonArrayBeginViewModel> implements View.OnLongClickListener {
    private final TextView keyTextView;
    private final View collapseView;

    public JsonArrayBeginViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        keyTextView = elementProvider.createKeyView(linearLayout);
        linearLayout.addView(keyTextView);
        collapseView = elementProvider.createCollapseView(linearLayout);
        if (collapseView != null) {
            collapseView.setOnClickListener(this);
            collapseView.setOnLongClickListener(this);
            linearLayout.addView(collapseView);
        }
        if (copyView != null) {
            linearLayout.addView(copyView);
        }
        TextView leftBracket = elementProvider.createOpeningBracketView(linearLayout);
        if (leftBracket != null) {
            leftBracket.setText(R.string.json_view_opening_bracket);
            linearLayout.addView(leftBracket);
        }
    }

    @Override
    public void onBind(JsonArrayBeginViewModel jsonArrayBeginViewModel) {
        super.onBind(jsonArrayBeginViewModel);
        CharSequence key = jsonArrayBeginViewModel.getKey(keyTextView.getContext());
        if (TextUtils.isEmpty(key)) {
            keyTextView.setVisibility(View.GONE);
        } else {
            keyTextView.setText(key);
            keyTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == collapseView) {
            getViewModel().collapse();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == collapseView) {
            getViewModel().collapseAll();
            return true;
        }
        return false;
    }
}
