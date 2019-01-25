package de.tud.model;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "vital_data")
public class VitalData extends EntityObject {

    /**
     * Holds the ID of the persistent VitalData object.
     */
    @Id
    @SequenceGenerator(name = "VitalDataGenerator", sequenceName = "VitalDataSequence", allocationSize = 1)
    @GeneratedValue(generator = "VitalDataGenerator")
    private long id;

    /**
     * Holds the height value of the VitalData object.
     */
    @Column(name = "height")
    private float height;

    /**
     * Holds the weight value of the VitalData object.
     */
    @Column(name = "weight")
    private float weight;

    /**
     * Holds the first blood pressure value of the VitalData object.
     */
    @Column(name = "blood_pressure_first_value")                                            // Blood Pressure ist stored in 2 Variables because it is measured in 2 Numbers ...e.g. 120/90
    private int bloodPressureFirstValue;

    /**
     * Holds the second blood pressure value of the VitalData object.
     */
    @Column(name = "blood_pressure_second_value")
    private int bloodPressureSecondValue;

    /**
     * Holds the heart rate value of the VitalData object.
     */
    @Column(name = "heart_rate")
    private int heartRate;

    /**
     * Secondary (empty) constructor of VitalData.
     */
    public VitalData(){}

    /**
     * Primary constructor of VitalData.
     * @param height
     * @param weight
     * @param bloodPressureFirstValue
     * @param bloodPressureSecondValue
     * @param heartRate
     */
    public VitalData(float height, float weight, short bloodPressureFirstValue, short bloodPressureSecondValue, short heartRate){
        this.height=height;
        this.weight=weight;
        this.bloodPressureFirstValue=bloodPressureFirstValue;
        this.bloodPressureSecondValue =bloodPressureSecondValue;
        this.heartRate=heartRate;
    }

    /**
     * Returns the height value of the VitalData object.
     * @return value of height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the height value of the VitalData object.
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Returns the weight value of the VitalData object.
     * @return value of weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Sets the weight value of the VitalData object.
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Returns the bloodPressureFirstValue value of the VitalData object.
     * @return value of bloodPressureFirstValue
     */
    public int getBloodPressureFirstValue() {
        return bloodPressureFirstValue;
    }

    /**
     * Sets the bloodPressureFirstValue value of the VitalData object.
     * @param bloodPressureFirstValue
     */
    public void setBloodPressureFirstValue(int bloodPressureFirstValue) {
        this.bloodPressureFirstValue = bloodPressureFirstValue;
    }

    /**
     * Returns the bloodPressureSecondValue value of the VitalData object.
     * @return value of bloodPressureSecondValue
     */
    public int getBloodPressureSecondValue() {
        return bloodPressureSecondValue;
    }

    /**
     * Sets the bloodPressureSecondValue value of the VitalData object.
     * @param bloodPressureSecondValue
     */
    public void setBloodPressureSecondValue(int bloodPressureSecondValue) {
        this.bloodPressureSecondValue = bloodPressureSecondValue;
    }

    /**
     * Returns the heartRate of the VitalData object.
     * @return value of heartRate
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * Sets the heartRate of the VitalData object.
     * @param heartRate
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * Returns the calculated Body Mass Index value.
     * @return calculated value of BMI
     */
    public float getBMI(){
        return (float) Math.round((this.getWeight()/(this.getHeight()*this.getHeight()))*100)/100;
    }

    /**
     * Returns the ID of the persistent VitalData object.
     * @return value of id
     */
    @Override
    public Long getId() {
        return id;
    }
}
