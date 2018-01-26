package com.moi.util;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Component
public class FieldCheckerImpl implements FieldChecker {

    public FieldCheckerImpl(){}

    public boolean checkIfEmpty(String s){
        if(s == null || s.matches("^\\s*$") || s.startsWith(" ")){
            return false;
        }
        return true;
    }

    public boolean checkIfEmpty(Set<String> set){
        for (Iterator<String> i = set.iterator(); i.hasNext();) {
            String item = i.next();
            if(!checkIfEmpty(item));{
                return false;
            }
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
