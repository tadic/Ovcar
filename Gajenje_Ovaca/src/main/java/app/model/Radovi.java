/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Radovi {
        
    @Id
    private int Id;
    @OneToOne
    private Aktivnost aktivnost;
    private String razlog;
    private String staSeRadilo_Nabavilo;
    private String kolicina;
    public Radovi(){
        
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Aktivnost getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Aktivnost aktivnost) {
        this.aktivnost = aktivnost;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public String getStaSeRadilo_Nabavilo() {
        return staSeRadilo_Nabavilo;
    }

    public void setStaSeRadilo_Nabavilo(String staSeRadilo_Nabavilo) {
        this.staSeRadilo_Nabavilo = staSeRadilo_Nabavilo;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public void setRazlog(Object radovi) {
        if (radovi!=null){
            razlog = radovi.toString();
        }
    }

    

}
