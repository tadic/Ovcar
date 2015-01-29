/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Parenje {
    @Id 
    private Integer id;
        
    @ManyToOne(fetch= FetchType.EAGER)
    private Aktivnost aktivnost;
    @ManyToOne
    private Ovca ovca;
    @ManyToOne
    private Ovca ovan;

    private String tip;
    private String napomena;
    
    public Parenje(){
        
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

    public Ovca getOvca() {
        return ovca;
    }

    public void setOvca(Ovca ovca) {
        this.ovca = ovca;
    }

    public Ovca getOvan() {
        return ovan;
    }

    public void setOvan(Ovca ovan) {
        this.ovan = ovan;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

   
}
