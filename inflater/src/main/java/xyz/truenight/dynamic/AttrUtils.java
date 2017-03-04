package xyz.truenight.dynamic;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import xyz.truenight.dynamic.annotation.Visibility;

/**
 * Created by true
 * date: 03/03/2017
 * time: 01:22
 * <p>
 * Copyright © Mikhail Frolov
 */

public class AttrUtils {
    private AttrUtils() {
    }

    public static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";

    /**
     * ------------- Params -------------
     */

    public static String getText(Context context, String value) {
        if (value.startsWith("@string") || value.startsWith("@android:string")) {
            return context.getString(getResId(context, value));
        } else {
            return value;
        }
    }

    @Visibility
    public static int getVisibility(String value) {
        switch (value) {
            case "visible":
                return View.VISIBLE;
            case "gone":
                return View.GONE;
            case "invisible":
                return View.INVISIBLE;
        }
        return View.VISIBLE;
    }

    public static ImageView.ScaleType getScaleType(String value) {
        switch (value) {
            case "center":
                return ImageView.ScaleType.CENTER;
            case "centerCrop":
                return ImageView.ScaleType.CENTER_CROP;
            case "centerInside":
                return ImageView.ScaleType.CENTER_INSIDE;
            case "fitCenter":
                return ImageView.ScaleType.FIT_CENTER;
            case "fitEnd":
                return ImageView.ScaleType.FIT_END;
            case "fitStart":
                return ImageView.ScaleType.FIT_START;
            case "fitXY":
                return ImageView.ScaleType.FIT_XY;
            case "matrix":
                return ImageView.ScaleType.MATRIX;
        }

        return ImageView.ScaleType.FIT_CENTER;
    }

    public static ColorStateList getColor(Context context, String value) {
        return context.getResources().getColorStateList(getResId(context, value));
    }

    public static TextUtils.TruncateAt getEllipsize(String value) {
        switch (value) {
            case "end":
                return TextUtils.TruncateAt.END;
            case "marquee":
                return TextUtils.TruncateAt.MARQUEE;
            case "middle":
                return TextUtils.TruncateAt.MIDDLE;
            case "start":
                return TextUtils.TruncateAt.START;
        }

        return null;
    }

    public static int getGravity(String value) {
        int result = -1;
        String[] items = value.split("\\|");
        for (String item : items) {
            if (result == -1) {
                result = getGravityItem(item);
            } else {
                result |= getGravityItem(item);
            }
        }

        if (result == -1) {
            result = Gravity.TOP | Gravity.START;
        }
        return result;
    }

    private static int getGravityItem(String value) {
        if ("center".equals(value)) {
            return Gravity.CENTER;
        } else if ("center_vertical".equals(value)) {
            return Gravity.CENTER_VERTICAL;
        } else if ("center_horizontal".equals(value)) {
            return Gravity.CENTER_HORIZONTAL;
        } else if ("center_vertical".equals(value)) {
            return Gravity.CENTER_VERTICAL;
        } else if ("bottom".equals(value)) {
            return Gravity.BOTTOM;
        } else if ("clip_horizontal".equals(value)) {
            return Gravity.CLIP_HORIZONTAL;
        } else if ("clip_vertical".equals(value)) {
            return Gravity.CLIP_VERTICAL;
        } else if ("end".equals(value)) {
            return Gravity.END;
        } else if ("fill".equals(value)) {
            return Gravity.FILL;
        } else if ("fill_horizontal".equals(value)) {
            return Gravity.FILL_HORIZONTAL;
        } else if ("fill_vertical".equals(value)) {
            return Gravity.FILL_VERTICAL;
        } else if ("left".equals(value)) {
            return Gravity.LEFT;
        } else if ("right".equals(value)) {
            return Gravity.RIGHT;
        } else if ("top".equals(value)) {
            return Gravity.TOP;
        } else if ("start".equals(value)) {
            return Gravity.START;
        }

        return Gravity.TOP | Gravity.START;
    }

    public static int getTextStyle(String value) {
        switch (value) {
            case "bold":
                return Typeface.BOLD;
            case "italic":
                return Typeface.ITALIC;
            case "normal":
                return Typeface.NORMAL;
        }
        return Typeface.NORMAL;
    }

    public static int getDimension(Context context, String value) {
        if ("match_parent".equals(value)) {
            // text
            return ViewGroup.LayoutParams.MATCH_PARENT;
        } else if ("wrap_content".equals(value)) {
            return ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (value.startsWith("@")) {
            // @dimen
            return context.getResources().getDimensionPixelOffset(getResId(context, value));
        } else if (value.endsWith("dp")) {
            // dp
            return dpToPx(context, parseFloat(value.replace("dp", "")));
        } else if (value.endsWith("px")) {
            // px
            return parseInt(value.replace("px", ""));
        } else if (value.endsWith("sp")) {
            // px
            return spToPx(context, parseFloat(value.replace("sp", "")));
        }

        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public static int dpToPx(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static int spToPx(Context context, float sp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }

    public static float parseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the value of the specified attribute as a string representation.
     * The lookup is performed using the attribute name.
     *
     * @param name The name of the attribute to get the value from.
     * @return A String containing the value of the attribute, or null if the
     * attribute cannot be found.
     */
    public static String getAndroidAttribute(AttributeSet attrs, String name) {
        return attrs.getAttributeValue(NAMESPACE_ANDROID, name);
    }

    /**
     * Returns the value of the specified attribute as a string representation.
     * The lookup is performed using the attribute name.
     *
     * @param attrs The attribute set where to search
     * @param name  The name of the attribute to get the value from.
     * @return resource id from attribute, or 0 if the
     * value of attribute cannot be found
     */
    public static int getAndroidResId(Context context, AttributeSet attrs, String name) {
        String attributeValue = attrs.getAttributeValue(NAMESPACE_ANDROID, name);
        if (attributeValue == null) {
            return -1;
        }
        return getResId(context, attributeValue);
    }

    public static int getResId(Context context, String value) {
        List<String> items = xyz.truenight.utils.Utils.splitAndClearEmpty(value, "/");
        if (value.startsWith("?attr")) {
            // Attempt to resolve the "?attr/name" string to an identifier.
            int identifier = context.getResources().getIdentifier(value.substring(1), null, null);
            return identifier > 0 ? identifier : -1;
        } else if (value.startsWith("@android")) {
            return getAndroidResourceId(context,
                    removeIdentifiers(xyz.truenight.utils.Utils.first(items)), xyz.truenight.utils.Utils.last(items));
        } else {
            return getResourceId(context,
                    removeIdentifiers(xyz.truenight.utils.Utils.first(items)), xyz.truenight.utils.Utils.last(items));
        }
    }

    private static int getAndroidResourceId(Context context, String type, String name) {
        try {
            int identifier = context.getResources().getIdentifier(name, type, "android");
            return identifier > 0 ? identifier : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    private static String removeIdentifiers(String string) {
        return string.replace("android:", "").replace("@+", "").replace("@", "").replace("?", "");
    }

    public static int getResourceId(Context context, String type, String name) {
        try {
            int identifier = context.getResources().getIdentifier(name, type, context.getPackageName());
            return identifier > 0 ? identifier : -1;
        } catch (Exception e) {
            return -1;
        }
    }
}
