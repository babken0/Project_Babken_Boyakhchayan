package com.example.project_babken_boyakhchayan;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.nio.file.FileVisitOption;

public class ShowPictureActivity extends AppCompatActivity {
    ImageView imageView;
    Button setWallpaperBtn;
    WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        imageView = findViewById(R.id.imageViewShowPicture);
        setWallpaperBtn = findViewById(R.id.setBtn);

        final Intent intent = getIntent();
        final String imageUrlString = intent.getStringExtra("Image");
        Picasso.with(this).load(imageUrlString).into(imageView);

        setWallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                setWallpaperFromUrl(imageUrlString);
            }
        });
    }
    private void setWallpaperFromUrl(String imagePageURL) {
        Glide.with(this)
                .asBitmap()
                .load(imagePageURL)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        //imageView.setImageBitmap(resource);
                        try {
                            wallpaperManager.setBitmap(resource);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
