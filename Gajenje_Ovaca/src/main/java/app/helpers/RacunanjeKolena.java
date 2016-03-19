package app.helpers;

import app.logic.Logic;
import app.model.Ovca;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class RacunanjeKolena {
    private Logic logic;
    private Ovca praOtac, praMajka;
    HashMap<Ovca, HashMap<Ovca, Float>> mapaKolena;
    
    
    public RacunanjeKolena(Logic logic){
        this.logic = logic;
        praOtac = logic.getOvca(1);
        praMajka = logic.getOvca(2);
        createMap();
    }
  
    private void ukloniPotomke(Ovca o, HashMap<Ovca, Float> ovanMap){
        Float index = ovanMap.get(o);
        HashMap<Ovca, Float> ovcaMapa = mapaKolena.get(o);
        for (Map.Entry<Ovca, Float> entry : ovcaMapa.entrySet()) {
            if (ovanMap.containsKey(entry.getKey())){       
                Ovca ovca = entry.getKey();
                Float value = ovanMap.get(ovca);
                ovanMap.put(ovca, value - index*ovcaMapa.get(entry.getKey()));
               // System.out.println("new value "+ (value - index*ovcaMapa.get(entry.getKey())));
                
            }
        }
       // System.out.println("Bio: " + ovanMap);
    }
    
    public String getUkrvljenost(Ovca ovca, Ovca ovan){
        Float ukrvljenost = 0.0f;
        Float maxUkrvljenost = 1.0f;        // 1 znaci da zna samo za sebe...

        HashMap<Ovca, Float> ovanClone = new HashMap<Ovca, Float>(mapaKolena.get(ovan));
        for (Map.Entry<Ovca, Float> entry : mapaKolena.get(ovca).entrySet()) {
            if (entry.getValue()<maxUkrvljenost){
                maxUkrvljenost = entry.getValue();
            }
            if (ovanClone.containsKey(entry.getKey())){
                ukrvljenost += ovanClone.get(entry.getKey())* entry.getValue();
                ukloniPotomke(entry.getKey(), ovanClone);
            } 
        }
        if (ukrvljenost==0.0f){
            maxUkrvljenost /=2;
            return "<" + maxUkrvljenost*100 + "%";
        }
        //System.out.println(""+ovca + ovan + " ukrlj:  "+ukrvljenost);
        return "" + ukrvljenost*100 + "%";
    }
    
    
    private Ovca getPraOtac(){
        return praOtac;
    }
    
    private Ovca getPraMajka(){
        return praMajka;
    }
    private List<Ovca> getListaZaTrazenjePorekla (Logic logic){
        List<Ovca> listaOvaca = logic.getAllSheep();
        listaOvaca.remove(getPraMajka());
        listaOvaca.remove(getPraOtac());
        return listaOvaca;
    }
    
    private void preslikajPoreklo(HashMap<Ovca, Float> poreklo, HashMap<Ovca, Float> porekloOca){
        for (Map.Entry<Ovca, Float> entry : porekloOca.entrySet()) {
                if (poreklo.containsKey(entry.getKey())){
                    poreklo.put(entry.getKey(), poreklo.get(entry.getKey()) + entry.getValue()/2);
                } else{
                    poreklo.put(entry.getKey(), entry.getValue()/2);
                }
        }  
    }
    
    private  HashMap<Ovca, Float> sortMapByValues(Map<Ovca, Float> unsortMap) {
		// Convert Map to List
		List<Map.Entry<Ovca, Float>> list = 
			new LinkedList<Map.Entry<Ovca, Float>>(unsortMap.entrySet());
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Ovca, Float>>() {
			public int compare(Map.Entry<Ovca, Float> o1,
                                           Map.Entry<Ovca, Float> o2) {
				return -(o1.getValue()).compareTo(o2.getValue());
			}
		});
		// Convert sorted map back to a Map
		HashMap<Ovca, Float> sortedMap = new LinkedHashMap<Ovca, Float>();
		for (Iterator<Map.Entry<Ovca, Float>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Ovca, Float> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
    }
    
    private HashMap<Ovca, Float> createPoreklo(Ovca o , HashMap<Ovca, Float> porekloOca, HashMap<Ovca, Float> porekloMajke){
        HashMap<Ovca, Float> poreklo = new HashMap<Ovca, Float>();

        poreklo.put(o, 1.0f);
        if (!o.getOtac().equals(getPraOtac())){
            preslikajPoreklo(poreklo, porekloOca);
        }
        if (!o.getMajka().equals(getPraMajka())){
            preslikajPoreklo(poreklo, porekloMajke);
        }
        return sortMapByValues(poreklo);
    }
    
    private HashMap<Ovca, HashMap<Ovca, Float>> formirajMapuSaPraroditeljima(){
        HashMap<Ovca, HashMap<Ovca, Float>> mapa = new HashMap<Ovca, HashMap<Ovca, Float>>();
        mapa.put(getPraOtac(), new HashMap<Ovca, Float>());
        mapa.put(getPraMajka(), new HashMap<Ovca, Float>());
        return mapa;
    }
    
    public void createMap(){
        HashMap<Ovca, HashMap<Ovca, Float>> mapa = formirajMapuSaPraroditeljima();
        
        List<Ovca> listaOvaca = getListaZaTrazenjePorekla(logic); // sve bez praroditelja (nepoznatih)
        for (int kolena=0; kolena!=listaOvaca.size(); ){
            for (int i=0; i<listaOvaca.size(); i++){
                Ovca o = listaOvaca.get(i);
                if (mapa.containsKey(o.getOtac()) && mapa.containsKey(o.getMajka())){
                    mapa.put(o, createPoreklo(o, mapa.get(o.getOtac()), mapa.get(o.getMajka())));
                    //System.out.println(o.getId()+", "+o+" - "+mapa.get(o));
                    listaOvaca.remove(o);
                }
            }
        }
        mapaKolena = mapa;
    }
}
