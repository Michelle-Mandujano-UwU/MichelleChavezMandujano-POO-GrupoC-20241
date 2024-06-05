package proyectoinstituto;

import dao.AlumnoDao;
import dao.CalificacionDao;
import dao.CarrerasDao;
import dao.CoordinadoresDao;
import dao.GraduacionDao;
import dao.GruposDao;
import dao.HistorialDao;
import dao.MateriasDao;
import dao.ProfesorDao;
import dao.SessionDao;
import dao.UsuarioDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import objetos.Alumno;
import objetos.Coordinador;
import objetos.Profesor;
import objetos.Graduacion;
import objetos.Usuario;
import objetos.Grupo;
import objetos.Materia;
import objetos.Calificacion;
import objetos.Historial;
import objetos.Semestre;
import utils.Constantes;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import java.util.Arrays;
import static utils.Constantes.Rol.ALUMNO;
import static utils.Constantes.Rol.TRABAJADOR_COORDINADOR;
import static utils.Constantes.Rol.TRABAJADOR_PROFESOR;
import utils.Utils;

/**
 *
 * @author
 */
public class Sistema {

    CarrerasDao carrerasDao = null;
    MateriasDao matDao = null;
    CoordinadoresDao coordDao = null;
    ProfesorDao profDao = null;
    AlumnoDao alumnoDao = null;
    GruposDao gruposDao = null;
    UsuarioDao usuarioDao = null;
    SessionDao sessionDao = null;
    CalificacionDao calificacionDao = null;

    HistorialDao historialDao = null;
    GraduacionDao graduacionDao = null;

    public void initSystem() {
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        //initDataDummy();
        initData();

        System.out.println("**---------- Bienvenid@ ----------*** ");

        try {

            do {
                System.out.println("1.- Entrar al sistema \n0.- Salir ");
                opt = sc.nextInt();

                switch (opt) {
                    case 1:
                        System.out.println("Por favor ingresa tu usuario y contraseña");
                        System.out.println("Usuario");
                        String usr = sc.next();
                        System.out.println("Contraseña");
                        String pass = sc.next();

                        if (sessionDao.validateLogin(usr, pass)) {
                            System.out.println("Usuario validado | Entrando en el sistema...");
                            System.out.println("");
                            switch (sessionDao.getUserOnSession().getRol()) {
                                case ALUMNO:
                                    onAlumnoMenu((Alumno) sessionDao.getUserOnSession());

                                    break;
                                case TRABAJADOR_PROFESOR:
                                    onProfesorMenu((Profesor) sessionDao.getUserOnSession());
                                    break;
                                case TRABAJADOR_COORDINADOR:
                                    onCoordinadorMenu((Coordinador) sessionDao.getUserOnSession());
                                    break;

                                default:
                                    break;
                            }
                        } else {
                            System.out.println("Usuario invalido, no se encontró el usuario");
                        }
                        break;
                }

            } while (opt != 0);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Ocurrio un error durante la ejecución");
            opt = 2;
        }

        System.out.println("Has salido del sistema...");
    }

    public void onCoordinadorMenu(Coordinador usr) {
        Scanner sc = new Scanner(System.in);
        String idGrupo = "";
        int opt = 0;
        System.out.println("****---- Bienvenido " + usr.getFullName() + " -----*****");

        try {
            do {
                System.out.println("");
                System.out.println("Elige una opción para continuar");
                System.out.println("0.-Salir\n"
                        + "1.-Registrar Coordinador\n"
                        + "2.-Registrar Profesor\n"
                        + "3.-Registrar Alumno\n"
                        + "4.-Modificar mi información\n"
                        + "5.-Ver Alumnos Registrados\n"
                        + "6.-Ver Alumnos Por Grupo\n"
                        + "7.-Ver Grupos\n"
                        + "8.-Registrar/Modificar Calificaciones por alumno\n"
                        + "9.-Ver calificaciones\n"
                        + "10.- Avanzar de semestre a grupo\n"
                        + "11.- Ver historial de Alumno\n"
                        + "12.- Ver lista de graduados");

                opt = sc.nextInt();

                switch (opt) {
                    case 1:
                    case 2:
                        initProcessAddProfesorCoordinador(opt, usr.getIdCarrera());
                        break;
                    case 3:
                        initProcessAddAlumno();
                        break;
                    case 4:
                        //initProcessAddGrupo(); Anteriormente era para agregar un grupo nuevo
                        initProcessEditData(usr, null);
                        break;
                    case 5:
                        alumnoDao.printAllAlumnos(gruposDao.getListGruposInSys(),
                                carrerasDao.getListCarrerasInSystem(), usr.getIdCarrera());
                        break;
                    case 6:

                        List<Integer> listValidGrupos = gruposDao.printAllGrupos(carrerasDao.getListCarrerasInSystem());

                        boolean isValidGrupo = false;
                        do {
                            System.out.println("Selecciona el grupo por ID. para listar a los alumnos, EJ. 0");
                            idGrupo = sc.next();

                            isValidGrupo = Utils.isNumeric(idGrupo)
                                    && listValidGrupos.contains(Integer.parseInt(idGrupo)) ? true : false;

                            if (!isValidGrupo) {
                                System.out.println("Inserta un valor valido de la lista");
                            }

                        } while (isValidGrupo == false);

                        alumnoDao.printAlumnosByIdGrupo(carrerasDao.getListCarrerasInSystem(),
                                gruposDao.getListGruposInSys(), Integer.parseInt(idGrupo));
                        break;
                    case 7:
                        gruposDao.printAllGrupos(carrerasDao.getListCarrerasInSystem());
                        break;
                    case 8:
                        initProcessCapturaCalificaciones(usr.getMateriasImparte());
                        break;
                    case 9:
                        initViewCalificacionesAlumnos(Constantes.Rol.TRABAJADOR_COORDINADOR,
                                usr.getMateriasImparte(), new ArrayList<Integer>(), usr.getIdCarrera());
                        break;
                    case 10:
                        initProcessPassGrupoSemestre(usr.getIdCarrera());
                        break;
                    case 11:
                        String idAlumno = "";
                        List<Integer> listValidAlumnos = alumnoDao.printAlumnosByIdCarrera(usr.getIdCarrera());

                        boolean isValidAlumno = false;
                        do {
                            System.out.println("Selecciona el alumno por ID. para listar a los alumnos, EJ. 0");
                            idAlumno = sc.next();

                            isValidAlumno = Utils.isNumeric(idAlumno)
                                    && listValidAlumnos.contains(Integer.parseInt(idAlumno)) ? true : false;

                            if (!isValidAlumno) {
                                System.out.println("Inserta un valor valido de la lista");
                            }

                        } while (isValidAlumno == false);

                        historialDao.printHistorialByAlumno(alumnoDao.getAlumnoById(Integer.parseInt(idAlumno)),
                                profDao.getListProfesoresInSys(),
                                coordDao.getListCoordinadoresInSys(),
                                matDao.getListMateriasInSys());

                        break;
                    case 12:
                        graduacionDao.printAllGraduacion(carrerasDao.getListCarrerasInSystem(),
                                alumnoDao.getListAlumnosInSys());
                        break;
                    default:
                        break;
                }
            } while (opt != 0);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Ocurrio un error durante la ejecución");
        }

    }

