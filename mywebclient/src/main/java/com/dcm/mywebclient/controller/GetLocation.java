package com.dcm.mywebclient.controller;

public class GetLocation {
    String location2=new String("上海");
    String location3=new String("广州");
    String location1=new String("北京");

    public int getDestination(String vehicleId){
        char[] c=new char[2];
        vehicleId.getChars(0,2,c,0);
        System.out.println(c);
        String ch=new String(c);
        if(ch.equals(location1)){
            return 1;
        }
        else if(ch.equals(location2))
            return 2;
        else if(ch.equals(location3))
            return 3;
        else
            return 0;

    }
    public int getServerDestination(String city){
        if(city.equals(location1)){
            return 1;
        }
        else if(city.equals(location2))
            return 2;
        else if(city.equals(location3))
            return 3;
        else
            return 0;
    }
}
