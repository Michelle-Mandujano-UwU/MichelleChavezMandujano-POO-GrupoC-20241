from Visitante import Visitant
from Animales import Animal
from Personal import Empleado
from Visitante import Visita
from Visitante import Guia
from array import array
from Actividades import Act

class Systema:
    lista_Visitantes=[]
    lista_Animales=[]
    lista_Empleados=[]
    lista_Visitas=[]
    lista_Actividades = []
    tru = True
    def inicioSistema(self):

        while self.tru == True:
            print("***********BIENVENIDO***********")
            print("Desea interactuar con que datos?")
            print("1. Personal")
            print("2. Visitantes")
            print("3. Animales")
            print("4. Visitas")
            print("5. Actividades")
            print("6. Salir")

          
            elec = ""
            while elec != "1" or elec != "2" or elec != "3" or elec != "4" or elec != "5" or elec != "6":
                elec = input("Inserte su elección: ")
                if elec == "1" or elec == "2" or elec == "3":
                    print("1. Registrar")
                    print("2. Modificar")
                    print("3. Eliminar")
                    print("4. Consulta")
                    elec2 = ""
                    while elec2 != "1" or elec2 != "2" or elec2 != "3" or elec2 != "4":
                        elec2 = input("Inserte su elección: ")
                        if elec2 == "1" or elec2 == "2" or elec2 == "3" or elec2 == "4":
                            break
                        else:
                            print("La opcion elegida no existe")
                    break
                elif elec == "4" or elec == "5":
                    print("1. Registrar")
                    print("4. Consulta")
                    elec2 = ""
                    while elec2 != "1" or elec2 != "4":
                        elec2 = input("Inserte su elección: ")
                        if elec2 == "1" or elec2 == "4":
                            break
                        else:
                            print("La opcion elegida no existe")
                    break
                elif elec == "6":
                    break
                else:
                    print("La opcion elegida no existe")
            


            if elec == "6":#Salida
                break

            #Personal
            elif elec == "1" and elec2 == "1":#Registrar
                self.almacenarInfoEmpleado()
            elif elec == "1" and elec2 == "2":#Modificar
                self.modificar_empleado()
            elif elec == "1" and elec2 == "3":#Eliminar
                self.eliminar_Datos_Empleado()
            elif elec == "1" and elec2 == "4":#Consulta
                self.listar_empleados()
            #####
            #Visitantes
            elif elec == "2" and elec2 == "1":#Registrar
                self.almacenarInfoVisitante()
            elif elec == "2" and elec2 == "2":#Modificar
                self.modificar_visitante()
            elif elec == "2" and elec2 == "3":#Eliminar
                self.eliminar_Datos_Visitante()
            elif elec == "2" and elec2 == "4":#Consulta
                self.listar_Visitantes()
            #####
            #Animales
            elif elec == "3" and elec2 == "1":#Registrar
                self.nuevoAnimal()
            elif elec == "3" and elec2 == "2":#Modificar
                self.modificar_animales()
            elif elec == "3" and elec2 == "3":#Eliminar
                self.eliminar_Datos_Animales()
            elif elec == "3" and elec2 == "4":#Consulta
                self.listar_Animales()
            #####
            #Visitas
            elif elec == "4" and elec2 == "1":#Registrar
                self.almacenarInfoVisita()
            elif elec == "4" and elec2 == "4":#Consulta
                self.listar_visitas()
            #####
            #Actividades
            elif elec == "5" and elec2 == "1":#Registrar
                self.registrar_actividad()
            elif elec == "5" and elec2 == "4":#Consulta
                self.listar_actividades()
            #####
                        

                
        
