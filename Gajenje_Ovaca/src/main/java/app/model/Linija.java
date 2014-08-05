/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ivantadic
 */
@Entity
public class Linija {
     @Id
     private Integer id;
     @OneToMany(mappedBy="linija")
     private List<Ovca> ovce;
     String imeLinije;
     String opis;

    public Linija() {
    }
    public Linija(String ime) {
        imeLinije = ime;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImeLinije() {
        return imeLinije;
    }

    public void setImeLinije(String ImeLinije) {
        this.imeLinije = ImeLinije;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Ovca> getO() {
        return ovce;
    }

    public void setO(List<Ovca> o) {
        this.ovce = o;
    }

    public List<Ovca> getOvce() {
        return ovce;
    }

    public void setOvce(List<Ovca> ovce) {
        this.ovce = ovce;
    }
    
    @Override
    public String toString(){
        return imeLinije;
    }
    
}
