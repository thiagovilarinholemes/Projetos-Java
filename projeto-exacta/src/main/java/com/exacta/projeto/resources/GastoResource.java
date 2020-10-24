package com.exacta.projeto.resources;

import com.exacta.projeto.entities.Gasto;
import com.exacta.projeto.entities.Tag;
import com.exacta.projeto.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/gastos")
public class GastoResource {

    @Autowired
    private GastoService service;

    @GetMapping
    public ResponseEntity<List<Gasto>> findAll(){
        List<Gasto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Gasto> findById(@PathVariable Long id){
        Gasto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Gasto> insert(@RequestBody Gasto obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Gasto> update(@PathVariable Long id, @RequestBody Gasto obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