#####################################--EMPLEADOS--####################################            

    #REGISTRAR        
    def registrar_empleado(self, nombre, apellidos, fecha_nacimiento, fecha_ingreso, rfc, curp, salario, horario, rol):
        empleado = Empleado(nombre, apellidos, fecha_nacimiento, fecha_ingreso, rfc, curp, salario, horario, rol)
        self.lista_Empleados.append(empleado)
        print("Empleado registrado")


    def almacenarInfoEmpleado(self):
        nombre = input("Ingrese el nombre del empleado: ")
        apellidos = input("Ingrese los apellidos: ")
        fecha_nacimiento = input("Ingrese la fecha de nacimiento (DD-MM-YYYY): ")
        fecha_ingreso = input("Ingrese la fecha de ingreso (DD-MM-YYYY): ")
        rfc = input("Ingrese el RFC: ")
        curp = input("Ingrese la CURP: ")
        salario = float(input("Ingrese el salario: "))
        horario = input("Ingrese el horario: ")
        rol = input("Ingrese el rol (Veterinario/Guía/Mantenimiento/Administración): ")
        self.registrar_empleado(nombre, apellidos, fecha_nacimiento, fecha_ingreso, rfc, curp, salario, horario, rol)
    ##########

    #MODIFICAR
    def modificar_empleado(self):
        self.listar_empleados()
        select = int(input("Elija el Empleado a editar (numero):"))-1
        select2 = input("Elija el dato a editar (nombre / apellido / fechaNacimiento / fechaIngreso / rfc / curp / salario / horario / rol):")
        if select2 == "nombre":
            new = input("Inserte el nuevo nombre del Empleado:")
            self.lista_Empleados[select].nombre = new
            print("Se ah editado exitosamente")

        elif select2 == "apellido":
            new = input("Inserte el nuevo apellido del Empleado:")
            self.lista_Empleados[select].apellidos = new
            print("Se ah editado exitosamente")

        elif select2 == "fechaNacimiento":
            new = input("Inserte la nueva fecha de Nacimiento del Empleado:")
            self.lista_Empleados[select].fecha_nacimiento= new
            print("Se ah editado exitosamente")

        elif select2 == "fechaIngreso":
            new = input("Inserte la nueva fecha de Ingreso del Empleado:")
            self.lista_Empleados[select].fecha_ingreso = new
            print("Se ah editado exitosamente")

        elif select2 == "rfc":
            new = input("Inserte el nuevo rfc del Empleado:")
            self.lista_Empleados[select].rfc = new
            print("Se ah editado exitosamente")

        elif select2 == "curp":
            new = input("Inserte la nueva curp del Empleado:")
            self.lista_Empleados[select].curp = new
            print("Se ah editado exitosamente")
        
        elif select2 == "salario":
            new = input("Inserte el nuevo salario del Empleado:")
            self.lista_Empleados[select].salario = new
            print("Se ah editado exitosamente")
        
        elif select2 == "horario":
            new = input("Inserte el nuevo horario del Empleado:")
            self.lista_Empleados[select].horario = new
            print("Se ah editado exitosamente")
        
        elif select2 == "rol":
            new = input("Inserte el nuevo rol del Empleado:")
            self.lista_Empleados[select].rol = new
            print("Se ah editado exitosamente")
    ##########

    #ELIMINAR    
    def eliminar_Datos_Empleado(self):
        self.listar_empleados()
        select = int(input("Elija el empleado a eliminar (numero):"))-1
        self.lista_Empleados.remove(self.lista_Empleados[select])
        print("Se ah eliminado el dato")    
    ##########         

    #CONSULTA
    def listar_empleados(self):
        print("Lista de empleados:")
        for empleado in self.lista_Empleados:
            print(f"{self.lista_Empleados.index(empleado) + 1}.-  Nombre: {empleado.nombre} {empleado.apellidos}, Rol: {empleado.rol}")
    ##########
        
