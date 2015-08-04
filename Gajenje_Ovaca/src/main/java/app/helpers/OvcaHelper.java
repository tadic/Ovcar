/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import app.model.Dan;
import app.model.Ovca;
import app.services.OvcaService;
import com.avaje.ebean.EbeanServer;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Days;

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

    public OvcaHelper() {
        
    }
    
    public float dnevniPrirastCetiriMeseca(Ovca o){
        if (o.getTezinaCetiriMeseca()<1){
            return 0.0f;
        }
        float tezinaRodjenje = o.getTezinaNaRodjenju();
        if (tezinaRodjenje<1){
            tezinaRodjenje = 3.0f;
        }
        float prirast = (o.getTezinaCetiriMeseca() - tezinaRodjenje)/120;
        return Math.round(prirast*1000);
        
    }
    public float racunajKoleno(Ovca a, Ovca b){
        a = list.get(list.indexOf(a));
        b = list.get(list.indexOf(b));
        if (a.equals(b)){
            return 0;
        } 

        if (a.getMajka().getId()<3 || a.getOtac().getId()<3 || b.getMajka().getId()<3 || b.getOtac().getId()<3){
            return 1.5f;
        }
        
        if(a.getMajka().equals(b.getMajka()) && a.getOtac().equals(b.getOtac())){
            return 1.0f;
        }
//        System.out.println("Poredjenje false: " +  a.getId() + " " + b.getId());
        
        return  (1.0f + min(racunajKoleno(a.getOtac(), b), racunajKoleno(a.getMajka(), b), 
                         racunajKoleno(a, b.getOtac()), racunajKoleno(a, b.getMajka())));
    }
     
    private float min(float a, float b, float c, float d){
        float min = a;
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
     public static int razlikaUDanima(Dan pocetak, Dan kraj){
        int p = pocetak.getDatum();
        int k = kraj.getDatum();
       
        return Days.daysBetween(new DateTime(new Date(p/10000, (p%10000)/100, p%100)), 
                    new DateTime(new Date(k/10000, (k%10000)/100, k%100))).getDays();
        
    }
}

    

