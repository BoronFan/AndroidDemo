package com.example.androiddemo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private int count;

    public HomeViewModel() {
        count = 0;
        mText = new MutableLiveData<>();
        mText.setValue("点击"+count+"次(ViewModel横屏也不会归零)");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void doSomething(){
        count += 1;
        mText.setValue("点击"+count+"次(ViewModel横屏也不会归零)");
    }
    public void reset(){
        count = 0;
        mText.setValue("点击"+count+"次(ViewModel横屏也不会归零)");
    }


}