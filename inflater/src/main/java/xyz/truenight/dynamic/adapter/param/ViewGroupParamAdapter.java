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

import xyz.truenight.dynamic.AttrUtils;

/**
 * Created by true
 * date: 03/03/2017
 * time: 03:05
 * <p>
 * Copyright © Mikhail Frolov
 */

public class ViewGroupParamAdapter implements TypedParamAdapter {
    @Override
    public boolean isSuitable(ViewGroup.LayoutParams params) {
        return true;
    }

    @Override
    public boolean apply(Context context, ViewGroup.LayoutParams params, String name, String value) {
        switch (name) {
            case LAYOUT_WIDTH:
                params.width = AttrUtils.getDimension(context, value);
                return true;
            case LAYOUT_HEIGHT:
                params.height = AttrUtils.getDimension(context, value);
                return true;
        }

        return false;
    }
}
