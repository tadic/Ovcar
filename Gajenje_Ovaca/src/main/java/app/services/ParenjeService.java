package app.services;

import app.model.Aktivnost;
import com.avaje.ebean.EbeanServer;


public class ParenjeService extends ActivityService{

    public ParenjeService(EbeanServer server) {
        super(server);
    }
     

    // metod radi tako sto brise sva stara parenja i samu staru aktivnost i snima novu...
    public void updateActivity(Aktivnost a){
        deleteActivity(a);
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
        nova.setParenja(a.getParenja());
        saveDayAndActivity(a.getDan(), nova);

    }

    public void createActivity(Aktivnost a){
            saveDayAndActivity(a.getDan(), a);
    }
    
    public void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        if (act!=null){
            server.delete(act);
        }
    }

    
}
