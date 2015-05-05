package app.services;

import app.model.Ovca;
import com.avaje.ebean.EbeanServer;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OvcaServiceTest  {
    private EbeanServer server;

    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
    }
    
    @Test
    public void firstTest(){
        Ovca fromBase = server.find(Ovca.class, 1);
        assertEquals(fromBase.getOznaka(), "nepoznat");
    }

}
