import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Archivo.Archivo;
import Tabla.Tabla;
import excepciones.*;

public class Menu {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Tabla tabla = new Tabla();
        cargarDatosIniciales(tabla);  // Tabla cargada con datos básicos

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ DE TESTING ---");
            System.out.println("1. Agregar columna numérica adicional");
            System.out.println("2. Agregar columna string adicional");
            System.out.println("3. Agregar columna booleana adicional");
            System.out.println("4. Test agregar columna con etiqueta repetida");
            System.out.println("5. Test completar NA y rellenar datos faltantes");
            System.out.println("6. Test ordenar tabla");
            System.out.println("7. Test acceso indexado");
            System.out.println("8. Eliminar fila");
            System.out.println("9. Eliminar columna");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    testColumnaNumerica(tabla);
                    break;
                case 2:
                    testColumnaString(tabla);
                    break;
                case 3:
                    testColumnaBoolConEtiqueta(tabla);
                    break;
                case 4:
                    testAgregarColumnaConEtiquetaRepetida(tabla);
                    break;
                case 5:
                    testCompletarNA(tabla);
                    break;
                case 6:
                    testOrdenarTabla(tabla);
                    break;
                case 7:
                    testAccesoIndexado(tabla);
                    break;
                case 8:
                    testEliminarFila(tabla);
                    break;
                case 9:
                    testEliminarColumna(tabla);
                    break;
                case 10:
                    System.out.println("Saliendo del programa.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    // Cargar datos iniciales en la tabla
    public static void cargarDatosIniciales(Tabla tabla) {
        List<Number> datos1 = Arrays.asList(1, 1.2, 1.3456, null);
        List<String> etiquetasFila = Arrays.asList("hola", "mi", "pequeño", "mundo");
        tabla.agregarColumna(datos1, 1, etiquetasFila);

        List<String> datos2 = Arrays.asList("Hola", "mundo", null, "NAaa");
        tabla.agregarColumna(datos2, 331);

        List<Boolean> datos3 = Arrays.asList(true, false, true, null);
        tabla.agregarColumna(datos3, "Perro");

        List<String> nombres = Arrays.asList("Ana", "Luis", "Marta", "Juan");
        tabla.agregarColumna(nombres, "Nombre");

        List<Number> edades = Arrays.asList(25, 30, 45, 50);
        tabla.agregarColumna(edades, "Edad");

        System.out.println("Datos iniciales cargados.");
        tabla.visualizar();
    }

    // Prueba 1: Agregar columna numérica adicional
    public static void testColumnaNumerica(Tabla tabla) {
        List<Number> nuevaColumnaNumerica = Arrays.asList(10, 20, 30, 40);
        tabla.agregarColumna(nuevaColumnaNumerica, "Puntaje");
        System.out.println("Columna numérica adicional agregada:");
        tabla.visualizar();
    }

    // Prueba 2: Agregar columna de tipo string adicional
    public static void testColumnaString(Tabla tabla) {
        List<String> nuevaColumnaString = Arrays.asList("A", "B", null, "D");
        tabla.agregarColumna(nuevaColumnaString, "Letras");
        System.out.println("Columna string adicional agregada:");
        tabla.visualizar();
    }

    // Prueba 3: Agregar columna de tipo booleano adicional
    public static void testColumnaBoolConEtiqueta(Tabla tabla) {
        List<Boolean> nuevaColumnaBool = Arrays.asList(false, true, null, true);
        tabla.agregarColumna(nuevaColumnaBool, "Booleans");
        System.out.println("Columna booleana adicional agregada:");
        tabla.visualizar();
    }

    public static void testAgregarColumnaConEtiquetaRepetida(Tabla tabla) {
        List<String> datos4 = Arrays.asList("X", "Y", "Z", "W");
        try {
            tabla.agregarColumna(datos4, "Perro");  // Intento de agregar una columna con etiqueta repetida
        } catch (EtiquetaEnUsoException e) {
            System.out.println(e.getMessage());
        }
        tabla.visualizar();
    }

    public static void testCompletarNA(Tabla tabla) {
        System.out.println("Probando completar NA y rellenar datos faltantes:");
        tabla.reemplazarNullConNA();
        tabla.visualizar();
    }

    public static void testEliminarFila(Tabla tabla) {
        System.out.println("Probando eliminar fila:");
        tabla.eliminarFila(0);
        tabla.visualizar();
    }

    public static void testEliminarColumna(Tabla tabla) {
        System.out.println("Probando eliminar columna:");
        tabla.eliminarColumna("Nombre");
        tabla.visualizar();
    }

    public static void testOrdenarTabla(Tabla tabla) {
        System.out.println("Probando ordenar tabla por 'Edad' ascendente y 'Nombre' descendente:");
        List<String> etiquetas = Arrays.asList("Edad", "Nombre");
        List<Boolean> orden = Arrays.asList(true, false);  // Edad ascendente, Nombre descendente
        tabla.ordenarTabla(etiquetas, orden);
        tabla.visualizar();
    }

    public static void testAccesoIndexado(Tabla tabla) {
        System.out.println("Probando acceso indexado:");
        // Acceder a una fila completa
        System.out.print("Accediendo a la fila con etiqueta 'mundo': ");
        List<Object> datosFila = tabla.obtenerFilaPorEtiqueta("mundo");
        System.out.println(datosFila);

        // Acceder a una columna completa
        System.out.print("Accediendo a la columna 'Edad': ");
        List<?> datosColumna = tabla.obtenerColumnaPorEtiquetaIndex("Edad");
        System.out.println(datosColumna);

        // Acceder a una celda específica
        System.out.print("Accediendo a la celda ('mundo', 'Edad'): ");
        Object datoCelda = tabla.obtenerCelda("mundo", "Edad");
        System.out.println(datoCelda);
    }
}