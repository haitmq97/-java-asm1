package me.haitmq.spring.mvc.crud.status;

public enum DonationStatus2 {
	New(0),
    Donating(1),
    End(2),
    Close(3)
    ;

    private final int value;

    private DonationStatus2(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
