package app.helpers;

import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Ovca;

public final class OvcaReport{
    private String datumRodjenja;
    private String pol;
    private Integer leglo;
    private String status;
    private String oznaka;
    private String nadimak;
    private String poreklo;
    private String procenatR;
    private String linija;
    private String opis;
    private String tezinaRodjenje;
    private String otac;
    private String majka;
    
    private String ootac;
    private String omajka;
    private String motac;
    private String mmajka;
    
    private String oootac;
    private String oomajka;
    private String omotac;
    private String ommajka;
    private String mmmajka;
    private String mmotac;
    private String momajka;
    private String mootac;
    
    private String oooo;
    private String ooom;
    private String oomo;
    private String oomm;
    private String ommm;
    private String ommo;
    private String omom;
    private String omoo;
    private String mooo;
    private String moom;
    private String momo;
    private String momm;
    private String mmmm;
    private String mmmo;
    private String mmom;
    private String mmoo;
    
    public OvcaReport(Ovca o, Logic l){
        datumRodjenja = o.getDatumRodjenja();
        pol = o.getPpol();
        status = o.getStatus();
        oznaka = o.getOznaka();
        nadimak = o.getNadimak();
        poreklo = o.getPoreklo();
        leglo = o.getLeglo();
        procenatR = o.getProcR();
        linija = o.getLlinija();
        opis = o.getOpis();
        tezinaRodjenje = String.valueOf(Aktivnost.round(o.getTezinaNaRodjenju(),1)) + "kg";
        postaviPorodicnoStablo(o, l);
        
    }
    
    public void postaviPorodicnoStablo(Ovca ovca, Logic l){
        otac = stringFrom(ovca.getOtac());
        majka = stringFrom(ovca.getMajka());
        
        Ovca o = l.getOvca(ovca.getOtac().getId());
        Ovca m = l.getOvca(ovca.getMajka().getId());
        
        Ovca om = l.getOvca(o.getMajka().getId());
        Ovca oo = l.getOvca(o.getOtac().getId());
        Ovca mo = l.getOvca(m.getOtac().getId());
        Ovca mm = l.getOvca(m.getMajka().getId());
        
        Ovca omo = l.getOvca(om.getOtac().getId());
        Ovca ooo = l.getOvca(oo.getOtac().getId());
        Ovca moo = l.getOvca(mo.getOtac().getId());
        Ovca mmo = l.getOvca(mm.getOtac().getId());
        Ovca omm = l.getOvca(om.getMajka().getId());
        Ovca oom = l.getOvca(oo.getMajka().getId());
        Ovca mom = l.getOvca(mo.getMajka().getId());
        Ovca mmm = l.getOvca(mm.getMajka().getId());
        
        ootac = string0From(oo);
        omajka = string0From(om);
        motac = string0From(mo);
        mmajka = string0From(mm);
        
        oootac = string1From(ooo);
        omotac = string1From(omo);
        mootac = string1From(moo);
        mmotac = string1From(mmo);
        ommajka = string1From(omm);
        oomajka = string1From(oom);
        momajka = string1From(mom);
        mmmajka = string1From(mmm);
        
        oooo = string2From(ooo.getOtac());
        omoo = string2From(omo.getOtac());
        mooo = string2From(moo.getOtac());
        mmoo = string2From(mmo.getOtac());
        ommo = string2From(omm.getOtac());
        oomo = string2From(oom.getOtac());
        momo = string2From(mom.getOtac());
        mmmo = string2From(mmm.getOtac());
        ooom = string2From(ooo.getMajka());
        omom = string2From(omo.getMajka());
        moom = string2From(moo.getMajka());
        mmom = string2From(mmo.getMajka());
        ommm = string2From(omm.getMajka());
        oomm = string2From(oom.getMajka());
        momm = string2From(mom.getMajka());
        mmmm = string2From(mmm.getMajka());
        
    }
    private String stringFrom(Ovca o){
        StringBuilder sb = new StringBuilder();
        sb.append(o.getNadimak()).append(" ID=").append(o.getOznaka());
        sb.append("\n\n\n").append(o.getDatumRodjenja()).append("\n").append(o.getLeglo()).append("\n").append(o.getLlinija());
        sb.append("\n(").append(o.getPoreklo()).append(")");
        return sb.toString();
    }
    private String string0From(Ovca o){
        StringBuilder sb = new StringBuilder();
        sb.append(o.getNadimak()).append(" ID=").append(o.getOznaka());
        sb.append("\n\n\n").append(o.getDatumRodjenja()).append("\n").append(o.getLlinija());
        sb.append("\n(").append(o.getPoreklo()).append(")");
        return sb.toString();
    }
    private String string1From(Ovca o){
        StringBuilder sb = new StringBuilder();
        sb.append(o.getNadimak()).append(" ID=").append(o.getOznaka());
        sb.append("\n\n").append(o.getLlinija()).append("\n(").append(o.getPoreklo()).append(")");
        return sb.toString();
    }
    private String string2From(Ovca o){
        StringBuilder sb = new StringBuilder();
        if (o.getPol()=='m'){
            sb.append("O: ");
        } else {
            sb.append("M: ");
        }
        sb.append(o.getNadimak()).append(" ID=").append(o.getOznaka());
        sb.append("\nL: ").append(o.getLlinija()).append(" P: (").append(o.getPoreklo()).append(")");
        return sb.toString();
    }
    

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getNadimak() {
        return nadimak;
    }

