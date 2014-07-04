/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Parenje {
    @Id 
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Ovca ovca;
    @OneToOne(cascade = CascadeType.ALL)
    private Ovca ovan;
    private Date datumParenja;
    private String komentar;
    
    public Parenje(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDatumParenja() {
        return datumParenja;
    }

    public void setDatumParenja(Date datumParenja) {
        this.datumParenja = datumParenja;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    
}
