package de.rwu.group_up.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class LoginViewModel extends ViewModel implements Serializable {
    private boolean isLoggedIn = false;
    private String userName = "";

    public boolean isLoggedIn() {

        System.out.println(isLoggedIn);
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        System.out.println(isLoggedIn);
        isLoggedIn = loggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }
}
