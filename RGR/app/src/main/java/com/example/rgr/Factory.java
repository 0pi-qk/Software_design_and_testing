package com.example.rgr;

import com.example.rgr.Builder.Builder;
import com.example.rgr.Builder.BuilderGPS;
import com.example.rgr.Builder.BuilderInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factory {


    public static ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Integer","GPS"));
        return list;
    }

    public static Builder getBuilderByName(String name) throws Exception
    {
        if (name.equals(BuilderGPS.typename))
        {
            return new BuilderGPS();
        }
        else if (name.equals(BuilderInteger.typename))
        {
            return new BuilderInteger();
        }
        else
        {
            return null;
        }
    }

}
