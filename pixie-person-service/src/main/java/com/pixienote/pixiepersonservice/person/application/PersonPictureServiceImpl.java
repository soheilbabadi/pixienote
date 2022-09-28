package com.pixienote.pixiepersonservice.person.application;

import com.pixienote.pixiepersonservice.exception.PersonException;
import com.pixienote.pixiepersonservice.person.domain.PersonPicture;
import com.pixienote.pixiepersonservice.person.domain.dto.PersonPictureDto;
import com.pixienote.pixiepersonservice.person.infra.PersonPictureRepository;
import com.pixienote.pixiepersonservice.person.infra.PersonRepository;
import com.pixienote.pixiepersonservice.utils.ImageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class PersonPictureServiceImpl implements PersonPictureService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonPictureRepository personPictureRepository;


    @Override
    public PersonPictureDto getPicture(String uid) {
        var picture = personPictureRepository.findById(UUID.fromString(uid)).orElse(null);
        return convertToDto(picture);
    }

    @Override
    public PersonPictureDto getPictureByPersonId(long personId) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonException("Person not found"));

        var picture = personPictureRepository.findByPerson(person).orElse(null);
        return convertToDto(picture);
    }


    @Override
    public PersonPictureDto getPictureByUsername(String username) {
        var person = personRepository.findByUsername(username)
                .orElseThrow(() -> new PersonException("Person not found"));
        var picture = personPictureRepository.findByPerson(person).orElse(null);
        return convertToDto(picture);
    }


    @Override
    public PersonPictureDto addPersonPicture(MultipartFile file, long personId) throws IOException {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonException("Person not found"));
        var uid = UUID.randomUUID();
        var thumb = ImageUtil.thumbnail(file);
        var picture = PersonPicture.builder()
                .filename(file.getOriginalFilename())
                .contentType("image/jpeg")
                .id(uid)
                .registerOn(LocalDateTime.now(ZoneOffset.UTC))
                .updateOn(LocalDateTime.now(ZoneOffset.UTC))
                .fileContent(thumb)
                .size(file.getSize())
                .person(person)
                .pictureUrl("/person-picture/get/" + uid)
                .build();

        personPictureRepository.save(picture);

        return convertToDto(picture);

    }

    @Override
    public PersonPictureDto updatePersonPicture(MultipartFile file, long personId) throws IOException {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonException("Person not found"));

        var picture = personPictureRepository.findByPerson(person).orElse(null);
        if (picture == null) {
            return addPersonPicture(file, personId);
        }
        var thumb = ImageUtil.thumbnail(file);

        picture.setFilename(file.getOriginalFilename());
        picture.setContentType("image/jpeg");
        picture.setUpdateOn(LocalDateTime.now(ZoneOffset.UTC));
        picture.setFileContent(thumb);
        picture.setSize(file.getSize());
        personPictureRepository.save(picture);

        return convertToDto(picture);

    }

    @Override
    public void deletePersonPicture(long personId) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonException("Person not found"));
        var picture = personPictureRepository.findByPerson(person).orElse(null);
        if (picture != null) {
            personPictureRepository.delete(picture);
        } else {
            throw new PersonException("Person picture not found");
        }

    }

    private PersonPictureDto convertToDto(PersonPicture picture) {
        var dto = new PersonPictureDto();
        BeanUtils.copyProperties(picture, dto);
        dto.setPersonName(picture.getPerson().getFullName());
        dto.setPersonId(picture.getPerson().getId());
        return dto;
    }
}
