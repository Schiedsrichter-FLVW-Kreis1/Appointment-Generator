package de.simontenbeitel.srk1.termingenerator;

public class Appointment {

    final String dayOfTheWeek;
    final String date;
    final String time;
    final String description;
    final String location;

    public Appointment(String dayOfTheWeek, String date, String time, String description, String location) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
    }

}
