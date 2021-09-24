package Clases;

import java.util.Locale;
import java.util.Scanner;

public class DispensadorV2 {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final String[] descripcion = {"Gel-Antibacterial","Jabon Liquido","Mascarilla Kid","Cubre Zapato","Mini Alcohol",
            "Lentes De Seguridad Blancos","Mascarilla K95","Mascarilla Adulto","Guantes Quirugicos","Cubre Cabeza"};
    private static final double[] costoSoles = {4.0, 5.0, 2.0, 1.5, 2.5, 3.5, 2.5, 2.0, 1.5, 1.5};
    private static final int[] costoEcomoneda = {120, 150, 60, 45, 75, 105, 75, 60, 45, 45};
    private static final int[] stock = {4, 4, 4, 4, 4, 4, 4, 4, 4, 0};

    private static int[] ventaUnidades = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] ventaSoles = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    private static int[] ventaEcomoneda = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {
            listadoDeProductos();
            opcion = sn.nextInt();

            switch (opcion) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    if(stock[opcion] > 0){
                        procesarPago(opcion);
                    }else{
                        stockInsuficiente();
                    }
                    break;
                case 99:
                    salir = true;
                    break;
                default:
                    opcionNoValida();
                    break;
            }
        }
    }

    private static void stockInsuficiente() {
        System.out.println(ANSI_RED + "========================================");
        System.out.println("         PRODUCTO NO DISPONIBLE         ");
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void procesarPago(int opcion) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcionPago;
        double importeSoles;
        int importeEcomoneda;

        while(!salir) {
            System.out.println(ANSI_BLUE + "========================================");
            System.out.println("SELECCIONADO: " + descripcion[opcion].toUpperCase(Locale.ROOT));
            System.out.println("IMPORTE SOLES: S/. " + costoSoles[opcion]);
            System.out.println("IMPORTE ECOMONEDA: " + costoEcomoneda[opcion]);
            System.out.println("========================================" + ANSI_RESET);
            System.out.println("Forma de pago");
            System.out.println("[ 1] Soles");
            System.out.println("[ 2] Ecomoneda");
            System.out.println("[99] Volver");
            System.out.print("Elija una opción: ");
            opcionPago = sn.nextInt();

            switch (opcionPago) {
                case 1:
                    System.out.print("Ingrese el importe en soles: ");
                    importeSoles = sn.nextDouble();
                    if (importeSoles >= costoSoles[opcion]) {
                        registrarVenta(opcion, opcionPago);
                        detalleVenta(opcion, importeSoles, 0);
                    } else {
                        saldoInsuficiente();
                    }
                    salir = true;
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad de tapas: ");
                    importeEcomoneda = sn.nextInt();
                    if (importeEcomoneda >= costoEcomoneda[opcion]) {
                        registrarVenta(opcion, opcionPago);
                        detalleVenta(opcion, 0.0, importeEcomoneda);
                    } else {
                        saldoInsuficiente();
                    }
                    salir = true;
                    break;
                case 99:
                    salir = true;
                    break;
                default:
                    opcionNoValida();
                    break;
            }
        }
    }

    private static void detalleVenta(int opcion, double importeSoles, int importeEcomoneda) {
        System.out.println(ANSI_BLUE + "========================================");
        System.out.println("            RESUMEN DE VENTA            ");
        System.out.println("========================================");
        System.out.println("PRODUCTO     : " + descripcion[opcion].toUpperCase(Locale.ROOT));
        if(importeSoles > 0){
            System.out.println("FORMA DE PAGO: SOLES");
            System.out.println("IMPORTE PAGO : " + importeSoles);
            System.out.println("VUELTO       : " + (costoSoles[opcion] - importeSoles));
        }else{
            System.out.println("FORMA DE PAGO: ECOMONEDA");
            System.out.println("IMPORTE PAGO : " + importeEcomoneda);
            System.out.println("DEVUELTO     : " + (costoEcomoneda[opcion] - importeEcomoneda));
        }
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void saldoInsuficiente() {
        System.out.println(ANSI_RED + "========================================");
        System.out.println("           SALDO INSUFICIENTE           ");
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void registrarVenta(int opcion, int opcionPago) {
        stock[opcion] = stock[opcion] - 1;
        ventaUnidades[opcion] = ventaUnidades[opcion] + 1;
        switch(opcionPago){
            case 1:
                ventaSoles[opcion] = ventaSoles[opcion] + costoSoles[opcion];
                break;
            case 2:
                ventaEcomoneda[opcion] = ventaEcomoneda[opcion] + costoEcomoneda[opcion];
                break;
        }
    }

    private static void listadoDeProductos() {
        System.out.println("========================================");
        System.out.println("     BIENVENIDO A LA ECOEXPENDEDORA     ");
        System.out.println("========================================");

        for(int i=0; i < descripcion.length; i++)
        {
            System.out.println("[ " + i + "] " + descripcion[i] + " (S/. " + costoSoles[i] + " - Ecomoneda " + costoEcomoneda[i] + ")");
        }
        System.out.println("[99] Salir");
        System.out.print("Escribe una de las opciones ");
    }

    private static void opcionNoValida(){
        System.out.println(ANSI_RED + "========================================");
        System.out.println("            OPCIÓN NO VÁLIDA             ");
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void continuar(){
        Scanner sn = new Scanner(System.in);
        String entrada;

        System.out.print("Presione enter para continuar.");
        entrada = sn.nextLine();
    }


    }

