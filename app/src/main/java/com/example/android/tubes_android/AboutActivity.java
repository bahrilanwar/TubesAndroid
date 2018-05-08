package com.example.android.tubes_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class AboutActivity extends AppCompatActivity {

    private Button button;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");

        about = findViewById(R.id.about);
        button = findViewById(R.id.btn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void btn(View view) {
        String uri = "https://www.google.com/maps/place/Universitas+Telkom/@-6.9786646,107.6297445,14.25z/data=!4m5!3m4!1s0x2dd6285c5b7da517:0x864485f26a388f95!8m2!3d-6.9740004!4d107.6303476";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
