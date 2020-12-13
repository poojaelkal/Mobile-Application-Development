package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class SortFragment extends Fragment implements SortRecyclerViewAdapter.SortByAttributesInterface {

    public static String TAG="demo";
    RecyclerView recyclerViewSort;
    LinearLayoutManager layoutManager;
    ArrayList<String> sortAttributes = new ArrayList<String>();

    SortRecyclerViewAdapter sortadapter;

    public SortFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        getActivity().setTitle(R.string.titleSort);
        sortAttributes.add("Name");
        sortAttributes.add("Age");
        sortAttributes.add("State");
        recyclerViewSort = view.findViewById(R.id.recyclerViewSort);
        recyclerViewSort.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSort.setLayoutManager(layoutManager);
        sortadapter = new SortRecyclerViewAdapter(sortAttributes,this);
        recyclerViewSort.setAdapter(sortadapter);
        return view;
    }

    InterfaceActivity mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mListener= (InterfaceActivity) context;
    }

    @Override
    public void sortByAttributeDesc(String attribute) {
        mListener.sortByAttributeActivityDesc(attribute);
    }

    @Override
    public void sortByAttributeAsc(String attribute) {
        mListener.sortByAttributeActivityAsc(attribute);
    }

    public interface InterfaceActivity{
        void sortByAttributeActivityDesc(String attribute);
        void sortByAttributeActivityAsc(String attribute);
    }
}