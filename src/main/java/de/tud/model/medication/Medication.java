package de.tud.model.medication;

import de.tud.model.EntityObject;

import javax.persistence.*;

enum Unit{
        MG, PIECES, ML
        }
@Entity
@Table(name = "medication")
public class Medication extends EntityObject {

    @Id
    @SequenceGenerator(name = "MedicationGenerator", sequenceName = "MedicationSequence", allocationSize = 1)
    @GeneratedValue(generator = "MedicationGenerator")
    private long id;
    private long dmId;

    @Enumerated (EnumType.STRING)
    private Unit unit;
    private float morningDosage;
    private float noonDosage;
    private float afternoonDosage;
    private float nightDosage;
    private String hints;
    private String reason;

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

    public Medication(){

    }

    @Override
    public Long getId() {
        return id;
    }

    public long getDmId() {
        return dmId;
    }

    public void setDmId(long dmId) {
        this.dmId = dmId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getMorningDosage() {
        return morningDosage;
    }

    public void setMorningDosage(float morningDosage) {
        this.morningDosage = morningDosage;
    }

    public float getNoonDosage() {
        return noonDosage;
    }

    public void setNoonDosage(float noonDosage) {
        this.noonDosage = noonDosage;
    }

    public float getAfternoonDosage() {
        return afternoonDosage;
    }

    public void setAfternoonDosage(float afternoonDosage) {
        this.afternoonDosage = afternoonDosage;
    }

    public float getNightDosage() {
        return nightDosage;
    }

    public void setNightDosage(float nightDosage) {
        this.nightDosage = nightDosage;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
