package model;

import java.util.*;

public class Auditorium implements Constants{

    private String name;
    private String location;
    private String state;
    private Chair[][] chairs;

    public Auditorium(String name, String location, String state){
        this.name = name; 
        this.location = location;
        this.state = state;
    }

    public void createChairs(int rows){
        chairs = new Chair[rows][5];
    }

    public void columns(int index, int column){
        chairs[index] = new Chair[column];
    }

    public String reportChair(char row, int column){
        String show = "The chair has been reported as defective.";
        int index = 0;
        if(row>90){
            index = row-71;
        }else{
            index = row-65;
        }
        try {
            chairs[index][column].setState(defective);
        } catch (Exception e) {
            show = "The chair doesn't exist.";
        }
        return show;
    }

    public float defectivePercent(){
        int f = 0;
        for(Chair[] temp: chairs){
            for(Chair select: temp){
                if(select!=null&&select.getState().equals(defective)){
                    f++;
                }
            }   
        }
        System.out.println(f);
        float percent = (float)((f*100)/numberOfChairs());
        return percent;
    }

    public int numberOfChairs(){
        int count = 0;
        for(int i=0;i<chairs.length;i++){
            for(int j=0;j<chairs[i].length;j++){
                count++;
            }
        }
        return count;
    }

    public int fillAuditorium(){
        Random r = new Random();
        int numberOfChairs = numberOfChairs();
        int i = (int)(r.nextInt()*numberOfChairs+1);
        int rows = chairs.length-1;
        int columns = 0;
        int indexrow = 0;
        int indexColumn = 0;
        while(i>0){
            indexrow = (int)(r.nextInt()*rows+0);
            columns = chairs[indexrow].length-1;
            indexColumn = (int)(r.nextInt()*columns+0);
            if(chairs[indexrow][indexColumn].isFree()==true&&
            (chairs[indexrow][indexColumn].getState()).equals(operational)){
                chairs[indexrow][indexColumn].setFree(false);
                i--;
            }
        }
        return i;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s",name,location,state);
    }

}