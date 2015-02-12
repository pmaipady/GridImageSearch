package com.example.prit.gridimagesearch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ImageFullDisplay extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_display);
        //Pull out the url from the intent
        //String url = getIntent().getStringExtra("url");
        ImageResult result = (ImageResult) getIntent().getSerializableExtra("result");
        //Find the image view
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        ImageView ivImageFullView = (ImageView) findViewById(R.id.ivImageFullView);
        //load the image url into the imageview using picasso
        Picasso.with(this).load(result.fullurl).into(ivImageFullView);

    }


    public void onReturn(View v) {
      //  Button btnReturn = (Button) findViewById(R.id.btnReturn);
     /*EditText etItem = (EditText) findViewById(R.id.etItem);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("name", etItem.getText().toString());
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
       */ finish(); // closes the activity, pass data to parent
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_full_display, menu);
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
