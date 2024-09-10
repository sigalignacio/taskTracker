package repo;

import entidad.Task;

import java.io.IOException;
import java.util.Set;

public interface IRepositorio {

    // guarda un set de Tasks, y si pasa algo tira esa Exception
    void saveTasks(Set<Task> tasks) throws IOException;

    // devuelve ese set de Tasks
    Set<Task> getTasks() throws IOException;

}
