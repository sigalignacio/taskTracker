package entidad;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

// Comparable<Task> es una interfaz que sirve para comparar y ordenar, en este caso
// ordenaria los objetos tipo Task, que nos va a servir para compararlos y luego ordenarlos
// por prioridad

@Component
public class Task implements Comparable<Task> {

    private final int id;
    private final String description;
    private final TaskStatus status;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTime;

    public Task(int id, String description, TaskStatus status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        // estos metodos son mas que nada validaciones, para ver si el objeto task
        // que se va a construir es apto.
        checkId(id);
        checkDescription(description);
        checkUpdatedTime(updatedTime, createdTime);

        this.id = id;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
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

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    // metodo que devuelve true si el updatedTime
    // esta despues del createdTime
    public boolean isUpdated() {
        return updatedTime.isAfter(createdTime);
    }

    // metodo interfaz Comparable de tipo Task
    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.id, o.id);
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

    // genera un hashcode basado en el id
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // este metodo le da un formato a la cadena de texto,
    // con el orden respecto. %d es un numero entero,
    // y %s es un String
    @Override
    public String toString() {
        return String.format("[Task: %d | Description: %s | Status: %s | Created: %s | Updated: %s]",
                this.id,
                this.description,
                this.status.getTitleCaseStatus(),
                this.createdTime.toString(),
                isUpdated() ? this.updatedTime.toString() : "N/A");

    }

    private void checkId(int id) {
        if (id <= 0) {
            // si el id es menor a 0, que tire esta Exception
            throw new IllegalArgumentException("Task ID must be positive");
        }
    }

    private void checkDescription(String description) {
        // isblank es si se mando solo espacios o esta vacia
        if ( description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty, neither only blank spaces");
        } if (description.length() > 255) {
            throw new IllegalArgumentException("Description is too long");
        }

    }

    private void checkUpdatedTime(LocalDateTime createdTime, LocalDateTime updatedTime) {
        // isbefore() metodo para ver si fue antes en tiempo
        if (updatedTime.isBefore(createdTime)) {
            throw new IllegalArgumentException("Updated time cannot be before the created ime");
        }
    }

}
