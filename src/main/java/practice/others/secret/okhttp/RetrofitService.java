package practice.others.secret.okhttp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitService {

    private final OkHttpClient okHttpClient;

    public RetrofitService() {
        this.okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(2L, TimeUnit.SECONDS)
                .addInterceptor(new RetrofitInterceptor())
                .authenticator(new AgencyAuthenticator())
                .build();
    }

    public static Retrofit getCli() {
        return new RetrofitService().createCli();
    }


    private Retrofit createCli() {
        return new Retrofit.Builder()
                .baseUrl("http://httpstat.us/401/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
