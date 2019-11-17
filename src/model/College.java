package model;

import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

public class College implements Constants{

    private String name;
    private Auditorium[] auditorium;
    private ArrayList<Event> events;

    public College(String name){
        this.name = name;
        auditorium = new Auditorium[12];
        events = new ArrayList<>();
    }

    public int addAuditorium(String name, String location, int crows){
        boolean flag = false;
        for(int i=0;i<auditorium.length&&flag;i++){
            if(auditorium[i]==null){
                auditorium[i] = new Auditorium(name, location);
                auditorium[i].createChairs(crows);
                flag = true;
            }
        }
        return index;
    }

    public String addEvent(String name, int year, int month, int day, int startHour, int endHour, 
    String responsibleTeacher, String responsibleFaculty){
        LocalDate date = LocalDate.of(year,month,day);
        events.add(new Event(name,date,startHour,endHour,responsibleTeacher,responsibleFaculty));
        return show;
    }

    public boolean checkDate(String audit, int year, int month, int day, int startHour, int endHour){
        LocalDate date = LocalDate.of(year,month,day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean check = true;
        for(Event event: events){
            if(((event.getDate()).format(formatter)).equals(date.format(formatter))){
                ArrayList<String> compare = event.getAuditorium();
                for(String select: compare){ 
                    if(select.equals(audit)){
                        if(startHour>=event.getStartHour()&&startHour<event.getEndHour()){
                            check = false;
                        }else if(endHour>event.getStartHour()){
                            check = false;
                        }
                    }
                  }
                
            }
        }
        return check;
    }

    public void forEvents(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("h");
        for(Event event: events){
            if((now.format(formatter)).equals((event.getDate()).format(formatter))){
                if((int)(now.format(hour))==event.getStartHour()){
                    for(Auditorium temp: auditorium){
                        ArrayList<String> compare = event.getAuditorium();
                        for(String aud: compare){ 
                            if(aud.equals(temp.getName())){
                                temp.setState(busy);
                                event.setPeopleAsist(temp.fillAuditorium());
                            }
                        }
                    }
                }else if((int)(now.format(hour))==event.getEndHour()){
                    for(Auditorium temp: auditorium){
                        ArrayList<String> compare = event.getAuditorium();
                        for(String aud: compare){ 
                            if(aud.equals(temp.getName())){
                                temp.setState(free);
                            }
                        }
                    }
                }
            }
        }
    }

    public void changeChairColumns(int index, int crow, int columns){
        auditorium[index].columns(crow, columns);
    }

    public String getName(){
        return name;
    }
    
}