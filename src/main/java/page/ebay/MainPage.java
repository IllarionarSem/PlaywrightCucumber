package page.ebay;

public class MainPage {

    private final String popularDestinationPath = "//ul[contains(@class,'popular-destinations')]//h3[contains(@class,'name') and contains(string(),'%s')]";

    public String getPopularDestinationPath(String category) {
        return String.format(popularDestinationPath, category);
    }
}
