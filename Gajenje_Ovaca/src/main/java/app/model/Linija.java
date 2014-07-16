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
public class Linija {
     @Id
     Integer id;
     @OneToOne(mappedBy="linija")
     Ovca o;
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

    public Ovca getO() {
        return o;
    }

    public void setO(Ovca o) {
        this.o = o;
    }
    
    @Override
    public String toString(){
        return imeLinije;
    }
    
}
