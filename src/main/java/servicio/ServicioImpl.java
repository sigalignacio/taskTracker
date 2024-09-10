package servicio;

import entidad.Task;
import entidad.TaskStatus;
import exception.NoSuchTaskException;
import repo.IRepositorio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;

public class ServicioImpl implements IServicio {

    private final IRepositorio repositorio;
    private final Set<Task> tasks;

    private int getNewId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

    private Task findTask(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchTaskException(String.format("Task with ID %d not found", id)));
    }

    private void markTaskStatus(int taskId, TaskStatus newStatus) {
        Task originalTask = findTask(taskId);

        Task changedStatusTask = new Task(
                originalTask.getId(),
                originalTask.getDescription(),
                newStatus,
                originalTask.getCreatedTime(),
                originalTask.getUpdatedTime()
        );

        tasks.remove(originalTask);
        tasks.add(changedStatusTask);
    }

    private List<Task> getTasksByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .sorted()
                .toList();
    }


    public ServicioImpl(IRepositorio repositorio) throws IOException {
        this.repositorio = repositorio;
        this.tasks = repositorio.getTasks();
    }
    @Override
    public void saveTasks() throws IOException {
        repositorio.saveTasks(tasks);
    }

    @Override
    public int addTask(String description) {
        // nos da el dia y tiempo actual
        LocalDateTime now = now();
        Task task = new Task(getNewId(), description, TaskStatus.TO_DO, now, now);
        tasks.add(task);
        return task.getId();
    }

    @Override
    public void updateTask(int id, String description) {
        Task originalTask = findTask(id);

        Task updatedTask = new Task(
                originalTask.getId(),
                description,
                originalTask.getStatus(),
                originalTask.getCreatedTime(),
                now()
        );

        tasks.remove(originalTask);
        tasks.add(updatedTask);
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(findTask(id));
    }

    @Override
    public void markTaskAsToDo(int id) {
        markTaskStatus(id, TaskStatus.TO_DO);
    }

    @Override
    public void markTaskAsInProgress(int id) {
        markTaskStatus(id, TaskStatus.IN_PROGRESS);
    }

    @Override
    public void markTaskAsDone(int id) {
        markTaskStatus(id, TaskStatus.DONE);
    }

    @Override
    public List<Task> getAllTasks() {
        // para trabajar con un Set que es tasks,
        // se tiene que convertirlo en un stream,
        // con .stream(), una vez ahi lo sorteamos
        // y lo convertimos en una List
        return tasks.stream()
                .sorted()
                .toList();
    }

    @Override
    public List<Task> getToDoTasks() {
        return getTasksByStatus(TaskStatus.TO_DO);
    }

    @Override
    public List<Task> getInProgressTasks() {
        return getTasksByStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public List<Task> getDoneTasks() {
        return getTasksByStatus(TaskStatus.DONE);
    }
}
