package dao;

import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Utils;
import objetos.Carrera;
import objetos.Materia;

/**
 *
 * @author
 */
public class CarrerasDao {
    List<Carrera> listCarrerasInSystem = new ArrayList<>();
    
    public CarrerasDao() {
        
    }
    
    public List<Carrera> getListCarrerasInSystem(){
        return listCarrerasInSystem;
    }
    
    
    public Carrera getCarreraById(int id){
        return listCarrerasInSystem.stream()
                .filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    
    public void initDataCarreras(){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonCarreras = Utils.initData("carreras.json");
        
        try {
            JSONArray jsonArrayCarreras = (JSONArray) jsonCarreras.get("carreras");
            Iterator it = jsonArrayCarreras.iterator();
            
            while(it.hasNext()){
                JSONObject jsonCarrera = (JSONObject) it.next();
                listCarrerasInSystem.add(new Carrera(jsonCarrera));
            }
            
            System.out.println("==> Terminé carga de carreras... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Integer> printCarreras(){
        String[] columns = {"ID","Carrera"};
        Object[][] data = new Object[listCarrerasInSystem.size()][columns.length];
        
        for (int i = 0;i< listCarrerasInSystem.size();i++) {
            Carrera carrera = listCarrerasInSystem.get(i);
            
            data[i][0] = carrera.getId();
            data[i][1] = carrera.getNombreCarrera();
        }
        
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();  
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Carrera carrera : listCarrerasInSystem) {
            listValidsId.add(carrera.getId());
        }
        
        return listValidsId;

    }
}
