package util;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUtil {

    private APIRequestContext context;

    public ApiUtil(ApiUtilBuilder builder) {
        this(builder.getPlaywright(), builder.getContextOptions());
    }

    public ApiUtil(Playwright playwright, APIRequest.NewContextOptions contextOptions) {
        this.context = playwright.request().newContext(contextOptions);
    }

    public APIResponse get(String endpoint) {
        return context.get(endpoint);
    }

    public APIResponse get(String endpoint, RequestOptions requestOptions) {
        return context.get(endpoint, requestOptions);
    }

    public APIResponse post(String endpoint, RequestOptions requestOptions) {
        return context.post(endpoint, requestOptions);
    }

    public APIResponse post(String endpoint, String body) {
        return post(endpoint, RequestOptions.create().setData(body));
    }

    public APIResponse put(String endpoint, RequestOptions requestOptions) {
        return context.put(endpoint, requestOptions);
    }

    public APIResponse put(String endpoint, String body) {
        return put(endpoint, RequestOptions.create().setData(body));
    }

    public APIResponse patch(String endpoint, RequestOptions requestOptions) {
        return context.patch(endpoint, requestOptions);
    }

    public APIResponse patch(String endpoint, String body) {
        return patch(endpoint, RequestOptions.create().setData(body));
    }

    public APIResponse delete(String endpoint) {
        return context.delete(endpoint);
    }

    public APIResponse delete(String endpoint, RequestOptions requestOptions) {
        return context.delete(endpoint, requestOptions);
    }

    public APIResponse delete(String endpoint, String body) {
        return context.delete(endpoint, RequestOptions.create().setData(body));
    }

    public void dispose() {
        if (Objects.nonNull(context)) {
            context.dispose();
        }
    }
}