    public void onProfesorMenu(Profesor usr) {
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        System.out.println("****---- Bienvenido " + usr.getFullName() + " -----*****");

        try {
            do {
                System.out.println("Elige una opción para continuar");
                System.out.println("0.-Salir\n1.-Registrar Alumno\n2.-Registrar Calificaciones\n3.- Ver Calificaciones\n4.- Modificar mi información");
                opt = sc.nextInt();

                switch (opt) {
                    case 1:
                        initProcessAddAlumno();
                        break;
                    case 2:
                        initProcessCapturaCalificaciones(usr.getMateriasImparte());
                        break;
                    case 3:
                        initViewCalificacionesAlumnos(Constantes.Rol.TRABAJADOR_PROFESOR,
                                usr.getMateriasImparte(), usr.getGruposAsignados(), -1);
                        break;
                    case 4:
                        initProcessEditData(null, usr);
                        break;
                    default:
                        break;
                }
            } while (opt != 0);
        } catch (Exception e) {
            System.out.println("Ocurrio un error durante la ejecución");
        }

    }

    public void onAlumnoMenu(Alumno usr) {
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        System.out.println("****---- Bienvenido " + usr.getFullName() + " -----*****");

        try {
            do {
                System.out.println("Elige una opción para continuar");
                System.out.println("0.-Salir\n1.-Ver calificaciones\n2.- Ver historial");
                opt = sc.nextInt();

                switch (opt) {
                    case 1:

                        alumnoDao.printCalificacionesByIdAlumno(
                                calificacionDao.getCalificacionesByIdAlumno(usr.getId()),
                                matDao.getListMateriasInSys());

                        break;
                    case 2:

                        historialDao.printHistorialByAlumno(usr,
                                profDao.getListProfesoresInSys(),
                                coordDao.getListCoordinadoresInSys(),
                                matDao.getListMateriasInSys());

                        break;
                    default:
                        break;
                }
            } while (opt != 0);
        } catch (Exception e) {
            System.out.println("Ocurrio un error durante la ejecución");
        }

    }

    public void initProcessEditData(Coordinador oldC, Profesor oldP) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, String> dataProfesor = new HashMap<>();

        System.out.println("Inicia proceso de edición de datos");

        if (oldC != null) {
            Coordinador c = coordDao.getCoordinadorById(oldC.getId());

            coordDao.printDataCoordinador(c, matDao.getListMateriasInSys(),
                    carrerasDao.getCarreraById(c.getIdCarrera()));
        } else {
            Profesor c = profDao.getProfesorById(oldP.getId());

            profDao.printDataProfessor(c, matDao.getListMateriasInSys());
        }

        System.out.print("Nombre:");
        dataProfesor.put("nombre", sc.nextLine());
        System.out.print("Apellidos:");
        dataProfesor.put("apellidos", sc.nextLine());

        boolean isValidFecha = false;
        do {
            System.out.print("Fecha de Nacimiento: (aaaa-mm-dd)");
            dataProfesor.put("fechaNacimiento", sc.nextLine());

            isValidFecha = Utils.validateFecha(dataProfesor.get("fechaNacimiento"));

            if (!isValidFecha) {
                System.out.println("Inserta una fecha acorde al formato");
            }

        } while (isValidFecha == false);

