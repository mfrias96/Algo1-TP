package Filtro;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;

import Columna.Columna;
import Columna.Columna_bool;
import Columna.Columna_num;
import Columna.Columna_string;
import Tabla.Tabla;

public interface Filtro {
// query : “columna1 > 3 and columna2 = Verdadero”
    default Tabla filtrar(Tabla tabla, String query) {
        List<String[]> condiciones = interpretarQuery(query);
        Tabla tablaFiltrada = tabla.copia_p();

        for (String[] condicion : condiciones) {
            String columna = condicion[0];
            char operador = condicion[1].charAt(0);
            String valor = condicion[2];
            String logico = condicion.length > 3 ? condicion[3].toLowerCase() : "";

            Tabla resultadoCondicion = filtrarPorCondicion(tablaFiltrada, columna, operador, valor);

            if ("and".equals(logico)) {
                tablaFiltrada = interseccion(tablaFiltrada, resultadoCondicion); // Mantener filas comunes
            } else if ("or".equals(logico)) {
                tablaFiltrada = union(tablaFiltrada, resultadoCondicion); // Mantener filas de cualquiera de los dos
            } else if ("not".equals(logico)) {
                tablaFiltrada = diferencia(tablaFiltrada, resultadoCondicion); // Eliminar filas que cumplen la condición
            } else {
                tablaFiltrada = resultadoCondicion; // Primera condición o sin operador lógico
            }
        }

        return tablaFiltrada;
    }

    private List<String[]> interpretarQuery(String query) {
        String[] queryPartes = query.split(" ");
        List<String[]> condiciones = new ArrayList<>();

        for (int i = 0; i < queryPartes.length; i += 4) {
            String columna = queryPartes[i];
            char operador = queryPartes[i + 1].charAt(0);
            String valor = queryPartes[i + 2];
            String logico = (i + 3 < queryPartes.length) ? queryPartes[i + 3] : "";

            condiciones.add(new String[]{columna, String.valueOf(operador), valor, logico});
        }

        return condiciones;
    }

    default Tabla filtrarPorCondicion(Tabla tabla, String etiquetaColumna, char operador, String valor) {
        Map<Character, Predicate<Object>> operadores = new HashMap<>();

        operadores.put('<', e -> compararNumeros(e, valor) < 0);
        operadores.put('>', e -> compararNumeros(e, valor) > 0);
        operadores.put('=', e -> compararNumeros(e, valor) == 0);
        operadores.put('!', e -> compararNumeros(e, valor) != 0);

        Predicate<Object> condicion = operadores.get(operador);

        if (condicion == null) {
            throw new IllegalArgumentException("Operador no válido. Los operadores válidos son '<', '>', '=', '!'");
        }

        Tabla tablaFiltrada = tabla.copia_p();
        Columna columnaAFiltrar = tablaFiltrada.obtenerColumnaPorEtiqueta(etiquetaColumna);

        if (columnaAFiltrar == null) {
            throw new IllegalArgumentException("La columna con la etiqueta " + etiquetaColumna + " no existe.");
        }

        for (int i = columnaAFiltrar.largoColumna() - 1; i >= 0; i--) {
            Object valorAComparar = columnaAFiltrar.getdato(i);
            if (valorAComparar == null || !condicion.test(valorAComparar)) {
                tablaFiltrada.eliminarFila(i);
            }
        }

        return tablaFiltrada;
    }

    private int compararNumeros(Object e, String valor) {
        if (e == null || valor == null) {
            return 1;
        }

        try {
            if (e instanceof Number) {
                Double eDouble = ((Number) e).doubleValue();
                Double valorDouble = Double.parseDouble(valor);
                return eDouble.compareTo(valorDouble);
            } else {
                return e.toString().compareTo(valor);
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El valor no es un número válido: " + valor, ex);
        }
    }

    private Tabla interseccion(Tabla tabla1, Tabla tabla2) {
        Tabla resultado = tabla1.copia_p();
        for (int i = resultado.getEtiquetasFilas().size() - 1; i >= 0; i--) {
            if (!tabla2.getEtiquetasFilas().contains(resultado.getEtiquetasFilas().get(i))) {
                resultado.eliminarFila(i);
            }
        }
        return resultado;
    }

    private Tabla union(Tabla tabla1, Tabla tabla2) {
        Tabla resultado = tabla1.copia_p();
        for (String etiquetaFila : tabla2.getEtiquetasFilas()) {
            if (!resultado.getEtiquetasFilas().contains(etiquetaFila)) {
                resultado.agregarfila(tabla2.obtenerFilaPorEtiqueta(etiquetaFila));
            }
        }
        return resultado;
    }

    private Tabla diferencia(Tabla tabla1, Tabla tabla2) {
        Tabla resultado = tabla1.copia_p();
        for (String etiquetaFila : tabla2.getEtiquetasFilas()) {
            resultado.eliminarFila(etiquetaFila);
        }
        return resultado;
    }
    default <E extends Comparable<E>> Tabla filtrar(Tabla tabla, String etiquetaColumna, char operador, E valor) {
        Map<Character, Predicate<E>> operadores = new HashMap<>();
    
        operadores.put('<', e -> compararNumeros(e, valor) < 0);
        operadores.put('>', e -> compararNumeros(e, valor) > 0);
        operadores.put('=', e -> compararNumeros(e, valor) == 0);
        operadores.put('!', e -> compararNumeros(e, valor) != 0);
    
        Predicate<E> condicion = operadores.get(operador);
    
        if (condicion == null) {
            throw new IllegalArgumentException("Operador no válido. Los operadores válidos son '<', '>', '=', '!'");
        }
    
        Tabla tablaFiltrada = tabla.copia_p();
        Columna columnaAFiltrar = tablaFiltrada.obtenerColumnaPorEtiqueta(etiquetaColumna);
    
        if (columnaAFiltrar == null) {
            throw new IllegalArgumentException("La columna con la etiqueta " + etiquetaColumna + " no existe.");
        }
    
        for (int i = columnaAFiltrar.largoColumna() - 1; i >= 0; i--) {
            E valorAComparar = (E) columnaAFiltrar.getdato(i);
            if (valorAComparar == null || !condicion.test(valorAComparar)) {
                tablaFiltrada.eliminarFila(i);
            }
        }
    
        return tablaFiltrada;
    }
    
    @SuppressWarnings("unchecked")
    private <E> int compararNumeros(E e, E valor) {
        if (e == null || valor == null) {
            return 1;  // Si alguno es nulo, tratamos el valor como no válido
        }
        
        // Convierte ambos valores a Double si son números
        if (e instanceof Number && valor instanceof Number) {
            Double eDouble = ((Number) e).doubleValue();
            Double valorDouble = ((Number) valor).doubleValue();
            return eDouble.compareTo(valorDouble);
        }
        
        // Si no son números, intenta la comparación genérica
        return ((Comparable<E>) e).compareTo(valor);
    }
}