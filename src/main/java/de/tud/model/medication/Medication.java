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
    private double amount;
    @Enumerated (EnumType.STRING)
    private Unit unit;
    private String dosage;


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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
