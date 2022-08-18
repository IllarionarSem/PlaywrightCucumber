package stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import model.Book;
import model.row.BookRow;
import org.junit.jupiter.api.BeforeAll;
import util.ModelHandler;

import java.util.List;

@Slf4j
public class StepDefUI extends BaseStep {

    @BeforeAll
    public void beforeAll() {
        log.info("Set up");
        log.info("Thread ID: " + Thread.currentThread().getId());
        super.startUp();
    }

    @Given("get book as list")
    public void getBookTableAsList(DataTable dataTable) {
        List<Book> books = ModelHandler.getModelList(dataTable, BookRow.class);
        books.forEach(System.out::println);
    }

    @Given("User navigates to {string}")
    public void openPage(String url) {
        log.info(String.format("User navigates to %s", url));
        getPage().navigate(url);
    }
}
