package model.row;

import model.BookingData;

import java.util.Objects;

public class BookingDataRow extends BaseRow<BookingData> {

    @ModelInfo(name = "First Name")
    private String fistName;
    @ModelInfo(name = "Last Name")
    private String lastName;
    @ModelInfo(name = "Total Price")
    private String totalPrice;
    @ModelInfo(name = "Deposit Paid")
    private String depositPaid;
    @ModelInfo(name = "Additional Needs")
    private String additionalNeeds;

    @Override
    public BookingData asModel() {
        return new BookingData(fistName,
                lastName,
                Objects.nonNull(totalPrice) ? Integer.parseInt(totalPrice) : null,
                Boolean.parseBoolean(depositPaid),
                null,
                additionalNeeds);
    }
}
