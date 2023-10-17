package model;

import java.sql.Date;

public class Student extends Person{


    private float pointMath;

    private float pointPhysical;


    public Student(int id, String name, Date dateOfBirth, String address, float pointMath, float pointPhysical) {
        super(id, name, dateOfBirth, address);
        this.pointMath = pointMath;
        this.pointPhysical = pointPhysical;
    }

    public Student() {
    }

    public float getPointMath() {
        return pointMath;
    }

    public void setPointMath(float pointMath) {
        this.pointMath = pointMath;
    }

    public float getPointPhysical() {
        return pointPhysical;
    }

    public void setPointPhysical(float pointPhysical) {
        this.pointPhysical = pointPhysical;
    }

    @Override
    public String toString() {
        return "Demo";
    }

    @Override
    public String demo() {
        return super.demo();
    }

    @Override
    public void displayInformation() {
        System.out.println("Student");
    }

    @Override
    public String getName() {
        return "Student name";
    }
}