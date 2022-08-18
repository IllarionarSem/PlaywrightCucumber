package stepdef;

import browser.BrowserInstance;
import com.microsoft.playwright.Page;
import lombok.Getter;

public abstract class BaseStep {

    private static final ThreadLocal<BrowserInstance> browserInstance = ThreadLocal.withInitial(BrowserInstance::getInstance);

    public void startUp() {
        browserInstance.get().initiate();
    }

    public static Page getPage() {
        return browserInstance.get().getPage();
    }

    public static BrowserInstance getBrowserInstance() {
        return browserInstance.get();
    }
}
