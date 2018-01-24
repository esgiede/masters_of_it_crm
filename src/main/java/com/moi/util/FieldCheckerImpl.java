package com.moi.util;

import org.springframework.stereotype.Component;

@Component
public class FieldCheckerImpl implements FieldChecker {

    public FieldCheckerImpl(){};

    public boolean checkIfEmpty(String s){
        if(s == null || s.matches("^\\s*$") || s.startsWith(" ")){
            return false;
        }
        return true;
    }

    public boolean checkLength(String s, int min, int max){
        if (s.length() > max || s.length() < min){
            return false;
        }
        return true;
    }

    public boolean checkLengthEqual(String s, int length){
        if(s.length() != length){
            return false;
        }
        return true;
    }

}
