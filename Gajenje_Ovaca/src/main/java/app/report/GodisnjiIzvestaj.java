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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ivantadic
 */
public class GodisnjiIzvestaj {
    private List<Aktivnost> list;
    private Logic logic;
    private String godina;
    private Integer romPocetak, odraslihPocetak, romZPocetak,  sviPocetak, mesec, promenaOvaca, promenaJaganjaca, promenaOvnova, promenaSvega, jagnjenja, ojagnjenih, ojagnjenihZivih, jedinci, dvojke, trojke, cetvorke, petice;
    private float sviRashodi, sviPrihodi;
    private List<String> meseci = Arrays.asList("Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul",
                                                "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar");
    private Map<String, Object> params = new HashMap<String, Object>();
    public GodisnjiIzvestaj(Logic logic, int mesec, String godina){
        this.logic = logic;
        this.mesec = mesec;
        this.godina = godina;
    }
    
    public GodisnjiIzvestaj(){
        
    }
    
    public void create(){
        osnovniPodatci();
        starosnaRaspodela();
        jagnjenja = 0;
        sviRashodi = 0;
        sviPrihodi = 0;
        promenaJaganjaca = 0;
        promenaOvaca = 0;
        promenaOvnova = 0;
        promenaSvega = 0;
        ojagnjenih = 0;
        ojagnjenihZivih = 0;
        jedinci = 0;
        dvojke = 0;
        trojke = 0;
        cetvorke = 0;
        petice = 0;
        setPodatkeDoZadatogDatuma(mesec, "ovaca", "ovnova", "jaganjaca", "sviPocetak");
        setPodatkeDoZadatogDatuma(13, "ovacaKraj", "ovnovaKraj", "jaganjacaKraj", "sviKraj");
        setPodatkeZaTrazenuGodinu();
        setPromene();
        
        try
        {
            String reportSource = "godisnjiIzvestaj.jrxml";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(popuniTabelu());
          
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
    private void starosnaRaspodela(){
        List<Ovca> list = logic.getSvaZivaGrla();
        Integer jaganjci=0;
        Integer ovceDoDveGod = 0;
        Integer ovceOdDveDoCetiri=0;
        Integer ovceOdCetiriDoSest=0;
        Integer ovcePrekoSest=0;
        for (Ovca o:list){
            if (o.starostUMesecima()<7){
                jaganjci++;
            } else {
                switch (o.starostUMesecima()/24){
                    case 0: ovceDoDveGod ++; break;
                    case 1: ovceOdDveDoCetiri ++; break;
                    case 2: ovceOdCetiriDoSest ++; break;
                    default: ovcePrekoSest ++; break;
                }
            }
        }
        params.put("jagnjadi", jaganjci); 
        params.put("ovceDoDveGod", ovceDoDveGod); 
        params.put("ovceOdDveDoCetiriGod", ovceOdDveDoCetiri);
        params.put("ovceOdCetiriDoSestGod", ovceOdCetiriDoSest);
        params.put("ovcePrekoSestGod", ovcePrekoSest);        
    }
    
    private int brojODraslihNa(int mesec, int godina){
        int broj = 0;
        for (Ovca o: logic.getAllSheep()){
            if (o.jelBiloOdraslo(mesec, godina)){
                broj ++;
            }
        }
        return broj;
    }
    private Meseci popuniMesec(int i){
        Meseci m = new Meseci(meseci.get(i).substring(0, 3));
        List<Aktivnost> aktivnosti = getAktivnostiU(i, Integer.parseInt(godina));
        m.setBrojPlodkinja(brojODraslihNa(i, Integer.parseInt(godina)));
        for (Aktivnost a: aktivnosti){
            if (a.getVrstaAktivnosti().getName().equals("Jagnjenje")){
                for (Jagnjenje j: a.getListaJagnjenja()){
                    if (j.isJelZivo()){
                        m.setOjagnjeno(m.getOjagnjeno()+1);
                        m.setBrojOvaca(m.getBrojOvaca()+1);
                        if (j.getSheep().getProcenatR()==100.0){
                            m.setBrojR(m.getBrojR()+1);  
                            if (j.getSheep().getPol()=='ž'){
                                m.setBrojRzenki(m.getBrojRzenki()+1);
                            }
                        }
                    }else {
                         m.setOjagnjenoMrtvih(m.getOjagnjenoMrtvih()+1);
                    }
                }
                
            } else if (a.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
                    m.setBrojOvaca(m.getBrojOvaca()+a.getNabavljenaGrla().size());
                    m.setRashodiNabavkaOvaca(m.getRashodiNabavkaOvaca() + a.getTroskovi());
                    for (NabavkaOvaca no: a.getNabavljenaGrla()){
                    if (no.getSheep().getProcenatR()==100.0){
                            m.setBrojR(m.getBrojR()+1);
                            if (no.getSheep().getPol()=='ž'){
                                m.setBrojRzenki(m.getBrojRzenki()+1);
                            }
                    }
                }
            } else if (a.getVrstaAktivnosti().getName().equals("Prodaja")){
                    m.setBrojOvaca(m.getBrojOvaca() - a.getProdaje().size());
                    for (Prodaja p:a.getProdaje()){
                        if (p.getOvca().getProcenatR()==100.0){
                            m.setBrojR(m.getBrojR()-1); 
                            if (p.getOvca().getPol()=='ž'){
                                m.setBrojRzenki(m.getBrojRzenki()-1);
                            }
                        }
                    }
                    m.setPrihodi(a.getTroskovi());
            } else if (a.getVrstaAktivnosti().getName().equals("Uginuce")){
                    m.setBrojOvaca(m.getBrojOvaca() - 1);
                    if (a.getUginuce().getO().getProcenatR()==100.0){
                            m.setBrojR(m.getBrojR()-1);  
                            if (a.getUginuce().getO().getPol()=='ž'){
                                m.setBrojRzenki(m.getBrojRzenki()-1);
                            }
                     }
            } else if (a.getVrstaAktivnosti().getName().equals("Radovi/nabavke")){
                if (a.getRadovi().getRazlog().equals("priprema/nabavka hrane")){
                    m.setRashodiHrana(m.getRashodiHrana()+a.getTroskovi());
                } else if (a.getRadovi().getRazlog().equals("objekat i infrastruktura")){
                    m.setRashodiObjekat(m.getRashodiObjekat()+a.getTroskovi());
                } else if (a.getRadovi().getRazlog().equals("kapara za ovce")){
                    m.setRashodiNabavkaOvaca(m.getRashodiNabavkaOvaca()+a.getTroskovi());
                } else {
                    m.setRashodiOstalo(m.getRashodiOstalo()+a.getTroskovi());
                }
            }
        }

        return m;
    }
    
    private List<Meseci> popuniTabelu(){
        List<Meseci> lista = new ArrayList<Meseci>();
        for (int i=0; i<12; i++){
            
                lista.add(popuniMesec(i));
                if (i!=0){
                    lista.get(i).setBrojOvaca(lista.get(i).getBrojOvaca() + lista.get(i-1).getBrojOvaca());
                    lista.get(i).setBrojR(lista.get(i).getBrojR() + lista.get(i-1).getBrojR());
                    lista.get(i).setBrojRzenki(lista.get(i).getBrojRzenki() + lista.get(i-1).getBrojRzenki());
                } else {
                    lista.get(i).setBrojOvaca(lista.get(i).getBrojOvaca() + sviPocetak);
                    lista.get(i).setBrojR(lista.get(i).getBrojR() + romPocetak);
                    lista.get(i).setBrojRzenki(lista.get(i).getBrojRzenki() + romZPocetak);
                }
        }
        return lista;
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
     
          private List<Aktivnost> getAktivnosti(int godina) {
            List<Dan> danList = logic.getAllDays();
            ArrayList<Aktivnost> list = new ArrayList<Aktivnost>();
            for (Dan d: danList){
                if ((d.getDatum()/10000)== godina){
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
        Integer rom = 0;
        Integer romZ = 0;
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
            if (o.getProcenatR()==100){
                rom++;
                if (o.getPol()=='ž'){
                    romZ++;
                }
            }
        }

        Integer svega = jaganjaca + ovaca + ovnova;
        if (svi.equals("sviPocetak")){
            sviPocetak = svega;
            romPocetak = rom;
            romZPocetak = romZ;
            odraslihPocetak = ovnova + ovaca;
        }
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

    private void setJagnjenja(ArrayList<Aktivnost> listaJagnjenja){
        Integer ojagnjenoZivi = 0;
        Integer ojagnjenoZivihR = 0;
        Integer ojagnjenoZivihROdDviski = 0;
        Integer ojagnjenihR = 0;
        Integer ojagnjenihROdDviski = 0;
        Integer jagnjenjaR = 0;
        Integer jagnjenjaRDviske = 0;

        for (Aktivnost a: listaJagnjenja){
            Ovca o = new Ovca();            // nova ovca
            int jagnjadiUJednomJagnjenju = 0;
            
            for (Jagnjenje j:a.getListaJagnjenja()){
                ojagnjenih ++;
                if (j.getOvca().equals(o)){
                    jagnjadiUJednomJagnjenju ++;
                } else {
                    jagnjenja++;
                    o = j.getOvca();
                    setJagnjadiUJagnjenju(jagnjadiUJednomJagnjenju);
                    if (o.getProcenatR()==100.0f){
                        jagnjenjaR ++;
                        if (o.wasDviska(a.getDan())){
                            jagnjenjaRDviske ++;
                        }
                    }
                    jagnjadiUJednomJagnjenju = 1;
                }

                if (j.isJelZivo()){
                    ojagnjenoZivi ++;
                    if (j.getSheep().getProcenatR()==100.0f){
                        ojagnjenoZivihR ++;
                        if (j.getOvca().wasDviska(a.getDan())){
                            ojagnjenoZivihROdDviski ++;
                        }
                    }
                } 
                if (j.getSheep().getProcenatR()==100.0f){
                    ojagnjenihR ++;
                    if (j.getOvca().wasDviska(a.getDan())){
                        ojagnjenihROdDviski ++;
                    }
                }
            }
            setJagnjadiUJagnjenju(jagnjadiUJednomJagnjenju);
//            if (o.getProcenatR()==100.0f){
//               jagnjenjaR ++;
//                if (o.wasDviska(a.getDan())){
//                    jagnjenjaRDviske ++;
//                }
//            }
//            setJagnjadiUJagnjenju(jagnjadiUJednomJagnjenju);
        }
        
        params.put("ojagnjenoZivihR", ojagnjenoZivihR);
        params.put("ojagnjenoZivihROdDviski", ojagnjenoZivihROdDviski);
        params.put("ojagnjenihR", ojagnjenihR); 
        params.put("ojagnjenihROdDviski", ojagnjenihROdDviski); 
        params.put("jagnjenjaR", jagnjenjaR);
        params.put("jagnjenjaRDviske", jagnjenjaRDviske);
        params.put("procenatJagnjenjaRStarije", "" + 
                Aktivnost.round((float)(100.0*(ojagnjenihR-ojagnjenihROdDviski))/(jagnjenjaR-jagnjenjaRDviske),1) + "%"); 
        params.put("procenatJagnjenjaRDviske", "" + Aktivnost.round((float)(100.0*ojagnjenihROdDviski)/jagnjenjaRDviske,1) + "%"); 
        params.put("ojagnjenoZivih", ojagnjenoZivi); 
        params.put("jedinci", jedinci); 
        params.put("dvojke", dvojke); 
        params.put("trojke", trojke);
        params.put("cetvorke", cetvorke);
        params.put("petice", petice);
        params.put("ojagnjenih", ojagnjenih); 
        params.put("jagnjenja", jagnjenja); 
        params.put("procenatJagnjenja", "" + Aktivnost.round((float)(100.0*ojagnjenih)/jagnjenja, 1) + "%"); 
        promenaJaganjaca += ojagnjenoZivi;
        promenaSvega += ojagnjenoZivi;
    }
    
    private void setJagnjadiUJagnjenju(int jagnjadiUJednomJagnjenju){
        switch (jagnjadiUJednomJagnjenju){
            case 1: jedinci ++; break;
            case 2: dvojke ++; break;
            case 3: trojke ++; break;
            case 4: cetvorke ++; break;
            case 5: petice ++; break;
        }
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
        float trosak = 0;
        Integer ovnova = 0;
        Integer ovaca = 0;
        for (Aktivnost a: nabavke){
            trosak += a.getTroskovi();
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
        params.put("nabavkaOvacaRashod", Aktivnost.round(trosak, 0)); 
        sviRashodi += trosak;
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
    
    private void setPodatkeZaTrazenuGodinu(){
        ArrayList<Aktivnost> listaJagnjenja = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaNabavki = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaUginuca = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaProdaja = new ArrayList<Aktivnost>();
        ArrayList<Aktivnost> listaRashoda = new ArrayList<Aktivnost>();
        for (Aktivnost a: getAktivnosti(Integer.parseInt(godina))){
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
        float kapara = 0;
        float ostalo = 0;
        for (Aktivnost a: listaRashoda){
            if (a.getRadovi().getRazlog().equals("priprema/nabavka hrane")){
                hrana += a.getTroskovi();
            } else if (a.getRadovi().getRazlog().equals("objekat i infrastruktura")){
                objekat += a.getTroskovi();
            } else if (a.getRadovi().getRazlog().equals("kapara za ovce")){
                kapara += a.getTroskovi();
            }else {
                ostalo += a.getTroskovi();
            }       
        }
        sviRashodi += hrana + objekat + ostalo + kapara;
        params.put("hranaRashod", Aktivnost.round(hrana, 0)); 
        params.put("nabavkaOvacaRashod", (Float)params.get("nabavkaOvacaRashod") + Aktivnost.round(kapara, 0)); 
        params.put("objekatRashod", Aktivnost.round(objekat, 0)); 
        params.put("ostaloRashod", Aktivnost.round(ostalo, 0)); 
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

    public List<Aktivnost> getList() {
        return list;
    }

    public void setList(List<Aktivnost> list) {
        this.list = list;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public Integer getMesec() {
        return mesec;
    }

    public void setMesec(Integer mesec) {
        this.mesec = mesec;
    }

    public Integer getPromenaOvaca() {
        return promenaOvaca;
    }

    public void setPromenaOvaca(Integer promenaOvaca) {
        this.promenaOvaca = promenaOvaca;
    }

    public Integer getPromenaJaganjaca() {
        return promenaJaganjaca;
    }

    public void setPromenaJaganjaca(Integer promenaJaganjaca) {
        this.promenaJaganjaca = promenaJaganjaca;
    }

    public Integer getPromenaOvnova() {
        return promenaOvnova;
    }

    public void setPromenaOvnova(Integer promenaOvnova) {
        this.promenaOvnova = promenaOvnova;
    }

    public Integer getPromenaSvega() {
        return promenaSvega;
    }

    public void setPromenaSvega(Integer promenaSvega) {
        this.promenaSvega = promenaSvega;
    }

    public Integer getJagnjenja() {
        return jagnjenja;
    }

    public void setJagnjenja(Integer jagnjenja) {
        this.jagnjenja = jagnjenja;
    }

    public Integer getOjagnjenih() {
        return ojagnjenih;
    }

    public void setOjagnjenih(Integer ojagnjenih) {
        this.ojagnjenih = ojagnjenih;
    }

    public Integer getOjagnjenihZivih() {
        return ojagnjenihZivih;
    }

    public void setOjagnjenihZivih(Integer ojagnjenihZivih) {
        this.ojagnjenihZivih = ojagnjenihZivih;
    }

    public Integer getJedinci() {
        return jedinci;
    }

    public void setJedinci(Integer jedinci) {
        this.jedinci = jedinci;
    }

    public Integer getDvojke() {
        return dvojke;
    }

    public void setDvojke(Integer dvojke) {
        this.dvojke = dvojke;
    }

    public Integer getTrojke() {
        return trojke;
    }

    public void setTrojke(Integer trojke) {
        this.trojke = trojke;
    }

    public Integer getCetvorke() {
        return cetvorke;
    }

    public void setCetvorke(Integer cetvorke) {
        this.cetvorke = cetvorke;
    }

    public Integer getPetice() {
        return petice;
    }

    public void setPetice(Integer petice) {
        this.petice = petice;
    }

    public float getSviRashodi() {
        return sviRashodi;
    }

    public void setSviRashodi(float sviRashodi) {
        this.sviRashodi = sviRashodi;
    }

    public float getSviPrihodi() {
        return sviPrihodi;
    }

    public void setSviPrihodi(float sviPrihodi) {
        this.sviPrihodi = sviPrihodi;
    }

    public List<String> getMeseci() {
        return meseci;
    }

    public void setMeseci(List<String> meseci) {
        this.meseci = meseci;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

        
}
