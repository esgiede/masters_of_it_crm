package com.moi.util;

import java.util.Set;

public interface FieldChecker {
    boolean checkIfEmpty(String s);
    boolean checkIfEmpty(Set<String> set);
    boolean checkLengthEqual(String s, int length);
    boolean checkLength(String s, int min, int max);
}
