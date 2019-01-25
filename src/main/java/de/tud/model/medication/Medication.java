package de.tud.model.medication;

import de.tud.model.EntityObject;

import javax.persistence.*;

@Entity
@Table(name = "medication")
public class Medication extends EntityObject {

    /**
     * Holds the ID of the persistent Medication object.
     */
    @Id
    @SequenceGenerator(name = "MedicationGenerator", sequenceName = "MedicationSequence", allocationSize = 1)
    @GeneratedValue(generator = "MedicationGenerator")
    private long id;

    /**
     * Holds the ID value of the dummy medication.
     */
    private long dmId;

    /**
     * Holds the Unit type value of the Medication object.
     */
    @Enumerated (EnumType.STRING)
    private Unit unit;

    /**
     * Holds the value of a possible morning dosage of a Medication object.
     */
    private float morningDosage;

    /**
     * Holds the value of a possible noon dosage of a Medication object.
     */
    private float noonDosage;

    /**
     * Holds the value of a possible afternoon dosage of a Medication object.
     */
    private float afternoonDosage;

    /**
     * Holds the value of a possible night dosage of a Medication object.
     */
    private float nightDosage;

    /**
     * Holds a value for a possible hint for a medication.
     */
    private String hints;
    /**
     * Holds a value for a reason for a medication .
     */
    private String reason;

    /**
     * Constructor of Medication.
     * @param dmId
     * @param unit
     * @param morningDosage
     * @param noonDosage
     * @param afternoonDosage
     * @param nightDosage
     * @param hints
     * @param reason
     */
    public Medication(long dmId, Unit unit, float morningDosage, float noonDosage, float afternoonDosage, float nightDosage, String hints, String reason) {
        this.dmId = dmId;
        this.unit = unit;
        this.morningDosage = morningDosage;
        this.noonDosage = noonDosage;
        this.afternoonDosage = afternoonDosage;
        this.nightDosage = nightDosage;
        this.hints = hints;
        this.reason = reason;
    }

    /**
     * Secondary (empty) constructor of Medication.
     */
    public Medication(){

    }

    /**
     * Returns the ID of the persistent Medication object.
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Returns the matching value of the dummy medication.
     * @return
     */
    public long getDmId() {
        return dmId;
    }

    /**
     * Sets the value for a dummy medication.
     * @param dmId
     */
    public void setDmId(long dmId) {
        this.dmId = dmId;
    }

    /**
     * Returns the Unit type value of the Medication object.
     * @return
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the Unit type for the Medication object.
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Returns the value of the possible morning dosage.
     * @return
     */
    public float getMorningDosage() {
        return morningDosage;
    }

    /**
     * Sets the value of a morning dosage.
     * @param morningDosage
     */
    public void setMorningDosage(float morningDosage) {
        this.morningDosage = morningDosage;
    }

    /**
     * Returns the value of the possible noon dosage.
     * @return
     */
    public float getNoonDosage() {
        return noonDosage;
    }

    /**
     * Sets the value of a noon dosage.
     * @param noonDosage
     */
    public void setNoonDosage(float noonDosage) {
        this.noonDosage = noonDosage;
    }

    /**
     * Returns the value of the possible afternoon dosage.
     * @return
     */
    public float getAfternoonDosage() {
        return afternoonDosage;
    }

    /**
     * Sets the value of an afternoon dosage.
     * @param afternoonDosage
     */
    public void setAfternoonDosage(float afternoonDosage) {
        this.afternoonDosage = afternoonDosage;
    }

    /**
     * Returns the value of the possible night dosage.
     * @return
     */
    public float getNightDosage() {
        return nightDosage;
    }

    /**
     * Sets the value of a night dosage.
     * @param nightDosage
     */
    public void setNightDosage(float nightDosage) {
        this.nightDosage = nightDosage;
    }

    /**
     * Returns the hints value of the Medication object.
     * @return
     */
    public String getHints() {
        return hints;
    }

    /**
     * Sets the hints value of the Medication object.
     * @param hints
     */
    public void setHints(String hints) {
        this.hints = hints;
    }

    /**
     * Returns the reason value of the Medication object.
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason value of the Medication object.
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
