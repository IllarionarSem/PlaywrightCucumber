package page.herokuapp;

import lombok.Getter;

public class ChallengingDomPage {

    @Getter
    private final String rowPath = "//table/tbody/tr";
    @Getter
    private final String itemPath = "//td[count(//table/thead//th[text()='%s']//preceding-sibling::th) + 1]";
}
