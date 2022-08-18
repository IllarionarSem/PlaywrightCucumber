package browser;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BrowserInstance {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    private static BrowserInstance INSTANCE;

    public static BrowserInstance getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new BrowserInstance();
        }
        return INSTANCE;
    }

    public void initiate() {
        log.info("Start up the Browser");
        if (Objects.isNull(playwright)) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(getLaunchOptions());
            context = browser.newContext(getContextOptions());
            page = context.newPage();
        }
    }

    private BrowserType.LaunchOptions getLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1000);
    }

    private Browser.NewContextOptions getContextOptions() {
        return new Browser.NewContextOptions()
                .setColorScheme(ColorScheme.DARK);
    }

    public void close() {
        if (Objects.nonNull(playwright)) {
            playwright.close();
        }
    }
}
