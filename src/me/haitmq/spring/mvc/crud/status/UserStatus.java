package me.haitmq.spring.mvc.crud.status;

public enum UserStatus {
    Active(1),
    Lock(0);

    private final int value;

    private UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
