//student name: Pooja Elkal - 801137492

package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements UsersFragment.StateInterface, FilterByStateFragment.sortListByState, SortFragment.InterfaceActivity {
    final static public String ARRAYLIST = "array";

    RelativeLayout containerId;
    ArrayList<User> userList = new ArrayList<>();
    String attributeSelected;
    boolean sortSelectedAsc=false;
    boolean sortSelectedDesc=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerId = findViewById(R.id.containerId);
        userList.addAll(Data.users);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARRAYLIST, userList);
        UsersFragment usersFragment = new UsersFragment();
        usersFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerId, usersFragment,"userFragment")
                .commit();

    }
    @Override
    public void sortState() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerId, new FilterByStateFragment(),"filterFragment")
                .addToBackStack(null)
                .commit();

    }
    @Override
    public void sortFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerId, new SortFragment(),"sortFragement")
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void sendStateToSort(String state) {
        userList.clear();
        if(state.equals("All States")){
            userList.addAll(Data.users);
        }
        else {
            for (User user:Data.users){
                if(user.state.equals(state)){
                    userList.add(user);
                }
            }
        }
        if(sortSelectedAsc){
            sortByAttributeActivityAsc(attributeSelected);
        }if(sortSelectedDesc){
            sortByAttributeActivityDesc(attributeSelected);
        }
        getSupportFragmentManager().popBackStack();

    }
    @Override
    public void sortByAttributeActivityDesc(String attribute) {
        attributeSelected = attribute;
        sortSelectedDesc = true;
        sortSelectedAsc=false;

        Comparator<User> nameSort = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.name.compareTo(o1.name);
            }
        };
        Comparator<User> ageSort = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.age - o1.age;
            }
        };
        Comparator<User> stateSort = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.state.compareTo(o1.state);
            }
        };

        if(attribute.equals("Name")) {
            Collections.sort(userList, nameSort);
        }else if(attribute.equals("State")){
            Collections.sort(userList, stateSort);
        }else if(attribute.equals("Age")){
            Collections.sort(userList, ageSort);
        }

        getSupportFragmentManager().popBackStack();
    }
    @Override
    public void sortByAttributeActivityAsc(String attribute) {
        attributeSelected = attribute;
        sortSelectedAsc=true;
        sortSelectedDesc=false;

        Comparator<User> nameSort = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        };
        Comparator<User> ageSort = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.age - o2.age;
            }
        };
        Comparator<User> stateSort = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.state.compareTo(o2.state);
            }
        };

        if(attribute.equals("Name")) {
            Collections.sort(userList, nameSort);
        }else if(attribute.equals("State")){
            Collections.sort(userList, stateSort);
        }else if(attribute.equals("Age")){
            Collections.sort(userList, ageSort);
        }
        getSupportFragmentManager().popBackStack();

    }
}