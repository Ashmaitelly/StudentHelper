package com.abdsh.studenthelper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class greeting extends Fragment {


    public greeting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_greeting, container, false);
        Calendar c = Calendar.getInstance();
        int TOD = c.get(Calendar.HOUR_OF_DAY);
        TextView WM=view.findViewById(R.id.welcome_message);
        //set welcome message
        if(TOD>=6&&TOD<12){
            WM.setText(getString(R.string.message_1));
        }
        else if(TOD>=12&&TOD<17){
            WM.setText(getString(R.string.message_2));
        }
        else if(TOD>=17&&TOD<23){
            WM.setText(getString(R.string.message_3));
        }
        else if(TOD==23){
            WM.setText(getString(R.string.message_4));
        }
        else if(TOD<3){
            WM.setText(getString(R.string.message_5));
        }
        else if(TOD>=3&&TOD<6){
            WM.setText(getString(R.string.message_6));
        }
        // Inflate the layout for this fragment
        return view;
    }


}
