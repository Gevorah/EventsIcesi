package model;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class College implements Constants{

    private String name;
    private Auditorium[] auditorium;
    private ArrayList<Event> events;

    public College(String name){
        this.name = name;
        auditorium = new Auditorium[15];
        events = new ArrayList<>();
    }

    public void addAuditorium(String name, String location, int rows){
        boolean flag = false;
        int i = 0;
        while(i<auditorium.length&&flag==false){
            if(auditorium[i]==null){
                auditorium[i] = new Auditorium(name,location,free);
                auditorium[i].createChairs(rows);
                flag = true;
            }
            i++;
        }
    }

    public String addEvent(String name, int year, int month, int day, int startHour, int endHour, 
    String responsibleTeacher, String responsibleFaculty, ArrayList<String> audit){
        LocalDate date = LocalDate.of(year,month,day);
        events.add(new Event(name,date,startHour,endHour,responsibleTeacher,responsibleFaculty,audit));
        return String.format("The Event has been Created");
    }

    public boolean checkDate(String audit, int year, int month, int day, int startHour, int endHour){
        LocalDate date = LocalDate.of(year,month,day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean check = true;
        for(Event event: events){
            if(((event.getDate()).format(formatter)).equals(date.format(formatter))){
                ArrayList<String> compare = event.getAuditorium();
                if(startHour>=event.getStartHour()&&startHour<event.getEndHour()){
                    for(String select: compare){ 
                        if(select.equals(audit)){
                            check = false;
                        }
                    }
                }else if(endHour>event.getStartHour()){
                    for(String select: compare){ 
                        if(select.equals(audit)){
                            check = false;
                        }
                    }
                }
            }
        }
        return check;
    }

    public String reportChair(String audit, char crow, int column){
        String show = "";
        for(Auditorium temp: auditorium){
            if(temp!=null&&audit.equalsIgnoreCase(temp.getName()))show=temp.reportChair(crow,column);
        }
        return show;
    }

    public void forEvents(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("h");
        for(Event event: events){
            if((now.format(formatter)).equals((event.getDate()).format(formatter))){
                if(Integer.parseInt(now.format(hour))==event.getStartHour()){
                    for(Auditorium temp: auditorium){
                        ArrayList<String> compare = event.getAuditorium();
                        for(String aud: compare){ 
                            if(temp!=null&&aud.equals(temp.getName())){
                                temp.setState(busy);
                                event.setPeopleAsist(temp.fillAuditorium());
                            }
                        }
                    }
                }else if(Integer.parseInt(now.format(hour))==event.getEndHour()){
                    for(Auditorium temp: auditorium){
                        ArrayList<String> compare = event.getAuditorium();
                        for(String aud: compare){ 
                            if(temp!=null&&aud.equals(temp.getName()))temp.setState(free);
                        }
                    }
                }
            }
        }
    }

    public void changeChairColumns(String name, int row, int columns){
        for(Auditorium temp: auditorium){
            if(temp!=null&&name.equalsIgnoreCase(temp.getName())){
                temp.columns(row,columns);
            }
        }
    }

    public String auditDefectivePercent(String name){
       float percent = 0;
        for(Auditorium temp: auditorium){
            if(temp!=null&&name.equalsIgnoreCase(temp.getName())){
                percent=temp.defectivePercent();
            }
        }
        return String.format("%s",percent);
    }

    public boolean checkEventName(String eventName){
        boolean exist = false;
        for(Event event: events){
            if(event.getName().equalsIgnoreCase(eventName))exist=true;
        }
        return exist;
    }

    public boolean checkAuditorium(String auditoriumName){
        boolean exist = false;
        for(Auditorium temp: auditorium){
            if(temp!=null&&temp.getName().equalsIgnoreCase(auditoriumName))exist=true;
        }
        return exist;
    }

    public boolean check(int year){
        boolean check = true;
        LocalDate now = LocalDate.now();
        DateTimeFormatter y = DateTimeFormatter.ofPattern("yyyy");
        if(year<Integer.parseInt(now.format(y)))check=false;
        return check;
    }

    public boolean checkSame(int year, int month){
        boolean check = true;
        LocalDate now = LocalDate.now();
        DateTimeFormatter y = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter m = DateTimeFormatter.ofPattern("MM");
        LocalDate date = LocalDate.of(year,month,1);
        if(date.format(y).equals(now.format(y))){
            if(Integer.parseInt(date.format(m))<Integer.parseInt(now.format(m))){
                check = false;
            }
        }
        return check;
    }

    public boolean checkSame(int year, int month, int day){
        boolean check = true;
        LocalDate now = LocalDate.now();
        DateTimeFormatter y = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter m = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd");
        LocalDate date = LocalDate.of(year,month,1);
        if(date.format(y).equals(now.format(y))&&date.format(m).equals(now.format(m))){
            if(Integer.parseInt(date.format(d))<Integer.parseInt(now.format(d))){
                check = false;
            }
        }
        return check;
    }

    public String showAuditorium(){
        String show = "";
        for(Auditorium temp: auditorium){
            if(temp!=null)show+="\n"+temp.toString();
        }
        return String.format("%-20s%-20s%-20s%s","Name","Location","State",show);
    }

    public String showEvents(){
        String show = "";
        for(Event temp: events){
            show+="\n"+temp.toString();
        }
        return String.format("%-20s%-15s%-15s%-25s%-25s%-25s%s","Name","Date","Time",
        "Responsible Teacher","Responsible Faculty","Auditorium",show);
    }

    /**
	*	This method check if the given month is check acording to the gregorian calendar.<br>
	*	<b>pre:</b> The month must be check acording to the gregorian calendar.<br>
	*	<b>post:</b> The given month is check or incorrect.<br>
	*	@param month The month to evaluate.
	*	@throws Exception If the month doesn't corresponds to the given month.<br>
	*	@return True if the given month corresponds to the months in the calendar or false if the month is incorrect.<br>
	*/
	public boolean checkMonth(int month){
        boolean check = false;
		if(month>0&&month<=12){
			check = true;
		}
		return check;
	}

    /**
	*	This method check if the given day corresponds to the given month.<br>
	*	<b>pre:</b> The day must be a integer number.<br>
	*	<b>post:</b> The given day is check or incorrect for the given month.<br>
	*	@param month The month.
	*	@param day The day to evaluate.
	*	@throws Exception If the day doesn't corresponds to the given month.<br>
	*	@return True if the given day corresponds to the given month or false if is incorrect.<br>
	*/
	public boolean checkDay(int month, int day){
		boolean check = false;
		if(month==2){
			if(day>0 && day<30){
				check = true;
			}
		}else if(month==1 || month==3 || month==5 ||
		month==7 || month==8 || month==10 || month==12){
			if(day>0 && day<=31){
				check = true;
			}
		}else{
			if(day>0 && day<31){
				check = true;
			}
		}	
		return check;
    }
    
    public char alphabet(int index){
        int character = 65;
        if(index>25)character=97;
        return (char)(character+index);
    }

    public String getName(){
        return name;
    }
    
}