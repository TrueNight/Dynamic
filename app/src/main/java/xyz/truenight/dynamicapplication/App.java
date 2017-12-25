package xyz.truenight.dynamicapplication;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

import xyz.truenight.dynamic.AttrUtils;
import xyz.truenight.dynamic.DynamicLayoutInflater;
import xyz.truenight.dynamic.adapter.attr.AttrAdapter;
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
                .registerAttrAdapter(ImageView.class, "android:src", new AttrAdapter<ImageView>() {
                    @Override
                    public boolean apply(final ImageView view, final String value) {
                        if (Utils.startsWith(value, "http")) {
                            imageFromUrl(view, value);
                            return true;
                        }
                        return false;
                    }
                })
                .registerAttrAdapter(View.class, "android:background", new AttrAdapter<View>() {
                    @Override
                    public boolean apply(final View view, final String value) {
                        if (Utils.startsWith(value, "http")) {
                            backgroundFromUrl(view, value);
                            return true;
                        }
                        return false;
                    }
                })
                .registerAttrAdapter(View.class, "app:visible", new AttrAdapter<View>() {
                    @Override
                    public boolean apply(final View view, final String value) {
                        boolean b = AttrUtils.getBoolean(view.getContext(), value);
                        view.setVisibility(b ? View.VISIBLE : View.GONE);
                        return true;
                    }
                })
                .registerAttrAdapter(new CompatFloatingActionButtonAttrAdapter())
                .create();
    }

    private void imageFromUrl(final ImageView view, final String value) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap bm = BitmapFactory.decodeStream(new URL(value).openConnection().getInputStream());
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e) {
                    Log.d("TAG", "", e);
                }
            }
        });
    }

    private void backgroundFromUrl(final View view, final String value) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap bm = BitmapFactory.decodeStream(new URL(value).openConnection().getInputStream());
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setBackground(new BitmapDrawable(view.getResources(), bm));
                        }
                    });
                } catch (Exception e) {
                    Log.d("TAG", "", e);
                }
            }
        });
    }
}
