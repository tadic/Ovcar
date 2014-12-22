/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.report;

import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.Prodaja;
import app.model.Uginuce;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.util.ArrayUtilities;

/**
 *
 * @author ivantadic
 */
public class MesecniIzvestaj {
    private List<Aktivnost> list;
    private Logic logic;
    private String godina;
    private Integer mesec, promenaOvaca, promenaJaganjaca, promenaOvnova, promenaSvega;
    private float sviRashodi, sviPrihodi, nabavkaOvacaRashod;
    private List<String> meseci = Arrays.asList("Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul",
                                                "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar");
    private Map<String, Object> params = new HashMap<String, Object>();
    public MesecniIzvestaj(Logic logic, int mesec, String godina){
        this.logic = logic;
        this.mesec = mesec;
        this.godina = godina;
    }
    
    public MesecniIzvestaj(){
        
    }
    
    public void create(){
        osnovniPodatci();
        sviRashodi = 0;
        sviPrihodi = 0;
        promenaJaganjaca = 0;
        promenaOvaca = 0;
        promenaOvnova = 0;
        promenaSvega = 0;
        nabavkaOvacaRashod = 0;
        setPodatkeDoZadatogDatuma(mesec, "ovaca", "ovnova", "jaganjaca", "sviPocetak");
        setPodatkeDoZadatogDatuma(mesec+1, "ovacaKraj", "ovnovaKraj", "jaganjacaKraj", "sviKraj");
        setPodatkeZaTrazeniMesec();
        setPromene();
        
        try
        {
            String reportSource = "mesecniIzvestaj.jrxml";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getAktivnostiU(mesec, Integer.parseInt(godina)));
            
            
            JasperDesign dis=JRXmlLoader.load(reportSource); 
            JasperReport jasperReport = JasperCompileManager.compileReport(dis);

            

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, params, dataSource);
            JasperViewer.viewReport(jasperPrint);
        }

        catch (JRException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    
     private List<Aktivnost> getAktivnostiU(int mesec, int godina) {
            int trazeniMesec = 100 * godina + (mesec);
            List<Dan> danList = logic.getAllDays();
            ArrayList<Aktivnost> list = new ArrayList<Aktivnost>();
            for (Dan d: danList){
                if ((d.getDatum()/100)==trazeniMesec){
                    for (Aktivnost a: d.getAktivnosti()){
                        list.add(a);
                    }
                } 
            }
            return list;
        }

    private List<Aktivnost> getAktivnostiDo(int mesec, int godina) {
        int pocetakMeseca = 10000 * godina + 100*(mesec);
        List<Dan> danList = logic.getAllDays();
        ArrayList<Aktivnost> list = new ArrayList<Aktivnost>();
        for (Dan d: danList){
            if (d.getDatum()<pocetakMeseca){
                for (Aktivnost a: d.getAktivnosti()){
                    list.add(a);
                }
            } else {
               return list;
            }
        }
        return list;
    }
    
    private void popuniPrethodne(List<Ovca> list, String ovca, String ovan, String jagnje, String svi){
        Integer ovnova=0;
        Integer ovaca =0;
        Integer jaganjaca =0;
        for (Ovca o : list){
            if (o.wasJagnje(mesec, Integer.parseInt(godina))){
                jaganjaca++;
            } else {
                if (o.getPol()=='m'){
                    ovnova ++;
                } else {
                    ovaca++;
                }
            }
        }
        Integer svega = jaganjaca + ovaca + ovnova;
        params.put(svi, svega.toString()); 
        params.put(ovan, ovnova.toString());
        params.put(ovca, ovaca.toString());
        params.put(jagnje, jaganjaca.toString());
    }
    
    private void setPodatkeDoZadatogDatuma(int mesec, String ovaca, String ovnova, String jagnjadi, String svi){
        ArrayList<Ovca> listaOvaca = new ArrayList<Ovca>();
        
        for (Aktivnost a: getAktivnostiDo(mesec, Integer.parseInt(godina))){
            if (a.getVrstaAktivnosti().getName().equals("Jagnjenje")){
                for (Jagnjenje j: a.getListaJagnjenja()){
                    if (j.isJelZivo()){
                        listaOvaca.add(logic.getOvca(j.getSheep().getId()));
                    }
                }
            } else if (a.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
                for (NabavkaOvaca no: a.getNabavljenaGrla()){
                    listaOvaca.add(logic.getOvca(no.getSheep().getId()));
                }
            } else if (a.getVrstaAktivnosti().getName().equals("Prodaja")){
                for (Prodaja p: a.getProdaje()){
                    listaOvaca.remove(logic.getOvca(p.getOvca().getId()));
                }
            } else if (a.getVrstaAktivnosti().getName().equals("Uginuce")){
                    listaOvaca.remove(logic.getOvca(a.getUginuce().getO().getId()));
            }
        }
        popuniPrethodne(listaOvaca, ovaca, ovnova, jagnjadi, svi);
    }

    private void setJagnjenja(ArrayList<Aktivnost> jagnjenja){
        Integer ojagnjeno = 0;
        for (Aktivnost a: jagnjenja){
            for (Jagnjenje jagnjenje:a.getListaJagnjenja()){
                if (jagnjenje.isJelZivo()){
                    ojagnjeno ++;
                }
            }
        }
        params.put("ojagnjenoJaganjaca", ojagnjeno.toString()); 
        promenaJaganjaca += ojagnjeno;
        promenaSvega += ojagnjeno;
    }
    private void setUginuca(ArrayList<Aktivnost> uginuca){
        Integer ovaca = 0;
        Integer jagnjadi = 0;
        Integer ovnova = 0;
        for (Aktivnost a: uginuca){
            Ovca o = logic.getOvca(a.getUginuce().getO().getId());
                if (o.wasJagnje(mesec, Integer.parseInt(godina))) {
                    jagnjadi ++;
                } else if (o.getPol()=='m'){
                    ovnova ++;
                } else {
                    ovaca ++;
                }
        }
        Integer svi = jagnjadi + ovaca + ovnova;
        params.put("sviUginulo", svi.toString()); 
        params.put("uginuloOvaca", ovaca.toString()); 
        params.put("uginuloOvnova", ovnova.toString()); 
        params.put("uginuloJaganjaca", jagnjadi.toString()); 
        promenaJaganjaca -= jagnjadi;
        promenaOvaca -= ovaca;
        promenaOvnova -= ovnova;
        promenaSvega -= svi;
        
    }
    
    private void setNabavke(ArrayList<Aktivnost> nabavke){
        Integer jagnjadi = 0;
        Integer ovnova = 0;
        Integer ovaca = 0;
        for (Aktivnost a: nabavke){
            nabavkaOvacaRashod += a.getTroskovi();
            for (NabavkaOvaca no: a.getNabavljenaGrla()){
                Ovca o = logic.getOvca(no.getSheep().getId());
                if (o.wasJagnje(mesec, Integer.parseInt(godina))) {
                    jagnjadi ++;
                } else if (o.getPol()=='m'){
                    ovnova ++;
                } else {
                    ovaca ++;
                }
            }
        }
        Integer svi = jagnjadi + ovaca + ovnova;
        params.put("sviNabavljeno", svi.toString()); 
        params.put("nabavljenoOvnova", ovnova.toString()); 
        params.put("nabavljenoJaganjaca", jagnjadi.toString()); 
        params.put("nabavljenoOvaca", ovaca.toString()); 

        promenaJaganjaca += jagnjadi;
        promenaOvaca += ovaca;
        promenaOvnova += ovnova;
        promenaSvega += svi;
    }
     private void setProdaje(ArrayList<Aktivnost> prodaje){
        Integer jagnjadi = 0;
        Integer ovnova = 0;
        Integer ovaca = 0;
        for (Aktivnost a: prodaje){
            sviPrihodi += a.getTroskovi();
            for (Prodaja p: a.getProdaje()){
                Ovca o = logic.getOvca(p.getOvca().getId());
                if (o.wasJagnje(mesec, Integer.parseInt(godina))) {
                    jagnjadi ++;
                } else if (o.getPol()=='m'){
                    ovnova ++;
                } else {
                    ovaca ++;
                }
            }
        }
        
        Integer svi = jagnjadi + ovaca + ovnova;
        params.put("sviProdato", svi.toString()); 
        params.put("prodatoOvaca", ovaca.toString()); 
        params.put("prodatoJaganjaca", jagnjadi.toString());
        params.put("prodatoOvnova", ovnova.toString());
        params.put("prodajaPrihod", String.valueOf(Aktivnost.round(sviPrihodi, 1))); 
        promenaJaganjaca -= jagnjadi;
        promenaOvaca -= ovaca;
        promenaOvnova -= ovnova;
        promenaSvega -= svi;
    }
    
    private void setPodatkeZaTrazeniMesec(){
        ArrayList<Aktivnost> listaJagnjenja = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaNabavki = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaUginuca = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaProdaja = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaRashoda = new ArrayList<Aktivnost>();
        for (Aktivnost a: getAktivnostiU(mesec, Integer.parseInt(godina))){
             if (a.getVrstaAktivnosti().getName().equals("Jagnjenje")){
                listaJagnjenja.add(a);
             } else if (a.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
                 listaNabavki.add(a);
             } else if (a.getVrstaAktivnosti().getName().equals("Uginuce")){
                 listaUginuca.add(a);
             } else if (a.getVrstaAktivnosti().getName().equals("Prodaja")){
                 listaProdaja.add(a);
             }  else if (a.getVrstaAktivnosti().getName().equals("Radovi/nabavke")){
                 listaRashoda.add(a);
             }
        }
        setJagnjenja(listaJagnjenja);
        setNabavke(listaNabavki);
        setUginuca(listaUginuca);
        setProdaje(listaProdaja);
        setRashode(listaRashoda);
    }
    
    private void setRashode(List<Aktivnost> listaRashoda){
        float hrana = 0;
        float objekat = 0;
        float ostalo = 0;
        for (Aktivnost a: listaRashoda){
            if (a.getRadovi().getRazlog().equals("priprema/nabavka hrane")){
                hrana += a.getTroskovi();
            } else if (a.getRadovi().getRazlog().equals("objekat i infrastruktura")){
                objekat += a.getTroskovi();
            } else if (a.getRadovi().getRazlog().equals("kapara za ovce")) {
                nabavkaOvacaRashod += a.getTroskovi();
            } else {
                ostalo += a.getTroskovi();
            }       
        }
        params.put("nabavkaOvacaRashod", String.valueOf(Aktivnost.round(nabavkaOvacaRashod, 0))); 
        sviRashodi += nabavkaOvacaRashod;
        sviRashodi += hrana + objekat + ostalo;
        params.put("hranaRashod", String.valueOf(Aktivnost.round(hrana, 0))); 
        params.put("objekatRashod", String.valueOf(Aktivnost.round(objekat, 0))); 
        params.put("ostaloRashod", String.valueOf(Aktivnost.round(ostalo, 0))); 
     //   params.put("hranaRashod", String.valueOf(Aktivnost.round(hrana, 1))); 
        
    }
    private void setPromene(){
       params.put("promenaOvaca", promenaOvaca.toString()); 
       params.put("promenaOvnova", promenaOvnova.toString()); 
       params.put("promenaJaganjaca", promenaJaganjaca.toString()); 
       params.put("sviPromena", promenaSvega.toString()); 
       params.put("sviRashodi", String.valueOf(Aktivnost.round(sviRashodi, 1))); 
       params.put("suma", String.valueOf(Aktivnost.round(sviPrihodi - sviRashodi, 1))); 
    }
    private void osnovniPodatci(){
        params.put("mesec", meseci.get(mesec)); 
        params.put("godina", godina); 
        params.put("startDate", (new java.util.Date()).toString());
    }

        
}
