package dao;

import dnl.utils.text.table.TextTable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import objetos.Materia;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Utils;
import objetos.Alumno;
import objetos.Calificacion;
import objetos.Carrera;
import objetos.Grupo;
import utils.Constantes;

/**
 *
 * @author
 */
public class AlumnoDao {

    List<Alumno> listAlumnosInSys = new ArrayList<Alumno>();

    public List<Alumno> getListAlumnosInSys() {
        return this.listAlumnosInSys;
    }

    public void avanzarAlumnosSemestre(int idGrupo, int newIdGrupo, String semestre, List<Alumno> listAlumnosAprobados) {
        //List<Alumno> listAlumnos = getAlumnosByIdGrupo(idGrupo);

        for (Alumno alumno : listAlumnosAprobados) {
            int index = listAlumnosInSys.indexOf(alumno);
            listAlumnosInSys.get(index).setIdGrupo(newIdGrupo);
            listAlumnosInSys.get(index).setSemestre(semestre);
            listAlumnosInSys.get(index).setPromedio("0"); //Ver si se coloca si cuando pasará a graduarse poner el promedio de carrera
        }

        writeOnJSON();
    }

    public List<Alumno> getAlumnosByIdGrupo(int idGrupo) {
        return listAlumnosInSys.stream()
                .filter(x -> x.getIdGrupo() == idGrupo).collect(Collectors.toList());
    }

    public List<Alumno> getAlumnosByIdCarrera(int idCarrera) {
        return listAlumnosInSys.stream()
                .filter(x -> x.getIdCarrera() == idCarrera).collect(Collectors.toList());
    }

    public Alumno getAlumnoById(int id) {
        return listAlumnosInSys.stream()
                .filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public List<Alumno> getAlumnosByCarreraAndSemestre(int idCarrera, String semestre, String filtroByCalificacion) {
        List<Alumno> listAlumnosFinded = null;

        switch (filtroByCalificacion) {
            case "1": // APROBADOS
                listAlumnosFinded = listAlumnosInSys.stream()
                        .filter(x -> x.getIdCarrera() == idCarrera
                        && x.getSemestre().equalsIgnoreCase(semestre)
                        && Double.parseDouble(x.getPromedio()) >= 70).collect(Collectors.toList());
                break;
            case "2": // REPROBADOS
                listAlumnosFinded = listAlumnosInSys.stream()
                        .filter(x -> x.getIdCarrera() == idCarrera
                        && x.getSemestre().equalsIgnoreCase(semestre)
                        && Double.parseDouble(x.getPromedio()) < 70).collect(Collectors.toList());
                break;
            case "3": //TODOS
                listAlumnosFinded = listAlumnosInSys.stream()
                        .filter(x -> x.getIdCarrera() == idCarrera
                        && x.getSemestre().equalsIgnoreCase(semestre)).collect(Collectors.toList());
                break;
            default:

                break;
        }

        return listAlumnosFinded;

    }

    public List<Alumno> getAlumnosByGrupoAndCalificaciones(int idGrupo, String filtroByCalificacion) {
        List<Alumno> listAlumnosFinded = new ArrayList<>();

        switch (filtroByCalificacion) {
            case "1": // APROBADOS
                listAlumnosFinded = listAlumnosInSys.stream()
                        .filter(x -> x.getIdGrupo() == idGrupo
                        && Double.parseDouble(x.getPromedio()) >= 70).collect(Collectors.toList());
                break;
            case "2": // REPROBADOS
                listAlumnosFinded = listAlumnosInSys.stream()
                        .filter(x -> x.getIdGrupo() == idGrupo
                        && Double.parseDouble(x.getPromedio()) < 70).collect(Collectors.toList());
                break;
            case "3": //TODOS
                listAlumnosFinded = listAlumnosInSys.stream()
                        .filter(x -> x.getIdGrupo() == idGrupo).collect(Collectors.toList());
                break;
            default:

                break;
        }

        return listAlumnosFinded;

    }

    public void addNewAlumno(Alumno alumno) {
        listAlumnosInSys.add(alumno);

        writeOnJSON();
    }

    public void updatePromedioAlumnoReprobado(List<Alumno> listAlumnosReprobados) {
        for (Alumno alumno : listAlumnosReprobados) {
            int index = listAlumnosInSys.indexOf(alumno);
            listAlumnosInSys.get(index).setPromedio("0");
        }
        writeOnJSON();
    }

    public void updatePromedioAlumno(List<Calificacion> listCalificaciones, Alumno alumno, int nmroMaterias) {
        DecimalFormat df = new DecimalFormat("#.00");

        Double sumatoria = 0.0;

        for (Calificacion calificacion : listCalificaciones) {
            sumatoria += (Double.parseDouble(calificacion.getCalificacion()));
        }

        String calFinal = df.format((sumatoria / nmroMaterias));

        int index = listAlumnosInSys.indexOf(alumno);
        listAlumnosInSys.get(index).setPromedio(calFinal);

        writeOnJSON();

    }

    public void writeOnJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Alumno alm : listAlumnosInSys) {
            jsonArray.add(alm.toJson());
        }

