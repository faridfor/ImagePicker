package com.nguyenhoanglam.imagepicker.ui.imagepicker;

import android.widget.ImageView;

import com.nguyenhoanglam.imagepicker.R;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * Created by hoanglam on 8/17/17.
 */

public class ImageLoader {

//    private RequestOptions options;

    public ImageLoader() {
        /*options = new RequestOptions()
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_placeholder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);*/
    }

    public void loadImage(String path, ImageView imageView) {
        Picasso.get().load(new File(path))
                .resize(300, 300)
//                .onlyScaleDown()
                .centerInside()
//                .centerCrop()
                .error(R.drawable.imagepicker_image_placeholder)
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .into(imageView);
        /*Glide.with(imageView.getContext())
                .load(path)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);*/
    }
}
