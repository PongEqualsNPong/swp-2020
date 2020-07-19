package com.praktikum.spapp.exception;

public class ResponseException extends Exception {

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(Exception exception) {
        super(exception);
    }

}
