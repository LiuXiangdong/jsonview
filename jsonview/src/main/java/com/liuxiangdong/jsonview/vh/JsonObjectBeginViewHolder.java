package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonObjectBeginViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonObjectBeginViewModel}.
 */
public class JsonObjectBeginViewHolder extends CopyJsonStringViewHolder<JsonObjectBeginViewModel> implements View.OnLongClickListener {
    private final TextView keyTextView;
    private final View collapseView;

    public JsonObjectBeginViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        keyTextView = elementProvider.createKeyView(linearLayout);
        linearLayout.addView(keyTextView);
        collapseView = elementProvider.createCollapseView(linearLayout);
        if (collapseView != null) {
            linearLayout.addView(collapseView);
            collapseView.setOnClickListener(this);
            collapseView.setOnLongClickListener(this);
        }
        if (copyView != null) {
            linearLayout.addView(copyView);
        }
        TextView leftBrace = elementProvider.createOpeningBraceView(linearLayout);
        if (leftBrace != null) {
            leftBrace.setText(R.string.json_view_opening_brace);
            linearLayout.addView(leftBrace);
        }
    }

    @Override
    public void onBind(JsonObjectBeginViewModel jsonObjectBeginViewModel) {
        super.onBind(jsonObjectBeginViewModel);
        CharSequence key = jsonObjectBeginViewModel.getKey(keyTextView.getContext());
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
