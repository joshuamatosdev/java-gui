package org.example;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;


public class Main {
    public static void main(String... args) {
        try {
            UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
            var latch = new CountDownLatch(1);
            javax.swing.SwingUtilities.invokeLater(() ->
            {
                new CarInventory();
                latch.countDown();
            });
            latch.await();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}