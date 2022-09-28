package com.pixienote.pixiepersonservice.person.presentation;

import com.pixienote.pixiepersonservice.person.application.PersonPictureService;
import com.pixienote.pixiepersonservice.person.domain.dto.PersonPictureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/person-picture")
public class PersonPictureController {

    @Autowired
    private  PersonPictureService personPictureService;



    @PostMapping(path = "/post", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<PersonPictureDto> uploadImage(@RequestBody MultipartFile file, @PathVariable long personId) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PersonPictureDto());
    }


    @GetMapping(path = {"/get-by-id/{id}"})
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") String id) {

        var picture = personPictureService.getPicture(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(picture.getContentType()))
                .body(picture.getFileContent());
    }

    @GetMapping(path = {"/get-by-username/{username}"})
    public ResponseEntity<byte[]> getImageByUsername(@PathVariable("username") String username) {

        var picture = personPictureService.getPictureByUsername(username);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(picture.getContentType()))
                .body(picture.getFileContent());
    }

}
