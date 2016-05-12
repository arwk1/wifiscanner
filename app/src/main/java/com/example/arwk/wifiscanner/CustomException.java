package com.example.arwk.wifiscanner;

/**
 * Created by arwk on 2016-05-07.
 */
public class CustomException extends Exception
    {
        public CustomException(){
        }
        public CustomException (String message)
            {
            super(message);
            }
        public CustomException (String message, Throwable throwable){
            super(message, throwable);
        }
        public CustomException(Throwable throwable){
            super(throwable);
        }

    }

