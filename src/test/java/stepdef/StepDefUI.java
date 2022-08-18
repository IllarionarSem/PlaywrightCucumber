package stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import model.Book;
import model.row.BookRow;
import util.DataStorage;
import util.ModelHandler;

import java.util.List;

@Slf4j
public class StepDefUI extends BaseStep {

    @Before
    public void setUp() {
        log.info("Set up");
        super.startUp();
    }

    @After
    public void terminate() {
        log.info("Close browser");
        getBrowserInstance().close();
        DataStorage.unload();
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
