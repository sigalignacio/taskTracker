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

    @Override
    public int compareTo(org.springframework.scheduling.config.Task o) {
        return 0;
    }
}
