package stepdef;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.BookingData;
import model.BookingDate;
import model.BookingInfo;
import model.Token;
import model.row.BookingDataRow;
import model.row.BookingDateRow;
import org.junit.Assert;
import page.ApiEndpoint;
import util.DataStorage;
import util.JsonUtil;
import util.ModelHandler;

import java.util.HashMap;
import java.util.Map;

public class BookApiStep extends BaseStep {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "password123";

    private APIRequest request;
    private APIRequestContext apiContext;
    private final Map<String, String> defaultHeaders = Map.of("Content-Type", "application/json", "Accept", "application/json");

    @Given("Set token")
    public void receiveAndSaveToken() {
        APIResponse response = apiContext.post(ApiEndpoint.EP_AUTH, RequestOptions.create()
                .setData(Map.of("username", USER_NAME, "password", PASSWORD)));
        Assert.assertTrue(response.ok());
        Map<String, String> headers = new HashMap<>(defaultHeaders);
        headers.put("Cookie", String.format("token=%s", JsonUtil.getObject(response.text(), Token.class).getToken()));
        apiContext = request
                .newContext(new APIRequest.NewContextOptions()
                        .setExtraHTTPHeaders(headers)
                        .setBaseURL(BASE_URL));
    }

    @Given("User have Booking and it stored by key {string}")
    public void storeBooking(String key, DataTable dataTable) {
        BookingData bookingData = ModelHandler.getModelList(dataTable, BookingDataRow.class).get(0);
        DataStorage.put(key, bookingData);
    }

    @And("Booking check in and check out dates, stored by key {string}")
    public void addBookingDates(String key, DataTable dataTable) {
        BookingDate bookingDate = ModelHandler.getModelList(dataTable, BookingDateRow.class).get(0);
        ((BookingData) DataStorage.get(key)).setBookingdates(bookingDate);
    }

    @When("Post booking stored by key {string} and store booking info by key {string}")
    public void postBooking(String bookingKey, String bookingInfoKey) {
        BookingData bookingData = (BookingData) DataStorage.get(bookingKey);
        APIResponse response = apiContext.post(
                ApiEndpoint.EP_BOOKING,
                RequestOptions.create()
                        .setData(JsonUtil.toJson(bookingData)));
        Assert.assertTrue(response.ok());
        BookingInfo bookingInfo = JsonUtil.getObject(response.text(), BookingInfo.class);
        Assert.assertEquals(bookingData, bookingInfo.getBooking());
        DataStorage.put(bookingInfoKey, bookingInfo);
    }

    @Then("Update Booking with id stored by key {string} first and last name")
    public void updateFirstLastName(String key, DataTable dataTable) {
        BookingData bookingData = ModelHandler.getModelList(dataTable, BookingDataRow.class).get(0);
        BookingInfo bookingInfo = (BookingInfo) DataStorage.get(key);
        bookingInfo.getBooking().setFirstname(bookingData.getFirstname());
        bookingInfo.getBooking().setLastname(bookingData.getLastname());
        APIResponse response = apiContext.patch(String.format(ApiEndpoint.EP_BOOKING_BY_ID, bookingInfo.getBookingid()), RequestOptions.create()
                .setData(JsonUtil.toJson(bookingInfo.getBooking())));
        BookingData updatedBookingData = JsonUtil.getObject(response.text(), BookingData.class);
        Assert.assertTrue(response.ok());
        Assert.assertEquals(bookingInfo.getBooking(), updatedBookingData);
    }

    @And("Delete Booking with id stored by key {string}")
    public void deleteBooking(String key) {
        BookingInfo bookingInfo = (BookingInfo) DataStorage.get(key);
        APIResponse response = apiContext.delete(String.format(ApiEndpoint.EP_BOOKING_BY_ID, bookingInfo.getBookingid()));
        Assert.assertTrue(response.ok());
        Assert.assertEquals(response.text(), "Created");
        response = apiContext.get(String.format(ApiEndpoint.EP_BOOKING_BY_ID, bookingInfo.getBookingid()));
        Assert.assertEquals(response.status(), 404);
        Assert.assertEquals(response.text(), "Not Found");
    }

    @Before
    public void setUp() {
        super.startUp();
        request = getBrowserInstance().getPlaywright().request();
        apiContext = request
                .newContext(new APIRequest.NewContextOptions()
                        .setExtraHTTPHeaders(defaultHeaders)
                        .setBaseURL(BASE_URL));
    }
}
