package juan.tareas.api_rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import juan.tareas.api_rest.entities.Tarea;
import juan.tareas.api_rest.repositories.TareaRepository;

@Service
public class TareaServiceJPA implements TareaService{

    @Autowired
    private TareaRepository repository;
    @Transactional(readOnly = true)
    @Override
    public List<Tarea> findAll() {
        return (List<Tarea>) repository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Tarea> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional  
    @Override
    public Tarea save(Tarea tarea) {
        return repository.save(tarea);
    }

    @Transactional
    @Override
    public Optional<Tarea> update(Long id, Tarea tarea) {
        Optional<Tarea> tareaOptional = repository.findById(id);
        if(tareaOptional.isPresent()){
            Tarea tareaDb = tareaOptional.orElseThrow();
            tareaDb.setTitulo(tarea.getTitulo());
            tareaDb.setDescripcion(tarea.getDescripcion());
            tareaDb.setEstado(tarea.getEstado());
            return Optional.of(repository.save(tareaDb));
        }
        return tareaOptional;
    }

    @Transactional
    @Override
    public Optional<Tarea> delete(Long id) {
        Optional<Tarea> tareaOptional = repository.findById(id);
        tareaOptional.ifPresent(tarea -> {
            repository.delete(tarea);
        });
        return tareaOptional;
    }
}
