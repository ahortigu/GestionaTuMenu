package com.aihg.gestionatumenu.database.util;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil {
    public static <T> T getOrAwaitValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
            }
        };

        addObserver(liveData, observer);

        try{
            latch.await(2, TimeUnit.SECONDS); // if time elapses data.length will be 0
        } catch(InterruptedException ex){
            throw new InterruptedException("latch.await() failure");
        }

        if(data.length > 0){
            return (T) data[0];
        } else {
            return null;
        }
    }

    private static <T> void addObserver(LiveData<T> liveDataQuery, Observer observer){
        Handler handler = new Handler(Looper.getMainLooper()); //main thread queue
        handler.post(new Runnable() { // task to run on the main thread queue
            @Override
            public void run() {
                liveDataQuery.observeForever(observer);
            }
        });
    }
}
