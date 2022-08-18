package stepdef;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepDefUI extends BaseStep {

    @Before
    public void setUp() {
        log.info("Start Browser");
        super.startUp();
    }

    @Given("User navigates to {string}")
    public void openPage(String url) {
        log.info(String.format("User navigates to %s", url));
        getPage().navigate(url);
    }
}
