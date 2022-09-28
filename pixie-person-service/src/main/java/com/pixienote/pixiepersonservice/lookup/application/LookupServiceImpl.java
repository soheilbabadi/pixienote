package com.pixienote.pixiepersonservice.lookup.application;

import com.pixienote.pixiepersonservice.exception.LookupExeption;
import com.pixienote.pixiepersonservice.lookup.domain.Lookup;
import com.pixienote.pixiepersonservice.lookup.domain.LookupDto;
import com.pixienote.pixiepersonservice.lookup.infra.LookupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {

    private LookupRepository lookupRepository;

    @Override
    public LookupDto getLookup(long id) {
        var entity = lookupRepository.findById(id)
                .orElseThrow(() -> new LookupExeption("Lookup not found"));
        var dto = new LookupDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public LookupDto getLookupByValue(String value) {
        var entity = lookupRepository.findByValueOrderBySortOrder(value)
                .orElseThrow(() -> new LookupExeption("Lookup not found"));
        var dto = new LookupDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public LookupDto getLookupByTitle(String title) {
        var entity = lookupRepository.findByTitleOrderBySortOrder(title)
                .orElseThrow(() -> new LookupExeption("Lookup not found"));
        var dto = new LookupDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<LookupDto> getLookupByParentId(long parentId) {
        var entity = lookupRepository.findByParentIdOrderBySortOrder(parentId);

        if (!entity.iterator().hasNext()) {
            throw new LookupExeption("Lookup not found");
        }


        var resultList = new ArrayList<LookupDto>();
        for (var item : entity) {
            var dto = new LookupDto();
            BeanUtils.copyProperties(item, dto);
        }
        return resultList;
    }

    @Override
    public List<LookupDto> getLookupByGroupId(long groupId) {
        var entity = lookupRepository.findByGroupIdOrderBySortOrder(groupId);

        if (!entity.iterator().hasNext()) {
            throw new LookupExeption("Lookup not found");
        }


        var resultList = new ArrayList<LookupDto>();
        for (var item : entity) {
            var dto = new LookupDto();
            BeanUtils.copyProperties(item, dto);

        }
        return resultList;
    }

    @Override
    public List<LookupDto> getLookupByGroupTitle(String title) {
        var entity = lookupRepository.findByGroupTitleContains(title);

        if (!entity.iterator().hasNext()) {
            throw new LookupExeption("Lookup not found");
        }


        var resultList = new ArrayList<LookupDto>();
        for (var item : entity) {
            var dto = new LookupDto();
            BeanUtils.copyProperties(item, dto);
        }
        return resultList;
    }

    @Override
    public List<LookupDto> getLookupByParentTitle(String title) {
        var entity = lookupRepository.findByParentTitleContains(title);

        if (!entity.iterator().hasNext()) {
            throw new LookupExeption("Lookup not found");
        }

        var resultList = new ArrayList<LookupDto>();
        for (var item : entity) {
            var dto = new LookupDto();
            BeanUtils.copyProperties(item, dto);
        }
        return resultList;
    }

    @Override
    public List<LookupDto> search(String title) {

        var entity = lookupRepository.search(title);

        if (!entity.iterator().hasNext()) {
            throw new LookupExeption("Lookup not found");
        }


        var resultList = new ArrayList<LookupDto>();
        for (var item : entity) {
            var dto = new LookupDto();
            BeanUtils.copyProperties(item, dto);
        }
        return resultList;
    }

    @Override
    public LookupDto update(LookupDto dto) {
        var entity = lookupRepository.findById(dto.getId())
                .orElseThrow(() -> new LookupExeption("Lookup not found"));
        BeanUtils.copyProperties(dto, entity);
        lookupRepository.save(entity);
        return dto;
    }

    @Override
    public LookupDto add(LookupDto dto) {
        var entity = new Lookup();
        BeanUtils.copyProperties(dto, entity);
        lookupRepository.save(entity);
        return dto;
    }

    @Override
    public int delete(long id) {
        lookupRepository.deleteById(id);
        return 1;
    }

    @Override
    public long countAll() {
        return lookupRepository.count();
    }

    @Override
    public long countByGroupId(long groupId) {
        return lookupRepository.countByGroupId(groupId);
    }

    @Override
    public long countByParentId(long parentId) {
        return lookupRepository.countByParentId(parentId);
    }

}
