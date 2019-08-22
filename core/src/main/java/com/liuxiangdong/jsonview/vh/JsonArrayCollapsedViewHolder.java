package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonArrayCollapsedViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonArrayCollapsedViewModel}.
 */
public class JsonArrayCollapsedViewHolder extends CopyJsonStringViewHolder<JsonArrayCollapsedViewModel> implements View.OnLongClickListener {
    private final TextView keyTextView;
    private final View expandView;
    private final TextView collapsedInfo;

    public JsonArrayCollapsedViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        keyTextView = elementProvider.createKeyView(linearLayout);
        linearLayout.addView(keyTextView);
        expandView = elementProvider.createExpandView(linearLayout);
        if (expandView != null) {
            linearLayout.addView(expandView);
            expandView.setOnClickListener(this);
            expandView.setOnLongClickListener(this);
        }
        collapsedInfo = elementProvider.createCollapsedArrayInfoView(linearLayout);
        if (copyView != null) {
            linearLayout.addView(copyView);
        }
        linearLayout.addView(collapsedInfo);
    }

    @Override
    public void onBind(JsonArrayCollapsedViewModel jsonArrayCollapsedViewModel) {
        super.onBind(jsonArrayCollapsedViewModel);
        CharSequence key = jsonArrayCollapsedViewModel.getKey(keyTextView.getContext());
        if (TextUtils.isEmpty(key)) {
            keyTextView.setVisibility(View.GONE);
        } else {
            keyTextView.setText(key);
            keyTextView.setVisibility(View.VISIBLE);
        }
        collapsedInfo.setText(jsonArrayCollapsedViewModel.getCollapsedInfo(collapsedInfo.getContext()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == expandView) {
            getViewModel().expand();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == expandView) {
            getViewModel().expandAll();
            return true;
        }
        return false;
    }
}
