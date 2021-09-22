package Proyecto;

//Avance en proceso, se puede añadir comentarios con éxtio//
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dispensador {

    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {
            System.out.println("=========================================");
            System.out.println("Bienvenido al dispensador ecoamigable");
            System.out.println("=========================================");
            System.out.println("1. Listado de productos");
            System.out.println("2. Comprar");
            System.out.println("3. Salir");
            System.out.println("=========================================");

            try {

                System.out.print("Escribe una de las opciones ");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        listadoDeProductos();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opción 2");
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

    private static void listadoDeProductos() {
        System.out.println("=========================================");
        System.out.println("Listado de productos");
        System.out.println("=========================================");
        System.out.println("1. Mascarilla\tS/. 1.50 soles\teco 15 tapas");
        System.out.println("2. Facial    \tS/. 3.00 soles\teco 30 tapas");
        System.out.println("2. Facial    \tS/. 3.00 soles\teco 30 tapas");
        System.out.println("2. Facial    \tS/. 3.00 soles\teco 30 tapas");
        System.out.println("2. Facial    \tS/. 3.00 soles\teco 30 tapas");
        System.out.println("2. Facial    \tS/. 3.00 soles\teco 30 tapas");
        
        //Comentario de Prueba

    }

}

