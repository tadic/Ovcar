/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.Linija;
import app.model.Prodaja;
import app.model.Radovi;
import app.model.Uginuce;
import app.model.Vakcinacija;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author ivantadic
 */
public class DBServer {
    private final EbeanServer server;
    
    public EbeanServer server() {
        return server;
    }

    public DBServer(boolean dropAndCreateDatabase){
        //seedDatabase(ser);
        server = initializeDatabaseServer(dropAndCreateDatabase);
        //seedDatabase(server);
        if (dropAndCreateDatabase){
            seedDatabase(server);
        }
    }

    public EbeanServer initializeDatabaseServer(boolean dropAndCreateDatabase) {
        ServerConfig config = new ServerConfig();
        config.setName("ovceDB");

        
            DataSourceConfig sqLite = new DataSourceConfig();
            sqLite.setDriver("org.sqlite.JDBC");
            sqLite.setUsername("ivan");
            sqLite.setPassword("ivan");
            sqLite.setUrl("jdbc:sqlite:ovce.db");
            config.setDataSourceConfig(sqLite);
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
        

        config.setDefaultServer(false);
        config.setRegister(false);
        
        config.addClass(Dan.class);
        config.addClass(Linija.class);
        config.addClass(Uginuce.class);
        config.addClass(VrsteAktivnosti.class);
        config.addClass(Radovi.class);
        config.addClass(Aktivnost.class);
        config.addClass(NabavkaOvaca.class);
        config.addClass(Vakcinacija.class);
        config.addClass(Prodaja.class);
        config.addClass(Jagnjenje.class);
        config.addClass(Ovca.class);
        
        
        if (dropAndCreateDatabase) {
            config.setDdlGenerate(true);
            config.setDdlRun(true);
           // config.setDebugSql(true);
        }
     
        return EbeanServerFactory.create(config);
    }
   private void seedDatabase(EbeanServer server) throws OptimisticLockException {

   //     server.save(new VrsteAktivnosti("Hranjenje i ispaša", new Color(255,0,0)));
    //    server.save(new VrsteAktivnosti("Košenje", new Color(255,0,187)));
        server.save(new VrsteAktivnosti("Uginuce", new Color(255,0,0)));
        server.save(new VrsteAktivnosti("Nabavka ovaca", new Color(144,0,255)));
    //    server.save(new VrsteAktivnosti("Nabavka hrane", new Color(43,0,255)));
        server.save(new VrsteAktivnosti("Prodaja", new Color(0,185,255)));
   //     server.save(new VrsteAktivnosti("Parenje", new Color(0,255,200)));
        server.save(new VrsteAktivnosti("Jagnjenje", new Color(0,255,123)));
        server.save(new VrsteAktivnosti("Vakcinacija/Lečenje", new Color(81,255,0)));
    //    server.save(new VrsteAktivnosti("Šišanje", new Color(255,255,0)));
        server.save(new VrsteAktivnosti("Radovi/nabavke", new Color(255,174,0)));
        server.save(new Linija("Nepoznata"));
        server.save(new Linija("Root"));
        server.save(new Linija("Rohan"));
        server.save(new Linija("Ravnogorac"));
      
    }

}