package com.example.prit.gridimagesearch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


public class ImageOptionsDisplay extends ActionBarActivity {

    private CheckBox cbRed;
    private CheckBox cbBlue;
    private CheckBox cbYellow;
    private CheckBox cbSmall;
    private CheckBox cbMedium;
    private CheckBox cbLarge;
    private CheckBox cbFaces;
    private CheckBox cbPhoto;
    private CheckBox cbClipart;
    private String st1, st2, st3, st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_options_display);


    }



    public void onSubmit(View v){

        st = "";
        cbRed = (CheckBox) findViewById(R.id.cbRed);
        cbBlue = (CheckBox) findViewById(R.id.cbBlue);
        cbYellow = (CheckBox) findViewById(R.id.cbYellow);
        cbSmall = (CheckBox) findViewById(R.id.cbSmall);
        cbMedium = (CheckBox) findViewById(R.id.cbMedium);
        cbLarge = (CheckBox) findViewById(R.id.cbLarge);
        cbFaces = (CheckBox) findViewById(R.id.cbFaces);
        cbPhoto = (CheckBox) findViewById(R.id.cbPhoto);
        cbClipart = (CheckBox) findViewById(R.id.cbClipart);
            if(cbRed.isChecked())
                st = st + " Red";
            if(cbBlue.isChecked())
                st = st + " Blue";
            if(cbYellow.isChecked())
                st = st + " Yellow";
        if(cbSmall.isChecked())
            st = st + " small";
        if(cbMedium.isChecked())
            st = st + " Medium";
        if(cbLarge.isChecked())
            st = st + " Large";
        if(cbFaces.isChecked())
            st = st + " Faces";
        if(cbPhoto.isChecked())
            st = st + " Photo";
        if(cbClipart.isChecked())
            st = st + " Clip art";

        Intent filterData = new Intent();
        filterData.putExtra("filter", st);
        setResult(RESULT_OK, filterData);
        // closes the activity and returns to first screen
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_options_display, menu);
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
