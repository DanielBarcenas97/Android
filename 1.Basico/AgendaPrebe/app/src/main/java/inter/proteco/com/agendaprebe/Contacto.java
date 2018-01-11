package inter.proteco.com.agendaprebe;


        import com.activeandroid.Model;
        import com.activeandroid.annotation.Column;
        import com.activeandroid.annotation.Table;

/**
 * Clase que simula un registro de una tabla de nuestra base de datos
 * En los arrobas (@) van los nombres que queremos que tengan los objetos de la base dentro
 * de la misma base de datos, no afectan nuestro código Java.
 */

@Table(name = "conversacion")
public class Contacto extends Model {
    //ActiveAndroid crea una PK por defecto, pero podríamos crear una aquí.
    //Campo que contiene el nombre de usuario
    @Column(name = "nombre")
    public String nombre;
    //Campo que contiene la contraseña del usuario
    @Column(name = "imagen")
    public String imagen;

    //Constructor por defecto de un usuario
    public Contacto(){
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
}