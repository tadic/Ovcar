/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Prodaja {
    @Id
    private Integer id; 
    
    @ManyToOne
    private Aktivnost aktivnost;
    @OneToOne
    private Ovca ovca;
    
    private double cenaGrla;
    private double tezina;
    private double cenaKg;
    private String kupac;

    private String napomena;

    public Prodaja() {
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

    public double getCenaGrla() {
        return cenaGrla;
    }

    public void setCenaGrla(double cenaGrla) {
        this.cenaGrla = cenaGrla;
    }

    public double getTezina() {
        return tezina;
    }

    public void setTezina(double tezina) {
        this.tezina = tezina;
    }

    public double getCenaKg() {
        return cenaKg;
    }

    public void setCenaKg(double cenaKg) {
        this.cenaKg = cenaKg;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void setTezina(Object value) {
         if (value != null && !value.toString().equals("")){
           this.tezina = Float.parseFloat(value.toString()) - 0.00f;
        }
    }

    public void setCenaKg(Object value) {
        if (value != null && !value.toString().equals("")){
           this.cenaKg = Float.parseFloat(value.toString()) - 0.00f;
        }   
    }
    public void setCenaGrla(Object value) {
        if (value != null && !value.toString().equals("")){
           this.cenaGrla = Float.parseFloat(value.toString()) - 0.00f;
        }   
    }

    public void setKupac(Object value) {
        if (value != null){
           this.kupac = value.toString();
        }
    }
    public void setNapomena(Object value) {
        if (value != null){
           this.napomena = value.toString();
        }
    }
    
    
}
