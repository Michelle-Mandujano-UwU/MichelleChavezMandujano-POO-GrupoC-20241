
package menu;

import java.util.Scanner;


public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Chavez Mandujano Abril Michelle
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de Figuras Geométricas:");
            System.out.println("1. Círculo");
            System.out.println("2. Rectángulo");
            System.out.println("3. Triángulo");
            System.out.println("4. Cuadrado");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el radio del círculo: ");
                    double radioCirculo = scanner.nextDouble();
                    Circulo circulo = new Circulo(radioCirculo);
                    System.out.println("Área del círculo: " + circulo.calcularArea());
                    System.out.println("Perímetro del círculo: " + circulo.calcularPerimetro());
                    break;
                case 2:
                    System.out.print("Ingrese la base del rectángulo: ");
                    double baseRectangulo = scanner.nextDouble();
                    System.out.print("Ingrese la altura del rectángulo: ");
                    double alturaRectangulo = scanner.nextDouble();
                    Rectangulo rectangulo = new Rectangulo(baseRectangulo, alturaRectangulo);
                    System.out.println("Área del rectángulo: " + rectangulo.calcularArea());
                    System.out.println("Perímetro del rectángulo: " + rectangulo.calcularPerimetro());
                    break;
                case 3:
                    System.out.print("Ingrese la base del triángulo: ");
                    double baseTriangulo = scanner.nextDouble();
                    System.out.print("Ingrese la altura del triángulo: ");
                    double alturaTriangulo = scanner.nextDouble();
                    System.out.print("Ingrese el lado 1 del triángulo:");
                    double lado1 = scanner.nextDouble();
                    System.out.print("Ingrese el lado 2 del triángulo:");
                    double lado2 = scanner.nextDouble();
                    Triangulo triangulo = new Triangulo(baseTriangulo,alturaTriangulo,lado1,lado2);
                    System.out.println("Área del triangulo: " + triangulo.calcularArea());
                    System.out.println("Perímetro del triangulo: " + triangulo.calcularPerimetro());
                    break;
                    
                 case 4:
                 System.out.print("Ingrese el valor de un lado del cudrado: ");
                    double lado = scanner.nextDouble(); 
                    Cuadrado cuadrado = new Cuadrado(lado);
                    System.out.println("Área del triangulo: " + cuadrado.calcularArea());
                    System.out.println("Perímetro del triangulo: " + cuadrado.calcularPerimetro());
                     break;
                     
                 case 5:
                     System.out.println("Saliendo del programa...");
                     break;
    
    
        }
    }
    }
}
