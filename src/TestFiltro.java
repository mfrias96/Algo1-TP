import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Archivo.Archivo;
import Columna.Columna;
import Tabla.Tabla;
import excepciones.*;

public class TestFiltro {
    public static void main(String[] args) throws Exception {
        // Test: Filtrado
        System.out.println("inicia test filtrado de tabla");
        Tabla tablaAFiltrar = new Tabla();
        tablaAFiltrar = Archivo.importar("src/datos_prueba.csv",",", false);
        tablaAFiltrar.visualizar();

        System.out.println("testeo filtrar sin query");
        System.out.println("Columna_0 > 0.1");
        tablaAFiltrar = tablaAFiltrar.filtrarPorCondicion(tablaAFiltrar, "Columna_0", '>', "0.1");
        tablaAFiltrar.visualizar();

        System.out.println("testeo filtrar con query");
        String query = "Columna_0 > 0 and Columna_1 = mundo";
        System.out.println(query);
        tablaAFiltrar = Archivo.importar("src/datos_prueba.csv",",", false);
        tablaAFiltrar = tablaAFiltrar.filtrar(tablaAFiltrar,query);
        tablaAFiltrar.visualizar();

    }
}
