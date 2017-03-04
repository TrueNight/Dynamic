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

    private static final String LAYOUT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<FrameLayout\n" +
            "    android:id=\"@+id/content_main\"\n" +
            "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    android:paddingBottom=\"@dimen/activity_vertical_margin\"\n" +
            "    android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n" +
            "    android:paddingRight=\"@dimen/activity_horizontal_margin\"\n" +
            "    android:paddingTop=\"@dimen/activity_vertical_margin\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:id=\"@+id/text_view\"\n" +
            "        android:layout_width=\"200dp\"\n" +
            "        android:layout_height=\"@dimen/test\"\n" +
            "        android:textAllCaps=\"true\"\n" +
            "        android:layout_gravity=\"center\"\n" +
            "        android:gravity=\"right|center_vertical\"\n" +
            "        android:background=\"#4d000000\"\n" +
            "        android:text=\"Hello World!\"/>\n" +
            "    \n" +
            "    <ImageView\n" +
            "        android:layout_margin=\"50dp\"\n" +
            "        android:layout_width=\"100dp\"\n" +
            "        android:layout_height=\"100dp\"\n" +
            "        android:src=\"@mipmap/ic_launcher\"/>\n" +
            "\n" +
            "</FrameLayout>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DynamicLayoutInflater.from(this).inflate(LAYOUT, (ViewGroup) findViewById(R.id.container));

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
