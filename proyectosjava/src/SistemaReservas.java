import java.util.Scanner;

public class SistemaReservas {

    static int asientosDisponibles = 29;
    static final int TIEMPO_LIMITE = 12;
    static final double MULTA = 0.20;
    static final int EQUIPAJE_MAX = 2;
    static final int PESO_MAX = 10;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE RESERVAS ===");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Salir");
            System.out.print("Ingrese opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    reservar(sc);
                    break;
                case 2:
                    cancelar(sc);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 3);

        sc.close();
    }

    public static void reservar(Scanner sc) {
        sc.nextLine(); // limpiar buffer

        if (asientosDisponibles == 0) {
            System.out.println("No hay asientos disponibles.");
            return;
        }

        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        // --- Validación de cédula ---
        String cedula;
        do {
            System.out.print("Ingrese su cédula (10 dígitos): ");
            cedula = sc.nextLine();
        } while (!cedula.matches("\\d{10}"));

        // --- Tipo de boleto ---
        int boleto;
        do {
            System.out.print("Tipo de boleto (1 = IDA, 2 = IDA Y VUELTA): ");
            boleto = sc.nextInt();
        } while (boleto != 1 && boleto != 2);

        // --- Ruta ---
        String ruta = (boleto == 1)
                ? "Ecuador → Perú"
                : "Ecuador → Perú → Ecuador";

        // --- Precio ---
        System.out.print("Ingrese precio del boleto: ");
        double precio = sc.nextDouble();

        // --- Equipaje ---
        System.out.print("¿Llevará equipaje? (1 = Sí, 2 = No): ");
        int lleva = sc.nextInt();

        int maletas = 0;
        double peso = 0;

        if (lleva == 1) {
            do {
                System.out.print("Cantidad de maletas (máx 2): ");
                maletas = sc.nextInt();
            } while (maletas < 1 || maletas > EQUIPAJE_MAX);

            do {
                System.out.print("Peso total equipaje (máx 10 kg): ");
                peso = sc.nextDouble();
            } while (peso < 1 || peso > PESO_MAX);
        }

        // --- Resumen ---
        System.out.println("\n=== RESERVA COMPLETADA ===");
        System.out.println("Pasajero: " + nombre);
        System.out.println("Cédula: " + cedula);
        System.out.println("Ruta: " + ruta);
        System.out.println("Precio: $" + precio);
        System.out.println("Maletas: " + maletas);
        System.out.println("Peso: " + peso + " kg");

        asientosDisponibles--;
    }

    public static void cancelar(Scanner sc) {
        System.out.print("Ingrese precio del boleto: ");
        double precio = sc.nextDouble();

        System.out.print("Horas desde la compra: ");
        int horas = sc.nextInt();

        if (horas > TIEMPO_LIMITE) {
            double multa = precio * MULTA;
            System.out.println("Se aplicará multa de $" + multa);
            System.out.println("Devolución total: $" + (precio - multa));
        } else {
            System.out.println("Cancelación sin multa.");
            System.out.println("Devolución: $" + precio);
        }

        asientosDisponibles++;
    }
}