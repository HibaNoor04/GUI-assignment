package com.example.guiassignment;
public class Person {
    private String name;
    private String fatherName;
    private String cnic;
    private String dateOfBirth;
    private String gender;
    private String city;
    private String imagePath;

    public Person(String name, String fatherName, String cnic, String dateOfBirth, String gender, String city) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.city = city;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getCnic() {
        return cnic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getImagePath() {
        return imagePath;
    }
}
