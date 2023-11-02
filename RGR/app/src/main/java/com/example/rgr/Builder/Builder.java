package com.example.rgr.Builder;

import com.example.rgr.Comparator.Comparator;

import com.example.rgr.Comparator.Comparator;


public interface Builder
{
    Object createObject();
    Object readObject();
    Object clone();
    Object parseObject(String ss);
    Comparator getComparator();
    String getName();

}