#####################################--VISITANTES--####################################        

    #REGISTRAR
    def almacenarInfoVisitante(self):

        nombre = input("Inserte el nombre del visitante: ")
        apellido = input("Inserte el apellido del visitante: ")
        fecha_Nac = input("Inserte la fecha de nacimiento del visitante (DD-MM-YYYY): ")
        curp = input("Inserte la CURP del visitante: ")
        fecha_Registro = input("Inserte fecha de Registro (DD-MM-YYYY): ")
        visitante = Visitant(nombre, apellido, fecha_Nac, curp, fecha_Registro)
        self.lista_Visitantes.append(visitante)
        print("--Se registro el Visitante--")
        print("")
    ##########
    #MODIFICAR
    def modificar_visitante(self):
        self.listar_Visitantes()
        select = int(input("Elija el visitante a editar (numero):"))-1
        select2 = input("Elija el dato a editar (nombre / apellido / fechaNacimiento / fechaRegistro / curp):")
        if select2 == "nombre":
            new = input("Inserte el nuevo nombre del Visitante:")
            self.lista_Visitantes[select].nombre = new
            print("Se ah editado exitosamente")

        elif select2 == "apellido":
            new = input("Inserte el nuevo apellido del Visitante:")
            self.lista_Visitantes[select].apellidos = new
            print("Se ah editado exitosamente")

        elif select2 == "fechaNacimiento":
            new = input("Inserte la nueva fecha de Nacimiento del Visitante:")
            self.lista_Visitantes[select].fecha_nacimiento= new
            print("Se ah editado exitosamente")

        elif select2 == "fechaRegistro":
            new = input("Inserte la nueva fecha de Ingreso del Visitante:")
            self.lista_Visitantes[select].fecha_ingreso = new
            print("Se ah editado exitosamente")

        elif select2 == "curp":
            new = input("Inserte la nueva curp del Visitante:")
            self.lista_Visitantes[select].curp = new
            print("Se ah editado exitosamente")
    ##########    
    #ELIMINAR    
    def eliminar_Datos_Visitante(self):
        self.listar_Visitantes()
        select = int(input("Elija el visitante a eliminar (numero):"))-1
        self.lista_Visitantes.remove(self.lista_Visitantes[select])
        print("Se ah eliminado el dato")    
    ##########    
    #CONSULTA
    def listar_Visitantes(self):
        print("Lista de visitas:")
        for visita in self.lista_Visitantes:
            print(f"{self.lista_Visitantes.index(visita)+1} Fecha: {visita.fecha_registro}, Nombre: {visita.nombre}, Apellido : {visita.apellido}")
    ##########

#####################################--ANIMALES--####################################

    #REGISTRAR
    def nuevoAnimal(self):
        tipo = input("Inserte el tipo de animal: ")
        fecha1 = input("Inserte la fecha de nacimiento (DD-MM-YYYY): ")
        fecha2 = input("Inserte la fecha de ingreso (DD-MM-YYYY): ")
        peso = input("Inserte el peso: ")
        elec = input("El animal tiene alguna enfermedad? si(S)  no(N): ")
        if elec == 's' or elec == 'S':
            enfermedades=[]
            tru = True
            while tru == True:
                enfermedad = input("Inserte el nombre de la enfermedad: ")
                enfermedades.append(enfermedad)
                elec = input("Desea añadir otra enfermedad? si(S) no(N): ")
                if elec == 'N' or elec == 'n':
                    tru = False
        frec_Alim = input("Inserte la frecuencia de alimentacion: ")
        tipo_Alim = input("Inserte el tipo de alimentacion: ")
        elec = input("El animal esta vacunado? si(S)  no(N): ")
        if elec == 's' or elec == 'S':
            vac = True
        elif elec == 'N' or elec == 'n':
            vac = False
        self.lista_Animales.append(Animal(tipo, fecha1, fecha2, enfermedades, peso, frec_Alim, tipo_Alim, vac))
        print("--Se registo el animal--")
        print("")
    ##########
    #MODIFICAR
    def modificar_animales(self):
        self.listar_Animales()
        select = int(input("Elija el animal a editar (numero):"))-1
        select2 = input("Elija el dato a editar (tipo / peso / fechaNacimiento / fechaLlegada / frecuenciaAliment / tipoAliment / estadoVacun):")
        if select2 == "tipo":
            new = input("Inserte el nuevo tipo del Animal:")
            self.lista_Animales[select].tipo_Animal = new
            print("Se ah editado exitosamente")

        elif select2 == "peso":
            new = input("Inserte el nuevo peso del Animal:")
            self.lista_Animales[select].peso = new
            print("Se ah editado exitosamente")

        elif select2 == "fechaNacimiento":
            new = input("Inserte la nueva fecha de Nacimiento del Animal:")
            self.lista_Animales[select].fecha_Nac= new
            print("Se ah editado exitosamente")

        elif select2 == "fechaLlegada":
            new = input("Inserte la nueva fecha de Llegada del Animal:")
            self.lista_Animales[select].fecha_ingreso = new
            print("Se ah editado exitosamente")

        elif select2 == "frecuenciaAliment":
            new = input("Inserte la nueva frecuancia del Animal:")
            self.lista_Animales[select].frec_Alim = new
            print("Se ah editado exitosamente")
        elif select2 == "tipoAliment":
            new = input("Inserte el nuevo tipo de alimento del Animal:")
            self.lista_Animales[select].tipo_Alim = new
            print("Se ah editado exitosamente")
        elif select2 == "estadoVacun":
            new = input("El animal esta vacunado? (si(S) / no(N))")
            if new == "S" or new == "s":
                self.lista_Animales[select].vacunas = True
            else:
                self.lista_Animales[select].vacunas = False
            print("Se ah editado exitosamente")
    ##########
    #ELIMINAR    
    def eliminar_Datos_Animales(self):
        self.listar_Animales()
        select = int(input("Elija el animal a eliminar (numero):"))-1
        self.lista_Animales.remove(self.lista_Animales[select])
        print("Se ah eliminado el dato")    
    ##########
    #CONSULTA
    def listar_Animales(self):
        print("Lista de animales:")
        for animal in self.lista_Animales:
            print(f"{self.lista_Animales.index(animal)+1}.-  Tipo: {animal.tipo_Animal}, Fecha de Nacimiento: {animal.fecha_Nac}, Fecha de Llegada: {animal.fecha_Lleg}, Enfermedades: {animal.enfermedades}, Peso: {animal.peso}, Frecuencia de Alimentacion: {animal.frec_Alim}, Tipo de Alimento: {animal.tipo_Alim}, Esta vacunado: {animal.vacunas}")
    ##########    

