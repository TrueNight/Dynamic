package xyz.truenight.dynamicapplication;

import android.app.Application;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import xyz.truenight.dynamic.DynamicLayoutInflater;
import xyz.truenight.dynamic.adapter.attr.TypedAttrAdapter;
import xyz.truenight.dynamic.compat.CompatDynamicLayoutInflater;
import xyz.truenight.utils.Utils;

/**
 * Created by true
 * date: 04/03/2017
 * time: 00:50
 * <p>
 * Copyright © Mikhail Frolov
 */

public class App extends Application {

    // DO NOT REMOVE
    // or gc will grab prototype
    private DynamicLayoutInflater inflater;

    @Override
    public void onCreate() {
        super.onCreate();

        // all other inflater will inherit params from this inflater
        inflater = CompatDynamicLayoutInflater.base(this)
                .registerAttrAdapter(new TypedAttrAdapter() {
                    @Override
                    public boolean isSuitable(View view) {
                        return view instanceof ImageView;
                    }

                    @Override
                    public boolean apply(View view, String name, String value) {
                        if ("src".equals(name) && !Utils.startsWith(value, "@")) {
                            try {
                                ((ImageView) view).setImageURI(Uri.parse(value));
                                return true;
                            } catch (Exception e) {
                                return false;
                            }
                        }

                        return false;
                    }
                })
                .create();
    }
}
