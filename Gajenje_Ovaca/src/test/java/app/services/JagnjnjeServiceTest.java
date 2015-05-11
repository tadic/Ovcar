package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JagnjnjeServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private JagnjenjaService jagnjenjeService;
     private ProdajaService prodajaService;
     private Aktivnost snimljena;
  
     private int ukupnoOvaca, naFarmi;
     
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        jagnjenjeService = new JagnjenjaService(server);  
        prodajaService = new ProdajaService(server);
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost novaAktivnost = novaJagnjenja();
        
        jagnjenjeService.createActivity(novaAktivnost);
        
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        List<Jagnjenje> fromBaseJagnjenja = snimljena.getListaJagnjenja();
        
        assertTrue(ukupnoOvaca+4 == ovcaService.getAllSheep().size());        // da li je broj uvecan za 6
        assertTrue((naFarmi+3) == ovcaService.getSvaZivaGrla().size());     // broj na farmi uvecan za 3za tri
        // test aktivnost
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(0.0f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150606), snimljena.getDan().getDatum());
        assertEquals("jagnjenje ovaca", snimljena.getNapomena());
        assertEquals(fromBaseJagnjenja.size(), 4);
        List<Jagnjenje> jagnjenja = server.find(Jagnjenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(jagnjenja.size(), 4);
       
        Ovca o1 = ovcaService.getOvca(24);  // Saara
        Ovca o2 = ovcaService.getOvca(34);  // Flekica
        Ovca otac1 = ovcaService.getOvca(1);
        Ovca otac2 = ovcaService.getOvca(69);   // Samson
        //jagnjenja
        assertEquals(fromBaseJagnjenja.get(0).getOvca(),o1);
        assertEquals(fromBaseJagnjenja.get(0).getNapomena(),"sve ok");
        assertTrue(fromBaseJagnjenja.get(0).isJelZivo());
        
        // jagnje1  
        Ovca jagnje1 = fromBaseJagnjenja.get(0).getSheep();
        assertEquals("na farmi", jagnje1.getStatus());
        assertNull(jagnje1.getOznaka());
        assertEquals(jagnje1.getPol(),'m');
        assertTrue(jagnje1.getProcenatR() == 100.0f);
        assertEquals(jagnje1.getOpis(),"crno");
        assertEquals(jagnje1.getDatumRodjenja(),"06.07.2015");
        assertEquals(jagnje1.getPoreklo(), "farma Tadići");
        assertEquals(jagnje1.getAktuelno(), "# 06.07.2015");
        assertEquals(jagnje1.getLinija().getImeLinije(), "Nepoznata");
        assertTrue(jagnje1.getLeglo()== 2);
        assertEquals(jagnje1.getOtac(), otac1);
        assertEquals(jagnje1.getMajka(), o1);
        
        
        assertEquals(fromBaseJagnjenja.get(1).getNapomena(),"sve ok");
        assertEquals(fromBaseJagnjenja.get(1).getOvca(),o1);
        assertTrue(fromBaseJagnjenja.get(1).isJelZivo());
        // jagnje2  
        Ovca jagnje2 = fromBaseJagnjenja.get(1).getSheep();
        assertEquals("na farmi", jagnje2.getStatus());
        assertNull(jagnje2.getOznaka());
        assertTrue(jagnje2.getPol() == 'ž');
        assertTrue(jagnje2.getProcenatR() == 100.0f);
        assertEquals(jagnje2.getOpis(),"crno");
        assertEquals(jagnje2.getDatumRodjenja(),"06.07.2015");
        assertEquals(jagnje2.getPoreklo(), "farma Tadići");
        assertEquals(jagnje2.getAktuelno(), "# 06.07.2015");
        assertEquals(jagnje2.getLinija().getImeLinije(), "Nepoznata");
        assertTrue(jagnje2.getLeglo()== 2);
        assertEquals(jagnje2.getOtac(), otac1);
        assertEquals(jagnje2.getMajka(), o1);
        
        
        assertEquals(fromBaseJagnjenja.get(2).getOvca(),o2);
        assertEquals(fromBaseJagnjenja.get(2).getNapomena(),"sve ok");
        assertTrue(fromBaseJagnjenja.get(2).isJelZivo());
        // jagnje3      
        Ovca jagnje3 = fromBaseJagnjenja.get(2).getSheep();
        assertEquals("na farmi", jagnje3.getStatus());
        assertNull(jagnje3.getOznaka());
        assertEquals(jagnje3.getPol(),'m');
        assertTrue(jagnje3.getProcenatR() == 100.0f);
        assertEquals(jagnje3.getOpis(),"crno");
        assertEquals(jagnje3.getDatumRodjenja(),"06.07.2015");
        assertEquals(jagnje3.getPoreklo(), "farma Tadići");
        assertEquals(jagnje3.getAktuelno(), "# 06.07.2015");
        assertEquals(jagnje3.getLinija().getImeLinije(), "Ravnogorac");
        assertTrue(jagnje3.getLeglo()== 2);
        assertEquals(jagnje3.getOtac(), otac2);
        assertEquals(jagnje3.getMajka(), o2);
        
        
        assertEquals(fromBaseJagnjenja.get(3).getOvca(),o2);
        assertNull(fromBaseJagnjenja.get(3).getNapomena());
        assertFalse(fromBaseJagnjenja.get(3).isJelZivo());
        // jagnje4     
        Ovca jagnje4 = fromBaseJagnjenja.get(3).getSheep();
        assertEquals("mrtvo rođeno", jagnje4.getStatus());
        assertNull(jagnje4.getOznaka());
        assertEquals(jagnje4.getPol(),'m');
        assertTrue(jagnje4.getProcenatR() == 100.0f);
        assertEquals(jagnje4.getOpis(),"crno");
        assertEquals(jagnje4.getDatumRodjenja(),"06.07.2015");
        assertEquals(jagnje4.getPoreklo(), "farma Tadići");
        assertEquals(jagnje4.getAktuelno(), "# 06.07.2015");
        assertEquals(jagnje4.getLinija().getImeLinije(), "Ravnogorac");
        assertTrue(jagnje4.getLeglo()== 2);
        assertEquals(jagnje4.getOtac(), otac2);
        assertEquals(jagnje4.getMajka(), o2);
        
        jagnjenjeService.deleteActivity(snimljena);
        
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        jagnjenja = server.find(Jagnjenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(jagnjenja.size(), 0);

        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
 
  @Test
    public void updateActivityPromenaBrojaJagnjadiTest(){
        Aktivnost novaAktivnost = novaJagnjenja();
        jagnjenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        assertTrue(ukupnoOvaca+4 == ovcaService.getAllSheep().size());        // da li je broj uvecan za 4
        assertTrue((naFarmi+3) == ovcaService.getSvaZivaGrla().size());     // broj na farmi uvecan za 3 za tri
        
        int stariId = snimljena.getId();
       // brise  se jedno jagnjenje (jagnje) iz prvog jagnjenja
        snimljena.getListaJagnjenja().remove(1);     
        // dodaje dva jagnjeta drugoj ovci
        Ovca o1 = ovcaService.getOvca(24);  // Saara
        Ovca o2 = ovcaService.getOvca(34);  // Flekica
        Ovca otac2 = ovcaService.getOvca(69);
        Jagnjenje j3 = new Jagnjenje();
        j3.setJelZivo(true);
        j3.setOvca(o2);
        j3.setNapomena("sve ok");
        Ovca jagnje3 = new Ovca("na farmi", null, 'ž');
        jagnje3.setProcenatR(100.0f);
        jagnje3.setOpis("crno");
        jagnje3.setLeglo(2);
        jagnje3.setMajka(j3.getOvca());
        jagnje3.setOtac(otac2);
        jagnje3.setLinija(otac2.getLinija());
        j3.setSheep(jagnje3);
        
        Jagnjenje j4 = new Jagnjenje();
        j4.setJelZivo(true);
        j4.setOvca(o2);
        j4.setNapomena("sve ok");
        Ovca jagnje4 = new Ovca("na farmi", null, 'ž');
        jagnje4.setProcenatR(100.0f);
        jagnje4.setOpis("crno");
        jagnje4.setLeglo(2);
        jagnje4.setMajka(j4.getOvca());
        jagnje4.setOtac(otac2);
        jagnje4.setLinija(otac2.getLinija());
        j4.setSheep(jagnje4);
        
        snimljena.getListaJagnjenja().add(j3);
        snimljena.getListaJagnjenja().add(j4);
        
        // menja osnovne podatke
        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 2, 4));
        snimljena.setNapomena("novaNapomena");
        
        jagnjenjeService.updateActivity(snimljena);  
       
        snimljena =  server.find(Aktivnost.class, snimljena.getId());
        
        List<Jagnjenje> fromBaseJagnjenja = snimljena.getListaJagnjenja();
        List<Jagnjenje> janjenja = server.find(Jagnjenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue((ukupnoOvaca+5) == ovcaService.getAllSheep().size());        // da li je broj ovaca smanjen zbog bisanja ocajen
        assertTrue((naFarmi+4) == ovcaService.getSvaZivaGrla().size());
        assertEquals(janjenja.size(), 5);           // broj snimljenih merenja
        assertEquals(fromBaseJagnjenja.size(), 5);   // velicina liste snimljenih merenja
        
        // provera updejtovanih podataka

        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150204), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        assertEquals(o1, fromBaseJagnjenja.get(0).getOvca());
        assertEquals(o2, fromBaseJagnjenja.get(1).getOvca());
        assertEquals(o2, fromBaseJagnjenja.get(2).getOvca());
        assertEquals(o2, fromBaseJagnjenja.get(3).getOvca());
        assertEquals(o2, fromBaseJagnjenja.get(4).getOvca());
        assertEquals(otac2, fromBaseJagnjenja.get(1).getSheep().getOtac());
        assertEquals(otac2, fromBaseJagnjenja.get(2).getSheep().getOtac());
        assertEquals(otac2, fromBaseJagnjenja.get(3).getSheep().getOtac());
        assertEquals(otac2, fromBaseJagnjenja.get(4).getSheep().getOtac());
        assertEquals("Nepoznata", fromBaseJagnjenja.get(0).getSheep().getLinija().getImeLinije());
        assertEquals("Ravnogorac", fromBaseJagnjenja.get(1).getSheep().getLinija().getImeLinije());
        assertEquals("Ravnogorac", fromBaseJagnjenja.get(2).getSheep().getLinija().getImeLinije());
        assertEquals("Ravnogorac", fromBaseJagnjenja.get(3).getSheep().getLinija().getImeLinije());
        assertEquals("Ravnogorac", fromBaseJagnjenja.get(4).getSheep().getLinija().getImeLinije());
        assertEquals('m', fromBaseJagnjenja.get(0).getSheep().getPol());
        assertEquals('m', fromBaseJagnjenja.get(1).getSheep().getPol());
        assertEquals('m', fromBaseJagnjenja.get(2).getSheep().getPol());
        assertEquals('ž', fromBaseJagnjenja.get(3).getSheep().getPol());
        assertEquals('ž', fromBaseJagnjenja.get(4).getSheep().getPol());
        
        
        // da li je ocistio staru aktivnost
        assertNotNull(server.find(Aktivnost.class, stariId));        
        assertTrue(snimljena.getId() == stariId);

        //delete Test

        jagnjenjeService.deleteActivity(snimljena);
        
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        janjenja = server.find(Jagnjenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(janjenja.size(), 0);
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
  /*
    @Test
    public void updateActivityTestStatus(){
        Aktivnost novaAktivnost = novaJagnjenja();
        jagnjenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Aktivnost uginuce = new Aktivnost();
        uginuce.setVrstaAktivnosti(new VrsteAktivnosti("Uginuce"));
        uginuce.setDan(new Dan(2014, 3, 24));
        Ovca uginulaO = snimljena.getNabavljenaGrla().get(0).getSheep();
        Uginuce u = new Uginuce();
        u.setO(uginulaO);
        uginuce.setUginuce(u);
        prodajaService.saveActivity(uginuce);
        
        assertEquals("uginulo", ovcaService.getOvca(uginulaO.getId()).getStatus());
        
        snimljena.getNabavljenaGrla().get(0).getSheep().setOpis("bla");
        snimljena.getNabavljenaGrla().get(0).setNapomena("blabla");
        jagnjenjeService.updateActivity(snimljena);
        
        Aktivnost no = server.find(Aktivnost.class, snimljena.getId());
        
        List<NabavkaOvaca> fromBaseNabavkeO =  no.getNabavljenaGrla();
        assertEquals("bla", fromBaseNabavkeO.get(0).getSheep().getOpis());
        assertEquals("uginulo", fromBaseNabavkeO.get(0).getSheep().getStatus());
        assertEquals("blabla", fromBaseNabavkeO.get(0).getNapomena());
        
        prodajaService.deleteActivity(uginuce);
        no = server.find(Aktivnost.class, snimljena.getId());
        jagnjenjeService.deleteActivity(no);
    }
   
*/
    private Aktivnost novaJagnjenja(){
        Dan d = new Dan(2015, 6, 6);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setVrstaAktivnosti(new VrsteAktivnosti("Jagnjenje"));
        a.setNapomena("jagnjenje ovaca");
        
        Ovca otac1 = ovcaService.getOvca(1);
        Ovca otac2 = ovcaService.getOvca(69);   // Samson
        List<Jagnjenje> jagnjenja = new ArrayList<Jagnjenje>();
        Jagnjenje j1 = new Jagnjenje();
        Ovca o1 = ovcaService.getOvca(24);  // Saara
        j1.setJelZivo(true);
        j1.setOvca(o1);
        j1.setNapomena("sve ok");
        Ovca jagnje = new Ovca("na farmi", null, 'm');
        jagnje.setProcenatR(100.0f);
        jagnje.setOpis("crno");
        jagnje.setLeglo(2);
        jagnje.setMajka(j1.getOvca());
        jagnje.setOtac(otac1);
        jagnje.setLinija(otac1.getLinija());
        j1.setSheep(jagnje);
        
        Jagnjenje j2 = new Jagnjenje();
        j2.setJelZivo(true);
        j2.setOvca(o1);
        j2.setNapomena("sve ok");
        Ovca jagnje2 = new Ovca("na farmi", null, 'ž');
        jagnje2.setProcenatR(100.0f);
        jagnje2.setOpis("crno");
        jagnje2.setLeglo(2);
        jagnje2.setMajka(j2.getOvca());
        jagnje2.setOtac(otac1);
        jagnje2.setLinija(otac1.getLinija());
        j2.setSheep(jagnje2);
        
        Ovca o2 = ovcaService.getOvca(34);  // Flekica
        Jagnjenje j3 = new Jagnjenje();
        j3.setJelZivo(true);
        j3.setOvca(o2);
        j3.setNapomena("sve ok");
        Ovca jagnje3 = new Ovca("na farmi", null, 'm');
        jagnje3.setProcenatR(100.0f);
        jagnje3.setOpis("crno");
        jagnje3.setTezinaNaRodjenju(2.8f);
        jagnje3.setLeglo(2);
        jagnje3.setMajka(j3.getOvca());
        jagnje3.setOtac(otac2);
        jagnje3.setLinija(otac2.getLinija());
        j3.setSheep(jagnje3);
        
        Jagnjenje j4 = new Jagnjenje();
        j4.setJelZivo(false);
        j4.setOvca(o2);
        Ovca jagnje4 = new Ovca("mrtvo rođeno", null, 'm');
        jagnje4.setProcenatR(100.0f);
        jagnje4.setOpis("crno");
        jagnje4.setTezinaNaRodjenju(3.4f);
        jagnje4.setLeglo(2);
        jagnje4.setMajka(j4.getOvca());
        jagnje4.setOtac(otac2);
        jagnje4.setLinija(otac2.getLinija());
        j4.setSheep(jagnje4);

        jagnjenja.add(j1);
        jagnjenja.add(j2);
        jagnjenja.add(j3);
        jagnjenja.add(j4);
        
        a.setListaJagnjenja(jagnjenja);
        return a;
    }

}
