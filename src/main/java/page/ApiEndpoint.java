package page;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiEndpoint {

    public static final String EP_BOOKING = "/booking";
    public static final String EP_BOOKING_BY_ID = "/booking/%s";
    public static final String EP_AUTH = "/auth";
}
