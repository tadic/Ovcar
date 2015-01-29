
package app.logic;

import app.config.DataBase;
import app.helpers.OvcaHelper;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Linija;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import app.services.ActivityService;
import app.services.ActivityServiceFactory;
import app.services.DanService;
import app.services.LinijeService;
import app.services.OvcaService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Logic {


    private DataBase db;
    //private List<Dan> listOfDays;
    private DanService danService;
    private LinijeService linijeService;
    private OvcaService ovcaService;
    private Calendar selectedDay;
    
    private List<VrsteAktivnosti> typesOfActivities;
    
    public Logic(DataBase dataBase){
        this.db = dataBase;
        danService = new DanService(db.server());
        linijeService = new LinijeService(db.server());
        ovcaService = new OvcaService(db.server());
        
        initialiseData();
    }

    private void initialiseData(){
        selectedDay = Calendar.getInstance();
        typesOfActivities = activityService(null).getAllTypesOfActivities();
    }

    public Calendar getSelectedDay(){
        return selectedDay;
    }
    public void setSelectedDay(Calendar day){
        selectedDay = day;
    }
    public void setSelectedDay(int year, int month, int day){
        selectedDay.set(year, month, day);
    }
    public void increaseSelectedDay(){
        selectedDay.add(Calendar.DAY_OF_MONTH, 1);
    }
    public void decreaseSelectedDate(){
        selectedDay.add(Calendar.DAY_OF_MONTH, -1);
    }
    public void setToday(){
        selectedDay = Calendar.getInstance();
    }
    public int getMonth(){
        return selectedDay.get(Calendar.MONTH);
    }
    public int getYear(){
        return selectedDay.get(Calendar.YEAR);
    }
    
    public List<Aktivnost> getActivitiesFrom(Calendar date) {
        Dan day = getDayWithDate(date);
        if (day!=null){
            return day.getAktivnosti();
        }
        return  (new ArrayList<Aktivnost>());
    }
    public Dan getDayWithDate(Calendar date){
        Dan day = new Dan(date);
        return getDayWithDate(day.getDatum());
    }
    public Dan getDayWithDate(Integer date){
        Dan day = danService.getDayWithDate(date);
        if (day!=null){
            return day;
        }
        return new Dan(date);
    }
    
    public VrsteAktivnosti getVrstaAktivnosti(String name){
        return typesOfActivities.get(typesOfActivities.indexOf(new VrsteAktivnosti(name)));
    }
    
    public List<VrsteAktivnosti> getTypesOfActivities(){
        return typesOfActivities;
    }

    public Aktivnost getActivityWithId(Integer id) {
        return activityService(null).getActivityWithId(id);
    }

    public void saveActivity(Aktivnost aktivnost) {
        activityService(aktivnost).saveActivity(aktivnost);
    }

    public List<Ovca> getAllSheep() {
        return ovcaService.getAllSheep();
    }

    public void updateOvca(Ovca ovca) {
        ovcaService.saveSheep(ovca);
    }

    public void delete(Aktivnost aktivnost) {
        activityService(aktivnost).deleteActivity(aktivnost);
    }

    public List<Linija> getLinije() {
        return linijeService.getLinije();
    }

    public Ovca getOvca(int id) {
        return ovcaService.getOvca(id);
    }

    public List<Ovca> getOvceZaJagnjenje() {
        return ovcaService.getOvceZaJagnjenje(); 
    }

    public List<Ovca> listaOvnova() {
        return ovcaService.listaOvnova();
    }

    public List<Ovca> getSvaZivaGrla() {
        return ovcaService.getSvaZivaGrla();
    }

    public List<Dan> getAllDays() {
        return danService.getAllDays();
    }

    public Dan getDan(Integer id) {
        return danService.getDan(id);
    }
    private ActivityService activityService(Aktivnost aktivnost){
        ActivityServiceFactory serviceFactory = new ActivityServiceFactory(db.server());
        return serviceFactory.getService(aktivnost);
    }
    
    private ActivityService activityService(){
        ActivityServiceFactory serviceFactory = new ActivityServiceFactory(db.server());
        return serviceFactory.getService(null);
    }

    public void updateOvcaFromList(Ovca o) {
        ovcaService.updateOvcaFromList(o);
    }

    public List<Aktivnost> getPoslednjaMerenja() {
        return activityService().getPoslednjaMerenja(5);
    }

    public float izracunajKoleno(Ovca a, Ovca b) {
        OvcaHelper ovcaHelper = new OvcaHelper(db.server());
        return ovcaHelper.racunajKoleno(a, b);
    }

    public float getPrirast(Ovca o) {
       OvcaHelper ovcaHelper = new OvcaHelper(db.server());
       return ovcaHelper.dnevniPrirastCetiriMeseca(o) -0.01f;
    }

    public List<Ovca> getSveOvnove() {
        return ovcaService.getSveOvnove();
    }
   

}
