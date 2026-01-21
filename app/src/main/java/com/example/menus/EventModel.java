package com.example.menus;

public class EventModel {
    public String eventTitle;
    public String eventDate;
    public String eventLocation;
    public Boolean solved;

    public EventModel(String eventName, String eventDate, String eventLocation) {
        this.eventTitle = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.solved = false;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }
}
