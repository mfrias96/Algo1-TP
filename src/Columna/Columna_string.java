package Columna;
import NA.NA;

public class Columna_string <T, U> extends Columna <T, U> {

        // Constructores
    public Columna_string(U etiqueta ) {
        super(etiqueta);
    }

    //generamos toString para visualizar mientras probamos.
    @Override
    public String toString() {
        return "Columna_string [columna=" + datos + "] "+ etiqueta.toString();
    }

    
    public void agregarDato(T dato) {
        if (dato == null){
            datos.add((T) new NA());
        }else {
            datos.add(dato);
        }

    }
}


