/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ivantadic
 */
@Entity
public class ProdajaJedinke {
    @Id
    private Integer id; 
    private Integer idJedinke;
    private Date datumProdaje;
    private String kategorija;
    private double cenaEvro;
    private double tezina;
    private String objasnjenje;
}
