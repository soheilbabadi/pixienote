package com.pixienote.pixiepersonservice.person.domain;

public enum RoleEnum {
    ADMIN(1),
    USER(2),
    ANONYMOUS(3),
    VET(4),
    SHOP(5),
    CUSTOMER(6);


    private final int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }


}
