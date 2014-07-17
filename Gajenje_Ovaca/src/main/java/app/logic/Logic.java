
package app.logic;

import app.database.DBServer;
import app.database.DataBase;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Linija;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Logic {


    private DataBase db;
    //private List<Dan> listOfDays;
    private Calendar selectedDay;
    private List<VrsteAktivnosti> typesOfActivities;
    
    public Logic(DataBase db){
        this.db = db;
        initialiseData();
    }

    private void initialiseData(){
        selectedDay = Calendar.getInstance();
        typesOfActivities = db.getAllTypesOfActivities();
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
        Dan day = db.getDayWithDate(date);
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
    
    /*public void saveDay(Dan day){
        dbServer.saveDay(day);
        if (listOfDays.contains(day)){
            Dan d = listOfDays.get(listOfDays.indexOf(day));
            d.setAktivnosti(day.getAktivnosti());
        } else {
             Dan dan = new Dan(day.getDatum());
             dan.setId(day.getId());
             dan.setAktivnosti(day.getAktivnosti());
             listOfDays.add(dan);
             System.out.println("velicina liste je " + listOfDays.size());
        }
    }
*/
    public Aktivnost getActivityWithId(Integer id) {
        return db.getActivityWithId(id);
    }

    public void saveActivity(Aktivnost aktivnost) {
        db.saveActivity(aktivnost);
    }

    public List<Ovca> getAllSheep() {
        return db.getAllSheep();
    }
    public Ovca getOvca(String oznaka) {
       return db.getOvca(oznaka);
    }

    public void updateOvca(Ovca ovca) {
        db.update(ovca);
    }

    public void delete(Aktivnost aktivnost) {
        db.deleteActivity(aktivnost);
    }

    public List<Linija> getLinije() {
        return db.getLinije();
    }

    public Ovca getOvca(int id) {
        return db.getOvca(id);
    }

    public List<Ovca> getOvceZaJagnjenje() {
        return db.getOvceZaJagnjenje(); 
    }

    public List<Ovca> listaOvnova() {
        return db.listaOvnova();
    }




}