        System.out.print("Ciudad:");
        dataProfesor.put("ciudad", sc.nextLine());
        System.out.print("Estado:");
        dataProfesor.put("estado", sc.nextLine());
        System.out.print("Direccion:");
        dataProfesor.put("direccion", sc.nextLine());
        System.out.print("Sueldo:");
        dataProfesor.put("sueldo", sc.nextLine());
        System.out.print("Username:");
        dataProfesor.put("username", sc.nextLine());
        System.out.print("Contraseña:");
        dataProfesor.put("password", sc.nextLine());

        if (oldC != null) {
            dataProfesor.put("fechaRegistro", oldC.getFechaRegistro());
            dataProfesor.put("id", oldC.getId() + "");
            dataProfesor.put("idCarrera", oldC.getIdCarrera() + "");
            dataProfesor.put("materias", String.join(",",
                    oldC.getMateriasImparte().stream().map(Object::toString).collect(Collectors.toList())
            ));

            Coordinador cNew = new Coordinador(dataProfesor);

            coordDao.updateDataCoordinador(oldC, cNew);

            int indexUsr = usuarioDao.getListUsersInSys().indexOf(oldC);
            usuarioDao.getListUsersInSys().remove(indexUsr);
            usuarioDao.getListUsersInSys().add(cNew);
        } else {
            dataProfesor.put("fechaRegistro", oldP.getFechaRegistro());
            dataProfesor.put("id", oldP.getId() + "");
            dataProfesor.put("materias", String.join(",",
                    oldP.getMateriasImparte().stream().map(Object::toString).collect(Collectors.toList())
            ));

            dataProfesor.put("gruposAsignados", String.join(",",
                    oldP.getGruposAsignados().stream().map(Object::toString).collect(Collectors.toList())
            ));

            Profesor pNew = new Profesor(dataProfesor);

            profDao.updateDataProfesor(oldP, pNew);

            int indexUsr = usuarioDao.getListUsersInSys().indexOf(oldP);
            usuarioDao.getListUsersInSys().remove(indexUsr);
            usuarioDao.getListUsersInSys().add(oldP);
        }

