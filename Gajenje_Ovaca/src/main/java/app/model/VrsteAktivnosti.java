
package app.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class VrsteAktivnosti {
   // private static HashMap<String, VrsteAktivnosti> map = new HashMap<String, VrsteAktivnosti>();
    
    @Id
    private int Id;
    private String name;
    private Integer color;
   // @OneToOne(mappedBy="vrstaAktivnosti")
   // private Aktivnost owner;
    
    
    public VrsteAktivnosti(){
        this.name = "Припуст";
    }
   

    public VrsteAktivnosti(String name) {
        this.name = name;
    }
    
    public VrsteAktivnosti(String naziv, Color boja){ 
        name = naziv;
        color = boja.getRGB();
    }
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof VrsteAktivnosti){
            VrsteAktivnosti vr = (VrsteAktivnosti) obj;
            return vr.name.equals(this.name);
        }
        return false;
    }
}
