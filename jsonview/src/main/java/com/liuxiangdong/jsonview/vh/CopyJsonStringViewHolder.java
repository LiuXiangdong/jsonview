package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.view.View;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonCompoundViewModel;

/**
 * A {@link android.support.v7.widget.RecyclerView.ViewHolder} that provides the function
 * of copying the Json value of a {@link com.liuxiangdong.jsonview.entry.JsonCompoundEntry}.
 * @param <T>
 */
public class CopyJsonStringViewHolder<T extends JsonCompoundViewModel<?>> extends ValidViewHolder<T> implements View.OnClickListener {
    final View copyView;

    CopyJsonStringViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        copyView = elementProvider.createCopyView(linearLayout);
        if (copyView != null) {
            copyView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == copyView) {
            getViewModel().copyJsonString();
        }
    }
}
