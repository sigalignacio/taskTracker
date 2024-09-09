package entidad;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Comparable<Task> es una interfaz que sirve para comparar y ordenar, en este caso
// ordenaria los objetos tipo Task, que nos va a servir para compararlos y luego ordenarlos
// por prioridad

@Component
public class Task implements Comparable<org.springframework.scheduling.config.Task> {

    private final int id;
    private final String description;
    private final TaskStatus status;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTIme;

    public Task(int id, String description, TaskStatus status, LocalDateTime createdTime, LocalDateTime updatedTIme) {
        // estos metodos son mas que nada validaciones, para ver si el objeto task
        // que se va a construir es apto.
        checkId(id);
        checkDescription(description);
        checkUpdatedTime(updatedTIme);

        this.id = id;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTIme = updatedTIme;
    }
    // getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getUpdatedTIme() {
        return updatedTIme;
    }

    // es un override del metodo equals, que es de la clase Object
    // Object engloba a todos los tipo objetos de java, osea
    // en este caso le pasaremos un Task, pero el metodo tiene ese parametro
    public boolean equals(Object o) {
        if ( this == o ) return true;
        // el metodo getClass() trae de que tipo de clase/objeto es,
        // osea para comparar que sean del tipo Task los dos
        if (o == null || getClass() != o.getClass()) return false;
        // Aunque el método equals ha verificado que el objeto o es de la clase Task con getClass(),
        //  el compilador de Java no lo sabe entonces casteamos el objeto o de la siguiente manera:
        Task task = (Task) o;
        // compara el id del objeto actual con el id del pasado x el argumento.
        return id == task.id;


    }

    @Override
    public int compareTo(org.springframework.scheduling.config.Task o) {
        return 0;
    }
}
