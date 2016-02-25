package io.github.hkusu.android_mvc_sample.common;

import android.util.Log;

import java.util.HashMap;

// 各モデルの参照を保持するクラス

public class ModeLocator {
    private final static String TAG = ModeLocator.class.getSimpleName();

    private ModeLocator() {}

    private static HashMap<Class, Object> showcase = new HashMap<>();

    public static void register(Class t) {
        try {
            showcase.put(t, t.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static <T> T get(Class<T> t) {
        Object obj = showcase.get(t);
        if (obj == null) {
            Log.e(TAG, t.getSimpleName() + " is not registered!");
            return null;
        }

        T ret = null;
        try {
            ret = (T) obj;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
