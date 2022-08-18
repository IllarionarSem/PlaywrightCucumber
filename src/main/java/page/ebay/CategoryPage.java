package page.ebay;

import lombok.Getter;

public class CategoryPage {

    @Getter
    private final String rowPath = "//ul[contains(@class,'results')]//div[contains(@class,'info')]";
    @Getter
    private final String rowItemPath = "//*[contains(@class,'item__%s')]";
}
