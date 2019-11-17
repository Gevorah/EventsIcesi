package model;

import java.util.*;

public class Auditorium implements Constants{

    private String name;
    private String location;
    private String state;
    private Chair[][] chairs;

    public Auditorium(String name, String location){
        this.name = name; 
        this.location = location;
    }

    public void createChairs(int crows){
        chairs = new Chair[crows][5];
    }

    public void columns(int index, int column){
        chairs[index] = new Chair[column];
        for(int i=0;i<index;i++){
            chairs[index][i] = operational;
        }
    }

    public String reportChair(char crow, char column){
        String show = "The chair has been reported as defective.";
        int index = 0;
        if(crow>90){
            index = crow-71;
        }else{
            index = crow-65;
        }
        try {
            chairs[index][column].setState(defective);
        } catch (Exception e) {
            show = "The chair doesn't exist.";
        }
        return show;
    }

    public int defectivePercent(){
        int f = 0;
        double percent = 0;
        for(Chair[] temp: chairs){
            for(Chair select: temp){
                if(select.getState().equals(defective)){
                    f++;
                }
            }   
        }
        percent = (double)(f/numberOfChairs());
        return (int)percent*100;
    }

    public int fillAuditorium(){
        Random r = new Random();
        int numberOfChairs = numberOfChairs();
        int i = (int)(r.nextInt()*numberOfChairs+1);
        int crows = chairs.length-1;
        int columns = 0;
        int indexCrow = 0;
        int indexColumn = 0;
        while(i>0){
            indexCrow = (int)(r.nextInt()*crows+0);
            columns = chairs[index].length-1;
            indexColumn = (int)(r.nextInt()*columns+0);
            if(chairs[indexCrow][indexColumn].isFree()==true&&
            chairs[indexCrow][indexColumn].getState().equals(operational)){
                chairs[indexCrow][indexColumn].setFree(false);
                i--;
            }
        }
        return i;
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

    public char alphabet(int index){
        int character = 65;
        if(index>25)character=97;
        return (char)(character+index);
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
}