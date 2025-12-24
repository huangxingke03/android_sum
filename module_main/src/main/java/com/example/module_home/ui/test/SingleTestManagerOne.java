package com.example.module_home.ui.test;

public class SingleTestManagerOne {
    private static SingleTestManagerOne singleTestManagerOne;

    public static SingleTestManagerOne instance() {
        if (singleTestManagerOne == null) {
            singleTestManagerOne = new SingleTestManagerOne();
        }
        return singleTestManagerOne;
    }
}
