package com.tzarr.client;

import java.util.ArrayDeque;
import java.util.Deque;

public final class CpsTracker {

    private static final Deque<Long> CLICKS = new ArrayDeque<>();

    private CpsTracker() {}

    public static void click() {
        long now = System.currentTimeMillis();
        CLICKS.addLast(now);
        trim(now);
    }

    public static int getCps() {
        long now = System.currentTimeMillis();
        trim(now);
        return CLICKS.size();
    }

    public static void reset() {
        CLICKS.clear();
    }

    private static void trim(long now) {
        while (!CLICKS.isEmpty() &&
               now - CLICKS.peekFirst() > 1000L) {
            CLICKS.removeFirst();
        }
    }
}
