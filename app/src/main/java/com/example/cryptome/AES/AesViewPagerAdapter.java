package com.example.cryptome.AES;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cryptome.R;

import java.util.List;

public class AesViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<AesItem> mListScreen;
    Animation imageanim, titleanim, descanim;


    public AesViewPagerAdapter(Context mContext, List<AesItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        imageanim = AnimationUtils.loadAnimation(mContext, R.anim.fadedown);
        titleanim = AnimationUtils.loadAnimation(mContext, R.anim.fadeleft);
        descanim = AnimationUtils.loadAnimation(mContext, R.anim.faderight);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutscreen = inflater.inflate(R.layout.layout_aes, null);

        ImageView imgSlide = layoutscreen.findViewById(R.id.introImgAes);
        TextView title = layoutscreen.findViewById(R.id.introTitleAes);
        TextView desc = layoutscreen.findViewById(R.id.introDescAes);

        imgSlide.setAnimation(imageanim);
        title.setAnimation(titleanim);
        desc.setAnimation(descanim);

        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());
        title.setText(mListScreen.get(position).getTitle());
        desc.setText(mListScreen.get(position).getDesc());


        container.addView(layoutscreen);

        return layoutscreen;

    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
