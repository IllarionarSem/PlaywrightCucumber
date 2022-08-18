package stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import model.row.TableObjectRow;
import page.herokuapp.ChallengingDomPage;
import util.ModelHandler;

@Slf4j
public class ChallengingDomStep extends BaseStep {

    private final ChallengingDomPage challengingDomPage = new ChallengingDomPage();
    private final StepDefUI stepDefUI = new StepDefUI();

    @Given("User open challenging dom page")
    public void openChallengingDomPage() {
        stepDefUI.openPage("http://the-internet.herokuapp.com/challenging_dom");
    }

    @Then("Print table objects")
    public void printTableObjects() {
        ModelHandler.getModelList(getPage(), challengingDomPage.getRowPath(), challengingDomPage.getItemPath(), TableObjectRow.class)
                .forEach(System.out::println);
    }
}
