package com.example.android.tourguide4;

public class ListCreator {

    private String Loc_name, Loc_Location, Loc_Phno;
    private int Loc_imageId,Loc_imageId2;

    public ListCreator(String name, String Location, int imageId,int imageId2,String phno){

        Loc_name=name;
        Loc_Location=Location;
        Loc_imageId=imageId;
        Loc_Phno=phno;
        Loc_imageId2=imageId2;
    }

    public String getLoc_name(){
        return Loc_name;
    }

    public String getLoc_Location(){
        return Loc_Location;
    }

    public String getLoc_Phno(){
        return Loc_Phno;
    }

    public int getLoc_imageid(){
        return Loc_imageId;
    }
    public int getLoc_imageid2(){
        return Loc_imageId2;
    }

    public boolean hasImage1() {
        return Loc_imageId2 == -1;
    }

    public boolean hasImage2() {
        return Loc_imageId == -1;
    }

    public boolean hasPhno() {
        return Loc_Phno != "null";
    }





}
