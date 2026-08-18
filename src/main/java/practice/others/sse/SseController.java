package practice.others.sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SseController {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    @GetMapping(value = "/sse/hello", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter hello() {
        // TODO 1: SseEmitter 인스턴스 생성
        SseEmitter emitter = new SseEmitter(5_000L);

        // TODO 2: executor.execute(...) 로 별도 스레드에서
        //   - 1초 간격으로 "MESSAGE" 이벤트 3개 전송 (data: "event 1", "event 2", "event 3")
        //   - Thread.sleep(1000) 사용, InterruptedException 처리 필요
        //   - send()는 IOException을 던지므로 try/catch로 감싸고,
        //     실패 시 emitter.completeWithError(e) 호출
        //   - 3개 다 보냈으면 emitter.complete() 호출
        executor.execute(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    emitter.send(SseEmitter.event().name("MESSAGE").data("event " + i));
                    Thread.sleep(1000);
                } catch (IOException e) {
                    emitter.completeWithError(e);
                    return;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    emitter.completeWithError(e);
                    return;
                }
            }
            // TODO 3: emitter 리턴
            emitter.complete();
        });

        return emitter;
    }
}
