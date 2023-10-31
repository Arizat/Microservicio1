package com.example.demo.Repository;

import com.example.demo.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TareaRepository extends JpaRepository <Tarea,Long> {


}
