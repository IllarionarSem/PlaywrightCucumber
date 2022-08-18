package util;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpCredentials;
import com.microsoft.playwright.options.Proxy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUtilBuilder {

    private Playwright playwright;
    private final Map<String, String> headers = new HashMap<>();
    private APIRequest.NewContextOptions contextOptions;

    public ApiUtilBuilder(Playwright playwright) {
        this.playwright = playwright;
        this.contextOptions = new APIRequest.NewContextOptions();
    }

    public ApiUtilBuilder setBaseUrl(String baseURL) {
        contextOptions.setBaseURL(baseURL);
        return this;
    }

    public ApiUtilBuilder addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public ApiUtilBuilder putHeaderValue(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public ApiUtilBuilder setHttpCredentials(String username, String password) {
        contextOptions.setHttpCredentials(username, password);
        return this;
    }

    public ApiUtilBuilder setHttpCredentials(HttpCredentials httpCredentials) {
        contextOptions.setHttpCredentials(httpCredentials);
        return this;
    }

    public ApiUtilBuilder setProxy(Proxy proxy) {
        contextOptions.setProxy(proxy);
        return this;
    }

    public ApiUtilBuilder ignoreHttpErrors(boolean ignoreHttpErrors) {
        contextOptions.setIgnoreHTTPSErrors(ignoreHttpErrors);
        return this;
    }

    public ApiUtilBuilder setStorageState(String storageState) {
        contextOptions.setStorageState(storageState);
        return this;
    }

    public ApiUtilBuilder setStorageStatePath(Path storageStatePath) {
        contextOptions.setStorageStatePath(storageStatePath);
        return this;
    }

    public ApiUtilBuilder setTimeout(double timeout) {
        contextOptions.setTimeout(timeout);
        return this;
    }

    public ApiUtilBuilder setUserAgent(String userAgent) {
        contextOptions.setUserAgent(userAgent);
        return this;
    }

    public ApiUtil build() {
        contextOptions.setExtraHTTPHeaders(headers);
        return new ApiUtil(this);
    }
}
