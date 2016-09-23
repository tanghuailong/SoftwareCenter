package softwarecenter.wt.com.softwarecenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import softwarecenter.wt.com.softwarecenter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApsFragment extends Fragment {


    public ApsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aps, container, false);
    }

}
