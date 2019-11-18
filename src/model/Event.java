package model;

import java.util.*;
import java.time.*;

public class Event{
    
    private String name;
    private LocalDate date;
    private int startHour;
    private int endHour;
    private String responsibleTeacher;
    private String responsibleFaculty;
    private int peopleAsist;
    private ArrayList<String> auditorium;

    public Event(String name, LocalDate date, int startHour, int endHour, 
    String responsibleTeacher, String responsibleFaculty, ArrayList<String> auditorium){
        this.name = name;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.responsibleTeacher = responsibleTeacher;
        this.responsibleFaculty = responsibleFaculty;
        this.auditorium = auditorium;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public String getResponsibleTeacher() {
        return responsibleTeacher;
    }

    public String getResponsibleFaculty() {
        return responsibleFaculty;
    }

    public int getPeopleAsist() {
        return peopleAsist;
    }

    public void setPeopleAsist(int peopleAsist) {
        this.peopleAsist = peopleAsist;
    }
    
    public ArrayList<String> getAuditorium() {
        return auditorium;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-15s%s to %-9s%-25s%-25s%-25s",name,date,startHour,endHour,
        responsibleTeacher,responsibleFaculty,auditorium);
    }

    

}