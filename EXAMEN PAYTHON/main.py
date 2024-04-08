
from Sistema import Systema

class main:

    contraseña_correcta = "12345"
    contraseña_ingresada = ""
    
    while contraseña_ingresada != contraseña_correcta:

        contraseña_ingresada = input("Ingrese la contraseña: ")
        
        if contraseña_ingresada == contraseña_correcta:
            Systema().inicioSistema()
        else:
            print("Contraseña incorrecta")
