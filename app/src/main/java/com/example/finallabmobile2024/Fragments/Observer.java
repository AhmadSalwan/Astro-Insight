package com.example.finallabmobile2024.Fragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class Observer implements LifecycleObserver {
    private final Fragment fragment;

    public Observer(Fragment fragment) {
        this.fragment = fragment;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        // Your code here
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        // Clean up if needed
    }
}