package de.tud.model.welfare;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Welfare {

    public enum Strength{
        WEAK{
            @Override
            public String toString() {
                return "Gut";
            }
        }, MIDDLE{
            @Override
            public String toString() {
                return "Mäßig";
            }
        }, SEVERE{
            @Override
            public String toString() {
                return "Schecht";
            }
        }
    }
    @Enumerated(EnumType.STRING)
    private Strength strength;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "symptom_id")
    protected long welfareId;

    public Welfare(){}

    protected Welfare(Strength strength){
        this.strength=strength;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName()+": "+strength.toString();
    }

    public Strength getStrength() {
        return strength;
    }

    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    public long getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(long welfareId) {
        this.welfareId = welfareId;
    }
}
