package daovudat.finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

     TextView intro;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("StreetBarz Introduction");
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        intro = (TextView) view.findViewById(R.id.intro);
        intro.setText("\tStreet bar is an application to help you follow the exercises from Madbarz, which includes the videos. It contains three levels (basic, medium and advanced)." +
                "\n\n\tYou must work out harder everyday and take some points to unlock medium and advanced exercises. Between exercises, you click \"Next exercise\" to move the next one." +
                " You can click \"View\" to watch instruction of that exercise. In addition, you can share the achievement on Facebook at the end of the exercise. " +
                "\n\n\tNow, let’s sweep left to right, choose \"Exercises\",work hard and transform to a perfect body.");

        // Inflate the layout for this fragment
        return view;
    }

}
