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

package xyz.truenight.dynamic.compat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import xyz.truenight.dynamic.AttributeApplier;
import xyz.truenight.dynamic.DynamicLayoutInflater;

class CompatViewInflater implements DynamicLayoutInflater.Factory2 {

    private static final String LOG_TAG = CompatViewInflater.class.getSimpleName();

    // todo support of theme attr
//    private static final Class<?>[] sConstructorSignature = new Class[]{
//            Context.class};
//
//    private static final String[] sClassPrefixList = {
//            "android.widget.",
//            "android.view.",
//            "android.webkit."
//    };


//    private static final Map<String, Constructor<? extends View>> sConstructorMap
//            = new ArrayMap<>();

    public CompatViewInflater() {
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs, AttributeApplier attributeApplier) {
        return createView(parent, name, context, attrs, attributeApplier);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs, AttributeApplier attributeApplier) {
        return createView(null, name, context, attrs, attributeApplier);
    }

    public final View createView(View parent, final String name, @NonNull Context context, AttributeSet attrs, AttributeApplier attributeApplier) {

        View view = null;

        // We need to 'inject' our tint aware Views in place of the standard framework versions
        switch (name) {
            case "TextView":
                view = new AppCompatTextView(context);
                break;
            case "ImageView":
                view = new AppCompatImageView(context);
                break;
            case "Button":
                view = new AppCompatButton(context);
                break;
            case "EditText":
                view = new AppCompatEditText(context);
                break;
            case "Spinner":
                view = new AppCompatSpinner(context);
                break;
            case "ImageButton":
                view = new AppCompatImageButton(context);
                break;
            case "CheckBox":
                view = new AppCompatCheckBox(context);
                break;
            case "RadioButton":
                view = new AppCompatRadioButton(context);
                break;
            case "CheckedTextView":
                view = new AppCompatCheckedTextView(context);
                break;
            case "AutoCompleteTextView":
                view = new AppCompatAutoCompleteTextView(context);
                break;
            case "MultiAutoCompleteTextView":
                view = new AppCompatMultiAutoCompleteTextView(context);
                break;
            case "RatingBar":
                view = new AppCompatRatingBar(context);
                break;
            case "SeekBar":
                view = new AppCompatSeekBar(context);
                break;
        }

//        if (view == null) {
//            // If the original context does not equal our themed context, then we need to manually
//            // inflate it using the name so that android:theme takes effect.
//            view = createViewFromTag(context, name, attrs);
//        }

        if (view != null) {
            attributeApplier.applyAttrs(view, attrs);
        }

        return view;
    }

//    private View createViewFromTag(Context context, String name, AttributeSet attrs) {
//        if (name.equals("view")) {
//            name = attrs.getAttributeValue(null, "class");
//        }
//
//        try {
//            if (-1 == name.indexOf('.')) {
//                for (int i = 0; i < sClassPrefixList.length; i++) {
//                    final View view = createView(context, name, sClassPrefixList[i], attrs);
//                    if (view != null) {
//                        return view;
//                    }
//                }
//                return null;
//            } else {
//                return createView(context, name, null, attrs);
//            }
//        } catch (Exception e) {
//            // We do not want to catch these, lets return null and let the actual LayoutInflater
//            // try
//            return null;
//        } finally {
//            // Don't retain references on context.
//        }
//    }

//    private View createView(Context context, String name, String prefix, AttributeSet attrs)
//            throws ClassNotFoundException, InflateException {
//        Constructor<? extends View> constructor = sConstructorMap.get(name);
//
//        try {
//            if (constructor == null) {
//                // Class not found in the cache, see if it's real, and try to add it
//                Class<? extends View> clazz = context.getClassLoader().loadClass(
//                        prefix != null ? (prefix + name) : name).asSubclass(View.class);
//
//                constructor = clazz.getConstructor(sConstructorSignature);
//                sConstructorMap.put(name, constructor);
//            }
//            constructor.setAccessible(true);
//            return constructor.newInstance(context);
//        } catch (Exception e) {
//            // We do not want to catch these, lets return null and let the actual LayoutInflater
//            // try
//            return null;
//        }
//    }
}