        System.out.println("Proceso de modificación completado");
    }

    public void initProcessPassGrupoSemestre(int idCarrera) {
        Scanner sc = new Scanner(System.in);

        List<Alumno> listAlumnosAprobados = new ArrayList<>();
        List<Alumno> listAlumnosReprobados = new ArrayList<>();
        boolean isValid = false;
        String idGrupo = "";

        System.out.println("Inicia Proceso de avanzar de semestre");
        List<Integer> listValidGrupos = gruposDao.printGruposByIdCarrera(idCarrera);

        boolean isValidGrupo = false;
        do {
            System.out.println("Selecciona el grupo que quieras avanzar de semestre");
            idGrupo = sc.next();

            isValidGrupo = Utils.isNumeric(idGrupo)
                    && listValidGrupos.contains(Integer.parseInt(idGrupo)) ? true : false;

            if (!isValidGrupo) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidGrupo == false);

        Grupo grupo = gruposDao.getGrupoById(Integer.parseInt(idGrupo));

        int semestreActualGrupo = Integer.parseInt(grupo.getSemestre());
        List<Alumno> listAlumnosInGrupo = alumnoDao.getAlumnosByIdGrupo(grupo.getId());

        for (Alumno alumno : listAlumnosInGrupo) {

            List<Calificacion> listCalificacionesAlumno
                    = calificacionDao.getCalificacionesByIdAlumnoAndSemestre(alumno.getId(), grupo.getSemestre());

            if (listCalificacionesAlumno.size() != grupo.getMaterias().size()) {

                System.out.println("El alumno " + alumno.getFullName() + " no tiene todas sus calificaciones registradas");
                isValid = false;
                break;

            } else {

                List<Calificacion> listCalificacionesReprobadasAlumno
                        = calificacionDao.getCalificacionesReprobadasByIdAlumnoAndSemestre(alumno.getId(), grupo.getSemestre());

                //TIENE TODAS SUS CALIFICACIONES (3), VERIFICAR CUALES NO AVANZAN Y CUALES SI.
                if (!listCalificacionesReprobadasAlumno.isEmpty()) { ///REPROBADO
                    listAlumnosReprobados.add(alumno);
                } else { //APROBADO
                    listAlumnosAprobados.add(alumno);
                }

                isValid = true;
            }
        }

        if (!isValid) {
            System.out.println("El grupo no puede avanzar de semestre, el proceso se cerrará");
        } else {

            int nroSemestre = Integer.parseInt(grupo.getSemestre()) + 1;

            //List<Materia> listMateriasBySemestre = matDao.getMateriasByIdCarreraAndSemestre(idCarrera, nroSemestre + "");
            /*List<Integer> listNewMaterias = new ArrayList<>();

            for (Materia materia : listMateriasBySemestre) {
                listNewMaterias.add(materia.getId());
            }*/
            //List<Alumno> listAlumnosToHistorial = alumnoDao.getAlumnosByIdGrupo(grupo.getId());
            //Verificar calificaciones de alumnos, para determinar quien reprobó alguna materia
            //List<Alumno> listALumnoAprobados = 
            List<String> ListProfesores = profDao.getProfesoresByMateriaInside(grupo.getMaterias());
            List<String> listCoordinadores = coordDao.getCoordinadoresByMateriaInside(grupo.getMaterias());

            ListProfesores.addAll(listCoordinadores);
            List<Historial> listHistorialAlumnos = new ArrayList<Historial>();
            int nroRegistros = historialDao.getListHistorialInSys().size();

            for (Alumno alumno : listAlumnosAprobados) { //Solo considerando los alumnos aprobados

                HashMap<String, String> mapHistorial = new HashMap<>();

                mapHistorial.put("idRegistro", nroRegistros + "");
                mapHistorial.put("idAlumno", alumno.getId() + "");

                String listMaterias = String.join(",",
                        grupo.getMaterias()
                                .stream()
                                .map(Object::toString)
                                .collect(Collectors.toList()));

                mapHistorial.put("profesoresImpartieron", String.join(", ", ListProfesores));
                mapHistorial.put("materiasCursadas", listMaterias);
                mapHistorial.put("semestre", alumno.getSemestre());
                mapHistorial.put("promedio", alumno.getPromedio());

                Historial hist = new Historial(mapHistorial);
                listHistorialAlumnos.add(hist);

                nroRegistros++;
            }

            Grupo newGrupo = gruposDao.getGrupoByIdCarreraAndSemestreAndTipoGrupo(idCarrera, nroSemestre, grupo.getTipoGrupo());
            int idNewGrupo = newGrupo != null ? newGrupo.getId() : -1; // Para controlar a los graduados
            alumnoDao.avanzarAlumnosSemestre(grupo.getId(),
                    idNewGrupo,
                    nroSemestre + "",
                    listAlumnosAprobados);
            //gruposDao.avanzarSemestreGrupo(listNewMaterias, grupo, nroSemestre + "");

            historialDao.addHistorial(listHistorialAlumnos);

            if (!listAlumnosReprobados.isEmpty()) {
                initProcesoReprobados(listAlumnosReprobados);
            }

            if (semestreActualGrupo == 3) { //COMENZARA PROCESO DE GRADUACION
                initProcessGraduacion(listAlumnosAprobados);
            } else {
                System.out.println("Los alumnos han avanzado de semestre exitosamente");
                if (!listAlumnosReprobados.isEmpty()) {
                    System.out.println("Sin embargo algunos no han conseguido aprobar");
                }
            }

        }

    }

    public void initProcesoReprobados(List<Alumno> listAlumnosReprobados) {
        System.out.println("Comenzando con proceso de reprobados...");
        alumnoDao.updatePromedioAlumnoReprobado(listAlumnosReprobados);  //Actualizar el promedio a 0
        calificacionDao.deleteCalificacionesFromAlumnos(listAlumnosReprobados); //Eliminar sus calificaciones de el registro de calificaciones
    }

    public void initProcessGraduacion(List<Alumno> listAlumnosToGraduacion) {
        List<Graduacion> listNewGraduacion = new ArrayList<>();
        int nroEleGraduacion = graduacionDao.getListGraduacionInSys().size();

        for (Alumno alumno : listAlumnosToGraduacion) {
            JSONObject jsonGraduacion = new JSONObject();
            List<Historial> listHistorial = historialDao.getListHistorialByAlumno(alumno.getId());
            Double promedio = 0.0;

            for (Historial historial : listHistorial) {
                promedio += Double.parseDouble(historial.getPromedioSemestre());
            }

            promedio = promedio / listHistorial.size();

            jsonGraduacion.put("idRegistro", nroEleGraduacion);
            jsonGraduacion.put("promedio", promedio + "");
            jsonGraduacion.put("idCarrera", alumno.getIdCarrera() + "");
            jsonGraduacion.put("idAlumno", alumno.getId() + "");
            jsonGraduacion.put("fechaGraduacion", LocalDate.now());
            jsonGraduacion.put("generacion", "Ene - Jun 2024");

            listNewGraduacion.add(new Graduacion(jsonGraduacion));
            nroEleGraduacion++;
        }

        graduacionDao.addAllGraduaciones(listNewGraduacion);
        System.out.println("Los alumnos han completado el proceso de graduacion");
    }

    public void initViewCalificacionesAlumnos(Constantes.Rol typeTrabajador,
            List<Integer> materiasImparte,
            List<Integer> gruposAsignados,
            int idCarrera) {
        Scanner sc = new Scanner(System.in);
        String optFiltro1 = "";
        String optFiltro2 = "";

        boolean isValidFiltro1 = false;
        do {
            System.out.println("Selecciona filtro para mostrar los alumnos");
            System.out.println("1.-Aprobados\n2.-Reprobados\n3.- Todos");
            optFiltro1 = sc.next();

            isValidFiltro1 = (Utils.isNumeric(optFiltro1)
                    && (Integer.parseInt(optFiltro1) == 1
                    || Integer.parseInt(optFiltro1) == 2
                    || Integer.parseInt(optFiltro1) == 3)) ? true : false;

            if (!isValidFiltro1) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidFiltro1 == false);

        boolean isValidFiltro2 = false;
        do {
            System.out.println("1.-Por grupo\n2.-Por materia\n3.- Por Semestre");
            optFiltro2 = sc.next();

            isValidFiltro2 = (Utils.isNumeric(optFiltro2)
                    && (Integer.parseInt(optFiltro2) == 1
                    || Integer.parseInt(optFiltro2) == 2
                    || Integer.parseInt(optFiltro2) == 3)) ? true : false;

            if (!isValidFiltro2) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidFiltro2 == false);

        switch (optFiltro2) {
            case "1": //POR GRUPO // PROMEDIO
                List<Integer> listGruposValid = new ArrayList<>();
                String idGrupoFilter = "";

                if (Constantes.Rol.TRABAJADOR_PROFESOR == typeTrabajador) {
                    listGruposValid = gruposDao.printGruposByIdGrupoInside(gruposAsignados);
                } else {
                    listGruposValid = gruposDao.printGruposByIdCarrera(idCarrera);
                }

                boolean isValidGrupo = false;
                do {
                    System.out.println("Selecciona por ID. el grupo");
                    idGrupoFilter = sc.next();

                    isValidGrupo = Utils.isNumeric(idGrupoFilter)
                            && listGruposValid.contains(Integer.parseInt(idGrupoFilter)) ? true : false;

                    if (!isValidGrupo) {
                        System.out.println("Inserta un valor valido de la lista");
                    }

                } while (isValidGrupo == false);

                alumnoDao.printAlumnosCalificacionesByGrupo(carrerasDao.getListCarrerasInSystem(),
                        gruposDao.getListGruposInSys(), Integer.parseInt(idGrupoFilter), optFiltro1);

                break;
            case "2": //POR MATERIA // CALIFICACION
                List<Integer> listMateriasValid = new ArrayList<>();
                String idMateria = "";
                if (Constantes.Rol.TRABAJADOR_PROFESOR == typeTrabajador) {
                    listMateriasValid = matDao.printMateriasByProfesorImparte(materiasImparte);
                } else {
                    listMateriasValid = matDao.printMateriasByIdCarrera(idCarrera);
                }

                boolean isValidMateria = false;
                do {
                    System.out.println("Selecciona por ID. la materia");
                    idMateria = sc.next();

                    isValidMateria = Utils.isNumeric(idMateria)
                            && listMateriasValid.contains(Integer.parseInt(idMateria)) ? true : false;

                    if (!isValidMateria) {
                        System.out.println("Inserta un valor valido de la lista");
                    }

                } while (isValidMateria == false);

                List<Calificacion> listCalificaciones
                        = calificacionDao.getCalificacionesByIdMateriaAndCalificacion(idMateria, optFiltro1);

                if (!listCalificaciones.isEmpty()) {
                    Materia materia = matDao.getMateriaById(Integer.parseInt(idMateria));

                    alumnoDao.printAlumnosByMateriaAndCalificaciones(
                            carrerasDao.getListCarrerasInSystem(),
                            gruposDao.getListGruposInSys(), listCalificaciones, materia);
                } else {
                    System.out.println("No se encontraron registros");
                }

                break;
            case "3": //POR SEMESTRE // CALIFICACION
                String semestre = "";

                boolean isValidSemestre = false;
                do {
                    System.out.println("Selecciona el semestre");
                    System.out.println("1.- 1\n2.- 2\n3.- 3");
                    semestre = sc.next();

                    isValidSemestre = (Utils.isNumeric(semestre)
                            && (Integer.parseInt(semestre) == 1
                            || Integer.parseInt(semestre) == 2
                            || Integer.parseInt(semestre) == 3)) ? true : false;

                    if (!isValidSemestre) {
                        System.out.println("Inserta un valor valido de la lista");
                    }

                } while (isValidSemestre == false);

                if (Constantes.Rol.TRABAJADOR_PROFESOR == typeTrabajador) {
                    alumnoDao.printAlumnosCalificacionesBySemestre(carrerasDao.getListCarrerasInSystem(),
                            calificacionDao.getCalificacionesBySemestreAndMateriaAndCalificacion(semestre,
                                    materiasImparte, optFiltro1),
                            matDao.getListMateriasInSys());
                } else {

                    alumnoDao.printAlumnosCalificacionesBySemestre(carrerasDao.getListCarrerasInSystem(),
                            calificacionDao.getCalificacionesBySemestreAndCarreraAndCalificacion(semestre,
                                    idCarrera + "", optFiltro1),
                            matDao.getListMateriasInSys());
                }

                break;
            default:
                break;
        }
    }

    public void initProcessCapturaCalificaciones(List<Integer> materiasImparte) {
        Scanner sc = new Scanner(System.in);
        String idMateria = "";
        String idGrupo = "";
        System.out.println("==> Comenzando con proceso de registro/modificacion de calificaciones");
        List<Integer> listValidMaterias = matDao.printMateriasByProfesorImparte(materiasImparte);

        boolean isValidMateria = false;
        do {
            System.out.println("==> Selecciona por ID la materia a la cual se registrarán las calificaciones");
            idMateria = sc.next();

            isValidMateria = Utils.isNumeric(idMateria)
                    && listValidMaterias.contains(Integer.parseInt(idMateria)) ? true : false;

            if (!isValidMateria) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidMateria == false);

        List<Integer> listValidGrupos = gruposDao.printGruposByMateriaInside(Integer.parseInt(idMateria));

        boolean isValidGrupo = false;
        do {
            System.out.println("==> Selecciona por ID el grupo para comenzar a capturar las calificaciones");
            idGrupo = sc.next();

            isValidGrupo = Utils.isNumeric(idGrupo)
                    && listValidGrupos.contains(Integer.parseInt(idGrupo)) ? true : false;

            if (!isValidGrupo) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidGrupo == false);

        Grupo g = gruposDao.getGrupoById(Integer.parseInt(idGrupo));

        List<Integer> listValidAlumnos = alumnoDao.printAlumnosByIdGrupo(carrerasDao.getListCarrerasInSystem(),
                gruposDao.getListGruposInSys(), Integer.parseInt(idGrupo));

        if (!listValidAlumnos.isEmpty()) {
            String idAlumno = "";

            boolean isValidAlumno = false;
            do {
                System.out.println("==> Selecciona por ID al alumno para capturar su calificación");
                idAlumno = sc.next();

                isValidAlumno = Utils.isNumeric(idAlumno)
                        && listValidGrupos.contains(Integer.parseInt(idAlumno)) ? true : false;

                if (!isValidAlumno) {
                    System.out.println("Inserta un valor valido de la lista");
                }

            } while (isValidAlumno == false);

            Alumno alumno = alumnoDao.getAlumnoById(Integer.parseInt(idAlumno));
            Calificacion calExistente = calificacionDao.getCalificacionByIdAlumnoAndMateria(idAlumno, idMateria);

            if (calExistente == null) {
                String calificacion = "";

                boolean isValidCalificacion = false;
                do {
                    System.out.println("Registra la calificación para " + alumno.getFullName() + ": ");
                    calificacion = sc.next();

                    isValidCalificacion = (Utils.isDouble(calificacion)
                            && Double.parseDouble(calificacion) >= 0
                            && Double.parseDouble(calificacion) <= 100) ? true : false;

                    if (!isValidCalificacion) {
                        System.out.println("Inserta una calificación valida de entre 0 y 100");
                    }

                } while (isValidCalificacion == false);

                Calificacion cl = new Calificacion(calificacionDao.getListCalificacionesInSys().size(), idMateria,
                        alumno.getId() + "", calificacion, alumno.getSemestre(), alumno.getIdCarrera() + "");

                calificacionDao.addCalificacion(cl);
                System.out.println("Calificacion Registrada de manera exitosa");

                updatePromedioAlumno(alumno, g);
            } else {
                System.out.println("El alumno ya tiene una calificación registrada para esta materia, la cual es : " + calExistente.getCalificacion());
                System.out.println("Deseas modificarla? \n1.-Si\n2.-No");
                String optModCal = sc.next();
                String newCalificacion = "";

                switch (optModCal) {
                    case "1":

                        boolean isValidCalificacion = false;
                        do {
                            System.out.println("Ingresa la nueva calificación:");
                            newCalificacion = sc.next();

                            isValidCalificacion = (Utils.isDouble(newCalificacion)
                                    && Double.parseDouble(newCalificacion) >= 0
                                    && Double.parseDouble(newCalificacion) <= 100) ? true : false;

                            if (!isValidCalificacion) {
                                System.out.println("Inserta una calificación valida de entre 0 y 100");
                            }

                        } while (isValidCalificacion == false);

                        calificacionDao.modCalificacion(calExistente, newCalificacion);
                        System.out.println("Calificacion modificada de manera exitosa");

                        updatePromedioAlumno(alumno, g);
                        break;
                    default:
                        break;
                }

            }
        } else {
            System.out.println("Terminará proceso de captura de calificaciones");
        }

    }

    public void updatePromedioAlumno(Alumno alumno, Grupo grupo) {
        List<Calificacion> listCalificacionesAlumno
                = calificacionDao.getCalificacionesByIdAlumnoAndSemestre(alumno.getId(), alumno.getSemestre());

        alumnoDao.updatePromedioAlumno(listCalificacionesAlumno, alumno, grupo.getMaterias().size());
    }

    public void initProcessAddProfesorCoordinador(int opt, int idCarrera) {

        Scanner sc = new Scanner(System.in);
        System.out.println("==> Comenzando con proceso de registro de " + (opt == 1 ? "coordinador" : "profesor"));
        HashMap<String, String> dataProfesor = new HashMap<>();
        //sc.nextLine();
        System.out.print("Nombre:");
        dataProfesor.put("nombre", sc.nextLine());
        System.out.print("Apellidos:");
        dataProfesor.put("apellidos", sc.nextLine());

        //Validate Fecha de nacimiento
        boolean isValidFecha = false;
        do {
            System.out.print("Fecha de Nacimiento: (aaaa-mm-dd)");
            dataProfesor.put("fechaNacimiento", sc.nextLine());

            isValidFecha = Utils.validateFecha(dataProfesor.get("fechaNacimiento"));

            if (!isValidFecha) {
                System.out.println("Inserta una fecha acorde al formato");
            }

        } while (isValidFecha == false);

        System.out.print("Ciudad:");
        dataProfesor.put("ciudad", sc.nextLine());
        System.out.print("Estado:");
        dataProfesor.put("estado", sc.nextLine());
        System.out.print("Direccion:");
        dataProfesor.put("direccion", sc.nextLine());
        //System.out.print("Fecha de registro: (dd/mm/aaaa)");
        dataProfesor.put("fechaRegistro", LocalDate.now() + "");

        System.out.print("Sueldo:");
        dataProfesor.put("sueldo", sc.nextLine());
        System.out.print("Username:");
        dataProfesor.put("username", sc.nextLine());
        System.out.print("Contraseña:");
        dataProfesor.put("password", sc.nextLine());

        List<Integer> listValidMaterias = matDao.printMaterias(carrerasDao.getListCarrerasInSystem());

        boolean isValidMateria = false;
        do {
            System.out.println("Selecciona las materias que impartirá por ID, EJ. (5,2,10)");
            dataProfesor.put("materias", sc.nextLine());

            for (String idMateria : dataProfesor.get("materias").split(",")) {
                if (!Utils.isNumeric(idMateria)) {
                    isValidMateria = false;
                    break;
                } else {
                    if (!listValidMaterias.contains(Integer.parseInt(idMateria))) {
                        isValidMateria = false;
                        break;
                    } else {
                        isValidMateria = true;
                    }
                }
            }

            if (!isValidMateria) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidMateria == false);

        if (opt == 1) {
            List<Integer> listValidCarreras = carrerasDao.printCarreras();

            boolean isValidCarerra = false;
            do {
                System.out.print("Selecciona por ID la carrera a la cual estará adscrito, EJ. 1");
                dataProfesor.put("idCarrera", sc.nextLine());

                isValidCarerra = Utils.isNumeric(dataProfesor.get("idCarrera"))
                        && listValidCarreras.contains(Integer.parseInt(dataProfesor.get("idCarrera"))) ? true : false;

                if (!isValidCarerra) {
                    System.out.println("Inserta un valor valido de la lista");
                }

            } while (isValidCarerra == false);

            List<Integer> listValidGrupos = gruposDao.printGruposByIdCarrera(Integer.parseInt(dataProfesor.get("idCarrera"))); //CUANDO REGISTRAN A COORDINADOR SIEMPRE SERÁ A LA CARRERA SELECCIONADA ANTERIORMENTE

            boolean isValidGrupos = false;
            do {
                System.out.println("Selecciona los grupos asignados por ID. EJ (0,1)");
                dataProfesor.put("gruposAsignados", sc.nextLine());

                for (String idGrupo : dataProfesor.get("gruposAsignados").split(",")) {
                    if (!Utils.isNumeric(idGrupo)) {
                        isValidGrupos = false;
                        break;
                    } else {
                        if (!listValidGrupos.contains(Integer.parseInt(idGrupo))) {
                            isValidGrupos = false;
                            break;
                        } else {
                            isValidGrupos = true;
                        }
                    }
                }

                if (!isValidGrupos) {
                    System.out.println("Inserta un valor valido de la lista");
                }

            } while (isValidGrupos == false);

            dataProfesor.put("id", coordDao.getListCoordinadoresInSys().size() + "");
            Coordinador coordinador = new Coordinador(dataProfesor);

            System.out.println("Coordinador registrado de manera exitosa");
            System.out.println(coordinador.toString());

            coordDao.addCoordinador(coordinador); //Agregando coordinador a lista general
            usuarioDao.addUserInSys(coordinador); //Agregando alumno a usuarios general
        } else {

            List<Integer> listValidGrupos = gruposDao.printGruposByIdCarrera(idCarrera); //CUANDO REGISTRAN A PROFESOR SIEMPRE SERÁN GRUPOS DE LA CARRERA DEL COORDINADOR
            boolean isValidGrupos = false;
            do {
                System.out.println("Selecciona los grupos asignados por ID. EJ (0,1)");
                dataProfesor.put("gruposAsignados", sc.nextLine());

                for (String idGrupo : dataProfesor.get("gruposAsignados").split(",")) {
                    if (!Utils.isNumeric(idGrupo)) {
                        isValidGrupos = false;
                        break;
                    } else {
                        if (!listValidGrupos.contains(Integer.parseInt(idGrupo))) {
                            isValidGrupos = false;
                            break;
                        } else {
                            isValidGrupos = true;
                        }
                    }
                }

                if (!isValidGrupos) {
                    System.out.println("Inserta un valor valido de la lista");
                }

            } while (isValidGrupos == false);

            dataProfesor.put("id", profDao.getListProfesoresInSys().size() + "");
            Profesor profesor = new Profesor(dataProfesor);

            System.out.println("Profesor registrado de manera exitosa");
            System.out.println(profesor.toString());

            profDao.addProfesor(profesor); // Agregando profesor a lista general 
            usuarioDao.addUserInSys(profesor); //Agregando alumno a usuarios general
        }
    }

    public void initProcessAddAlumno() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==> Comenzando con proceso de registro de Alumno");

        HashMap<String, String> dataAlumno = new HashMap<>();
        //sc.nextLine();
        System.out.print("Nombre:");
        dataAlumno.put("nombre", sc.nextLine());
        System.out.print("Apellidos:");
        dataAlumno.put("apellidos", sc.nextLine());

        //Validate Fecha de nacimiento
        boolean isValidFecha = false;
        do {
            System.out.print("Fecha de Nacimiento: (aaaa-mm-dd)");
            dataAlumno.put("fechaNacimiento", sc.nextLine());

            isValidFecha = Utils.validateFecha(dataAlumno.get("fechaNacimiento"));

            if (!isValidFecha) {
                System.out.println("Inserta una fecha acorde al formato");
            }

        } while (isValidFecha == false);

        System.out.print("Ciudad:");
        dataAlumno.put("ciudad", sc.nextLine());
        System.out.print("Estado:");
        dataAlumno.put("estado", sc.nextLine());
        System.out.print("Direccion:");
        dataAlumno.put("direccion", sc.nextLine());
        //System.out.print("Fecha de registro: (dd/mm/aaaa)");
        dataAlumno.put("fechaRegistro", LocalDate.now() + "");

        System.out.print("Username:");
        dataAlumno.put("username", sc.nextLine());
        System.out.print("Contraseña:");
        dataAlumno.put("password", sc.nextLine());

        List<Integer> validCarreraValues = carrerasDao.printCarreras();

        boolean isValidCarerra = false;
        do {
            System.out.println("Selecciona la carrera por ID, EJ. 1");
            dataAlumno.put("idCarrera", sc.nextLine());

            isValidCarerra = Utils.isNumeric(dataAlumno.get("idCarrera"))
                    && validCarreraValues.contains(Integer.parseInt(dataAlumno.get("idCarrera"))) ? true : false;

            if (!isValidCarerra) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidCarerra == false);

        List<Integer> validGrupoValues = gruposDao.
                printGruposByIdCarrera(Integer.parseInt(dataAlumno.get("idCarrera")));

        boolean isValidGrupo = false;

        do {
            System.out.println("Selecciona el grupo por ID, EJ. 0");
            dataAlumno.put("idGrupo", sc.nextLine());

            isValidGrupo = Utils.isNumeric(dataAlumno.get("idGrupo"))
                    && validGrupoValues.contains(Integer.parseInt(dataAlumno.get("idGrupo"))) ? true : false;

            if (!isValidGrupo) {
                System.out.println("Inserta un valor valido de la lista");
            }

        } while (isValidGrupo == false);

        dataAlumno.put("id", alumnoDao.getListAlumnosInSys().size() + "");
        Alumno alumno = new Alumno(dataAlumno);

        System.out.println("Alumno registrado de manera exitosa");
        System.out.println(alumno.toString());

        alumnoDao.addNewAlumno(alumno); //Agregando alumno a lista de alumnos actual 
        usuarioDao.addUserInSys(alumno); //Agregando alumno a usuarios general
    }

    public void initProcessAddGrupo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==> Comenzando con proceso de registro de Grupo");
        HashMap<String, String> dataGrupo = new HashMap<>();

        carrerasDao.printCarreras();
        System.out.println("Selecciona la carrera a la que pertenecerá el grupo por ID, EJ. 1");
        dataGrupo.put("idCarrera", sc.nextLine());

        if (gruposDao.getGruposByIdCarrera(Integer.parseInt(dataGrupo.get("idCarrera"))).size() > 1) {
            System.out.println("Ya existen 2 grupos en esta carrera");
            System.out.println("Se cancelará el proceso de creación de grupo");
        } else {
            matDao.printMateriasByIdCarrera(Integer.parseInt(dataGrupo.get("idCarrera")));
            System.out.println("Selecciona las materias que tendrá el grupo por ID, EJ. (5,2,10)");
            dataGrupo.put("materias", sc.nextLine());

            dataGrupo.put("tipoGrupo", "B");
            dataGrupo.put("semestre", "1");

            dataGrupo.put("id", gruposDao.getListGruposInSys().size() + "");
            Grupo grupo = new Grupo(dataGrupo);

            gruposDao.addGrupo(grupo); //Agregando grupo a lista general
        }
    }

    public void initData() {
        carrerasDao = new CarrerasDao();
        carrerasDao.initDataCarreras();

        matDao = new MateriasDao();
        matDao.initDataMaterias();

        coordDao = new CoordinadoresDao();
        coordDao.initDataCoordinadores();

        profDao = new ProfesorDao(matDao);
        profDao.initDataProfesores();

        alumnoDao = new AlumnoDao();
        alumnoDao.initDataAlumnos();

        gruposDao = new GruposDao();
        gruposDao.initDataGrupos();

        calificacionDao = new CalificacionDao();
        calificacionDao.initDataCalificaciones();

        historialDao = new HistorialDao();
        historialDao.initDataHistorial();

        graduacionDao = new GraduacionDao();
        graduacionDao.initDataGraduacion();

        System.out.println("==> REGISTRANDO USUARIOS GENERALES EN SISTEMA PARA SESION");
        System.out.println("");
        usuarioDao = new UsuarioDao();
        usuarioDao.addListUsersInSys(new ArrayList<Usuario>(profDao.getListProfesoresInSys()));
        usuarioDao.addListUsersInSys(new ArrayList<Usuario>(coordDao.getListCoordinadoresInSys()));
        usuarioDao.addListUsersInSys(new ArrayList<Usuario>(alumnoDao.getListAlumnosInSys()));

        sessionDao = new SessionDao();
        sessionDao.addListUsrInSystem(usuarioDao.getListUsersInSys());

    }

}
