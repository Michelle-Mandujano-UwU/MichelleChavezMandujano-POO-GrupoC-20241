
package dao;
import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import objetos.Alumno;
import objetos.Carrera;
import utils.Utils;
import objetos.Graduacion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Constantes;
/**
 *
 * @author 
 */
public class GraduacionDao {
 
     List<Graduacion> listGraduacionInSys = new ArrayList<Graduacion>();
    
     public List<Graduacion> getListGraduacionInSys() {
        return this.listGraduacionInSys;
    }
     
     public void addAllGraduaciones(List<Graduacion> listGraduacion) {
        listGraduacionInSys.addAll(listGraduacion);
        writeOnJSON();
    }
     
     public void initDataGraduacion() {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonAlumnos = Utils.initData(Constantes.NAME_FILE_GRADUACION);

        try {
            JSONArray jsonArrayGraduacion= (JSONArray) jsonAlumnos.get("graduacion");
            Iterator it = jsonArrayGraduacion.iterator();

            while (it.hasNext()) {
                JSONObject jsonGraduacion = (JSONObject) it.next();

                listGraduacionInSys.add(new Graduacion(jsonGraduacion, 0));
            }

            System.out.println("==> Terminé carga de graduacion... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void writeOnJSON(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Graduacion gra : listGraduacionInSys) {
            jsonArray.add(gra.toJson());
        }

        jsonObject.put("graduacion", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_GRADUACION);
    }
     
     public void printAllGraduacion(List<Carrera> listCarrerasInSys,
                List<Alumno> listAlumnosInSys){
        
        String[] columns = {"Carrera","Alumno","Promedio","Fecha Graduacion","Generacion"};
        Object[][] data = new Object[listGraduacionInSys.size()][columns.length];
        
        for (int i = 0;i< listGraduacionInSys.size();i++) {
            
            Graduacion graduacion = listGraduacionInSys.get(i);
            
            data[i][0] = listCarrerasInSys.stream().
                    filter( x -> x.getId() == graduacion.getIdCarrera())
                        .findFirst().get().getNombreCarrera();
            
            data[i][1] = listAlumnosInSys.stream().
                            filter( x -> x.getId() == graduacion.getIdAlumno())
                        .findFirst().get().getFullName();
            
            data[i][2] = graduacion.getPromedio();
            data[i][3] = graduacion.getFechaGraduacion();
            data[i][4] = graduacion.getGeneracion();
            
        }
        
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable(); 
        
    }
      
}
