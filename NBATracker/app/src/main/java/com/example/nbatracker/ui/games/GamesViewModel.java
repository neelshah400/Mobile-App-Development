package com.example.nbatracker.ui.games;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GamesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GamesViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is games fragment");

    }

    public LiveData<String> getText() {

        return mText;

    }

}