package com.example.prit.gridimagesearch;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prit on 2/10/2015.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {
    public ImageResultsAdapter(Context context, List <ImageResult> images) {
        super(context, R.layout.item_image_result, images);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageResult imageInfo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result,parent,false);

        }
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        ImageView tvImage = (ImageView)convertView.findViewById(R.id.tvImage);
        tvTitle.setText(imageInfo.title);
        tvImage.setImageResource(0);
        Picasso.with(getContext()).load(imageInfo.thumburl).into(tvImage);
        return convertView;
    }
}
