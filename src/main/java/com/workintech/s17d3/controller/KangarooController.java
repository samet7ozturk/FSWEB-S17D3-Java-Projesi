package com.workintech.s17d3.controller;

import com.workintech.s17d3.entity.Kangaroo;
import com.workintech.s17d3.validation.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Kangaroo> findAll() {
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo find(@PathVariable Integer id) {
        ZooValidation.isIdValid(id);
        ZooValidation.checkKangarooExistence(kangaroos, id, true);
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo) {
        ZooValidation.checkKangarooExistence(kangaroos, kangaroo.getId(), false);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
        ZooValidation.isIdValid(id);
        ZooValidation.checkKangarooExistence(kangaroos, id, true);
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id) {
        ZooValidation.isIdValid(id);
        ZooValidation.checkKangarooExistence(kangaroos, id, true);
        return kangaroos.remove(id);
    }

}