package com.example.labs_81;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentsAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lstFragment = new ArrayList<>();

    public FragmentsAdapter(FragmentManager ft){
        super(ft);
    }

    @Override
    public Fragment getItem(int pos){
        return lstFragment.get(pos);
    }

    @Override
    public int getCount(){
        return lstFragment.size();
    }

    public void AddFragement(Fragment fragment){
        lstFragment.add(fragment);
    }
}
