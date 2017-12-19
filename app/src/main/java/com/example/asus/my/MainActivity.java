package com.example.asus.my;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ReplicaFragment.OnFragmentInteractionListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Message> messages;
    private ArrayList<Boolean> whichUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnA = (Button)findViewById(R.id.btn_replica_a);
        Button btnB = (Button)findViewById(R.id.btn_replica_b);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        messages = new ArrayList<>();
        whichUser = new ArrayList<>();

        mAdapter = new MessageAdapter(messages, whichUser, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {
        DialogFragment fragment;
        switch (view.getId()){
            case R.id.btn_replica_a:
                fragment = ReplicaFragment.newInstance(1);
                fragment.show(getSupportFragmentManager(), null);
                break;
            case R.id.btn_replica_b:
                fragment = ReplicaFragment.newInstance(2);
                fragment.show(getSupportFragmentManager(), null);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Message replica, int user) {
        switch (user){
            case 1:
                whichUser.add(true);
                messages.add(replica);
                mAdapter.notifyItemInserted(messages.size());
                mRecyclerView.scrollToPosition(messages.size()-1);
                break;
            case 2:
                whichUser.add(false);
                messages.add(replica);
                mAdapter.notifyItemInserted(messages.size());
                mRecyclerView.scrollToPosition(messages.size()-1);
                break;
        }
    }
}
