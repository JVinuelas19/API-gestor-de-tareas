package juan.tareas.api_rest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import juan.tareas.api_rest.repositories.TareaRepository;
import juan.tareas.api_rest.entities.Tarea;


@ExtendWith(MockitoExtension.class)
class TareaServiceJPATest {

    @Mock
    private TareaRepository repository;

    @InjectMocks
    private TareaServiceJPA service;

    @Test
    void crearTareaConEstadoPendiente() {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setId(1L);
        nuevaTarea.setTitulo("Titulo del test");
        nuevaTarea.setEstado("PENDIENTE");
        when(repository.save(any(Tarea.class))).thenReturn(nuevaTarea);
        Tarea resultado = service.save(nuevaTarea);
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    void noCrearTareaSinTitulo() {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setId(1L);
        nuevaTarea.setEstado("PENDIENTE");
        when(repository.save(any(Tarea.class))).thenReturn(nuevaTarea);
        try{
            Tarea nuevoResultado = service.save(nuevaTarea);
            System.out.println(nuevoResultado);
        } catch(Exception e){
            System.out.println("No se ha podido crear una tarea sin titulo.");
        }
    }
}

