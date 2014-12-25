/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Dan implements Serializable  {
    @Id
    private Integer id;
    
    private Integer datum;
    
    @OneToMany(mappedBy="dan", fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Aktivnost> aktivnosti;
    
    public Dan(){
        Calendar c = Calendar.getInstance();
        datum = c.get(Calendar.YEAR)*10000 + c.get(Calendar.MONTH)*100
                + c.get(Calendar.DAY_OF_MONTH);
        aktivnosti= new ArrayList<Aktivnost>();
    }
    public Dan(int year, int month, int day){
        datum = year*10000 + month*100 + day; 
        aktivnosti= new ArrayList<Aktivnost>();
    }
    public Dan(Integer n){
        this.datum = n;
        aktivnosti= new ArrayList<Aktivnost>();
    }
    public Dan(Calendar cal){
        datum = cal.get(Calendar.YEAR)*10000 + cal.get(Calendar.MONTH)*100
                + cal.get(Calendar.DAY_OF_MONTH);
        aktivnosti = new ArrayList<Aktivnost>();
    }
    public Calendar getDate(){
        Calendar c = Calendar.getInstance();
        c.set(datum/10000,(datum%10000)/100, datum%100); 
        System.err.println(datum/10000+" "+(datum%10000)/100+" "+ datum%100);
        System.out.println(c);
        return c;
    }
    
  /*   public static Dan getInstance(int year, int month, int day){
         Integer datum = year*10000 + month*100 + day; 
         Dan d = NewJFrame.getServer().find(Dan.class).where().like("datum", datum.toString()).findUnique();
         if (d==null){
             System.out.println(""+datum+" dan prazan");
             return new Dan(year, month, day);
         }
          System.out.println(" -"+d.getDatum() +" dan nije prazan!!!");
         return d;
     }*/

     public void insertActivity(Aktivnost a){
         aktivnosti.add(a);
         Collections.sort(aktivnosti);
     }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDatum() {
        return datum;
    }

    public void setDatum(Integer datum) {
        this.datum = datum;
    }

    public List<Aktivnost> getAktivnosti() {
        if (aktivnosti!=null){
        return aktivnosti;
        } 
        return new ArrayList<Aktivnost>();
    }

    public void setAktivnosti(List<Aktivnost> aktivnosti) {
        this.aktivnosti = aktivnosti;
    }
   /* public void save(){
        NewJFrame.getServer().save(this);
    }*/
    @Override
    public boolean equals(Object obj){
        boolean res = false;
        if (obj != null && obj instanceof Dan){
        Dan d = (Dan) obj;
        res = ((d.datum - this.datum)==0);
        }
        return res;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.datum != null ? this.datum.hashCode() : 0);
        return hash;
    }
    private String getTwoCharacters(int n){
        if (n>9){
            return String.valueOf(n);
        } else {
            return "0" + String.valueOf(n);
        }
    }
    @Override
    public String toString(){
        return "" + getTwoCharacters(datum%100) + "." + getTwoCharacters(1+(datum%10000)/100) + "." + datum/10000;
    }
}
