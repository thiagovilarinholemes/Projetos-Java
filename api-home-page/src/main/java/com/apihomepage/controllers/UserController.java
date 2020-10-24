package com.apihomepage.controllers;

import com.apihomepage.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private Firestore db;

    @GetMapping("/users")
    public List<User> getUsers() throws ExecutionException, InterruptedException {

        // Recupera o collections de forma assíncrona
        ApiFuture<QuerySnapshot> future =
                db.collection("users").whereEqualTo("timestampDelete", false).get();

        // Resposta
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> listUser = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            listUser.add(document.toObject(User.class));
        }
        return listUser;
    }

    @GetMapping("/user/{id}")
    public Map<String, Object> getByIdUser(@PathVariable String id) throws ExecutionException, InterruptedException {

        DocumentReference docRef = db.collection("users").document(id);

        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();

        // future.get() blocks on response
        DocumentSnapshot document = future.get();

        return document.getData();
    }

    @PostMapping("/user")
    public void insertUser(@RequestBody User user){

        // Para gerar um collection com ID automatico. Users será o nome da collection e o document será o ID,
        // neste caso ele será automatico pois está vazio, sem nome
        DocumentReference addedDocRef = db.collection("users").document();

        //Grava no banco de dados
        ApiFuture<WriteResult> future = addedDocRef.set(user);

        // Insert Update timestamp and id
        ApiFuture<WriteResult> updateTimestamp = addedDocRef.update("timestampCreate", FieldValue.serverTimestamp());
        ApiFuture<WriteResult> writeResult = addedDocRef.update("timestampDelete", false);
        ApiFuture<WriteResult> updateId = addedDocRef.update("id", addedDocRef.getId());


    }
    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable String id,  @RequestBody User obj) throws ExecutionException, InterruptedException {

        DocumentReference docRef = (DocumentReference) db.collection("users").document(id);

        // Atualiza no banco de dados
        ApiFuture<WriteResult> future = docRef.update(
                "name", obj.getName(),
                "age", obj.getAge()
        );

        // timestamp
        ApiFuture<WriteResult> writeResult = docRef.update("timestampUpdate", FieldValue.serverTimestamp());
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id){
        DocumentReference docRef = (DocumentReference) db.collection("users").document(id);

        // timestamp
        ApiFuture<WriteResult> writeResult = docRef.update("timestampDelete", true);

//        ApiFuture<WriteResult> future = docRef.delete();
    }


}
