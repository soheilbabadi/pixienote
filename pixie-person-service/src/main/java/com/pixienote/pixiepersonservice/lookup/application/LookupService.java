package com.pixienote.pixiepersonservice.lookup.application;

import com.pixienote.pixiepersonservice.lookup.domain.LookupDto;

import java.util.List;

public interface LookupService {
    LookupDto getLookup(long id);

    LookupDto getLookupByValue(String value);

    LookupDto getLookupByTitle(String title);

    List<LookupDto> getLookupByParentId(long parentId);

    List<LookupDto> getLookupByGroupId(long groupId);

    List<LookupDto> getLookupByGroupTitle(String title);

    List<LookupDto> getLookupByParentTitle(String title);

    List<LookupDto> search(String title);


    LookupDto update(LookupDto dto);

    LookupDto add(LookupDto dto);

    int delete(long id);

    long countAll();

    long countByGroupId(long groupId);

    long countByParentId(long parentId);


}
