package model;

public class Auditorium{

    private String name;
    private String location;

    public Auditorium(String name, String location){
        this.name = name; 
        this.location = location;
    }

    public getName(){
        return name;
    }

    public getLocation(){
        return location;
    }
}