package juan.tareas.api_rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CrearTareaDTO {
    @NotBlank
    @Size(max = 50)
    @Schema(description = "Título de la tarea. Campo obligatorio.", example = "Hacer la compra")
    private String titulo;
    @Size(max = 300)
    @Schema(description = "Descripción de la tarea.", example = "Pan, carne, huevos, fruta, leche")
    private String descripcion;

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
}
