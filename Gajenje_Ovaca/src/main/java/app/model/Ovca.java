/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "ovca")
    private List<Vakcinacija> vakcinacije;
    @OneToOne(mappedBy = "ovca")
    private Prodaja prodaja;
    @OneToMany(cascade= CascadeType.ALL, mappedBy="ovca")
    private List<Jagnjenje> listaJagnjenja;
    
    @OneToOne(mappedBy = "sheep")
    private Jagnjenje rodjenje;
    private String oznaka;
    private String nadimak;
    private float procenatR;
    private String datumRodjenja;
    private char pol; // m ili z
    @ManyToOne(fetch=FetchType.EAGER)
    private Ovca otac; 
    @ManyToOne(fetch=FetchType.EAGER)
    private Ovca majka;
    private String opis;
    private String pracenje;
    private String status;
    private Integer leglo;
    @ManyToOne
    private Linija linija;
    @OneToOne(mappedBy = "o")
    private Uginuce uginuce;
    private float tezinaNaRodjenju;
    private String aktuelno;
   // mozda ne treba private ArrayList<Date> listaJagnjenja;
    
    public Ovca(){
        
    }
    public Ovca(String status, String oznaka, char pol){
        this.status = status;
        this.oznaka = oznaka;
        this.pol = pol;
    }
    public Ovca getMe(){
        return this;
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
            if (oznaka.toString().length()>0){
                this.oznaka = oznaka.toString();
            }
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
public String getProcR(){
    return String.valueOf(Aktivnost.round(procenatR, 2));
}
public String getPpol(){
    if (pol=='m'){
        return "muško";
    } 
    return "žensko";
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
        if (oznaka!=null){
            if (nadimak!=null){
                return oznaka + " " + nadimak;
            } else {
                return oznaka + " " + oznaka;
            }
        }
        return "bez oznake";
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
    public String getLlinija(){
        if (linija !=null){
        return linija.getImeLinije();
        }
        return "nepoznata";
    }

    public void setLeglo(Object valueAt) {
        if (valueAt!=null)
        {
            leglo = Integer.parseInt(valueAt.toString());
        }
    }
    private float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    public Integer starostUMesecima(){
        if (this.datumRodjenja!=null && datumRodjenja.length()>0){
            Calendar c = Calendar.getInstance();
            int cmonth = c.get(Calendar.MONTH)+1;
            int cyear = c.get(Calendar.YEAR);
            int rmonth = Integer.parseInt(datumRodjenja.substring(3, 5));
            int ryear = Integer.parseInt(datumRodjenja.substring(6));
            return   12*(cyear-ryear) + cmonth-rmonth;
        }
        return 0;
    }
    
    public String getStarost(){
        Integer monthDifference = starostUMesecima();
        if (monthDifference!=null && status.equals("na farmi")){
            if (monthDifference>11){
                float diff = (float)monthDifference/12 - 0.01f;
                return Float.toString(round(diff, 1)) + " god."; 
            } 
            return String.valueOf(monthDifference) + " mes.";
        }
        return "-";
    }

    public String getAktuelno() {
        return aktuelno;
    }

    public void setAktuelno(String aktuelno) {
        this.aktuelno = aktuelno;
    }

    public List<Jagnjenje> getListaJagnjenja() {
        return listaJagnjenja;
    }

    public void setListaJagnjenja(List<Jagnjenje> listaJagnjenja) {
        this.listaJagnjenja = listaJagnjenja;
    }

    public Jagnjenje getRodjenje() {
        return rodjenje;
    }

    public void setRodjenje(Jagnjenje rodjenje) {
        this.rodjenje = rodjenje;
    }

    public float getTezinaNaRodjenju() {
        return tezinaNaRodjenju;
    }

    public void setTezinaNaRodjenju(float tezinaNaRodjenju) {
        this.tezinaNaRodjenju = tezinaNaRodjenju;
    }

    public void setTezinaNaRodjenju(Object value) {
         if (value != null && !value.toString().equals("")){
           this.tezinaNaRodjenju = Float.parseFloat(value.toString()) - 0.00f;
        }
    }

    public Uginuce getUginuce() {
        return uginuce;
    }

    public void setUginuce(Uginuce uginuce) {
        this.uginuce = uginuce;
    }

    public Prodaja getProdaja() {
        return prodaja;
    }

    public void setProdaja(Prodaja prodaja) {
        this.prodaja = prodaja;
    }
    
    
    public Integer getBrojJagnjenja(){
        Integer brojJagnjenja = 0;
        int acId = this.getListaJagnjenja().get(0).getAktivnost().getId();
        for (Jagnjenje j: this.listaJagnjenja){
            if (j.getAktivnost().getId()!=acId){
                acId = j.getAktivnost().getId();
                brojJagnjenja ++;
            }
        } 
        brojJagnjenja ++;
        return brojJagnjenja;
    }
    public String getPoreklo(){
        if (nabavka==null){
            return "sa farme";
        } 
        return nabavka.getAktivnost().getLokacija();
    }
    public String getOotac(){
        if (otac!=null){
            return otac.toString();
        }
        return "-";
    }
    public String getLleglo(){
        if (leglo !=null){
        return String.valueOf(leglo);
        }
        return "?";
    }
    public String getMmajka(){
        if (majka!=null){
             return majka.toString();
        }
        return "-";
    } 
    public String procenatJagnjenja(){
        if (this.listaJagnjenja==null || this.listaJagnjenja.isEmpty()){
            return "0.0%";
        }
        Integer brojJagnjadi = this.listaJagnjenja.size();
        Integer brojJagnjenja = getBrojJagnjenja();
        return Float.toString(100*((float) (brojJagnjadi.floatValue())/brojJagnjenja) - 0.0f) + "%";
    }

    public List<Vakcinacija> getVakcinacije() {
        return vakcinacije;
    }

    public void setVakcinacije(List<Vakcinacija> vakcinacije) {
        this.vakcinacije = vakcinacije;
    }
    
    public static String parseOznaka(String slog){
        int i=slog.indexOf(" ");
        if (i>0){
            return slog.substring(0,i);
        }
        return slog;
    }
    
    
    public static String mnozinaJagnje(int n){
        switch (n%10){
            case 1: return "" + n + " jagnje";
            case 2: return "" + n + " jagnjeta";
            case 3: return "" + n + " jagnjeta";
            case 4: return "" + n + " jagnjeta";          
        }
        return "" + n + " jagnjadi";
    }
    public static String mnozinaOvan(int n){
        switch (n%10){
            case 1: return "" + n + " ovan";
            case 2: return "" + n + " ovna";
            case 3: return "" + n + " ovna";
            case 4: return "" + n + " ovna";          
        }
        return "" + n + " ovnova";
    }
    
    
    public static String mnozinaOvca(int n){
        switch (n%10){
            case 1: return "" + n + " ovca";
            case 2: return "" + n + " ovce";
            case 3: return "" + n + " ovce";
            case 4: return "" + n + " ovce";          
        }
        return "" + n + " ovaca";
    }
    
        public static String mnozinaGro(int n){
        switch (n%10){
            case 1: return "" + n + " grlo";          
        }
        return "" + n + " grla";
    }
    private Integer parseMesec(String datum){
        return Integer.parseInt(datum.substring(3, 5));
    }
    private Integer parseGodina(String datum){
        return Integer.parseInt(datum.substring(6, 10));
    }
     public boolean wasJagnje(int mesec, int godina){
        if (this.datumRodjenja!=null && datumRodjenja.length()>0){
            int rmonth = Integer.parseInt(datumRodjenja.substring(3, 5));
            int ryear = Integer.parseInt(datumRodjenja.substring(6));
            return   (12*(godina-ryear) + mesec-rmonth)<7;
        }    
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ovca other = (Ovca) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
     /**
     *
     * @param o
     * @return
     */

    
    
}
