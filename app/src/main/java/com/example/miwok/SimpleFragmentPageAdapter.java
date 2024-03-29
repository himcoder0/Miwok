package com.example.miwok;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPageAdapter(@NonNull FragmentManager fm,Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new NumbersFragment();
        }else if(position==1){
            return new FamilyFragment();
        }else if(position==2){
            return new ColorsFragment();
        }else{
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if(position == 2){
            return mContext.getString(R.string.category_colors);
        }else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}
