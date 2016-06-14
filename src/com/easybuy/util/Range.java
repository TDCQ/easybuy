package com.easybuy.util;

/**
 * Created by lenovo on 2016/6/14.
 */
public final class Range {
    public static void range(int[] target){
        int x = target.length;
        for(int i=0; i<x; i++){
            target[i] = i;
        }
    }
}
