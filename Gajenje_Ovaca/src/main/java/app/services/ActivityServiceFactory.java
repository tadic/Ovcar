
package app.services;

import app.model.Aktivnost;
import com.avaje.ebean.EbeanServer;
import java.util.HashMap;

public class ActivityServiceFactory {
    private HashMap<String, ActivityService> map = new HashMap<String, ActivityService>();
    
    public ActivityServiceFactory(EbeanServer server){
        map.put("Nabavka ovaca", new NabavkaOvacaService(server));
        map.put("Jagnjenje", new JagnjenjaService(server));
        map.put("Uginuce", new UginucaService(server));
        map.put("Prodaja", new ProdajaService(server));
        map.put("Vakcinacija/Lečenje", new VakcinacijaService(server));
        map.put("Radovi/nabavke", new RadoviService(server));
    }
    
    public ActivityService getService(Aktivnost a){
        if (a==null){
            return map.get("Prodaja");
        }
        String nazivAktivnosti = a.getVrstaAktivnosti().getName();
        return map.get(nazivAktivnosti);
    }
}
