package juan.tareas.api_rest.repositories;
import juan.tareas.api_rest.entities.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends CrudRepository<Tarea, Long>{

}
