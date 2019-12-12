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
package com.liuxiangdong.jsonview.renderer;

/**
 * A helper class that generates the view type.
 */
final class ItemViewTypeProvider {
    //starting from 1, assumes that 0 is an invalid view type
    private static int sItemViewType = 1;

    static final int INVALID_VIEW_TYPE = 0;

    static int nextItemViewType() {
        return sItemViewType++;
    }
}