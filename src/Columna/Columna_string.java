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
            datos.add(null);
        }else {
            datos.add(dato);
        }

    }

    public Class<String> getTipoClase() {
        return String.class;
    }


    public void cambiarDato(T dato, int i) {
        if (dato == null){
            datos.set(i,(T) new NA());
        }else {
            datos.set(i,dato);
        }
    }
    
    public U getetiqueta(){
        return etiqueta;
    }
    public T getdato(int i){
        return datos.get(i);
    }

    public boolean contieneDato(String dato) {
        return datos.contains(dato);
    }
}


