
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
    
   
    
   /* public static List<VrsteAktivnosti> getAll(){
        List<VrsteAktivnosti> list = new ArrayList<VrsteAktivnosti>();
        for (VrsteAktivnosti vr: map.values()){
            list.add(vr);
        }
        return list;
    }*/
    
 /*   public void setIdAndCOlor(){
        EbeanServer server = NewJFrame.getServer();
        VrsteAktivnosti vr = server.find(VrsteAktivnosti.class).where().like("name", name).findUnique();
        if (vr!=null){
            Id = vr.getId();
            color = vr.getColor();
            System.out.print("Postoji: " + vr.getName());
        } else {
            System.out.print("Ne ostoji: !");
            name = "Zaba"; 
            color = Color.yellow.getRGB();
        }
    }*/

   /* public Aktivnost getOwner() {
        return owner;
    }

    public void setOwner(Aktivnost owner) {
        this.owner = owner;
    }*/

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
    
   /* public static VrsteAktivnosti get(String name){
        if (map.isEmpty()){
            List<VrsteAktivnosti> list = NewJFrame.getServer().find(VrsteAktivnosti.class).where().findList();
            for (VrsteAktivnosti vr: list){
                map.put(vr.getName(), vr);
            }
        }
        return map.get(name);
    }
    */
}
