package com.moi.util;

public interface FieldChecker {
    boolean checkIfEmpty(String s);
    boolean checkLengthEqual(String s, int length);
    boolean checkLength(String s, int min, int max);
}
