package com.pixienote.pixiepersonservice.person.application;

import com.pixienote.pixiepersonservice.person.domain.dto.PersonPictureDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PersonPictureService {
    PersonPictureDto getPicture(String uid);

    PersonPictureDto getPictureByPersonId(long personId);

    PersonPictureDto getPictureByUsername(String username);

    PersonPictureDto addPersonPicture(MultipartFile file, long personId) throws IOException;

    PersonPictureDto updatePersonPicture(MultipartFile file, long personId) throws IOException;

    void deletePersonPicture(long personId);


}
