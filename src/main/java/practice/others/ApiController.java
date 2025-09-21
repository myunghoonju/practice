package practice.others;

import lombok.RequiredArgsConstructor;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.archive.ArchiveFile;
import practice.others.archive.ReceiveData;
import practice.others.archive.ReceiveDatas;
import practice.others.redis.RedisService;
import practice.others.secret.okhttp.MybootApiService;
import practice.others.secret.okhttp.RetrofitService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// https://business.juso.go.kr/addrlink/download.do?reqType=DC&regYmd=2025&stdde=20250301&fileName=20250301_dailynoticedata.zip&realFileName=20250301_dailynoticedata.zip

@RestController
@RequiredArgsConstructor
public class ApiController {

  static final List<String> NAMES = new ArrayList<>();

  private final RedisService redisService;

//  @Scheduled(fixedDelay = 10_000L)
  public void scheduledBizLogic() {
    redisService.list("1").forEach(it -> System.out.printf("  " + it + "  "));
  }

  @GetMapping("/")
  public String health() {
    return "ok";
  }

  @GetMapping("/down")
  public String down(){
    Call<ResponseBody> execute = RetrofitService.getCli("https://update.juso.go.kr")
            .create(MybootApiService.class)
            .download();

    try(ResponseBody responseBody = execute.execute().body()) {
      InputStream inputStream = responseBody.byteStream();
      ZipInputStream zipStream = new ZipInputStream(inputStream);

      List<String> fileNames = new ArrayList<>();
      ZipEntry entry;
      while ((entry = zipStream.getNextEntry()) != null) {
        fileNames.add(entry.getName());
        zipStream.closeEntry();
      }
      zipStream.close();
      return fileNames.get(0);
    } catch (Exception e) {
      return e.getMessage();
    }
  }

    @GetMapping("/test")
    public String test() throws IOException {
      Call<ResponseBody> push = RetrofitService.getCli("http://update.juso.go.kr")
              .create(MybootApiService.class)
              .push("U01TX0FVVEgyMDI1MDIyMDIzNDAwNDExNTQ5MDU=",
                      "D",
                      "100001",
                      "N",
                      "20250222",
                      "20250222");
      Response<ResponseBody> execute = push.execute();
      Headers headers = execute.headers();
      int total_cnt = Integer.parseInt(headers.get("total_count"));
      int normal_cnt = Integer.parseInt(headers.get("normal_count"));
      int retry_cnt = Integer.parseInt(headers.get("retry_count"));
      String err_code = headers.get("err_code");
      ReceiveDatas receiveData = new ReceiveDatas();

      receiveData.setResTotalCnt(total_cnt);
      receiveData.setResNormalCnt(normal_cnt);
      receiveData.setResRetryCnt(retry_cnt);
      receiveData.setResCode(err_code);

      try (ResponseBody responseBody = execute.body()) {
        if (responseBody != null) {
          makeReceiveData(receiveData, total_cnt, true, responseBody.byteStream());
        }

        if (receiveData.getReceiveDatas() != null) {
          if (receiveData.getResult() == -1) {
            System.out.println("error");
          }

          List<ReceiveData> receiveDatas1 = receiveData.getReceiveDatas();
          receiveDatas1.forEach(d -> {
            try {
              ArchiveFile.unzip(NAMES, d.getFileDate(), d.getFileName());
              NAMES.forEach(name -> ArchiveFile.read(d, name));
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          });
        }

        return "";
      } catch (Exception e) {
        return e.getMessage();
      }
      }

  private void makeReceiveData(ReceiveDatas receiveData, int total_cnt, boolean sizeZeroMake,  InputStream is) throws IOException{
    FileOutputStream fos = null;
    for(int i = 0; i<total_cnt; i++){
      byte[] meta_data = new byte[100];				    // 각 파일별 수신 정보 데이터
      read(meta_data ,is);
      String tmp_header = new String(meta_data);
      ReceiveData receive = new ReceiveData();
      //-------------------- response 데이터 수신 정보(100바이트)------------------------
      int file_seq = Integer.parseInt(tmp_header.substring(0, 2)); // 수신받은 파일 순번
      String file_date = tmp_header.substring(2, 10); // 수신받은 파일의 기준일
      String file_name= tmp_header.substring(10, 60).trim();	// 수신받은 파일명
      String tmp_data_size = tmp_header.substring(60, 70);	// 파일 사이즈(byte)
      int data_size = Integer.parseInt(tmp_data_size);
      String err_cd = tmp_header.substring(70, 75);		// 각 파일당 응답코드
      String cntc_code = tmp_header.substring(75, 81);	// 제공받는 CNTC_CD 정보
      String retry_yn = tmp_header.substring(81, 82);		// 재반영데이터 여부
      String make_date = tmp_header.substring(82, 90);	// 파일 생성일자
      receive.setFileDate(file_date);
      receive.setFileName(file_name);
      receive.setFileSize(data_size);
      receive.setCntcCode(cntc_code);
      receive.setCreateDate(make_date);
      receive.setResCode(err_cd);
      receive.setRetryYn(retry_yn);
      receive.setFilePath("");

      receiveData.add(receive);

      //----------------------------------------------------------------------------
      byte[] body;
      if(data_size > 1024){
        body = new byte[1024];	 	  // 수신시 받을 데이터 단위 설정(기본 1024byte)
      }else{
        body = new byte[data_size];	   // 수신시 받을 데이터 단위 설정(이하 데이타 길이만큼)
      }

      if(file_name.equals("")) continue; // 파일명이 없는 경우, Skip

      File file = null;
      if(sizeZeroMake){
        file = new File(file_name);
        fos = new FileOutputStream(file);
        receive.setFilePath(file.getAbsolutePath());
        if(data_size == 0) continue;	   // 데이터 사이즈가 0일 경우, 빈 파일만 생성 후 Skip
      }else if(!sizeZeroMake && data_size == 0){
        continue;	   // 데이터 사이즈가 0일 경우, 빈 파일만 생성 후 Skip
      }else{
        // 해당 파일의 디렉토리를 생성한다.
        file = new File(file_name);
        fos = new FileOutputStream(file);
        receive.setFilePath(file.getAbsolutePath());
      }

      int tmp_size = 0;

      while((tmp_size = read(body, is)) > 0){
        data_size -= tmp_size;
        fos.write(body);				// File 저장
        fos.flush();
        if(data_size - tmp_size < 0){
          body = new byte[data_size];
        }
      }
    }
  }

  private int read(byte[] data, InputStream is) throws IOException {
    return read(data, 0, data.length, is);
  }

  private int read(byte[] data, int offset, int length, InputStream is) throws IOException {
    int received = 0;
    int maxcount = 0;
    int recv = 0;
    int _timeout = 1000;
    while((maxcount += 10) <= _timeout){
      recv = is.read(data, offset+received, length-received);
      if(recv > 0){
        received += recv;
      }
      if(received >= length) break;
      try{ Thread.sleep(10); } catch (Exception ignored){}
    }
    if(received < length) return -1;
    return received;
  }

}
