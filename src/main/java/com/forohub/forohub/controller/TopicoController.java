package com.forohub.forohub.controller;

import com.forohub.forohub.domain.topicos.*;
import com.forohub.forohub.domain.topicos.dto.*;
import com.forohub.forohub.domain.topicos.validaciones.ValidadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ValidadorService validadorService;

    @Tag(name = "post", description = "Método POST de la API para creación de tópicos")
    @Operation(
            summary = "Crea un nuevo tópico",
            description = "Añadir un nuevo tópico utilizando el título y/o mensaje proporcionado. La respuesta incluye el id del tópico creado, título, mensaje, estado, autor, nombre del curso y fecha."
    )
    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallesTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        var topico = validadorService.registrarTopico(datosRegistroTopico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesTopico(topico));
    }

    @Tag(name = "get", description = "Métodos GET de la API para listar tópicos")
    @Operation(
            summary = "Obtener todos los tópicos",
            description = "Recuperar una lista de todos los tópicos existentes. La respuesta contiene id, título, mensaje, estado, autor, nombre del curso y fecha de cada tópico."
    )
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(Pageable pageable) {
        var topicos = topicoRepository.findAll(pageable)
                .map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicos);
    }

    @Tag(name = "get", description = "Métodos GET de la API para ordenar tópicos por fecha")
    @Operation(
            summary = "Obtener tópicos por fecha",
            description = "Recuperar tópicos ordenados de manera ascendente por su fecha. La respuesta contiene id, título, mensaje, estado, autor, nombre del curso y fecha de cada tópico."
    )
    @GetMapping("/filtrar1")
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicosForFecha(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable pageable) {
        var topicos = topicoRepository.findAll(pageable)
                .map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicos);
    }

    @Tag(name = "get", description = "Métodos GET de la API para filtrar tópicos por curso y año")
    @Operation(
            summary = "Obtener tópicos por curso y año",
            description = "Listar tópicos que coincidan con el nombre del curso y año especificados. La respuesta incluye id, título, mensaje, estado, autor, nombre del curso y fecha de cada tópico."
    )
    @GetMapping("/filtrar2")
    public ResponseEntity listarTopicosPorNombreCursoYAño(@RequestParam String curso, @RequestParam Integer año) {
        var topico = topicoRepository.findByNombreCursoAndFechaAño(curso, año);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getLogin(),
                topico.getNombreCurso(),
                topico.getFecha()
        ));
    }

    @Tag(name = "get", description = "Métodos GET de la API para obtener detalles de un tópico")
    @Operation(
            summary = "Obtener detalles de un tópico",
            description = "Recuperar detalles de un tópico específico utilizando su id. La respuesta incluye id, título, mensaje, estado, autor, nombre del curso y fecha del tópico."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        var topico = validadorService.validarExistencia(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getLogin(),
                topico.getNombreCurso(),
                topico.getFecha()
        ));
    }

    @Tag(name = "put", description = "Métodos PUT de la API para modificar tópicos")
    @Operation(
            summary = "Modificar un tópico existente",
            description = "Actualizar los datos de un tópico ya existente. La respuesta contiene el id, título, mensaje, estado, autor, nombre del curso y fecha del tópico actualizado."
    )
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topico = validadorService.validarExistencia(id);
        topico.actualizar(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getLogin(),
                topico.getNombreCurso(),
                topico.getFecha()
        ));
    }

    @Tag(name = "delete", description = "Métodos DELETE de la API para eliminar tópicos")
    @Operation(
            summary = "Borrar un tópico",
            description = "Eliminar un tópico específico por su id. La respuesta es un estado de 'sin contenido' si la eliminación se realiza con éxito."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topicoOptional = validadorService.validarExistencia(id);
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}