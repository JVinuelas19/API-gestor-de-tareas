package juan.tareas.api_rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Entidad que representa una tarea dentro del sistema")
@Entity
@Table(name="tareas")
public class Tarea {
    @Schema(description = "Identificador único de la tarea", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Título de la tarea. Campo obligatorio.", example = "Estudiar Docker")
    @NotBlank
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String titulo;

    @Schema(description = "Descripción ampliada de la tarea", example = "Tema 2 curso de Udemy: Dockerfile - Comandos básicos")
    @Size(max = 300)
    @Column(length = 300)
    private String descripcion;

    @Schema(description = "Estado de la tarea. Los estados válidos son COMPLETADA o PENDIENTE", example = "PENDIENTE")
    @Size(max = 20)
    @Column(length = 20)
    private String estado;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
 
}
