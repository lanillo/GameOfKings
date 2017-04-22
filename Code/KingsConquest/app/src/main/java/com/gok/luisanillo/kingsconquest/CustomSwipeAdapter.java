package com.gok.luisanillo.kingsconquest;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by LuisFelipe on 2017-04-21.
 *
 * Special thanks to PRABEESH R K for his video.
 *
 * Will take care of allowing swipping the screen
 */

public class CustomSwipeAdapter extends PagerAdapter {
    // Contains images
    private int[] image_ressources = {R.drawable.warrior, R.drawable.archer, R.drawable.knight};
    private int[] name_ressources = {R.string.Warrior, R.string.Archer, R.string.Knight};

    // Contex
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx) {

        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_ressources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // ???
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ???
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
         ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
         TextView textView = (TextView) item_view.findViewById(R.id.image_count);
         imageView.setImageResource(image_ressources[position]);
         textView.setText(name_ressources[position]);
         container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
