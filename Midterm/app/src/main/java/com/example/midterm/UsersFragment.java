package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<User> userList = new ArrayList<>();
    UserRecyclerViewAdapter adapter;

    public static String ARRAY_LIST="array";

    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance(ArrayList<User> users) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARRAY_LIST, users);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userList = getArguments().getParcelableArrayList(MainActivity.ARRAYLIST);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        getActivity().setTitle(R.string.titleUser);
        container.removeAllViews();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserRecyclerViewAdapter(userList);
        recyclerView.setAdapter(adapter);
        view.findViewById(R.id.btnFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListerner.sortState();
            }
        });

        view.findViewById(R.id.btnSort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListerner.sortFragment();
            }
        });

        return view;
    }
    StateInterface mListerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListerner = (StateInterface) context;
    }

    public interface StateInterface{
        void sortState();
        void sortFragment();
    }
}