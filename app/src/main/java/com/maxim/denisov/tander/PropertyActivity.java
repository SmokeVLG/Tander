package com.maxim.denisov.tander;

/**
 * Created by Максим on 10.08.2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        // get intent data
        Intent intent = getIntent();

        // Selected image id
        String position = intent.getExtras().getString("id") ;
        float k = intent.getExtras().getFloat("k");


        TextView imageView = (TextView) findViewById(R.id.textViewProperty);
        imageView.setText(String.valueOf(position));

        ProgressButton b = (ProgressButton) findViewById(R.id.buttonProperty);
        b.setRatio(k);

    }
}