package xyz.truenight.dynamicapplication;

import android.app.Application;
import android.net.Uri;
import android.widget.ImageView;

import xyz.truenight.dynamic.DynamicLayoutInflater;
import xyz.truenight.dynamic.adapter.attr.ImageViewAttrAdapter;

/**
 * Created by true
 * date: 04/03/2017
 * time: 00:50
 * <p>
 * Copyright © Mikhail Frolov
 */

public class App extends Application {

    // DO NOT REMOVE
    // or gc will grab base
    private DynamicLayoutInflater inflater;

    @Override
    public void onCreate() {
        super.onCreate();

        // all other inflater will inherit params from this inflater
        inflater = DynamicLayoutInflater.base(this)
                .registerAttrAdapter(new ImageViewAttrAdapter() {
                    @Override
                    public boolean setSrc(ImageView view, String value) {
                        if (!super.setSrc(view, value)) {
                            view.setImageURI(Uri.parse(value));
                        }
                        return true;
                    }
                })
                .create();
    }
}
