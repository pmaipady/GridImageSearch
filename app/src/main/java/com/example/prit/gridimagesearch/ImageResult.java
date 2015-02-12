package com.example.prit.gridimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prit on 2/10/2015.
 */
public class ImageResult {
    public String fullurl;
    public String thumburl;
    public String title;

    public ImageResult(JSONObject json) {
        try {

            this.fullurl = json.getString("url");
            this.thumburl = json.getString("tbUrl");
            this.title = json.getString("title");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray array) {

        ArrayList<ImageResult> results = new ArrayList<ImageResult>();

        for (int i = 0; i < array.length(); i++) {


            try {

                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
            return results;

        }

    }
