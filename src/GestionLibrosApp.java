import java.util.ArrayList;
import java.util.List;

class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    //Constructor
    public Libro(int id, String titulo, String autor, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }
    //Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
//Interface
interface LibroDAO{
    void agregarLibro(Libro libro);
    Libro obtenerLibro(int id);
    void actualizarLibro(Libro libro);
    void eliminarLibro(int id);
}

class LibroDAOImpl implements  LibroDAO{
    private List<Libro> libros = new ArrayList<>();

    @Override
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    @Override
    public Libro obtenerLibro(int id) {
        return libros.stream().filter(libro -> libro.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void actualizarLibro(Libro libro) {
        Libro libroExistente = obtenerLibro(libro.getId());
        if (libroExistente != null) {
            libroExistente.setTitulo(libro.getTitulo());
            libroExistente.setAutor(libro.getAutor());
            libroExistente.setAnioPublicacion(libro.getAnioPublicacion());
            System.out.println("Libro actualizado: " + libro.getTitulo());
        }
    }

    @Override
    public void eliminarLibro(int id) {
        libros.removeIf(libro -> libro.getId() == id);//removeIf es un método que elimina elementos de la lista que cumplan con una condición específica.
        //Esto significa que el método eliminará todos los libros cuyo ID sea igual al valor de id que se pasa como argumento.
        System.out.println("Libro eliminado con ID: " + id);
    }
}
public class GestionLibrosApp {
    public static void main(String[] args) {
        LibroDAO libroDAO = new LibroDAOImpl();

        Libro libro1 = new Libro(1, "Código Limpio: Manual de Artesanía de Software Ágil", "Robert C. Martin", 2008);
        libroDAO.agregarLibro(libro1);

        Libro libro2 = new Libro(2, "Java Efectivo", "Joshua Bloch", 2001);
        libroDAO.agregarLibro(libro2);

        libroDAO.eliminarLibro(1);

        libroDAO.actualizarLibro(new Libro(2, "Java Efectivo Actualizado", "Joshua Bloch", 2001));
    }
}