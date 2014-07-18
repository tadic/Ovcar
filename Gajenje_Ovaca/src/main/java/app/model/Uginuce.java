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
public class Uginuce {
    
    @Id
    private Integer id;
    private String razlog;
    @OneToOne
    private Aktivnost a;
    @OneToOne 
    private Ovca o;
    public Uginuce(){
        
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public Aktivnost getA() {
        return a;
    }

    public void setA(Aktivnost a) {
        this.a = a;
    }

    public Ovca getO() {
        return o;
    }

    public void setO(Ovca o) {
        this.o = o;
    }
    
    
    
}
