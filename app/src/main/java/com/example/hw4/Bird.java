package com.example.hw4;

class Bird {

    public String birdName;
    public int zipcode;
    public String personReporting;
    public long timestamp;

    public Bird(){
    }

    public Bird(String birdName, int zipcode, String personReporting, long timestamp) {
        this.birdName = birdName;
        this.zipcode = zipcode;
        this.personReporting = personReporting;
        this.timestamp = timestamp;
    }
}
