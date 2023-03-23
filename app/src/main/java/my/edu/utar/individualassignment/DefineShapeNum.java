package my.edu.utar.individualassignment;

import java.util.Random;

public class DefineShapeNum {

    public int count = 0;
    public int num = 0;
    public String ManyColor ="";
    public String OneColor="";

    //set the needed colors
    private String arrManyColor[] = new String[]{
            "#FF0000",
            "#FFFF00",
            "#3ADF00",
            "#0174DF",
            "#0101DF",
            "#A901DB",
            "#FF0040",
            "#900C3F",
            "#1565C0",
            "#1565C0",
            "#FF6F00",
            "#FF6F00",
            "#4A148C",
            "#4A148C"
    };
    private String arrOneColor[] = new String[]{
            "#FA5858",
            "#F3F781",
            "#ACFA58",
            "#2ECCFA",
            "#5858FA",
            "#D0A9F5",
            "#F7819F",
            "#C70039",
            "#1E88E5",
            "#64B5F6",
            "#FF8F00",
            "#FBC02D",
            "#9C27B0",
            "#7B1FA2"

    };

    public int level = 1;
    public int Total_time = 5;
    public int Run_time = Total_time*1000;


    public void randomColor(){
        Random r = new Random();
        int rc = r.nextInt(arrManyColor.length);
        ManyColor = arrManyColor[rc];
        OneColor = arrOneColor[rc];
    }

    // set the level and define the num of shape
    public void setLevel(){
        if(level < 5){
            int i = level + 1;
            count = i;

        }
        else if(level < 10){
            count = 6;
        }
        else if(level < 20){
            count = 7;
        }
        else if(level < 30){
            count = 8;
        }
        else if(level < 40){
            count = 9;
        }
        else{
            count = 10;
        }
        num = count * count;
    }
}