#####################################--VISITA--####################################

    #REGISTRAR
    def almacenarInfoVisita(self):
        guia_nombre = input("Ingrese el nombre del guía: ")
        guia = Guia(guia_nombre)
        cantidad_visitantes = int(input("Ingrese la cantidad de visitantes: "))
        visitantes = []
        for _ in range(cantidad_visitantes):
            nombre = input("Ingrese el nombre del visitante: ")
            encontrado = False
            for visitante_registrado in self.lista_Visitantes:
                if visitante_registrado.nombre == nombre:
                    visitantes.append(visitante_registrado)
                    encontrado = True
                    break
            if not encontrado:
                print("El visitante no está registrado")
                break
        else:
            fecha_visita = input("Ingrese la fecha de la visita (DD-MM-YYYY): ")
            self.registrar_visita(guia, visitantes, fecha_visita)

    #ELIMINAR    
    def eliminar_Datos_Visita(self):
        self.listar_visitas()
        select = int(input("Elija la visita a eliminar (numero):"))-1
        self.lista_Visitas.remove(self.lista_Visitas[select])
        print("Se ah eliminado el dato")    
    ##########
    #CONSULTA
    def listar_visitas(self):
        print("Lista de visitas:")
        for visita in self.lista_Visitas:
            print(f"{self.lista_Visitas.index(visita)+1}.-  Fecha: {visita.fecha_visita}, Guía: {visita.guia.nombre}, Costo Total: {visita.costo_total}, Niños: {visita.cantidad_niños}, Adultos: {visita.cantidad_adultos}")
    ##########

#####################################--Actividades--####################################           

    #REGISTRAR        
    def registrar_actividad(self):
        nombre_empleado = input("Ingrese el nombre del empleado que realizó la actividad: ")
        id_animal = input("Ingrese el ID del animal involucrado: ")
        proceso = input("Ingrese el proceso realizado: ")
        fecha = input("Ingrese la fecha del proceso (DD-MM-YYYY): ")
        observaciones = input("Ingrese observaciones (opcional): ")
        actividad = Act(nombre_empleado, id_animal, proceso, fecha, observaciones)
        self.lista_Actividades.append(actividad)
        print("Actividad registrada")

    #Consultar

    def listar_actividades(self):
        print("Lista de actividades:")
        for index, actividad in enumerate(self.lista_Actividades, start=1):
            print(f"{index}. Nombre empleado: {actividad.nombre_empleado}, ID animal: {actividad.id_animal}, Proceso: {actividad.proceso}, Fecha: {actividad.fecha}, Observaciones: {actividad.observaciones}")
    