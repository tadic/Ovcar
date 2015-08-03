
package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Merenje implements Comparable<Merenje> {
    @Id
    Integer id;
    
    @ManyToOne
    private Aktivnost aktivnost;
    
    @ManyToOne
    private Ovca ovca;
    
    private float tezina;
    
    private String napomena;
    
    public Merenje(){
        
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

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void setNapomena(Object value) {
        if (value!=null){
            this.napomena = value.toString();
        }
    }

    public float getTezina() {
        return tezina;
    }

    public void setTezina(float tezina) {
        this.tezina = tezina;
    }

    @Override
    public String toString() {
        return "Merenje{" + "id=" + id + ", aktivnost=" + aktivnost + ", ovca=" + ovca + ", , jelZivo=, napomena=" + napomena + '}';
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Merenje)
        {
            Integer objectId = ((Merenje) object).id;
            if (this.id!=null && objectId!=null && this.id!=0){
                sameSame = this.id == objectId;
            }
        }

        return sameSame;
    }
    
    public void setId(Object oid) {
        if (oid!=null){
            this.id = Integer.parseInt(oid.toString());
        }
    }

    public int compareTo(Merenje o) {
        return this.getAktivnost().compareTo(o.getAktivnost());
    }
    
    
}
