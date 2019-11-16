package com.example.hw4;

//Bird constructor
class Bird {

    //Declare variables for new bird object
    public String birdName;
    public int zipcode;
    public String personReporting;
    public long timestamp;

    public Bird(){
    }

    //set entered birds to the variables declared
    public Bird(String birdName, int zipcode, String personReporting, long timestamp) {
        this.birdName = birdName;
        this.zipcode = zipcode;
        this.personReporting = personReporting;
        this.timestamp = timestamp;
    }
}
