package com.androidtutz.anushka.lifecycledemo.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class MainActivityViewModel extends AndroidViewModel {


    private int clickCount = 0;


    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<Integer> getInitialCount() {

        countLiveData.setValue(clickCount);

        return countLiveData;
    }

    public void getCurrentCount(int count) {

        if (count == 0) {
            clickCount += 1;
            countLiveData.setValue(clickCount);
        } else {

            countLiveData.setValue(count);
        }


    }

}
