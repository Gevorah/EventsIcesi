package model;

public class Event{
    
    private String name;
    private Date date;
    private int startHour;
    private int endHour;
    private String responsibleTeacher;
    private String responsibleFaculty;
    private int peopleAsist;
    private ArrayList<String> auditorium;

    public Event(String name, Date date, int startHour, int endHour, 
    String responsibleTeacher, String responsibleFaculty, int peopleAsist){
        this.name = name;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.responsibleTeacher = responsibleTeacher;
        this.responsibleFaculty = responsibleFaculty;
        this.peopleAsist = peopleAsist;
        auditorium = new ArrayList<>();
    }

    public void addAuditorium(String name){
        auditorium.add(name);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
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

}