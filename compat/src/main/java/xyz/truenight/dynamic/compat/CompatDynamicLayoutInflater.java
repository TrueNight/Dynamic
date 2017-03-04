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
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import xyz.truenight.dynamic.DynamicLayoutInflater;
import xyz.truenight.utils.Utils;

public class CompatDynamicLayoutInflater extends DynamicLayoutInflater {
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit."
    };

    /**
     * Initializing of base {@link CompatDynamicLayoutInflater}
     * <p>
     * Can be used for setting params which will be copied
     * to instances given by {@link CompatDynamicLayoutInflater#from(Context)}
     *
     * @param context Application context
     * @return Base inflater
     */
    public static Builder base(Context context) {
        return new Builder(context);
    }

    public static DynamicLayoutInflater from(Context context) {
        DynamicLayoutInflater unwrap = Utils.unwrap(DynamicLayoutInflater.mBase);
        if (unwrap == null) {
            // clone for factory unlock
            return new CompatDynamicLayoutInflater(context).cloneInContext(context);
        } else {
            return new CompatDynamicLayoutInflater(unwrap, context);
        }
    }

    /**
     * Instead of instantiating directly, you should retrieve an instance
     * through {@link CompatDynamicLayoutInflater#from(Context)}
     *
     * @param context The Context in which in which to find resources and other
     *                application-specific things.
     */
    protected CompatDynamicLayoutInflater(Context context) {
        super(context);
        setFactory2(new LayoutInflater.Factory2() {
            CompatViewInflater inflater = new CompatViewInflater();

            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                return inflater.createView(parent, name, context, attrs, getAttributeApplier());
            }

            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                return inflater.createView(null, name, context, attrs, getAttributeApplier());
            }
        });
    }

    protected CompatDynamicLayoutInflater(DynamicLayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    /**
     * Override onCreateView to instantiate names that correspond to the
     * widgets known to the Widget factory. If we don't find a match,
     * call through to our super class.
     */
    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }

        return super.onCreateView(name, attrs);
    }

    public DynamicLayoutInflater cloneInContext(Context newContext) {
        return new CompatDynamicLayoutInflater(this, newContext);
    }

    public static class Builder extends DynamicLayoutInflater.Builder {

        private Builder(Context context) {
            super(context);
        }

        @Override
        protected DynamicLayoutInflater instance(Context context) {
            return from(context);
        }
    }
}