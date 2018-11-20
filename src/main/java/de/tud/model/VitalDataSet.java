package de.tud.model;

import javax.persistence.*;


@Entity
@Table(name = "vital_data")
public class VitalDataSet extends EntityObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "height")
    private float height;
    @Column(name = "weight")
    private float weight;
    @Column(name = "blood_pressure_first_value")
    private short bloodPressureFirstValue;
    @Column(name = "blood_pressure_second_value")
    private short bloodPressureSecondValue;
    @Column(name = "heart_rate")
    private short heartRate;

    public VitalDataSet(){}

    public VitalDataSet(float height, float weight, short bloodPressureFirstValue,short bloodPressureSecondValue, short heartRate){
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

    public short getBloodPressureFirstValue() {
        return bloodPressureFirstValue;
    }

    public void setBloodPressureFirstValue(short bloodPressureFirstValue) {
        this.bloodPressureFirstValue = bloodPressureFirstValue;
    }

    public short getBloodPressureSecondValue() {
        return bloodPressureSecondValue;
    }

    public void setBloodPressureSecondValue(short bloodPressureSecondValue) {
        this.bloodPressureSecondValue = bloodPressureSecondValue;
    }

    public short getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(short heartRate) {
        this.heartRate = heartRate;
    }

    public float getBMI(){
        return this.getWeight()/this.getHeight()*this.getHeight();
    }
    @Override
    public Long getId() {
        return id;
    }
}
