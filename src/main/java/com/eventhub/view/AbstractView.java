package main.java.com.eventhub.view;

import java.util.Scanner;

public abstract class AbstractView {
    protected static Scanner scanner = new Scanner(System.in);

    public abstract void show();
}