        jsonObject.put("alumnos", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_ALUMNOS);
    }

    public void initDataAlumnos() {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonAlumnos = Utils.initData(Constantes.NAME_FILE_ALUMNOS);

        try {
            JSONArray jsonArrayAlumnos = (JSONArray) jsonAlumnos.get("alumnos");
            Iterator it = jsonArrayAlumnos.iterator();

            while (it.hasNext()) {
                JSONObject jsonAlumno = (JSONObject) it.next();

                listAlumnosInSys.add(new Alumno(jsonAlumno, 0));
            }

            System.out.println("==> Terminé carga de alumnos... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyCalificacionesAlumno(List<Integer> materias, List<Calificacion> listCalificaciones) {

    }

    public void printAllAlumnos(List<Grupo> listGruposInSys,
            List<Carrera> listCarrerasInSystem, int idCarrera) {

        String[] columns = {"ID", "Numero de Control", "Carrera", "Semestre", "Grupo", "Nombre", "Apellidos", "Fecha de Nacimiento", "Ciudad", "Estado", "Direccion", "Curp"};

        List<Alumno> listAlumnoByCarrera = getAlumnosByIdCarrera(idCarrera);

        Object[][] data = new Object[listAlumnoByCarrera.size()][columns.length];

        for (int i = 0; i < listAlumnoByCarrera.size(); i++) {

            Alumno alumno = listAlumnoByCarrera.get(i);

            data[i][0] = alumno.getId();
            data[i][1] = alumno.getNumeroControl();

            data[i][2] = listCarrerasInSystem.stream().
                    filter(x -> x.getId() == alumno.getIdCarrera())
                    .findFirst().get().getNombreCarrera();

            data[i][3] = alumno.getSemestre();

            if (alumno.getIdGrupo() == -1) { //Es un alumno graduado
                data[i][4] = "Graduado";
            } else {
                data[i][4] = listGruposInSys.stream().
                        filter(x -> x.getId() == alumno.getIdGrupo())
                        .findFirst().get().getTipoGrupo();
            }

            data[i][5] = alumno.getNombre();
            data[i][6] = alumno.getApellidos();
            data[i][7] = alumno.getFechaNacimiento();
            data[i][8] = alumno.getCiudad();
            data[i][9] = alumno.getEstado();
            data[i][10] = alumno.getDireccion();
            data[i][11] = alumno.getCurp();

        }

        TextTable tt = new TextTable(columns, data);
        tt.printTable();

    }

    public List<Integer> printAlumnosByIdCarrera(int idCarrera) {
        String[] columns = {"ID", "Numero de Control", "Nombre", "Apellidos", "Fecha de Nacimiento", "Ciudad", "Estado", "Direccion", "Curp"};
        List<Alumno> listAlumnosByIdCarrera = getAlumnosByIdCarrera(idCarrera);

        Object[][] data = new Object[listAlumnosByIdCarrera.size()][columns.length];

        for (int i = 0; i < listAlumnosByIdCarrera.size(); i++) {

            Alumno alumno = listAlumnosByIdCarrera.get(i);

            data[i][0] = alumno.getId();
            data[i][1] = alumno.getNumeroControl();
            data[i][2] = alumno.getNombre();
            data[i][3] = alumno.getApellidos();
            data[i][4] = alumno.getFechaNacimiento();
            data[i][5] = alumno.getCiudad();
            data[i][6] = alumno.getEstado();
            data[i][7] = alumno.getDireccion();
            data[i][8] = alumno.getCurp();

        }

        TextTable tt = new TextTable(columns, data);
        tt.printTable();
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Alumno alumno : listAlumnosByIdCarrera) {
            listValidsId.add(alumno.getId());
        }

        return listValidsId;

    }

    public List<Integer> printAlumnosByIdGrupo(List<Carrera> listCarrerasInSystem,
            List<Grupo> listGruposInSys, int idGrupo) {
        String[] columns = {"ID", "Numero de Control", "Carrera", "Grupo", "Nombre", "Apellidos", "Fecha de Nacimiento", "Ciudad", "Estado", "Direccion", "Curp"};
        List<Alumno> listAlumnosByIdGrupo = getAlumnosByIdGrupo(idGrupo);
        Object[][] data = new Object[listAlumnosByIdGrupo.size()][columns.length];

        for (int i = 0; i < listAlumnosByIdGrupo.size(); i++) {

            Alumno alumno = listAlumnosByIdGrupo.get(i);

            data[i][0] = alumno.getId();
            data[i][1] = alumno.getNumeroControl();

            data[i][2] = listCarrerasInSystem.stream().
                    filter(x -> x.getId() == alumno.getIdCarrera())
                    .findFirst().get().getNombreCarrera();

            data[i][3] = listGruposInSys.stream().
                    filter(x -> x.getId() == alumno.getIdGrupo())
                    .findFirst().get().getTipoGrupo();

            data[i][4] = alumno.getNombre();
            data[i][5] = alumno.getApellidos();
            data[i][6] = alumno.getFechaNacimiento();
            data[i][7] = alumno.getCiudad();
            data[i][8] = alumno.getEstado();
            data[i][9] = alumno.getDireccion();
            data[i][10] = alumno.getCurp();

        }

        if (listAlumnosByIdGrupo.size() > 0) {
            TextTable tt = new TextTable(columns, data);
            tt.printTable();
        } else {
            System.out.println("No hay registro de alumnos");
        }

        List<Integer> listValidsId = new ArrayList<>();
        for (Alumno alumno : listAlumnosByIdGrupo) {
            listValidsId.add(alumno.getId());
        }

        return listValidsId;

    }

    public void printAlumnosByMateriaAndCalificaciones(
            List<Carrera> listCarrerasInSystem,
            List<Grupo> listGruposInSys,
            List<Calificacion> listCalificaciones,
            Materia materia) {
        String[] columns = {"Materia", "ID", "Numero de Control", "Carrera", "Grupo", "Nombre", "Apellidos", "Calificacion"};

        Object[][] data = new Object[listCalificaciones.size()][columns.length];

        for (int i = 0; i < listCalificaciones.size(); i++) {

            Alumno alumno = getAlumnoById(
                    Integer.parseInt(listCalificaciones.get(i).getIdAlumno()));

            data[i][0] = materia.getNombreMateria();
            data[i][1] = alumno.getId();
            data[i][2] = alumno.getNumeroControl();

            data[i][3] = listCarrerasInSystem.stream().
                    filter(x -> x.getId() == alumno.getIdCarrera())
                    .findFirst().get().getNombreCarrera();

            data[i][4] = listGruposInSys.stream().
                    filter(x -> x.getId() == alumno.getIdGrupo())
                    .findFirst().get().getTipoGrupo();

            data[i][5] = alumno.getNombre();
            data[i][6] = alumno.getApellidos();
            data[i][7] = listCalificaciones.get(i).getCalificacion();

        }

        TextTable tt = new TextTable(columns, data);
        tt.printTable();
    }

    public void printAlumnosCalificacionesByGrupo(List<Carrera> listCarrerasInSystem,
            List<Grupo> listGruposInSys, int idGrupo, String filtroCalificacion) {

        String[] columns = {"ID", "Numero de Control", "Carrera", "Grupo", "Nombre", "Apellidos", "Promedio"};
        List<Alumno> listAlumnosByGrupo = getAlumnosByGrupoAndCalificaciones(idGrupo, filtroCalificacion);

        if (!listAlumnosByGrupo.isEmpty()) {
            Object[][] data = new Object[listAlumnosByGrupo.size()][columns.length];

            for (int i = 0; i < listAlumnosByGrupo.size(); i++) {

                Alumno alumno = listAlumnosByGrupo.get(i);

                data[i][0] = alumno.getId();
                data[i][1] = alumno.getNumeroControl();

                data[i][2] = listCarrerasInSystem.stream().
                        filter(x -> x.getId() == alumno.getIdCarrera())
                        .findFirst().get().getNombreCarrera();

                data[i][3] = listGruposInSys.stream().
                        filter(x -> x.getId() == alumno.getIdGrupo())
                        .findFirst().get().getTipoGrupo();

                data[i][4] = alumno.getNombre();
                data[i][5] = alumno.getApellidos();
                data[i][6] = alumno.getPromedio();

            }

            TextTable tt = new TextTable(columns, data);
            tt.printTable();
        } else {
            System.out.println("No se encontraron registros");
        }

    }

    public void printAlumnosCalificacionesBySemestre(
            List<Carrera> listCarrerasInSystem,
            List<Calificacion> listCalificaciones,
            List<Materia> listMaterias) {

        String[] columns = {"Materia", "ID", "Numero de Control", "Carrera", "Nombre", "Apellidos", "Calificacion"};

        Object[][] data = new Object[listCalificaciones.size()][columns.length];

        for (int i = 0; i < listCalificaciones.size(); i++) {

            Calificacion cal = listCalificaciones.get(i);

            Alumno alumno = getAlumnoById(
                    Integer.parseInt(cal.getIdAlumno()));

            Materia materia = listMaterias.stream().filter(x
                    -> cal.getIdMateria().equalsIgnoreCase(x.getId() + "")).findFirst().orElse(null);

            data[i][0] = materia.getNombreMateria();
            data[i][1] = alumno.getId();
            data[i][2] = alumno.getNumeroControl();

            data[i][3] = listCarrerasInSystem.stream().
                    filter(x -> x.getId() == alumno.getIdCarrera())
                    .findFirst().get().getNombreCarrera();

            data[i][4] = alumno.getNombre();
            data[i][5] = alumno.getApellidos();
            data[i][6] = cal.getCalificacion();

        }

        TextTable tt = new TextTable(columns, data);
        tt.printTable();
    }

    public void printCalificacionesByIdAlumno(
            List<Calificacion> listCalificaciones,
            List<Materia> listMaterias) {

        String[] columns = {"Materia", "Semestre", "Numero de Control", "Nombre", "Apellidos", "Calificacion"};

        Object[][] data = new Object[listCalificaciones.size()][columns.length];

        for (int i = 0; i < listCalificaciones.size(); i++) {

            Calificacion cal = listCalificaciones.get(i);

            Alumno alumno = getAlumnoById(
                    Integer.parseInt(cal.getIdAlumno()));

            Materia materia = listMaterias.stream().filter(x
                    -> cal.getIdMateria().equalsIgnoreCase(x.getId() + "")).findFirst().orElse(null);

            data[i][0] = materia.getNombreMateria();
            data[i][1] = cal.getSemestre();
            data[i][2] = alumno.getNumeroControl();

            data[i][3] = alumno.getNombre();
            data[i][4] = alumno.getApellidos();
            data[i][5] = cal.getCalificacion();

        }

        TextTable tt = new TextTable(columns, data);
        tt.printTable();
    }

}
