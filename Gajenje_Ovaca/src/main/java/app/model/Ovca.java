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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.joda.time.DateTime;
import org.joda.time.Days;

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
    @OneToOne(cascade= CascadeType.ALL, mappedBy = "ovca")
    private Prodaja prodaja;
    @OneToMany(cascade= CascadeType.REMOVE, mappedBy="ovca")
    private List<Jagnjenje> listaJagnjenja;
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "ovca")
    private List<Merenje> merenja;
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "ovca")
    private List<Parenje> parenja;
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "ovan")
    private List<Parenje> pripusti;
    
    @OneToOne(mappedBy = "sheep", fetch= FetchType.EAGER)
    private Jagnjenje rodjenje;
    
    @Column(unique=true)
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
    private String poreklo;
    private String status;
    private Integer leglo;
    @ManyToOne
    private Linija linija;
    @OneToOne(mappedBy = "o")
    private Uginuce uginuce;
    private float tezinaNaRodjenju;
    private float tezinaDvaMeseca;
    private float tezinaCetiriMeseca;

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
                return nadimak + " " + oznaka;
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
    
    public boolean wasPrvojagnjenka(Dan d){
         for (Jagnjenje j: this.getListaJagnjenja()){
            if (j.getAktivnost().getDan().getDatum()<d.getDatum()){
                return false;
            }
        }
        return true;
    }
    
    public float teoretskiJagnjadiGodisnje(){
        Integer brojJagnjadi = getListaJagnjenja().size();
        Integer brojJagnjenja = getBrojJagnjenja();
        if (brojJagnjadi>0){
            float prosekJagnjenja = ((float) brojJagnjadi.floatValue())/brojJagnjenja;
            return Aktivnost.round(365*prosekJagnjenja/danaIzmedjuJagnjenja(), 2);
        }
        return 0;
    }
    
    private float jagnjenjaGodisnje(){
        return (float)365.0f/danaIzmedjuJagnjenja();
    }
    
    public float indexMesecneZarade(){
        float godisnjeJagnjadi = teoretskiJagnjadiGodisnje();
        float zaradaOdJagnjadi = godisnjeJagnjadi*80;
        float ovcaTrosak = 16*jagnjenjaGodisnje() + 48 + 5; // za osnovno odrzavanje 5€/m = 60€ plus 10€ po jagnjenju, plus ostalo 5€
        float jagnjadTrosak = godisnjeJagnjadi*15 + 5;      // 15€ hrana i 5€ sve ostalo
        return (zaradaOdJagnjadi-ovcaTrosak-jagnjadTrosak)/12;
        
    }
    public float teoretskiJagnjadiGodisnje(int godina){
        List<Jagnjenje> listaJ = this.getListaJagnjenja();
        if (listaJ==null || listaJ.isEmpty()){
            return 0;
        }
        int jagnjadiUGodini=0;
        boolean brojacPostavljen = false;
        Dan pocetak, kraj;
        Aktivnost a = listaJ.get(0).getAktivnost();
        pocetak = a.getDan();
        for (Jagnjenje j : listaJ){
            if(j.getAktivnost().getDan().getDatum()/10000 == godina){
                if (brojacPostavljen){
                    jagnjadiUGodini++;
                } else {
                    pocetak = a.getDan();
                    if (!j.getAktivnost().equals(a)){
                        brojacPostavljen = true;
                        jagnjadiUGodini = 1;
                    }
                }
                 
            }
            a = j.getAktivnost();
        }
        kraj = a.getDan();
        return ((float)365*jagnjadiUGodini)/razlikaUDanima(pocetak, kraj);
    }
    
    public int danaIzmedjuJagnjenjaU(int godina){
        List<Jagnjenje> listaJ = this.getListaJagnjenja();
        if (listaJ==null || listaJ.isEmpty()){
            return 0;
        }
        int brojJagnjenja=0;
        boolean brojacPostavljen = false;
        Dan pocetak, kraj;
        Aktivnost a = listaJ.get(0).getAktivnost();
        pocetak = a.getDan();
        for (Jagnjenje j : listaJ){
            if(j.getAktivnost().getDan().getDatum()/10000 == godina){
                if (brojacPostavljen){
                    if (!j.getAktivnost().equals(a)){
                        brojJagnjenja++;
                    }
                } else {
                    pocetak = a.getDan();
                    if (!j.getAktivnost().equals(a)){
                        brojacPostavljen = true;
                        brojJagnjenja = 1;
                    }
                }
                 
            }
            a = j.getAktivnost();
        }
        kraj = a.getDan();
        if (brojJagnjenja>0){
        return razlikaUDanima(pocetak, kraj)/(brojJagnjenja);
        } 
        return 0;
    }

    public int danaIzmedjuJagnjenja(){      // racuna 6 meseci plus vreme od prvog do poslednjeg
        List<Jagnjenje> listaJ = this.getListaJagnjenja();
        if (listaJ==null || listaJ.isEmpty()){
            return 0;
        }
        int brojJagnjenja=getBrojJagnjenja();

        Dan pocetak = listaJ.get(0).getAktivnost().getDan(); 
        Dan kraj = listaJ.get(listaJ.size()-1).getAktivnost().getDan();
        if (brojJagnjenja>0){
            return (razlikaUDanima(pocetak, kraj) + 180)/(brojJagnjenja);
        } 
        return 0;
    }
    
    private int razlikaUDanima(Dan pocetak, Dan kraj){
        int p = pocetak.getDatum();
        int k = kraj.getDatum();
       
        return Days.daysBetween(new DateTime(new Date(p/10000, (p%10000)/100, p%100)), 
                    new DateTime(new Date(k/10000, (k%10000)/100, k%100))).getDays();
        
    }
    public boolean wasDviska(Dan d){
        if (this.getListaJagnjenja()==null || this.getListaJagnjenja().isEmpty()){
            return true; //!?
        }
        int rmonth = Integer.parseInt(datumRodjenja.substring(3, 5))-1;
        int ryear = Integer.parseInt(datumRodjenja.substring(6));
        int datumRodj = ryear*10000 + rmonth*100;
        int datumProvere = d.getDatum();
        int razlika = datumProvere - datumRodj;
        if (razlika>20000){     // ako je starija od 2 godine
            return false;
        }
        
        return wasPrvojagnjenka(d);
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
    public Integer starostUDanima(Calendar date){
        if (this.datumRodjenja!=null && datumRodjenja.length()>0){
            int cyear = date.get(Calendar.YEAR);
            int cday = date.get(Calendar.DATE);
            int cmonth = date.get(Calendar.MONTH);
                   
            int rmonth = Integer.parseInt(datumRodjenja.substring(3, 5))-1;
            int ryear = Integer.parseInt(datumRodjenja.substring(6));
            int rdate = Integer.parseInt(datumRodjenja.substring(0,2));
     
            int days = Days.daysBetween(new DateTime(new Date(ryear, rmonth, rdate)), 
                    new DateTime(new Date(cyear, cmonth, cday))).getDays();
            //Integer starostDana = 365*(cyear-ryear) + cday - d.getDate().DAY_OF_YEAR;
            return   days;
        }
        return 0;
    }
    public String getStarost(){
        Integer dayDifference = starostUDanima(Calendar.getInstance());
        Integer monthDifference = dayDifference/30;
        if (monthDifference!=null && status.equals("na farmi")){
            if (monthDifference>11){
                float diff = (float)monthDifference/12 - 0.01f;
                return Float.toString(round(diff, 1)) + " god."; 
            } else if (monthDifference<3){
                return dayDifference.toString() + " dana";
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
        
        if (this.getListaJagnjenja()==null || this.getListaJagnjenja().isEmpty()){
            return 0;
        }
        Integer brojJagnjenja = 0;
        int acId = -1;
        for (Jagnjenje j: this.listaJagnjenja){
            if (j.getAktivnost().getId()!=acId){
                acId = j.getAktivnost().getId();
                brojJagnjenja ++;
            }
        } 
        return brojJagnjenja;
    }

    public String getPoreklo() {
        return poreklo;
    }

    public void setPoreklo(String poreklo) {
        this.poreklo = poreklo;
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
        return Float.toString(100*((float) (brojJagnjadi.floatValue()/brojJagnjenja) - 0.0f)) + "%";
    }

    public List<Vakcinacija> getVakcinacije() {
        return vakcinacije;
    }

    public void setVakcinacije(List<Vakcinacija> vakcinacije) {
        this.vakcinacije = vakcinacije;
    }
    
    
    private Integer parseMesec(String datum){
        return Integer.parseInt(datum.substring(3, 5));
    }
    private Integer parseGodina(String datum){
        return Integer.parseInt(datum.substring(6, 10));
    }
    
    public boolean wasOdrasloNaFarmi(int mesec, int godina){
        if (jelBiloOdraslo(mesec, godina)){ // podrazumeva se da je ili rodjeno na farmi ili nabavljeno, znaci poznato mu je rodjenje
            if (this.getStatus().equals("na farmi")){   // ako je i dalje na farmi a tada je bilo odraslo
                return true;
            }
            if (!this.wasProdato(mesec, godina) && !this.wasUginulo(mesec, godina)){ // ako u trenutku provere nije bilo prodati ili uginulo
                return true;
            }
        }
        return false;
    }
    
    private boolean wasProdato(int mesec, int godina){
         if (this.getProdaja()!=null){
             int datumProvere = 1000*godina + 100*mesec;
             int datumProdaje = this.getProdaja().getAktivnost().getDan().getDatum();
             if (datumProdaje<datumProvere){
                 return true;
             }
         }
         return false;
    }
    private boolean wasUginulo(int mesec, int godina){
         if (this.getUginuce()!=null){
             int datumProvere = 1000*godina + 100*mesec;
             int datumUginuca = this.getUginuce().getA().getDan().getDatum();
             if (datumUginuca<datumProvere){
                 return true;
             }
         }
         return false;
    }
     public boolean wasJagnje(int mesec, int godina){
        if (this.datumRodjenja!=null && datumRodjenja.length()>0){
            int rmonth = Integer.parseInt(datumRodjenja.substring(3, 5));
            int ryear = Integer.parseInt(datumRodjenja.substring(6));
            int razlikaMeseci = (12*(godina-ryear) + mesec-rmonth);
            if (razlikaMeseci<7 && razlikaMeseci>=0){
                return true;
            }
        }    
        return false;
    }
      public boolean jelBiloOdraslo(int mesec, int godina){
          if (this.getParenja()==null || this.getParenja().isEmpty()){
              return false;
          }
          int datumProvere = 10000*godina + 100*mesec;
       //   System.out.println(this.getNadimak() + " ima parenja ");
          for (Parenje p: this.getParenja()){
              if (p.getAktivnost().getDan().getDatum()<datumProvere){
                  return (!this.wasUginulo(mesec, godina) && !wasProdato(mesec, godina));
              }
          } 
        return false;
    }

    public List<Merenje> getMerenja() {
        return merenja;
    }

    public void setMerenja(List<Merenje> merenja) {
        this.merenja = merenja;
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

    public float getTezinaDvaMeseca() {
        return tezinaDvaMeseca;
    }

    public void setTezinaDvaMeseca(float tezinaDvaMeseca) {
        this.tezinaDvaMeseca = tezinaDvaMeseca;
    }

    public float getTezinaCetiriMeseca() {
        return tezinaCetiriMeseca;
    }

    public void setTezinaCetiriMeseca(float tezinaCetiriMeseca) {
        this.tezinaCetiriMeseca = tezinaCetiriMeseca;
    }

    public List<Parenje> getParenja() {
        return parenja;
    }

    public void setParenja(List<Parenje> parenja) {
        this.parenja = parenja;
    }

    public List<Parenje> getPripusti() {
        return pripusti;
    }

    public void setPripusti(List<Parenje> pripusti) {
        this.pripusti = pripusti;
    }
    
}
