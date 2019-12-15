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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.webkit.URLUtil;

import com.liuxiangdong.jsonview.vm.JsonStringViewModel;

/**
 * A subclass of {@link JsonStringViewModel} that convert a valid url to a clickable
 * {@link URLSpan}.
 */
public class DecoratedJsonStringViewModel extends JsonStringViewModel {
    public DecoratedJsonStringViewModel(String key, String value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }

    @Override
    public CharSequence getValueText(Context context) {
        if (URLUtil.isValidUrl(getValue())) {
            Spannable builder = new SpannableStringBuilder('"' + getValue() + '"');
            builder.setSpan(new URLSpan(getValue()), 1, builder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return builder;
        }
        return super.getValueText(context);
    }
}
