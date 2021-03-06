/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Jagnjenje {
    @Id
    Integer id;
    
    @ManyToOne
    private Aktivnost aktivnost;
    
    @ManyToOne
    private Ovca ovca;
    
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private Ovca sheep;
    
    private boolean jelZivo;
    
    private String napomena;
    
    public Jagnjenje(){
        
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

    public Ovca getSheep() {
        return sheep;
    }

    public void setSheep(Ovca jagnje) {
        this.sheep = jagnje;
    }

    public boolean isJelZivo() {
        return jelZivo;
    }

    public void setJelZivo(boolean jelZivo) {
        this.jelZivo = jelZivo;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void setJelZivo(Object valueAt) {
        if (valueAt==null){
            this.jelZivo = false;
        } else{
        this.jelZivo =  Boolean.valueOf(valueAt.toString());
        }
    }

    public void setNapomena(Object value) {
        if (value!=null){
            this.napomena = value.toString();
        }
    }

    @Override
    public String toString() {
        return "Jagnjenje{" + "id=" + id + ", aktivnost=" + aktivnost + ", ovca=" + ovca + ", jagnje=" + sheep + ", jelZivo=" + jelZivo + ", napomena=" + napomena + '}';
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Jagnjenje)
        {
            Integer objectId = ((Jagnjenje) object).id;
            if (this.id!=null && objectId!=null && this.id!=0){
                sameSame = this.id.intValue() == objectId.intValue();
            }
        }

        return sameSame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    public void setId(Object oid) {
        if (oid!=null){
            this.id = Integer.parseInt(oid.toString());
        }
    }
    
    
}
