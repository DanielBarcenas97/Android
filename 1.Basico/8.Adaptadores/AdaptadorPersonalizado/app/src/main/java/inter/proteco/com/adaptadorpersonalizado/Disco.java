package inter.proteco.com.adaptadorpersonalizado;

/**
 * Created by proteco on 20/10/17.
 */

public class Disco {

    String Disco;
    String artista;
    int portada;
    double precio;

    public Disco(String disco, String artista, int portada, double precio) {
        Disco = disco;
        this.artista = artista;
        this.portada = portada;
        this.precio = precio;
    }

    public String getDisco() {
        return Disco;
    }

    public String getArtista() {
        return artista;
    }

    public int getPortada() {
        return portada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setDisco(String disco) {
        Disco = disco;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setPortada(int portada) {
        this.portada = portada;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
