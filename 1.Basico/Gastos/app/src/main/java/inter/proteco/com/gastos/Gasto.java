package inter.proteco.com.gastos;

import java.io.Serializable;

/**
 * Created by proteco on 27/10/17.
 */

public class Gasto implements Serializable{
    String descripcion;
    int cantidad;

    public Gasto(String descripcion, int cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

}
