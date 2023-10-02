package com.driver;

public class Insufficientbalance extends Exception{
    Insufficientbalance(String message){
        super(message);
    }
}