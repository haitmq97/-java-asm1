package me.haitmq.spring.mvc.crud.entity.status;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DonationStatus {
	NEW,
	DONATING,
	END,
	CLOSED;
	
	public final static Map<DonationStatus, DonationStatus> getSTATUS_CHANGE_MAP() {
		
		Map<DonationStatus, DonationStatus> theMap = Stream.of(new Object[][] {
            { NEW, DONATING },
            { DONATING, END },
            { END, CLOSED }
        }).collect(Collectors.toMap(data -> (DonationStatus) data[0], data -> (DonationStatus) data[1]));
		
		
		return theMap;
	}
	
}
