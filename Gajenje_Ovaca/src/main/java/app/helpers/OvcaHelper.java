/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import app.model.Ovca;
import app.services.OvcaService;
import com.avaje.ebean.EbeanServer;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class OvcaHelper {
    private OvcaService ovcaService;
    private Ovca nepoznata, nepoznat; 
    private List<Ovca> list;
    
    public OvcaHelper(EbeanServer server){
        ovcaService = new OvcaService(server);
        nepoznata = ovcaService.getOvca("nepoznata");
        nepoznat = ovcaService.getOvca("nepoznat");
        list = ovcaService.getAllSheep();
    }
    
    public int racunajKoleno(Ovca a, Ovca b){
        a = list.get(list.indexOf(a));
        b = list.get(list.indexOf(b));
        if (a.equals(b)){
            return 0;
        } 
        if (a.getId()<3 || b.getId()<3){    // id<3 u bazi podataka znaci da je nepoznat ili nepoznata...
            return 1;
        }
        System.out.println("Poredjenje false: " +  a.getId() + " " + b.getId());
//        Ovca aOtac = ovcaService.getOvca(a.getOtac().getId());
//        Ovca aMajka = ovcaService.getOvca(a.getMajka().getId());
//                
//        Ovca bOtac = ovcaService.getOvca(b.getOtac().getId());
//        Ovca bMajka = ovcaService.getOvca(b.getMajka().getId());
        
        return 1 + min(racunajKoleno(a.getOtac(), b), racunajKoleno(a.getMajka(), b), 
                racunajKoleno(a, b.getOtac()), racunajKoleno(a, b.getMajka()));
    }
     
    private int min(int a, int b, int c, int d){
        int min = a;
        if (b<min){
            min = b;
        }
        if (c<min){
            min = c;
        }
        if (d<min){
            min = d;
        }
        return min;
    }
}

    

