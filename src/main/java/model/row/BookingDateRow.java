package model.row;

import model.BookingDate;

public class BookingDateRow extends BaseRow<BookingDate> {

    @ModelInfo(name = "Check In")
    private String checkIn;
    @ModelInfo(name = "Check Out")
    private String checkOut;

    @Override
    public BookingDate asModel() {
        return new BookingDate(checkIn, checkOut);
    }
}
