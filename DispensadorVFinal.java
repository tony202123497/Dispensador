package Proyecto;

import java.util.Locale;
import java.util.Scanner;

public class Dispensador {

    //ANALITICAS:
    //Producto más vendido
    //Venta en soles
    //Venta en ecomoneda
    //... Productos con quiebre de stock
    //... Total de venta por producto
    //... Ventas de categoria mascarillas

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";

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
            listadoDeProductos(); //pintar el menu principal
            opcion = sn.nextInt(); //leer la opcion digitada por el usuario

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
                case 98:
                    analiticas(); //pinta el menu de analiticas
                    break;
                case 99:
                    salir = true;
                    break;
                default:
                    opcionNoValida(); //pinta opción no válida
                    break;
            }
        }
    }

    private static void analiticas() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcionAnalitica;

        while(!salir)
        {
            System.out.println("========================================");
            System.out.println("               ANALITICAS               ");
            System.out.println("========================================");
            System.out.println("[ 1] Producto más vendido");
            System.out.println("[ 2] Venta por forma de pago");
            System.out.println("[ 3] Productos con quiebre de stock");
            System.out.println("[ 4] Venta por producto");
            System.out.println("[ 5] Venta de mascarillas (kid, adulto, kn95)");
            System.out.println("[99] Salir");
            System.out.print("Elija una opción: ");
            opcionAnalitica = sn.nextInt();

            switch (opcionAnalitica) {
                case 1 -> productoMasVendido();
                case 2 -> ventaFormaDePago();
                case 3 -> productosSinStock();
                case 4 -> ventaPorProducto();
                case 5 -> ventaMascarillas();
                case 99 -> salir = true;
                default -> opcionNoValida();
            }
        }
    }

    private static void ventaMascarillas() {
        int totalUnidMascarillas = 0;
        double totalSolesMacarillas = 0;
        int totalEcomonedaMarcarillas = 0;

        System.out.println(ANSI_BLUE + "========================================");
        System.out.println("         VENTA MASCARILLAS");
        System.out.println("========================================");
        System.out.println("[Cód] [Descripción        ]");

        for(int i=0; i < ventaUnidades.length; i++)
        {
            if(descripcion[i].substring(0,10).equals("Mascarilla"))
            {
                System.out.println("[" + i + "]" + descripcion[i] + ", Unid: " + ventaUnidades[i] + ", Soles: " + ventaSoles[i] + " , Ecomoneda: " + ventaEcomoneda[i]);
            }

            totalUnidMascarillas = totalUnidMascarillas + ventaUnidades[i];
            totalSolesMacarillas = totalSolesMacarillas + ventaSoles[i];
            totalEcomonedaMarcarillas = totalEcomonedaMarcarillas + ventaEcomoneda[i];
        }

        System.out.println("========================================");
        System.out.println("TOTAL UNIDADES  : " + totalUnidMascarillas);
        System.out.println("TOTAL SOLES     : " + totalSolesMacarillas);
        System.out.println("TOTAL ECOMONEDAS: " + totalEcomonedaMarcarillas);
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void ventaPorProducto() {
        int totalUnidades = 0;
        int totalEcomonedas = 0;
        double totalSoles = 0.0;

        System.out.println(ANSI_BLUE + "========================================");
        System.out.println("         VENTA POR PRODUCTO");
        System.out.println("========================================");
        System.out.println("[Cód] [Descripción        ]");
        System.out.println("========================================");
        for(int i=0; i < ventaUnidades.length; i++)
        {
            System.out.println("[ " + i + " ] " + descripcion[i].toUpperCase(Locale.ROOT));
            System.out.println("Unidades: " + ventaUnidades[i] + ", Soles: " + ventaSoles[i] + " , Ecomoneda: " + ventaEcomoneda[i]);

            totalUnidades = totalUnidades + ventaUnidades[i];
            totalSoles = totalSoles + ventaSoles[i];
            totalEcomonedas = totalEcomonedas + ventaEcomoneda[i];
        }
        System.out.println("========================================");
        System.out.println("TOTAL UNIDADES  : " + totalUnidades);
        System.out.println("TOTAL SOLES     : " + totalSoles);
        System.out.println("TOTAL ECOMONEDAS: " + totalEcomonedas);
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void productosSinStock() {
        int cantidad = 0;

        System.out.println(ANSI_BLUE + "========================================");
        System.out.println("         PRODUCTOS SIN STOCK");
        System.out.println("========================================");

        for(int i=0; i < stock.length; i++)
        {
            if(stock[i] == 0)
            {
                cantidad = cantidad + 1;
                System.out.println("[" + cantidad + "] " + descripcion[i].toUpperCase(Locale.ROOT));
            }
        }
        System.out.println("TOTAL: " + cantidad + " PRODUCTO(S) SIN STOCK." + ANSI_RESET);
        continuar();
    }

    private static void ventaFormaDePago() {
        int cantVtaSoles = 0, cantVtaEcomoneda = 0, cantTotalVtas = 0;
        double montoVtaSoles = 0;
        int montoVtaEcomoneda = 0;

        for(int i = 0; i < ventaSoles.length; i++)
        {
            if(ventaSoles[i] > 0){
                montoVtaSoles = montoVtaSoles + ventaSoles[i];
                cantVtaSoles = cantVtaSoles + 1;
                cantTotalVtas = cantTotalVtas + 1;
            }

            if(ventaEcomoneda[i] > 0){
                montoVtaEcomoneda = montoVtaEcomoneda + ventaEcomoneda[i];
                cantVtaEcomoneda = cantVtaEcomoneda + 1;
                cantTotalVtas = cantTotalVtas + 1;
            }
        }

        System.out.println(ANSI_BLUE + "========================================");
        System.out.println("         VENTA POR FORMA DE PAGO");
        System.out.println("========================================");
        System.out.println("SOLES");
        System.out.println("========================================");
        System.out.println("Cantidad de ventas: " + cantVtaSoles);
        if(cantTotalVtas > 0)
            System.out.printf("Porcentaje del total de ventas: %.2f \n", (cantVtaSoles*100.00/cantTotalVtas));
        System.out.println("Monto             : " + montoVtaSoles + " soles");
        System.out.println("========================================");
        System.out.println("ECOMONEDA");
        System.out.println("========================================");
        System.out.println("Cantidad de ventas: " + cantVtaEcomoneda);
        if(cantTotalVtas > 0)
            System.out.printf("Porcentaje del total de ventas: %.2f \n", (cantVtaEcomoneda*100.00/cantTotalVtas));
        System.out.println("Monto             : " + montoVtaEcomoneda + " tapas");
        System.out.println("========================================" + ANSI_RESET);
        continuar();
    }

    private static void productoMasVendido() {
        int cantMayor, posMayor;

        posMayor = -1;
        cantMayor = 0;
        for(int i = 0; i < ventaUnidades.length; i++) {
            if(cantMayor < ventaUnidades[i]) {
                cantMayor = ventaUnidades[i];
                posMayor = i;
            }
        }

        if(posMayor != -1)
        {
            System.out.println(ANSI_BLUE + "========================================");
            System.out.println("          PRODUCTO MAS VENDIDO          ");
            System.out.println("========================================");
            System.out.println(descripcion[posMayor].toUpperCase(Locale.ROOT) + " CON " + ventaUnidades[posMayor] + " UNIDAD(ES)." + ANSI_RESET);
            continuar();
        }
        else{
            System.out.println(ANSI_BLUE + "========================================");
            System.out.println("        NO HAY PRODUCTOS VENDIDOS       ");
            System.out.println("========================================" + ANSI_RESET);
            continuar();
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
            System.out.println("Ingrese la forma de pago");
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
        System.out.println(ANSI_GREEN + "========================================");
        System.out.println("            RESUMEN DE VENTA            ");
        System.out.println("========================================");
        System.out.println("PRODUCTO     : " + descripcion[opcion].toUpperCase(Locale.ROOT));
        if(importeSoles > 0){
            System.out.println("FORMA DE PAGO: SOLES");
            System.out.println("IMPORTE PAGO : " + importeSoles);
            System.out.println("VUELTO       : " + (importeSoles - costoSoles[opcion]));
        }else{
            System.out.println("FORMA DE PAGO: ECOMONEDA");
            System.out.println("IMPORTE PAGO : " + importeEcomoneda);
            System.out.println("DEVUELTO     : " + (importeEcomoneda - costoEcomoneda[opcion]));
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
        System.out.println("[98] Análiticas");
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