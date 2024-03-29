package com.androidtutz.anushka.lifecycledemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidtutz.anushka.lifecycledemo.viewModel.MainActivityViewModel;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CounterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CounterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // ViewModel
    MainActivityViewModel mainActivityViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    // Ui stuff
    private TextView textView;
    private TextView tvCount2TextView;
    private Integer countToSaveState;

    private boolean clickCountChanged = false;

    public CounterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CounterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CounterFragment newInstance(String param1, String param2) {
        CounterFragment fragment = new CounterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        textView = view.findViewById(R.id.tvCount);

        tvCount2TextView = view.findViewById(R.id.tvCount2);


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        LiveData<Integer> count = mainActivityViewModel.getInitialCount();

        count.getValue();

        count.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (clickCountChanged) {
                    textView.setText(String.format("Count is: %s", countToSaveState));


                    tvCount2TextView.setText(String.format("Count is: %s", countToSaveState));
                    clickCountChanged = false;
                }

                textView.setText(String.format("Count is: %s", integer));


                tvCount2TextView.setText(String.format("Count is: %s", integer));
                countToSaveState = integer;

            }
        });


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (clickCountChanged) {
                    mainActivityViewModel.getCurrentCount(countToSaveState);
                }
                mainActivityViewModel.getCurrentCount(0);
            }
        });


        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save user email instance variable value in bundle.
//        outState.putInt("countValue", countToSaveState);
//        outState.putBoolean("flag", clickCountChanged);
//        outState.putSerializable("viewModel", (Serializable) mainActivityViewModel);

//        outState.putSerializable("countValue", countToSaveState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

//        if (savedInstanceState != null) {
//            countToSaveState = savedInstanceState.getInt("countValue");
//            clickCountChanged = savedInstanceState.getBoolean("flag");
//        }


//        if ((savedInstanceState != null) && (savedInstanceState.getSerializable("countValue") != null)) {
//            countToSaveState = (Integer) savedInstanceState.getSerializable("countValue");
//            clickCountChanged = true;
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
