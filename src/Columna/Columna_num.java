package Columna;
import NA.NA;

public class Columna_num<T, U> extends Columna <T, U> {
    // Constructores
    public Columna_num(U etiqueta ) {
        super(etiqueta);
    }

    //generamos toString para visualizar mientras probamos.
    @Override
    public String toString() {
        return "Columna_num [columna=" + datos.toString() + "] " + etiqueta.toString();
    }
    
    public void agregarDato(T dato) {
        if (dato == null){
            datos.add(null);
        }else {
            datos.add(dato);
        }

    }

    // public void cambiarDato(T dato, int i) {
    //     if (dato == null){
    //         datos.set(i,(T) new NA());
    //     }else {
    //         datos.set(i,dato);
    //     }
    // }

    public Class<Number> getTipoClase() {
        return Number.class;
    }
    
    public U getetiqueta(){
        return etiqueta;
    }
    public T getdato(int i){
        return datos.get(i);
    }

}
