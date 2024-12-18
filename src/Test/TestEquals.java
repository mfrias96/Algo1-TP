package Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import Archivo.Archivo;
import Columna.Columna;
import Tabla.Tabla;
import excepciones.*;

public class TestEquals {
public static void main(String[] args) throws Exception {
    
    Tabla tabla1 = new Tabla();
    Tabla tabla2 = new Tabla();

    //TEST COLUMNA STRING
    System.out.println();
    System.out.println("\nInicio test equals\n");
    List<String> datosString = Arrays.asList ("Hola","mundo",null,"NAaa");
    tabla1.agregarColumna(datosString);
    tabla2.agregarColumna(datosString);

    System.out.println("Test equal columnas string\n");
    System.out.println("Columna de tabla 1" + tabla1.obtenerColumnaPorEtiquetaIndex(0));
    System.out.println("Columna de tabla 2" + tabla2.obtenerColumnaPorEtiquetaIndex(0));
    System.out.println("\nProbando = por referencia");
    System.out.println(tabla1.obtenerColumnaPorEtiquetaIndex(0) == 
                        tabla2.obtenerColumnaPorEtiquetaIndex(0));
    System.out.println("\nProbando equals");
    System.out.println(tabla1.obtenerColumnaPorEtiquetaIndex(0).equals(tabla2.obtenerColumnaPorEtiquetaIndex(0)));

    //TEST COLUMNA NUMERICA
    List<Number> datosNumbers = Arrays.asList (1,20,null,0.01);
    tabla1.agregarColumna(datosNumbers);
    tabla2.agregarColumna(datosNumbers);

    System.out.println("\nTest equal columnas numericas\n");
    System.out.println("Columna de tabla 1" + tabla1.obtenerColumnaPorEtiquetaIndex(1));
    System.out.println("Columna de tabla 2" + tabla2.obtenerColumnaPorEtiquetaIndex(1));
    System.out.println("\nProbando = por referencia");
    System.out.println(tabla1.obtenerColumnaPorEtiquetaIndex(1) == 
                        tabla2.obtenerColumnaPorEtiquetaIndex(1));
    System.out.println("\nProbando equals");
    System.out.println(tabla1.obtenerColumnaPorEtiquetaIndex(1).equals(tabla2.obtenerColumnaPorEtiquetaIndex(1)));

    //TEST COLUMNA BOOLEANA
    List<Boolean> datosBool = Arrays.asList (true,false, true, false);
    tabla1.agregarColumna(datosBool);
    tabla2.agregarColumna(datosBool);

    System.out.println("\nTest equal columnas booleanas\n");
    System.out.println("Columna de tabla 1" + tabla1.obtenerColumnaPorEtiquetaIndex(2));
    System.out.println("Columna de tabla 2" + tabla2.obtenerColumnaPorEtiquetaIndex(2));
    System.out.println("\nProbando = por referencia");
    System.out.println(tabla1.obtenerColumnaPorEtiquetaIndex(2) == 
                        tabla2.obtenerColumnaPorEtiquetaIndex(2));
    System.out.println("\nProbando equals");
    System.out.println(tabla1.obtenerColumnaPorEtiquetaIndex(2).equals(tabla2.obtenerColumnaPorEtiquetaIndex(2)));

    // System.out.println("Test equal Tablas");
    // System.out.println("Tabla 1");
    // tabla1.visualizar();
    // System.out.println("Tabla 2");
    // tabla2.visualizar();

    // System.out.println("Probando = por referencia");
    // System.out.println(tabla1 == tabla2);
    // System.out.println("Probando equals");
    // System.out.println(tabla1.equals(tabla2));
 }
}
