package practice.others.secret.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import practice.others.secret.TokenGenerator;

/**
 * triggered when request error occurred
 * Auto Retry
 * No Response Code Check (always unauthorized error)
 **/
@Slf4j
public class AgencyAuthenticator implements Authenticator {

    public static final String HEADER_FIELD = "authorization";

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route,
                                @NotNull Response res) {
        int cnt = getCnt(res);
        if (cnt > 2) {
            return null;
        }
        log.warn("auth error token {}", res.request().headers().get("authorization"));

        String token = TokenGenerator.getToken();
        return res.request()
                .newBuilder()
                .header(HEADER_FIELD, token)
                .build();
    }

    private int getCnt(Response res) {
        int result = 1;
        while ((res = res.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
