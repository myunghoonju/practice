package practice.others.secret.okhttp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface MybootApiService {

    @Streaming
    @POST("/updateInfo.do")
    Call<ResponseBody> push(@Query("app_key") String appKey,
                            @Query("date_gb") String dateGb,
                            @Query("cntc_cd") String cntcCd,
                            @Query("retry_in") String retryIn,
                            @Query("req_date") String reqDate,
                            @Query("req_date2") String reqDate2);

    @Streaming
    @GET("/download.do?reqType=DC&regYmd=2025&stdde=20250301&fileName=20250301_dailynoticedata.zip&realFileName=20250301_dailynoticedata.zip")
    Call<ResponseBody> download();

}
