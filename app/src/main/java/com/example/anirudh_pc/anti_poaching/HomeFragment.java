package com.example.anirudh_pc.anti_poaching;

/**
 * Created by anand on 4/5/15.
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anand on 4/5/15.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {


    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.mainactivity, null);
        View view = inflater.inflate(R.layout.mainactivity,container, false);
        Intent intent = new Intent(getActivity(), UserHome.class);
        getActivity().startActivity(intent);
        return root; }

}

