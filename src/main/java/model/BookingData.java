package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class BookingData extends Model {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private boolean depositpaid;
    private BookingDate bookingdates;
    private String additionalneeds;
}
