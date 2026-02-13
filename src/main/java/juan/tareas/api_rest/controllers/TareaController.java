package juan.tareas.api_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import juan.tareas.api_rest.dto.request.CrearTareaDTO;
import juan.tareas.api_rest.entities.Tarea;

import juan.tareas.api_rest.services.TareaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@OpenAPIDefinition(info=@Info(title="Gestor de tareas", 
                              description="Permite creación, lectura, modificación y borrado de tareas. "+
                              "Todas las tareas creadas comienzan con el estado PENDIENTE. Consulte las "+
                              "restricciones de parámetros en la sección 'Schemas'"))
@RestController
@RequestMapping(value="/api/tareas")
public class TareaController {

    @Autowired
    private TareaService service;

    @Operation(
        summary = "Lista todas las tareas",
        description = "Muestra todas las tareas almacenadas en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devuelve lista de tareas"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<Tarea> list(){
        return service.findAll();
    }

    @Operation(
        summary = "Lista todas las tareas con estado PENDIENTE",
        description = "Muestra todas las tareas almacenadas con estado PENDIENTE en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devuelve lista de tareas pendientes"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value="/pendientes")
    public List<Tarea> listarPendientes(){
        List<Tarea> tareasPendientes = new ArrayList<Tarea>();
        for (Tarea t: service.findAll()){
            if(t.getEstado().equals("PENDIENTE")){
                tareasPendientes.add(t);
            }
        }
        return tareasPendientes;
    }

    @Operation(
        summary = "Lista todas las tareas con estado COMPLETADA",
        description = "Muestra todas las tareas almacenadas con estado COMPLETADA en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devuelve lista de tareas completadas"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value="/completadas")
    public List<Tarea> listarCompletadas(){
        List<Tarea> tareasCompletadas = new ArrayList<Tarea>();
        for (Tarea t: service.findAll()){
            if(t.getEstado().equals("COMPLETADA")){
                tareasCompletadas.add(t);
            }
        }
        return tareasCompletadas;
    }

    @Operation(
        summary = "Lista la tarea con el ID asociado que se le pase. Si el ID no existe devuelve 404.",
        description = "Muestra todas las tareas almacenadas con estado PENDIENTE en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devuelve la tarea con el ID asociado"),
        @ApiResponse(responseCode = "404", description = "El ID de la tarea no existe en el sistema"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Tarea> tareaOptional = service.findById(id);
        if(tareaOptional.isPresent()){
            return ResponseEntity.ok(tareaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear una nueva tarea",
        description = "Crea una nueva tarea en el sistema. "
                    + "El campo 'titulo' es obligatorio y no puede superar los 50 caracteres."
                    + "Las tareas por defecto comienzan en estado PENDIENTE"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarea creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/agregarTarea")
    public ResponseEntity<?> add(@Valid @RequestBody CrearTareaDTO dtoTarea){
        if (dtoTarea.getTitulo().isBlank() || dtoTarea.getTitulo().length()>50 || dtoTarea.getDescripcion().length()>300) 
            return ResponseEntity.badRequest().build();
        Tarea tarea = new Tarea();
        tarea.setTitulo(dtoTarea.getTitulo());
        tarea.setDescripcion(dtoTarea.getDescripcion());
        tarea.setEstado("PENDIENTE");
        Tarea guardada = service.save(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @Operation(
        summary = "Modificar una tarea existente",
        description = "Permite modificar los campos de una tarea"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarea modificada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "El ID de la tarea no existe en el sistema"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> update(@PathVariable Long id, @RequestBody Tarea tarea){
        String estado = tarea.getEstado();
            if(estado.equals("COMPLETADA") || estado.equals("PENDIENTE")){
                Optional<Tarea> tareaOpcional = service.update(id, tarea);
                if(tareaOpcional.isPresent()) 
                    return ResponseEntity.status(HttpStatus.CREATED).body(tareaOpcional.orElseThrow());
                else
                    return ResponseEntity.notFound().build();
            }
            else
                return ResponseEntity.badRequest().build();
    }

    @Operation(
        summary = "Eliminar una tarea existente",
        description = "Permite eliminar una tarea del sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarea eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "El ID de la tarea no existe en el sistema"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Tarea> tareaOptional= service.delete(id);
        if(tareaOptional.isPresent()){
            return ResponseEntity.ok(tareaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
