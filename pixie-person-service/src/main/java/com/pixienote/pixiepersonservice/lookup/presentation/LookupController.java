package com.pixienote.pixiepersonservice.lookup.presentation;

import com.pixienote.pixiepersonservice.lookup.application.LookupService;
import com.pixienote.pixiepersonservice.lookup.domain.LookupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lookup")
public class LookupController {

    @Autowired
    private LookupService lookupService;

    @GetMapping("/get/{id}")
    public ResponseEntity<LookupDto> getLookup(@PathVariable long id) {
        return new ResponseEntity<>(lookupService.getLookup(id), HttpStatus.OK);
    }

    @GetMapping("/get-by-value/{value}")
    public ResponseEntity<LookupDto> getLookupByValue(@PathVariable String value) {
        return new ResponseEntity<>(lookupService.getLookupByValue(value), HttpStatus.OK);
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<LookupDto> getLookupByTitle(@PathVariable String title) {
        return new ResponseEntity<>(lookupService.getLookupByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/get-by-parent-id/{parentId}")
    public ResponseEntity<List<LookupDto>> getLookupByParentId(@PathVariable long parentId) {
        return new ResponseEntity<>(lookupService.getLookupByParentId(parentId), HttpStatus.OK);
    }

    @GetMapping("/get-by-group-id/{groupId}")
    public ResponseEntity<List<LookupDto>> getLookupByGroupId(@PathVariable long groupId) {
        return new ResponseEntity<>(lookupService.getLookupByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/get-by-group-title/{title}")
    public ResponseEntity<List<LookupDto>> getLookupByGroupTitle(@PathVariable String title) {
        return new ResponseEntity<>(lookupService.getLookupByGroupTitle(title), HttpStatus.OK);
    }

    @GetMapping("/get-by-parent-title/{title}")
    public ResponseEntity<List<LookupDto>> getLookupByParentTitle(@PathVariable String title) {
        return new ResponseEntity<>(lookupService.getLookupByParentTitle(title), HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<LookupDto>> search(@PathVariable String title) {
        return new ResponseEntity<>(lookupService.search(title), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<LookupDto> update(@RequestBody LookupDto dto) {
        return new ResponseEntity<>(lookupService.update(dto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<LookupDto> add(@RequestBody LookupDto dto) {
        return new ResponseEntity<>(lookupService.add(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> delete(@PathVariable long id) {
        return new ResponseEntity<>(lookupService.delete(id), HttpStatus.OK);
    }


}
