package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Slf4j
public class BookingDate extends Model {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    private Date checkin;
    private Date checkout;

    public BookingDate(String checkin, String checkout) {
        try {
            this.checkin = DATE_FORMAT.parse(checkin);
            this.checkout = DATE_FORMAT.parse(checkout);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
    }
}
