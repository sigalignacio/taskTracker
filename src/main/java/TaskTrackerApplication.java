import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cli.TaskManagerCLI;
import repo.RepositorioImpl;
import servicio.ServicioImpl;

import java.io.IOException;
import java.nio.file.Path;

@SpringBootApplication
public class TaskTrackerApplication {

	private static final String PATH = "tasks.json";
	public static void main(String[] args) throws IOException {
		new TaskManagerCLI(new ServicioImpl(new RepositorioImpl(Path.of(PATH)))).run();

	}

}