    public void setNadimak(String nadimak) {
        this.nadimak = nadimak;
    }

    public String getPoreklo() {
        return poreklo;
    }

    public void setPoreklo(String poreklo) {
        this.poreklo = poreklo;
    }

    public String getProcenatR() {
        return procenatR;
    }

    public void setProcenatR(String procenatR) {
        this.procenatR = procenatR;
    }

    public String getLinija() {
        return linija;
    }

    public void setLinija(String linija) {
        this.linija = linija;
    }

    public String getOtac() {
        return otac;
    }

    public void setOtac(String otac) {
        this.otac = otac;
    }

    public String getMajka() {
        return majka;
    }

    public void setMajka(String majka) {
        this.majka = majka;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTezinaRodjenje() {
        return tezinaRodjenje;
    }

    public void setTezinaRodjenje(String tezinaRodjenje) {
        this.tezinaRodjenje = tezinaRodjenje;
    }

    public String getOotac() {
        return ootac;
    }

    public void setOotac(String ootac) {
        this.ootac = ootac;
    }

    public Integer getLeglo() {
        return leglo;
    }

    public void setLeglo(Integer leglo) {
        this.leglo = leglo;
    }

    
    public String getOmajka() {
        return omajka;
    }

    public void setOmajka(String omajka) {
        this.omajka = omajka;
    }

    public String getMotac() {
        return motac;
    }

    public void setMotac(String motac) {
        this.motac = motac;
    }

    public String getMmajka() {
        return mmajka;
    }

    public void setMmajka(String mmajka) {
        this.mmajka = mmajka;
    }

    public String getOootac() {
        return oootac;
    }

    public void setOootac(String oootac) {
        this.oootac = oootac;
    }

    public String getMmmajka() {
        return mmmajka;
    }

    public void setMmmajka(String mmmajka) {
        this.mmmajka = mmmajka;
    }

    public String getOomajka() {
        return oomajka;
    }

    public void setOomajka(String oomajka) {
        this.oomajka = oomajka;
    }

    public String getOmotac() {
        return omotac;
    }

    public void setOmotac(String omotac) {
        this.omotac = omotac;
    }

    public String getOmmajka() {
        return ommajka;
    }

    public void setOmmajka(String ommajka) {
        this.ommajka = ommajka;
    }

    public String getMmotac() {
        return mmotac;
    }

    public void setMmotac(String mmotac) {
        this.mmotac = mmotac;
    }

    public String getMomajka() {
        return momajka;
    }

    public void setMomajka(String momajka) {
        this.momajka = momajka;
    }

    public String getMootac() {
        return mootac;
    }

    public void setMootaca(String mootaca) {
        this.mootac = mootaca;
    }

    public String getOooo() {
        return oooo;
    }

    public void setOooo(String oooo) {
        this.oooo = oooo;
    }

    public String getOoom() {
        return ooom;
    }

    public void setOoom(String ooom) {
        this.ooom = ooom;
    }

    public String getOomo() {
        return oomo;
    }

    public void setOomo(String oomo) {
        this.oomo = oomo;
    }

    public String getOomm() {
        return oomm;
    }

    public void setOomm(String oomm) {
        this.oomm = oomm;
    }

    public String getOmmm() {
        return ommm;
    }

    public void setOmmm(String ommm) {
        this.ommm = ommm;
    }

    public String getOmmo() {
        return ommo;
    }

    public void setOmmo(String ommo) {
        this.ommo = ommo;
    }

    public String getOmom() {
        return omom;
    }

    public void setOmom(String omom) {
        this.omom = omom;
    }

    public String getOmoo() {
        return omoo;
    }

    public void setOmoo(String omoo) {
        this.omoo = omoo;
    }

    public String getMooo() {
        return mooo;
    }

    public void setMooo(String mooo) {
        this.mooo = mooo;
    }

    public String getMoom() {
        return moom;
    }

    public void setMoom(String moom) {
        this.moom = moom;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public String getMomm() {
        return momm;
    }

    public void setMomm(String momm) {
        this.momm = momm;
    }

    public String getMmmm() {
        return mmmm;
    }

    public void setMmmm(String mmmm) {
        this.mmmm = mmmm;
    }

    public String getMmmo() {
        return mmmo;
    }

    public void setMmmo(String mmmo) {
        this.mmmo = mmmo;
    }

    public String getMmom() {
        return mmom;
    }

    public void setMmom(String mmom) {
        this.mmom = mmom;
    }

    public String getMmoo() {
        return mmoo;
    }

    public void setMmoo(String mmoo) {
        this.mmoo = mmoo;
    }
    
    
    
}
