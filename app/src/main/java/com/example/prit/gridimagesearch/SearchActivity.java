package com.example.prit.gridimagesearch;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


import java.io.Serializable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    private EditText etSearch;
    private GridView gvImage;
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter aImageResults;
    String Filter,searchUrl;
    private final int REQUEST_CODE = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this,imageResults);
        gvImage.setAdapter(aImageResults);
        Filter = null;

    }


    private void setUpViews(){
        etSearch = (EditText)findViewById(R.id.etSearch);
        gvImage = (GridView)findViewById(R.id.gvImage);
        gvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //launch the image display activity
                //Creating on intent
                Intent i = new Intent(SearchActivity.this,  ImageFullDisplay.class);
                //Get the image result to display
                ImageResult result = imageResults.get(position);
                //Pass image result into the intent
                 i.putExtra("result",  result);  //need to either be serializable or parcelable
                //launch the new activity
                startActivity(i);
            }
        });
    }



    public void onImageSearch(View v){
       Filter = "";
        showImage();
    }
    public void showImage(){
    String query = etSearch.getText().toString();
        AsyncHttpClient client = new AsyncHttpClient();
        String searchUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + Filter + "&rsz=8";
        //searchUrl = searchUrl + query + "&rsz=8&start=";
        client.get(searchUrl, new JsonHttpResponseHandler() {
        @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            Log.d("DEBUG", response.toString());
           JSONArray imageResultsJson = null;


            try{
                imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
               imageResults.clear();
               aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJson));
            }catch (JSONException e){
                e.printStackTrace();
            }

            Log.i("info", imageResults.toString());
            }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        }
    });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

        if (id == R.id.filter) {
            launchFilterView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void launchFilterView() {
        Intent i = new Intent(this, ImageOptionsDisplay.class);
      //  if (filter == null)
       //     filter = new Filter("small", "", "car", null);
         startActivityForResult(i, REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras

             Filter = data.getExtras().getString("filter");
            // Set the new text to List View
             showImage();

        }
    }
}
