package com.xyz.imageloading;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvNrupul;
    private Button mBtnFetchImg;
    private Button mBtnFetchGlide;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL("https://i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=0d3f33fb6aa6e0154b7713a00454c83d");
                // Get the original image in the bitmap format
                final Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                // Scale down the image to a different resolution
                final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update the scaled bitmap version on to the image view.
                        mIvNrupul.setImageBitmap(scaledBitmap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mIvNrupul = findViewById(R.id.ivNrupul);
        mBtnFetchImg = findViewById(R.id.btnFetchImage);
        mBtnFetchGlide = findViewById(R.id.btnFetchGlide);

        /*
         * This will fetch the image from the server using Glide library
         */
        mBtnFetchGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mIvNrupul)
                        .load("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634__340.png")
                        .placeholder(R.drawable.high_resol)
                        .into(mIvNrupul);
            }
        });

        /*
        This will fetch the image from the server without Glide library
         */
        mBtnFetchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }
}
//