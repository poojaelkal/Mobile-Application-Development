package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class FilterByStateFragment extends Fragment {

    ListView stateList;
    ArrayAdapter<String> adapter;
    ArrayList<String> statesUnique = new ArrayList<>();

    public FilterByStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_by_state, container, false);
        getActivity().setTitle(R.string.titleFilter);
        stateList = view.findViewById(R.id.stateList);

        HashSet<String> states = new HashSet<>();
        for (User user: Data.users){
            states.add(user.state);
        }

        statesUnique.addAll(states);
        Collections.sort(statesUnique);
        statesUnique.add(0, "All States");

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,statesUnique);
        stateList.setAdapter(adapter);

        stateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String state = statesUnique.get(position);
                adapter.clear();
                mListenerSort.sendStateToSort(state);

            }
        });
        return view;
    }

    sortListByState mListenerSort;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListenerSort = (sortListByState) context;
    }

    public interface sortListByState{
        void sendStateToSort(String state);
    }
}