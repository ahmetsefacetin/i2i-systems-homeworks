package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerLoggings {
    private static final Logger logger = LogManager.getLogger(MyTimerLoggings.class);

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.debug("second log");
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.info("minute log");
            }
        }, 0, 60000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.error("hour log");
            }
        }, 0, 3600000);
    }
}
