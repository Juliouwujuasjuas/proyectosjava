import javax.swing.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

class Factura {
    int numero;
    String cliente;
    String producto;
    int cantidad;
    double precio;
    double total;

    public Factura(int numero, String cliente, String producto, int cantidad, double precio) {
        this.numero = numero;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = cantidad * precio;
    }
}

public class SistemaFacturas {

    static ArrayList<Factura> facturas = new ArrayList<>();

    public static void main(String[] args) {

        int opcion = 0;

        do {

            String menu = "MENU\n"
        + "1. Registro de facturas\n"
        + "2. Consulta específica de una factura\n"
        + "3. Mostrar facturas en archivo de texto\n"
        + "4. Salir";

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {

                case 1:
                    registrarFactura();
                    break;

                case 2:
                    consultarFactura();
                    break;

                case 3:
                    guardarArchivo();
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 4);
    }

    static void registrarFactura() {

        int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de factura"));
        String cliente = JOptionPane.showInputDialog("Nombre del cliente");
        String producto = JOptionPane.showInputDialog("Producto");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio"));

        Factura nueva = new Factura(numero, cliente, producto, cantidad, precio);
        facturas.add(nueva);

        JOptionPane.showMessageDialog(null, "Factura registrada correctamente");
    }

    static void consultarFactura() {

        int numeroBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de factura"));

        boolean encontrada = false;

        for (Factura f : facturas) {

            if (f.numero == numeroBuscar) {

                JOptionPane.showMessageDialog(null,
                        "Factura: " + f.numero +
                        "\nCliente: " + f.cliente +
                        "\nProducto: " + f.producto +
                        "\nCantidad: " + f.cantidad +
                        "\nPrecio: " + f.precio +
                        "\nTotal: " + f.total);

                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            JOptionPane.showMessageDialog(null, "Factura no se encuentra registrada");
        }
    }

    static void guardarArchivo() {

        try {

            FileWriter archivo = new FileWriter("facturas.txt");

            for (Factura f : facturas) {

                archivo.write("Factura: " + f.numero +
                        " | Cliente: " + f.cliente +
                        " | Total: " + f.total + "\n");
            }

            archivo.close();

            JOptionPane.showMessageDialog(null, "Archivo generado correctamente");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Error al crear el archivo");
        }
    }
}