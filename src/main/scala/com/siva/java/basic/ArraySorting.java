package com.siva.java.basic;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.System.out;

/**
 * Created by sivakumaran on 12/22/2017.
 */
public class ArraySorting {
    public static void main(String args[]){
        Integer[] arr = {13, 7, 6, 45, 21, 9, 101, 102};
        Arrays.sort(arr);
        Arrays.sort(arr, Collections.reverseOrder());
        for(int a:arr)
            out.println(a);
    }
}
