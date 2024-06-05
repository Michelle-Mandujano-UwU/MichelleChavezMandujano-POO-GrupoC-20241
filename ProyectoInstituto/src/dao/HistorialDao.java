package dao;

import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import objetos.Historial;
import objetos.Alumno;
import objetos.Calificacion;
import objetos.Profesor;
import objetos.Coordinador;
import objetos.Materia;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Constantes;
import utils.Utils;

/**
 *
 * @author
 */
public class HistorialDao {

    List<Historial> listHistorialInSys = new ArrayList<Historial>();

    public List<Historial> getListHistorialInSys() {
        return this.listHistorialInSys;
    }

    public void addHistorial(List<Historial> listHistorial) {
        listHistorialInSys.addAll(listHistorial);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Historial historial : listHistorialInSys) {
            jsonArray.add(historial.toJson());
        }

        jsonObject.put("historial", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_HISTORIAL);
    }

    public List<Historial> getListHistorialByAlumno(int idAlumno) {
        return listHistorialInSys.stream()
                .filter(x -> x.getIdAlumno() == idAlumno).collect(Collectors.toList());
    }

    public void initDataHistorial() {
        JSONObject jsonAlumnos = Utils.initData(Constantes.NAME_FILE_HISTORIAL);

        try {
            JSONArray jsonArrayHistorial = (JSONArray) jsonAlumnos.get("historial");
            Iterator it = jsonArrayHistorial.iterator();

            while (it.hasNext()) {
                JSONObject jsonHistorial = (JSONObject) it.next();

                List<Integer> listMateriasCursadas = new ArrayList<>();

                JSONArray jsonArrayMateriasCursadas = (JSONArray) jsonHistorial.get("materiasCursadas");
                Iterator itMats = jsonArrayMateriasCursadas.iterator();

                while (itMats.hasNext()) {
                    JSONObject jsonMateria = (JSONObject) itMats.next();
                    listMateriasCursadas.add(
                            Integer.parseInt(
                                    jsonMateria.get("id").toString()));
                }

               // List<Integer> listProfesoresImpartieron = new ArrayList<>();

                /*JSONArray jsonArrayProfesoresImpartieron = (JSONArray) jsonHistorial.get("profesoresImpartieron");
                Iterator itProf = jsonArrayProfesoresImpartieron.iterator();

                while (itProf.hasNext()) {
                    JSONObject jsonProfesor = (JSONObject) itProf.next();
                    listProfesoresImpartieron.add(
                            Integer.parseInt(
                                    jsonProfesor.get("id").toString()));
                }*/

                listHistorialInSys.add(new Historial(jsonHistorial, listMateriasCursadas));
            }

            System.out.println("==> Terminé carga de historial... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printHistorialByAlumno(Alumno alumno,
            List<Profesor> listProfesores,
            List<Coordinador> listCoordinador,
            List<Materia> listMaterias) {

        String[] columns = {"Semestre", "Materias Cursadas", "Profesores", "Promedio Semestre"};

        List<Historial> listHistorial = getListHistorialByAlumno(alumno.getId());

        Object[][] data = new Object[listHistorial.size()][columns.length];

        for (int i = 0; i < listHistorial.size(); i++) {
            String materiasCursadas = "";
            String profesores = "";

            for (Integer idMateria : listHistorial.get(i).getMateriasCursadas()) {
                Materia materia = listMaterias.stream().filter(x -> x.getId() == idMateria).findFirst().orElse(null);
                materiasCursadas += materia.getNombreMateria() + ", ";
            }

            /*for (Integer idProfesor : listHistorial.get(i).getProfesoresImpartieron()) {
                Profesor prof = listProfesores.stream().filter(x -> x.getId() == idProfesor).findFirst().orElse(null);
                if (prof != null) {
                    profesores += prof.getFullName() + ", ";
                }

                Coordinador coord = listCoordinador.stream().filter(x -> x.getId() == idProfesor).findFirst().orElse(null);
                if (prof != null) {
                    profesores += coord.getFullName() + ", ";
                }

            }*/

            data[i][0] = listHistorial.get(i).getSemestre();
            data[i][1] = materiasCursadas;
            data[i][2] = listHistorial.get(i).getProfesoresImpartieron();

            data[i][3] = listHistorial.get(i).getPromedioSemestre();

        }

        System.out.println("************* HISTORIAL DE " + alumno.getFullName() + " ****************");
        TextTable tt = new TextTable(columns, data);
        tt.printTable();
        System.out.println("______________________________________________________________________________________________________________________");

    }

}
