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
public class Vakcinacija {
    @Id
    private Integer id;
    
    @ManyToOne(fetch= FetchType.EAGER)
    private Aktivnost aktivnost;
    
    @ManyToOne
    private Ovca ovca;
    private Boolean jelRedovno;
    private String razlog;
    private String sredstvo;
    private String napomena;
    
    public Vakcinacija(){
        
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

    public void setOvca(Ovca sheep) {
        this.ovca = sheep;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public String getSredstvo() {
        return sredstvo;
    }

    public void setSredstvo(String sredstvo) {
        this.sredstvo = sredstvo;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void setNapomena(Object value) {
        if (value!=null) {
            this.napomena = value.toString();
        }
    }

    public Boolean getJelRedovno() {
        return jelRedovno;
    }

    public void setJelRedovno(Boolean jelRedovno) {
        this.jelRedovno = jelRedovno;
    }
    
    
    
}
