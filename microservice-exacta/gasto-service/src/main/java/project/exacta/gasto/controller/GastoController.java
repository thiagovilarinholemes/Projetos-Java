package project.exacta.gasto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.exacta.gasto.entity.Gasto;
import project.exacta.gasto.service.GastoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    /** Method GET All Gastos **/
    @GetMapping
    public ResponseEntity<List<Gasto>> listGasto(){
        List<Gasto> gastos = gastoService.listAllGasto();
        if (gastos == null ){
            if (gastos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(gastos);
    }

    /** Method GET ID Gasto **/
    @GetMapping(value = "/{id}")
    public ResponseEntity<Gasto> getGasto(@PathVariable("id") Long id) {
        Gasto gasto =  gastoService.getGasto(id);
        if (gasto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gasto);
    }

    /** Method Create Gasto **/
    @PostMapping
    public ResponseEntity<Gasto> createGasto( @RequestBody Gasto gasto, BindingResult result ){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Gasto gastoCreate =  gastoService.createGasto(gasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoCreate);
    }

    /** Method GET Update Gasto **/
    @PutMapping(value = "/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable("id") Long id, @RequestBody Gasto gasto){
        gasto.setId(id);
        Gasto gastoDB =  gastoService.updateGasto(id, gasto);
        if (gastoDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gastoDB);
    }

    /** Method GET Delete Gasto **/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Gasto> deleteGasto(@PathVariable("id") Long id){
        Gasto gastoDelete = gastoService.deleteGasto(id);
        if (gastoDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gastoDelete);
    }

    /** Method Return Error Valid Gasto **/
    @GetMapping(value = "{}")
    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
