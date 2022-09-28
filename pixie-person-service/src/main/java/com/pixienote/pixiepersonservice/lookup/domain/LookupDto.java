package com.pixienote.pixiepersonservice.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LookupDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 364038382421521185L;

    private long id;
    private String name;
    private String value;
    private String title;
    private int sortOrder;
    private long parentId;
    private String parentTitle;
    private long groupId;
    private String groupTitle;

}
