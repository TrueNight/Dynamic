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

package xyz.truenight.dynamic.adapter.attr;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import xyz.truenight.dynamic.AttrUtils;

final class TextViewAttrAdapter implements TypedAttrAdapter {
    @Override
    public boolean isSuitable(View view) {
        return view instanceof TextView;
    }

    @Override
    public boolean apply(View v, String name, String value) {
        TextView view = (TextView) v;
        switch (name) {
            case "textSize":
                view.setTextSize(TypedValue.COMPLEX_UNIT_PX, AttrUtils.getDimension(view.getContext(), value));
                return true;
            case "textColor":
                return setTextColor(view, value);
            case "gravity":
                view.setGravity(AttrUtils.getGravity(value));
                return true;
            case "maxLines":
                view.setMaxLines(AttrUtils.parseInt(value));
                return true;
            case "ellipsize":
                view.setEllipsize(AttrUtils.getEllipsize(value));
                return true;
            case "textAllCaps":
                if (value.startsWith("@")) {
                    view.setAllCaps(v.getContext().getResources().getBoolean(AttrUtils.getResId(view.getContext(), value)));
                } else {
                    view.setAllCaps(Boolean.valueOf(value));
                }
            case "textStyle":
                view.setTypeface(view.getTypeface(), AttrUtils.getTextStyle(value));
                return true;
            case "text":
                if (value.startsWith("@")) {
                    view.setText(AttrUtils.getResId(view.getContext(), value));
                } else {
                    view.setText(value);
                }
                return true;
        }

        return false;
    }

    public boolean setTextColor(TextView view, String value) {
        if (value.startsWith("#")) {
            view.setTextColor(Color.parseColor(value));
        } else {
            view.setTextColor(AttrUtils.getColor(view.getContext(), value));
        }
        return true;
    }
}
