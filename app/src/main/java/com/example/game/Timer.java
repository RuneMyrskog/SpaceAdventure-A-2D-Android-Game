package com.example.game;

class Timer {
    /**
     * Elapsed time in seconds.
     */
    private static int timeSeconds;
    /**
     * Equivalent to 1 update cycle of the program. (60 cycles per realtime second).
     */
    private static int ticker;

    public Timer() {
    }

    /**
     * Moves the Timer one timestep forward.
     * Increments timeSeconds by 1 once 1 second has passed.
     */
    static void timeStep() {
        ticker++;
        if (ticker == 60) {
            ticker = 0;
            timeSeconds++;
        }
    }

    static void resetTimer() {
        ticker = 0;
        timeSeconds = 0;
    }

    static int getTimeSeconds() {
        return timeSeconds;
    }
}
