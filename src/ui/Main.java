package ui;

import java.util.*;
import model.College;

public class Main{

    private Scanner reader;
    private College college;

    public Main(){
        reader = new Scanner(System.in);
    }

    public void init(){
        college = new College("Icesi");
        college.addAuditorium("Manuelita", "location", 10);
        ArrayList<String> aud = new ArrayList<>();
        aud.add("Manuelita");
        college.addEvent("Conferencia",2019,12,31,10,18,"Ricardo","Facultad de Derecho",aud);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.init();
        boolean end = false;
        do{
            main.menu();
            int menu = main.reader.nextInt();
            int temp = 0;
            boolean endAux = false;
            String eventName,responsibleTeacher,responsibleFaculty,auditoriumName,location;
            int rows = 0;
            int year,startHour,endHour,columns;
            int month = 0;
            int day = 0;
            String chair = "";
            boolean next = false;
            boolean aux1 = false;
            boolean aux2 = false;
            if(menu==1){
                do{
                    main.reader.nextLine();
                    ArrayList<String> audit = new ArrayList<String>();
                    do{
                        System.out.printf("%nEvent Name: ");
                        eventName = main.reader.nextLine();
                        if(main.college.checkEventName(eventName)==false)next=true;
                    }while(next==false);
                    next = false;
                    System.out.printf("%nDate: ");
                    do{
                        System.out.printf("%nYear: ");
                        year = main.reader.nextInt();
                        if(main.college.check(year)==true){
                            do{
                                System.out.printf("%nMonth: ");
                                month = main.reader.nextInt();
                                if(main.college.checkMonth(month)==true&&main.college.checkSame(year,month)==true){
                                    do{
                                        System.out.printf("%nDay: ");
                                        day = main.reader.nextInt();
                                        if(main.college.checkDay(month,day)==true&main.college.checkSame(year,month,day)==true){
                                            aux2 = true;
                                            aux1 = true;
                                            next = true;
                                        }
                                    }while(aux2==false);
                                }
                            }while(aux1==false);
                        }
                    }while(next==false);
                    next = false;
                    aux1 = false;
                    do{
                        System.out.printf("%nStart Hour: ");
                        startHour = main.reader.nextInt();
                        System.out.printf("%nEnd Hour: ");
                        endHour = main.reader.nextInt();
                        main.reader.nextLine();
                        int count = 0;
                        do{
                            System.out.printf("%nAuditorium: ");
                            auditoriumName = main.reader.nextLine();
                            if(main.college.checkAuditorium(auditoriumName)==false){
                                if(main.college.checkDate(auditoriumName,year,month,day,startHour,endHour)==true){
                                    System.out.printf("%n<1>Continue or <2>Finish.%n");
                                    temp = main.reader.nextInt();
                                    audit.add(auditoriumName);
                                }else{
                                    if(count==0){
                                        count++;
                                        System.out.printf("%n<1>Change the Hour or <2>Change the Auditorium.%n");
                                        temp = main.reader.nextInt()+10; 
                                    }else{
                                        System.out.printf("%nChange the Auditorium.%n");
                                    }                         
                                }
                            }else{
                                System.out.printf("%nThe Auditorium doesn't exist.%n");
                            }
                            if(temp==1 || temp==11){

                            }else if(temp==2){
                                aux1 = true;
                                next = true;
                            }else if(temp==11){
                                aux1 = true;
                            }else{
                                System.out.printf("%nTry Again.%n");
                            }
                            main.reader.nextLine();
                        }while(aux1==false);
                    }while(next==false);
                    System.out.printf("%nResponsible Teacher: ");
                    responsibleTeacher = main.reader.nextLine();
                    System.out.printf("%nResponsible Faculty: ");
                    responsibleFaculty = main.reader.nextLine();
                    System.out.printf("%s",main.college.addEvent(eventName,year,month,day,startHour,
                    endHour,responsibleTeacher,responsibleFaculty,audit));
                    next = false;
                    do{
                        System.out.printf("%n<1>Add Another Event or <2>Return to the Menu.%n");
                        temp = main.reader.nextInt();
                        if(temp==1){
                            next = true;
                        }else if(temp==2){
                            endAux = true;
                            next = true;
                        }else{
                            System.out.printf("%nTry Again.%n");    
                        }
                    }while(next==false);
                }while(endAux==false);
            }else if(menu==2){
                System.out.printf("%s%n",main.college.showEvents());
                main.college.forEvents();
            }else if(menu==3){
                do{
                    main.reader.nextLine();
                    next = false;
                    do{
                        System.out.printf("%nAuditorium Name: ");
                        auditoriumName = main.reader.nextLine();
                        if(main.college.checkAuditorium(auditoriumName)==true){ 
                            System.out.printf("%nThe Auditorium %s Already Exist. Try Again.%n",auditoriumName);
                        }else{
                            next = true;
                        }
                    }while(next==false);
                    System.out.printf("%nAuditorium Location: ");
                    location = main.reader.nextLine();
                    System.out.printf("%nAuditorium Rows: ");
                    rows = main.reader.nextInt();
                    main.college.addAuditorium(auditoriumName,location,rows);
                    for(int row=0;row<rows;row++){
                        System.out.printf("%nChairs in Row %s: ",main.college.alphabet(row));
                        columns = main.reader.nextInt();
                        main.college.changeChairColumns(auditoriumName,row,columns);
                    }
                    do{
                        System.out.printf("%n<1>Add Another Auditorium or <2>Return to the Menu.%n");
                        temp = main.reader.nextInt();
                        if(temp==1){
                            next = true;
                        }else if(temp==2){
                            endAux = true;
                            next = true;
                        }else{
                            System.out.printf("%nTry Again.%n");    
                        }
                    }while(next==false);
                }while(endAux==false);
            }else if(menu==4){
                System.out.printf("%s%n",main.college.showAuditorium());
            }else if(menu==5){
                do{
                    main.reader.nextLine();
                    next = false;
                    do{
                        System.out.printf("%nAuditorium Name: ");
                        auditoriumName = main.reader.nextLine();
                        if(main.college.checkAuditorium(auditoriumName)==true){
                            System.out.printf("%nInput the chair: ");
                            chair = main.reader.next();
                            char chairCrow = chair.charAt(0);
                            int chairColumn = Integer.parseInt(chair.substring(1));
                            System.out.printf("%s",main.college.reportChair(auditoriumName,chairCrow,chairColumn));
                            next = true;
                        }else{
                            System.out.printf("%nThe Auditorium %s Doesn't Exist. Try Again.%n",auditoriumName);
                        }
                    }while(next==false);
                    do{
                        System.out.printf("%n<1>Report Another Chair or <2>Return to the Menu.%n");
                        temp = main.reader.nextInt();
                        if(temp==1){
                            next = true;
                        }else if(temp==2){
                            endAux = true;
                            next = true;
                        }else{
                            System.out.printf("%nTry Again.%n");    
                        }
                    }while(next==false);
                }while(endAux==false);
            }else if(menu==6){
                do{
                    main.reader.nextLine();
                    next = false;
                    do{
                        System.out.printf("%nAuditorium Name: ");
                        auditoriumName = main.reader.nextLine();
                        if(main.college.checkAuditorium(auditoriumName)==true){ 
                            System.out.printf("%nThe Auditorium Defective Chairs Percent is %s%s%n",
                            main.college.auditDefectivePercent(auditoriumName),"%");
                            next = true;
                        }else{
                            System.out.printf("%nThe Auditorium %s Doesn't Exist. Try Again.%n",auditoriumName);
                        }
                    }while(next==false);
                    do{
                        System.out.printf("%n<1>Search Another Auditorium or <2>Return to the Menu.%n");
                        temp = main.reader.nextInt();
                        if(temp==1){
                            next = true;
                        }else if(temp==2){
                            endAux = true;
                            next = true;
                        }else{
                            System.out.printf("%nTry Again.%n");    
                        }
                    }while(next==false);
                }while(endAux==false);
            }else if(menu==0){
                end = true;
            }else{
                System.out.printf("%nInvalid Function.%n");
            }
        }while(end==false);

    }

    public void menu(){
        System.out.printf("%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n",
        college.getName(),"<1>Add Event.","<2>Show Events.","<3>Add Auditorium.",
        "<4>Show Auditorium.","<5>Report Chair.","<6>Defective Chairs Percent.","<0>Exit");
    }
}