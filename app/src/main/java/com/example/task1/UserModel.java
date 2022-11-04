package com.example.task1;

import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    public final LiveData<User> userLiveData = new LiveData<>();

    public UserModel() {
        // trigger user load.
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }
}
