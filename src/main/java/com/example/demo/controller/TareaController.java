package com.example.demo.controller;

import com.example.demo.Repository.TareaRepository;
import com.example.demo.model.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private TareaRepository tareaRepository;


    @GetMapping
    public List<Tarea> getAllTareas(){
        return tareaRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getBookId(@PathVariable long id){
        Optional<Tarea> tarea = tareaRepository.findById(id);
        if(tarea.isPresent()){
            return ResponseEntity.ok(tarea.get());
        }else return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
        Tarea savedTarea = tareaRepository.save(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTarea);
    }

    @PutMapping({"/id"})
    public ResponseEntity<Tarea> updatedTarea(@PathVariable Long id, @RequestBody Tarea updatedTarea){
        if(!tareaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updatedTarea.setId(id);
        Tarea savedTarea = tareaRepository.save((updatedTarea));
        return ResponseEntity.ok(savedTarea);


    }
    public ResponseEntity<Tarea> deleteTarea(@PathVariable Long id){
        if (!tareaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        tareaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
