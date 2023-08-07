package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       StringListImpl stringList=new StringListImpl(2);
        System.out.println("stringList.add(1) = " + stringList.add(1));
        System.out.println("stringList.toArray() = " + Arrays.toString(stringList.toArray()));
        System.out.println("stringList.add(2) = " + stringList.add(2));
        System.out.println("stringList.toArray() = " + Arrays.toString(stringList.toArray()));
        System.out.println("stringList.add(3) = " + stringList.add(3));
        System.out.println("stringList.toArray() = " + Arrays.toString(stringList.toArray()));
        System.out.println("stringList.size() = " + stringList.size());
    }
}