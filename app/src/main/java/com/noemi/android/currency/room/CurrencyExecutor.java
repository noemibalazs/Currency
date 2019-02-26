package com.noemi.android.currency.room;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CurrencyExecutor {

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;
    private static final Object LOCK = new Object();
    private static CurrencyExecutor sInstance;

    private CurrencyExecutor(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static CurrencyExecutor getExecutor(){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new CurrencyExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }

        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThrad() {
        return mainThread;
    }

    static class MainThreadExecutor implements Executor{

        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);
        }
    }
}
