package com.uio443.diarybackend.controller;


import com.uio443.diarybackend.model.Collection;
import com.uio443.diarybackend.service.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Collection> addCollection(@PathVariable("userId") Long userId, @RequestBody Collection collection) {
        Collection newCollection = collectionService.addCollection(userId, collection);
        return new ResponseEntity<>(newCollection, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);

        return new ResponseEntity<>(String.format("Collection with ID: %d has been deleted", id), HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Collection>> getAllUserCollections(@PathVariable Long userId) {
        List<Collection> collectionList = collectionService.getAllUserCollections(userId);

        return new ResponseEntity<>(collectionList, HttpStatus.OK);
    }

    @GetMapping("/collectionId/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable Long id) {
        Collection collection = collectionService.getCollectionById(id);

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }


    @GetMapping("/title/{title}/userId/{userId}")
    public ResponseEntity<Collection> getCollectionByTitleAndUserId(@PathVariable("title") String title, @PathVariable("userId") Long userId) {
        Collection collection = collectionService.getCollectionByTitleAndUserId(title, userId);

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Collection>> getAllCollectionsByTitle(@PathVariable String title) {
        List<Collection> collectionList = collectionService.getAllCollectionsByTitle(title);

        return new ResponseEntity<>(collectionList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Collection> updateCollection(@RequestBody Collection collection) {
        Collection newCollection = collectionService.updateCollection(collection);

        return new ResponseEntity<>(newCollection, HttpStatus.OK);
    }

}
