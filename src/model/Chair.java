package model;

public class Chair implements Constants{

    private String state;
    private boolean free;

    public Chair(String state){
        state = operational;
    }

    public String getState(){
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}