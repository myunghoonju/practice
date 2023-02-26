package practice.others.secret.okhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

import java.util.Map;

public interface UserApiService {

    @GET("users/{userId}/posts")
    Call<Object> getUserPost(@HeaderMap Map<String, String> header, @Path("userId") int userId);
}
