package com.techietech.ps5emulatorprank.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.techietech.ps5emulatorprank.R;

public class OnBoardingAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public OnBoardingAdapter(Context context) {
        this.context = context;


    }


    int[] images = {
            R.drawable.ic_on_boarding1,
            R.drawable.ic_on_boarding2,
            R.drawable.ic_on_boarding3,
            R.drawable.ic_on_boarding4,
    };


    int[] headings = {

            R.string.onBoarding_title1,
            R.string.onBoarding_title2,
            R.string.onBoarding_title3,
            R.string.onBoarding_title4,
    };

    int[] description = {

            R.string.onBoarding_subtitle1,
            R.string.onBoarding_subtitle2,
            R.string.onBoarding_subtitle3,
            R.string.onBoarding_subtitle4,

    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(description[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
