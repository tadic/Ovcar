/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Reports;

/**
 *
 * @author ivantadic
 */
public class Meseci {
    private int brojOvaca;
    private int brojR;
     private int ojagnjeno;
     private int ojagnjenoMrtvih;
    private float rashodiHrana;
    private float rashodiNabavkaOvaca;
    private float rashodiOstalo;
    private float rashodiObjekat;
    private float prihodi;
    private float bilans;
    private String nazivMeseca;

    public Meseci(String nazivMeseca, int brojOvaca, int brojR, float rashodi, float prihodi, float bilans) {
        this.brojOvaca = brojOvaca;
        this.brojR = brojR;
        this.rashodiHrana = rashodi;
        this.rashodiNabavkaOvaca = 0;
        this.rashodiOstalo = 0;
        this.rashodiObjekat = 0;
        this.prihodi = prihodi;
        this.bilans = bilans;
        this.ojagnjeno = 0;
        this.ojagnjenoMrtvih = 0;
        this.nazivMeseca = nazivMeseca;
    }

    public int getBrojOvaca() {
        return brojOvaca;
    }

    public void setBrojOvaca(int brojOvaca) {
        this.brojOvaca = brojOvaca;
    }

    public int getBrojR() {
        return brojR;
    }

    public void setBrojR(int brojR) {
        this.brojR = brojR;
    }

    public float getPrihodi() {
        return prihodi;
    }

    public void setPrihodi(float prihodi) {
        this.prihodi = prihodi;
    }

    public float getBilans() {
        return bilans;
    }

    public void setBilans(float bilans) {
        this.bilans = bilans;
    }

    public String getNazivMeseca() {
        return nazivMeseca;
    }

    public void setNazivMeseca(String nazivMeseca) {
        this.nazivMeseca = nazivMeseca;
    }

    public float getRashodiHrana() {
        return rashodiHrana;
    }

    public void setRashodiHrana(float rashodiHrana) {
        this.rashodiHrana = rashodiHrana;
    }

    public float getRashodiNabavkaOvaca() {
        return rashodiNabavkaOvaca;
    }

    public void setRashodiNabavkaOvaca(float rashodiNabavkaOvaca) {
        this.rashodiNabavkaOvaca = rashodiNabavkaOvaca;
    }

    public float getRashodiOstalo() {
        return rashodiOstalo;
    }

    public void setRashodiOstalo(float rashodiOstalo) {
        this.rashodiOstalo = rashodiOstalo;
    }

    public float getRashodiObjekat() {
        return rashodiObjekat;
    }

    public void setRashodiObjekat(float rashodiObjekat) {
        this.rashodiObjekat = rashodiObjekat;
    }

    public int getOjagnjeno() {
        return ojagnjeno;
    }

    public void setOjagnjeno(int ojagnjeno) {
        this.ojagnjeno = ojagnjeno;
    }

    public int getOjagnjenoMrtvih() {
        return ojagnjenoMrtvih;
    }

    public void setOjagnjenoMrtvih(int ojagnjenoMrtvih) {
        this.ojagnjenoMrtvih = ojagnjenoMrtvih;
    }
    
    
    
}
