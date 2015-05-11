/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class NabavkaOvaca {
    @Id
    private Integer id;
    
    @ManyToOne
    private Aktivnost aktivnost;
    
    @OneToOne
    private Ovca sheep;
    private float cena;
    private String napomena;
    
    public NabavkaOvaca(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aktivnost getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Aktivnost aktivnost) {
        this.aktivnost = aktivnost;
    }

    public Ovca getSheep() {
        return sheep;
    }

    public void setSheep(Ovca sheep) {
        this.sheep = sheep;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }
    
    public void setCena(Object cena) {
        if (cena !=null){
           this.cena = Float.parseFloat(cena.toString()) - 0.001f;
        }
    }
    public String getNapomena() {
        if (napomena==null){
            return "";
        }
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    public void setNapomena(Object napomena){
        if (napomena!=null){
            this.napomena = napomena.toString();
        }
    }
    
}
