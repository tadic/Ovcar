/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti.JDateChooserRenderer;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Ovca {
    @Id
    private Integer id; 
    @OneToOne(mappedBy = "sheep")
    private NabavkaOvaca nabavka;
    
    private String oznaka;
    private String nadimak;
    private float procenatR;
    private String datumRodjenja;
    private char pol; // m ili z
    @OneToOne
    private Ovca otac; 
    @OneToOne
    private Ovca majka;
    private String opis;
    private String pracenje;
    private String status;
    private Integer leglo;
    @OneToOne
    private Linija linija;
   // mozda ne treba private ArrayList<Date> listaJagnjenja;
    
    public Ovca(){
        
    }
    public Ovca(String status, String oznaka, char pol){
        this.status = status;
        this.oznaka = oznaka;
        this.pol = pol;
    }
    public Ovca(String status){
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatumRodjenja() {
        if (datumRodjenja!=null){
             return datumRodjenja;
        }
        return "";
    }

    public void setDatumRodjenja(String datumOjagnjenja) {
        if (datumOjagnjenja!=null){
            this.datumRodjenja = datumOjagnjenja.toString();
        }
    }
    public void setDatumRodjenja(Object datum) {
        if (datum!=null){
            if (datum instanceof String){
                this.datumRodjenja = datum.toString();
            } else {
                Date jp = (Date) datum;
                this.datumRodjenja =new SimpleDateFormat("dd.MM.yyyy").format(jp);
            }
        }
    }

    public char getPol() {
        return pol;
    }

    public void setPol(char pol) {
        this.pol = pol;
    }
    public void setPol(Object pol) {
        if (pol!=null){
            char p = pol.toString().charAt(0);
            if (p=='m'|| p=='M'){
                this.pol = 'm';
            } else {
                this.pol = 'ž';
            }
        }
    }



    public Ovca getOtac() {
        return otac;
    }

    public void setOtac(Ovca otac) {
        this.otac = otac;
    }

    public Ovca getMajka() {
        return majka;
    }

    public void setMajka(Ovca majka) {
        this.majka = majka;
    }

    public NabavkaOvaca getNabavka() {
        return nabavka;
    }

    public void setNabavka(NabavkaOvaca nabavka) {
        this.nabavka = nabavka;
    }

    public String getOznaka() {
            return oznaka;
    }

    public void setOznaka(String oznaka) {
        if (oznaka!=null){
            this.oznaka = oznaka;
        }
    }
    public void setOznaka(Object oznaka) {
        if (oznaka!=null){
            this.oznaka = oznaka.toString();
        }
    }

    public String getNadimak() {
          return nadimak;
    }

    public void setNadimak(String nadimak) {
        if (nadimak!=null){
            this.nadimak = nadimak;
        }
    }
    public void setNadimak(Object nadimak) {
        if (nadimak!=null){
            this.nadimak = nadimak.toString();
        }
    }

    public float getProcenatR() {
        return procenatR;
    }

    public void setProcenatR(float procenatR) {
        this.procenatR = procenatR;
    }
    public void setProcenatR(Object o){
        if (o != null){
           this.procenatR = Float.parseFloat(o.toString()) - 0.00f;
        }
    }

    public String getOpis() {
            return opis;
    }

    public void setOpis(String opis) {
            this.opis = opis;
    }
    public void setOpis(Object opis){
         if (opis!=null){
             this.opis = opis.toString();
         }
    }

    public String getPracenje() {
            return pracenje;
    }

    public void setPracenje(String pracenje) {
            this.pracenje = pracenje;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return oznaka;
    }

    public Integer getLeglo() {
        return leglo;
    }

    public void setLeglo(Integer leglo) {
        this.leglo = leglo;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
    }

    public void setLeglo(Object valueAt) {
        if (valueAt!=null)
        {
            leglo = Integer.parseInt(valueAt.toString());
        }
    }
    
    
    
}
