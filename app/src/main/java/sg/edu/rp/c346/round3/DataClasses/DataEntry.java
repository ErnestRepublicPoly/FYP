package sg.edu.rp.c346.round3.DataClasses;

import java.util.Date;

public class DataEntry {
    private double SystolicPressure;
    private double DiastolicPressure;
    private double BodyFat;
    private double QuadPower;
    private double RackPull;
    private double Agility;
    private double Weight;
    private double Height;
    private Date date;

    public DataEntry(double SystolicPressure, double DiastolicPressure, double BodyFat, double QuadPower, double RackPull, double Agility, double Weight, double Height, Date date ) {
        this.SystolicPressure = SystolicPressure;
        this.DiastolicPressure = DiastolicPressure;
        this.BodyFat = BodyFat;
        this.QuadPower = QuadPower;
        this.RackPull = RackPull;
        this.Agility = Agility;
        this.Weight = Weight;
        this.Height = Height;
        this.date = date;
    }


    public double getSystolicPressure() {
        return SystolicPressure;
    }

    public void setSystolicPressure(double SystolicPressure) {
        this.SystolicPressure = SystolicPressure;
    }

    public double getDiastolicPressure() {
        return DiastolicPressure;
    }

    public void setDiastolicPressure(double DiastolicPressure) {
        this.DiastolicPressure = DiastolicPressure;
    }

    public double getBodyFat() {
        return BodyFat;
    }

    public void setBodyFat(double BodyFat) {
        this.BodyFat = BodyFat;
    }

    public double getQuadPower() {
        return QuadPower;
    }

    public void setQuadPower(double QuadPower) {
        this.QuadPower = QuadPower;
    }

    public double getRackPull() {
        return RackPull;
    }

    public void setRackPull(double RackPull) {
        this.RackPull = RackPull;
    }

    public double getAgility() {
        return Agility;
    }

    public void setAgility(double Agility) {
        this.Agility = Agility;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double w) {
        this.Weight = Weight;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double h) {
        this.Height = Height;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
