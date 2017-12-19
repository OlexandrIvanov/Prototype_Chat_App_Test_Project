package com.example.asus.my;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ReplicaFragment extends DialogFragment {

    private static final String USER_REPLICA = "user";

    private int user;

    Message replica;
    EditText et;

    private OnFragmentInteractionListener mListener;

    public ReplicaFragment() {
        // Required empty public constructor
    }


    public static ReplicaFragment newInstance(int param) {
        ReplicaFragment fragment = new ReplicaFragment();
        Bundle args = new Bundle();
        args.putInt(USER_REPLICA, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getInt(USER_REPLICA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_replica, container, false);
        TextView tv = (TextView)v.findViewById(R.id.fragment_tv);
        tv.setText(getText(R.string.dialog_message)+(user == 1?" A":" B"));
        Button buttonCancel = (Button)v.findViewById(R.id.btn_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        et = (EditText)v.findViewById(R.id.et_text);

        Button buttonOk = (Button)v.findViewById(R.id.btn_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okButtonPressed(user);
                dismiss();
            }
        });

        return v;
    }

    public void okButtonPressed(int mUser) {
        if (mListener != null) {
            String text = et.getText().toString();
            replica = new Message(text);
            mListener.onFragmentInteraction(replica, mUser);
        }
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Message replica, int user);
    }
}
