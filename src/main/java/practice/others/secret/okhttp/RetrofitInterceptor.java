package practice.others.secret.okhttp;

import com.tc.util.io.IOUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
        Response proceed = chain.proceed(req);
        InputStream inputStream = proceed.body().byteStream();
        String collect = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("|"));
        System.err.println("collect = " + collect);
        return proceed;
    }
}
