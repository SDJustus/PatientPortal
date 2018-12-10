package de.tud.model;

import javax.persistence.*;


@Entity
@Table(name = "vital_data")
public class VitalData extends EntityObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "height")
    private float height;
    @Column(name = "weight")
    private float weight;
    @Column(name = "blood_pressure_first_value")                                            // Blood Pressure ist stored in 2 Variables because it is measured in 2 Numbers ...e.g. 120/90
    private int bloodPressureFirstValue;
    @Column(name = "blood_pressure_second_value")
    private int bloodPressureSecondValue;
    @Column(name = "heart_rate")
    private int heartRate;

    public VitalData(){}

    public VitalData(float height, float weight, short bloodPressureFirstValue, short bloodPressureSecondValue, short heartRate){
        this.height=height;
        this.weight=weight;
        this.bloodPressureFirstValue=bloodPressureFirstValue;
        this.bloodPressureSecondValue =bloodPressureSecondValue;
        this.heartRate=heartRate;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getBloodPressureFirstValue() {
        return bloodPressureFirstValue;
    }

    public void setBloodPressureFirstValue(int bloodPressureFirstValue) {
        this.bloodPressureFirstValue = bloodPressureFirstValue;
    }

    public int getBloodPressureSecondValue() {
        return bloodPressureSecondValue;
    }

    public void setBloodPressureSecondValue(int bloodPressureSecondValue) {
        this.bloodPressureSecondValue = bloodPressureSecondValue;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public float getBMI(){
        return Math.round(this.getWeight()/(this.getHeight()*this.getHeight()));
    }
    @Override
    public Long getId() {
        return id;
    }
}
