
package app.report;

import app.model.Ovca;

/**
 *
 * @author ivantadic
 */
public class Ukrvljenost {
    private String procOvan1;
    private String procOvan2;
    private String procOvan3;
    private String procOvan4;
    private String procOvan5;

    private String oznaka;
    private String naziv;
    private String pol;
    private String starost;
    private String leglo;
    private String aktuelno;
    
    public Ukrvljenost(Ovca ovca) {
        this.oznaka = ovca.getOznaka();
        this.naziv = ovca.getNadimak();
        this.leglo = ovca.getLleglo();
        this.pol = ovca.getPpol();
        this.starost = ovca.getStarost();
        this.aktuelno = ovca.getAktuelno();
               
    }

    public String getProcOvan1() {
        return procOvan1;
    }

    public void setProcOvan1(String procOvan1) {
        this.procOvan1 = procOvan1;
    }

    public String getProcOvan2() {
        return procOvan2;
    }

    public void setProcOvan2(String procOvan2) {
        this.procOvan2 = procOvan2;
    }

    public String getProcOvan3() {
        return procOvan3;
    }

    public void setProcOvan3(String procOvan3) {
        this.procOvan3 = procOvan3;
    }

    public String getProcOvan4() {
        return procOvan4;
    }

    public void setProcOvan4(String procOvan4) {
        this.procOvan4 = procOvan4;
    }

    public String getProcOvan5() {
        return procOvan5;
    }

    public void setProcOvan5(String procOvan5) {
        this.procOvan5 = procOvan5;
    }

   
    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getStarost() {
        return starost;
    }

    public void setStarost(String starost) {
        this.starost = starost;
    }

    public String getLeglo() {
        return leglo;
    }

    public void setLeglo(String leglo) {
        this.leglo = leglo;
    }

    public String getAktuelno() {
        return aktuelno;
    }

    public void setAktuelno(String aktuelno) {
        this.aktuelno = aktuelno;
    }
    
    
}
