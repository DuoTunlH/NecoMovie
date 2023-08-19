package com.example.necomovie.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;
    public String[] sections = {"Trending","Upcoming","Popular","Top Rated"};

    public HomeViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

//    //public LiveData<String> getText() {
//        return mText;
//    }
}