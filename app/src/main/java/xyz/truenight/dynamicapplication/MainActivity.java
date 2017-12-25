package xyz.truenight.dynamicapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.truenight.dynamic.DynamicLayoutInflater;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_LAYOUT =
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<android.support.design.widget.CoordinatorLayout\n" +
                    "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                    "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
                    "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
                    "    android:layout_width=\"match_parent\"\n" +
                    "    android:layout_height=\"match_parent\"\n" +
                    "    android:fitsSystemWindows=\"true\"\n" +
                    "    tools:context=\"xyz.truenight.dynamicapplication.MainActivity\">\n" +
                    "\n" +
                    "    <android.support.design.widget.AppBarLayout\n" +
                    "        android:layout_width=\"match_parent\"\n" +
                    "        android:layout_height=\"wrap_content\"\n" +
                    "        android:theme=\"@style/AppTheme.AppBarOverlay\">\n" +
                    "\n" +
                    "        <android.support.v7.widget.Toolbar\n" +
                    "            android:id=\"@+id/toolbar\"\n" +
                    "            android:layout_width=\"match_parent\"\n" +
                    "            android:layout_height=\"?attr/actionBarSize\"\n" +
                    "            android:background=\"?attr/colorPrimary\"\n" +
                    "            app:popupTheme=\"@style/AppTheme.PopupOverlay\"/>\n" +
                    "\n" +
                    "    </android.support.design.widget.AppBarLayout>\n" +
                    "\n" +
                    "    <FrameLayout\n" +
                    "        android:layout_width=\"match_parent\"\n" +
                    "        android:layout_height=\"match_parent\"\n" +
                    "        app:layout_behavior=\"@string/appbar_scrolling_view_behavior\">\n" +
                    "\n" +
                    "        <FrameLayout\n" +
                    "            android:id=\"@+id/container\"\n" +
                    "            android:layout_width=\"match_parent\"\n" +
                    "            android:layout_height=\"match_parent\"/>\n" +
                    "\n" +
                    "        <android.support.design.widget.FloatingActionButton\n" +
                    "            android:id=\"@+id/fab\"\n" +
                    "            android:layout_width=\"wrap_content\"\n" +
                    "            android:layout_height=\"wrap_content\"\n" +
                    "            android:layout_gravity=\"bottom|end\"\n" +
                    "            android:layout_margin=\"@dimen/fab_margin\"\n" +
                    "            app:srcCompat=\"@android:drawable/ic_dialog_email\"/>\n" +
                    "\n" +
                    "    </FrameLayout>\n" +
                    "\n" +
                    "</android.support.design.widget.CoordinatorLayout>\n";

    private static final String LAYOUT =
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<FrameLayout\n" +
            "    android:id=\"@+id/content_main\"\n" +
            "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                    "    xmlns:blah=\"http://schemas.android.com/apk/res-auto\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
                    "    android:background=\"#fdd835\"\n" +
            "    android:paddingBottom=\"@dimen/activity_vertical_margin\"\n" +
            "    android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n" +
            "    android:paddingRight=\"@dimen/activity_horizontal_margin\"\n" +
            "    android:paddingTop=\"@dimen/activity_vertical_margin\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:id=\"@+id/text_view\"\n" +
                    "        android:layout_width=\"wrap_content\"\n" +
                    "        android:layout_height=\"wrap_content\"\n" +
                    "        android:padding=\"16dp\"\n" +
            "        android:textAllCaps=\"true\"\n" +
                    "        android:textColor=\"@color/white\"\n" +
            "        android:layout_gravity=\"center\"\n" +
            "        android:gravity=\"right|center_vertical\"\n" +
                    "        android:background=\"https://ak.picdn.net/assets/cms/17d705f0349ed08f5387b39c7644054a59affe52-LOHP_vector_module_shutterstock_307324316.jpg\"\n" +
            "        android:text=\"Hello World!\"/>\n" +
            "    \n" +
            "    <ImageView\n" +
            "        android:layout_margin=\"50dp\"\n" +
            "        android:layout_width=\"100dp\"\n" +
                    "        android:layout_height=\"@dimen/test\"\n" +
                    "        android:scaleType=\"centerCrop\"\n" +
                    "        android:src=\"https://avatars1.githubusercontent.com/u/2495154\"/>\n" +
                    "    \n" +
                    "    <View\n" +
                    "        android:layout_width=\"300dp\"\n" +
                    "        android:layout_height=\"400dp\"\n" +
                    "        android:background=\"#ffff0000\"\n" +
                    "        blah:visible=\"false\"\n/>\n" +
            "\n" +
            "</FrameLayout>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicLayoutInflater inflater = DynamicLayoutInflater.from(this);
        setContentView(inflater.inflate(MAIN_LAYOUT, null));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inflater.inflate(LAYOUT, (ViewGroup) findViewById(R.id.container));

        ((TextView) findViewById(R.id.text_view)).setTextSize(20);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
