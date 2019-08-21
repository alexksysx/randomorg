package me.alexksysx.randomorg.exception;

public class RandomorgException extends Exception {
    public RandomorgException(int errorCode, String message) {
        super("Error: " + errorCode + ", " + message);
    }
}
