package stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.row.ProductRow;
import page.ebay.CategoryPage;
import page.ebay.MainPage;
import util.ModelHandler;

public class EbayStepDef extends BaseStep {

    private final StepDefUI stepDefUI = new StepDefUI();
    private final MainPage mainPage = new MainPage();
    private final CategoryPage categoryPage = new CategoryPage();

    @Given("User open EBay page")
    public void openEbayPage() {
        stepDefUI.openPage("https://www.ebay.com/");
    }

    @When("User navigates to popular category {string}")
    public void navigateToPopularSection(String category) {
        getPage().click(mainPage.getPopularDestinationPath(category));
    }

    @Then("Print section items")
    public void printSectionItems() {
        ModelHandler.getModelList(getPage(), categoryPage.getRowPath(), categoryPage.getRowItemPath(), ProductRow.class)
                .forEach(System.out::println);
    }
}
