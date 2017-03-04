/**
 * Copyright (C) 2017 Mikhail Frolov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.truenight.dynamic.adapter.param;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import xyz.truenight.dynamic.AttrUtils;

/**
 * Created by true
 * date: 03/03/2017
 * time: 03:05
 * <p>
 * Copyright © Mikhail Frolov
 */

public class FrameLayoutParamAdapter implements TypedParamAdapter {
    @Override
    public boolean isSuitable(ViewGroup.LayoutParams params) {
        return params instanceof FrameLayout.LayoutParams;
    }

    @SuppressWarnings("WrongConstant")
    @Override
    public boolean apply(Context context, ViewGroup.LayoutParams p, String name, String value) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) p;
        switch (name) {
            case LAYOUT_GRAVITY:
                params.gravity = AttrUtils.getGravity(value);
                return true;
        }

        return false;
    }
}
