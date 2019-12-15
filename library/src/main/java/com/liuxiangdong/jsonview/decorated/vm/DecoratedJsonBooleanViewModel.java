/*
 * Copyright 2019 LiuXiangdong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liuxiangdong.jsonview.decorated.vm;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonBooleanViewModel;

/**
 * A subclass of {@link JsonBooleanViewModel} that displays true or false in a colored
 * manner, so as to stand out the boolean value.
 */
public class DecoratedJsonBooleanViewModel extends JsonBooleanViewModel {
    public DecoratedJsonBooleanViewModel(String key, Boolean value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }

    @Override
    public CharSequence getValueText(Context context) {
        CharSequence v = super.getValueText(context);
        ForegroundColorSpan span;
        if (getValue()) {
            span = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.json_view_true_color));
        } else {
            span = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.json_view_false_color));
        }
        Spannable result = new SpannableString(v);
        result.setSpan(span, 0, v.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return result;
    }
}
