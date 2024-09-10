package repo;

import com.fasterxml.jackson.databind.json.JsonMapper;
import entidad.Task;
import repo.helper.JasonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class RepositorioImpl implements IRepositorio {


    private final Path path;

    public RepositorioImpl(Path path) {
        this.path = path;
    }

    @Override
    public void saveTasks(Set<Task> tasks) throws IOException {
        // Files es una clase que se usa para leer, y manipular archivos,
        // ya sean .txt u otros
        // si no existe el archivo en el sig if, lo crea
        if (!Files.exists(path)) {
            Files.createFile(path);
            // else, escribe un string dentro, que usa el metodo
            // tasksToJson que convierte el Set<Task> en Json.
        }
        Files.writeString(path, JasonMapper.tasksToJson(tasks));

    }

    @Override
    public Set<Task> getTasks() throws IOException {
        // si el archivo no existe, devuelve
        // hashSet vacio
        if (!Files.exists(path)) {
            return new HashSet<>();
        }
        // Files.readString(path) lee el archivo dentro del path,
        // y JasonMapper.jsonToTasks lo transforma en un Set<Task>
        return JasonMapper.jsonToTasks(Files.readString(path));
    }

}
