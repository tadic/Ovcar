package app.services;


import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.Linija;
import app.model.Merenje;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.Parenje;
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


public class DBHelper {
        public EbeanServer initializeDatabaseServer() {
        ServerConfig config = new ServerConfig();
        config.setName("ovceTestDB");

        
            DataSourceConfig sqLite = new DataSourceConfig();
            sqLite.setDriver("org.sqlite.JDBC");
            sqLite.setUsername("ivan");
            sqLite.setPassword("ivan");
            sqLite.setUrl("jdbc:sqlite:ovceTest.db");
            config.setDataSourceConfig(sqLite);
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
        

        config.setDefaultServer(false);
        config.setRegister(false);
        config.addClass(Ovca.class);
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
        config.addClass(Merenje.class);
        config.addClass(Parenje.class);

        config.setDefaultServer(true);
        return EbeanServerFactory.create(config);
    }
    
}
