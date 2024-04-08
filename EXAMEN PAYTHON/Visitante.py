class Visitant:
    def __init__(self, nombre, apellido, fecha_nacimiento, curp, fecha_registro):
        self.nombre = nombre
        self.apellido = apellido
        self.fecha_nacimiento = fecha_nacimiento
        self.curp = curp
        self.fecha_registro = fecha_registro
        self.numero_visitas = 0

    def aplicar_descuento(self):
        if self.numero_visitas % 5 == 0 and self.numero_visitas > 0:
            return 0.8 
        else:
            return 1.0  

class Visita:
    def __init__(self, guia, visitantes, fecha_visita):
        self.guia = guia
        self.visitantes = visitantes
        self.fecha_visita = fecha_visita
        self.cantidad_niños = sum(1 for visitante in visitantes if self.es_niño(visitante))
        self.cantidad_adultos = len(visitantes) - self.cantidad_niños
        self.costo_total = self.calcular_costo_total()

    def es_niño(self, visitante):
        fecha_hoy = self.fecha_visita.split("-")
        fecha_nac = visitante.fecha_nacimiento.split("-")
        edad = int(fecha_hoy[2]) - int(fecha_nac[2])
        if int(fecha_hoy[1]) < int(fecha_nac[1]):
            edad -= 1
        elif int(fecha_hoy[1]) == int(fecha_nac[1]) and int(fecha_hoy[0]) < int(fecha_nac[0]):
            edad -= 1
        return edad < 18

    def calcular_costo_total(self):
        costo_niños = self.cantidad_niños * 50  
        costo_adultos = self.cantidad_adultos * 100  
        costo_total_sin_descuento = costo_niños + costo_adultos
        descuento = 1.0
        for visitante in self.visitantes:
            descuento *= visitante.aplicar_descuento()
        return costo_total_sin_descuento * descuento

class Guia:
    def __init__(self, nombre):
        self.nombre = nombre

class Zoo:
    def __init__(self):
        self.visitas = []
        self.lista_visitantes = []  

    def registrar_visita(self, guia, visitantes, fecha_visita):
        visita = Visita(guia, visitantes, fecha_visita)
        self.visitas.append(visita)
        for visitante in visitantes:
            visitante.numero_visitas += 1
        print("Visita registrada")

    def listar_visitas(self):
        print("Lista de visitas:")
        for visita in self.visitas:
            print(f"Fecha: {visita.fecha_visita}, Guía: {visita.guia.nombre}, Costo Total: {visita.costo_total}, Niños: {visita.cantidad_niños}, Adultos: {visita.cantidad_adultos}")

    def almacenar_info_visitante(self):
        nombre = input("Inserte el nombre del visitante: ")
        apellido = input("Inserte el apellido del visitante: ")
        fecha_nac = input("Inserte la fecha de nacimiento del visitante (DD-MM-YYYY): ")
        curp = input("Inserte la CURP del visitante: ")
        fecha_registro = input("Inserte fecha de Registro (DD-MM-YYYY): ")
        visitante = Visitant(nombre, apellido, fecha_nac, curp, fecha_registro)
        self.lista_visitantes.append(visitante)
        print("--Se registró el Visitante--")
        print("")

zoo = Zoo()