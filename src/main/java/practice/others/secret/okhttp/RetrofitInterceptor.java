package practice.others.secret.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class RetrofitInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request req = chain.request()
                           .newBuilder()
                           .build();

        if(req.body() != null) {
            Buffer buffer = new Buffer();
            req.body().writeTo(buffer);
            log.debug(req.url() + " request : " + buffer.readUtf8());
        }

        return chain.proceed(req);
    }
}
