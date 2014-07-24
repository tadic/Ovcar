
package app.model;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlRow;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ivantadic
 */
@Entity
public class Aktivnost implements Comparable<Aktivnost> {

    @Id
    private Integer id;  
    @OneToOne
    private VrsteAktivnosti vrstaAktivnosti;
    @OneToOne(mappedBy = "a")
    private Uginuce uginuce;
    @OneToOne(mappedBy="aktivnost")
    private Radovi radovi;
    @OneToMany(cascade = CascadeType.ALL)
    private List<NabavkaOvaca> nabavljenaGrla;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vakcinacija> vakcinacije;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Prodaja> prodaje;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Jagnjenje> listaJagnjenja;
    
    @ManyToOne
    private Dan dan;

    private Integer vremePocetka; // sati puta 100 plus minuta
    private Integer vremeZavrsetka;
    private String lokacija;
    private String napomena;
    private String bilans;
    private float troskovi;
    
    public Aktivnost() {
    }
    
    public Aktivnost (int sati, int minuta){
        vrstaAktivnosti = new VrsteAktivnosti("Hranjenje i ispaša", new Color(255, 0, 0));
        this.vremePocetka = sati*100 + minuta;
        vremeZavrsetka = vremePocetka + 200;
        
    }
   /* public Aktivnost(VrsteAktivnosti vrAktivnosti,  vremePocetka, long vremeZavrsetka){
        this.vrstaAktivnosti = vrAktivnosti;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Dan getDan() {
        return dan;
    }

    public void setDan(Dan dan) {
        this.dan = dan;
    }

    public Integer getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Integer vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public VrsteAktivnosti getVrstaAktivnosti() {
        return vrstaAktivnosti;
    }

    public void setVrstaAktivnosti(VrsteAktivnosti vrstaAktivnosti) {
        this.vrstaAktivnosti = vrstaAktivnosti;
    }

    public Integer getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(Integer vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    public void setNapomena(Object napomena) {
        if (napomena!=null){
            this.napomena = napomena.toString();
        }
    }
    
    
    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
       
    public final long timeInMillis(Integer year, Integer mount,Integer day,  Integer houres, Integer minutes){
        Calendar c = Calendar.getInstance();
        c.set(year, mount,day, houres, minutes);
        return c.getTimeInMillis();
    }
    public int pocetakUSatima(){
        return vremePocetka/100;
    }
    
    public int zavrsetakUSatima(){
        return vremeZavrsetka/100;
    }
    public int pocetakMinuta(){
        return vremePocetka%100;
    }
    public int zavrsetakMinuta(){
        return vremeZavrsetka%100;
    }
    
    public int getWidth(){
        return 400;
    }
    
    public int getHight(){
        int trajanjeUSatima = zavrsetakUSatima()-pocetakUSatima();
        int trajanjeUMinutima = zavrsetakMinuta() - pocetakMinuta();
        int visina =(4 + (64*trajanjeUSatima) + (trajanjeUMinutima/15)*16); 
        if (visina>20){
            return visina;
        }
        return 22;
    }
    
    public int getYPosition(){
        return  (64*pocetakUSatima() + (pocetakMinuta()/15)*16)-4;
    }
    public int getXPosition(){
        return 56;
    }
    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Шифра активности: ").append(this.id).append("\n");
       sb.append("Назив: ").append(this.getVrstaAktivnosti().getName()).append(" \n");
//       sb.append("боја: ").append(this.getVrstaAktivnosti().getColor().toString()).append("\n");
       sb.append("локација: ").append(lokacija).append("\n");
       sb.append("време: од ").append(new Date(vremePocetka)).append(" - до ").append(new Date(vremeZavrsetka));
       sb.append("\n------------");
        return sb.toString();
    }
    public String vremeToString(){
        StringBuilder sb = new StringBuilder();
        String vreme = vremePocetka.toString();
        if (vremePocetka<100) {
            vreme = "00" + vreme;
        }
        sb.append(vreme.substring(0,vreme.length()-2));
        sb.append(':');
        sb.append(vreme.substring(vreme.length()-2, vreme.length()));
        sb.append('-');
        vreme = vremeZavrsetka.toString();
        sb.append(vreme.substring(0,vreme.length()-2));
        sb.append(':');
        sb.append(vreme.substring(vreme.length()-2, vreme.length()));
        return sb.toString();
    }
    
     public void snimiUBazu(EbeanServer server){
        server.save(this);
    }
     
     public static List<Aktivnost> getAllFromGivenDate(EbeanServer server, int year, int month, int day){
         List<Aktivnost> list = new ArrayList<Aktivnost>();
         Calendar dayBefore = Calendar.getInstance();
         Calendar dayAfter = Calendar.getInstance();
         dayBefore.set(year, month, day-1, 23, 59);
         dayAfter.set(year, month, day, 23, 59);
         String sql = "select * from aktivnost where vreme_pocetka > " + dayBefore.getTimeInMillis() + " and vreme_zavrsetka < " + dayAfter.getTimeInMillis() +";";  
         List<SqlRow> sqlList= server.createSqlQuery(sql).findList(); 
         if (sqlList==null){
             return list;
         }
         for (SqlRow sqlObject: sqlList){
             list.add((Aktivnost) sqlObject);
         }
         return list;
         
     }
    public static void odstampajSveAktivnosti(EbeanServer server){
        List<Aktivnost> list;
        list = server.find(Aktivnost.class).findList();
        for (Aktivnost ak:list){
            System.out.println(ak);
        }
    }

    public int compareTo(Aktivnost t) {
        return this.vremePocetka-t.vremePocetka;
    }

    public List<NabavkaOvaca> getNabavljenaGrla() {
        return nabavljenaGrla;
    }

    public void setNabavljenaGrla(List<NabavkaOvaca> nabavljenaGrla) {
        this.nabavljenaGrla = nabavljenaGrla;
    }

    public float getTroskovi() {
        return troskovi;
    }

    public void setTroskovi(float troskovi) {
        this.troskovi = troskovi;
    }
    public void setTroskovi(Object t){
        try{
            String trosak = t.toString();
            this.troskovi = Float.parseFloat(trosak) - 0.001f;
        } catch(Exception e){
            
        }
    }
        public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public List<Jagnjenje> getListaJagnjenja() {
        if (listaJagnjenja==null){
            listaJagnjenja = new ArrayList<Jagnjenje>();
        }
        return listaJagnjenja;
    }

    public void setListaJagnjenja(List<Jagnjenje> listaJagnjenja) {
        this.listaJagnjenja = listaJagnjenja;
    }

    public String getBilans() {
        return bilans;
    }

    public void setBilans(String bilans) {
        this.bilans = bilans;
    }

    public Uginuce getUginuce() {
        return uginuce;
    }

    public void setUginuce(Uginuce uginuce) {
        this.uginuce = uginuce;
    }

    public List<Prodaja> getProdaje() {
        return prodaje;
    }

    public void setProdaje(List<Prodaja> prodaje) {
        this.prodaje = prodaje;
    }

    public List<Vakcinacija> getVakcinacije() {
        return vakcinacije;
    }

    public void setVakcinacije(List<Vakcinacija> vakcinacije) {
        this.vakcinacije = vakcinacije;
    }

    public Radovi getRadovi() {
        return radovi;
    }

    public void setRadovi(Radovi radovi) {
        this.radovi = radovi;
    }
    
    
    
    

}

    
    
    
