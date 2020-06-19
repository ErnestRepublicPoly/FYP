package sg.edu.rp.c346.round3.DataClasses;

import java.util.Date;

public class GoalEntry {
    private double quadPower;
    private double rackPull;
    private double agility;
    private double weight;
    private Date date;

    public GoalEntry(double quadPower, double rackPull, double agility, double weight, Date date) {
        this.quadPower = quadPower;
        this.rackPull = rackPull;
        this.agility = agility;
        this.weight = weight;
        this.date = date;
    }

    public double getQuadPower() {
        return quadPower;
    }

    public void setQuadPower(double quadPower) {
        this.quadPower = quadPower;
    }

    public double getRackPull() {
        return rackPull;
    }

    public void setRackPull(double rackPull) {
        this.rackPull = rackPull;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
