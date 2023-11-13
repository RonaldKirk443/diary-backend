package com.uio443.diarybackend.controller;

import com.uio443.diarybackend.model.Entry;
import com.uio443.diarybackend.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/entry")
public class EntryController {

    EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Entry> addEntry(@PathVariable("userId") Long userId, @RequestBody Entry entry) {
        Entry newEntry = entryService.addEntry(userId, entry);
        return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Entry> updateEntry(@RequestBody Entry entry) {
        Entry updatedEntry = entryService.updateEntry(entry);
        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
    }

    @GetMapping("/entryId/{entryId}")
    public ResponseEntity<Entry> getEntryById(@PathVariable("entryId") Long entryId) {
        Entry newEntry = entryService.getEntryById(entryId);
        return new ResponseEntity<>(newEntry, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Entry>> getAllUserEntries(@PathVariable("userId") Long userId) {
        List<Entry> entries = entryService.getAllUserEntries(userId);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @GetMapping("/collectionId/{collectionId}")
    public ResponseEntity<List<Entry>> getAllCollectionEntries(@PathVariable("collectionId") Long collectionId) {
        List<Entry> entries = entryService.getAllCollectionEntries(collectionId);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Entry>> getAllEntriesByTitle(@PathVariable("title") String title) {
        List<Entry> entries = entryService.getAllEntriesByTitle(title);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}/title/{title}")
    public ResponseEntity<List<Entry>> getAllEntriesByTitleAndUserId(@PathVariable("userId") Long userId, @PathVariable("title") String title) {
        List<Entry> entries = entryService.getAllEntriesByTitleAndUserId(userId, title);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{entryId}")
    public ResponseEntity<String> deleteEntryById(@PathVariable("entryId") Long entryId) {
        entryService.deleteEntry(entryId);
        return new ResponseEntity<>(String.format("Entry with ID: %d has been deleted", entryId), HttpStatus.OK);
    }


}
