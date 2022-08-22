package stepdef;

import browser.BrowserInstance;
import com.microsoft.playwright.Page;

public abstract class BaseStep {

    private final ThreadLocal<BrowserInstance> browserInstance = ThreadLocal.withInitial(BrowserInstance::getInstance);

    public void startUp() {
        browserInstance.get().initiate();
    }

    public Page getPage() {
        return browserInstance.get().getPage();
    }

    public BrowserInstance getBrowserInstance() {
        return browserInstance.get();
    }
}
