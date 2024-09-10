package servicio;

import entidad.Task;

import java.io.IOException;
import java.util.List;

public interface IServicio {
    void saveTasks() throws IOException;

    int addTask(String description);

    void updateTask(int id,String description);

    void deleteTask(int id);

    void markTaskAsToDo(int id);

    void markTaskAsInProgress(int id);

    void markTaskAsDone(int id);

    List<Task> getAllTasks();

    List<Task> getToDoTasks();

    List<Task> getInProgressTasks();

    List<Task> getDoneTasks();



}
