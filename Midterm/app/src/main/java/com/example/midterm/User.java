package com.example.midterm;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    String name,gender,group,state,state_abv;
    int age;

    public User(String name, int age, String gender, String group, String state, String state_abv) {
        this.name = name;
        this.gender = gender;
        this.group = group;
        this.state = state;
        this.state_abv = state_abv;
        this.age = age;
    }

    protected User(Parcel in) {
        name = in.readString();
        gender = in.readString();
        group = in.readString();
        state = in.readString();
        state_abv = in.readString();
        age = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(group);
        dest.writeString(state);
        dest.writeString(state_abv);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", group='" + group + '\'' +
                ", state='" + state + '\'' +
                ", state_abv='" + state_abv + '\'' +
                ", age=" + age +
                '}';
    }
}
