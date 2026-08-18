package practice.others.sse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseSectionController {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    @GetMapping(value = "/sse/sections", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sections() {
        SseEmitter emitter = new SseEmitter(10_000L);

        // TODO 1: FAST(100ms), MEDIUM(500ms), SLOW(2000ms) 3개의 CompletableFuture를 만드세요.
        //   - CompletableFuture.supplyAsync(() -> { Thread.sleep(지연시간); return "이름 + 결과"; }, executor)
        //   - supplyAsync의 람다는 체크 예외(InterruptedException)를 못 던지므로,
        //     내부에서 try-catch 하거나 Thread.sleep을 감싸는 헬퍼를 만드세요.

        // TODO 2: 각 future에 .whenComplete((result, error) -> {...})를 등록해서
        //   완료되는 즉시 emitter.send(SseEmitter.event().name("SECTION").data(result)) 호출
        //   (error가 null이 아니면 이 섹션만 실패 처리 — 지금은 로그만 남기거나 무시해도 OK, 4단계에서 다룸)

        // TODO 3: CompletableFuture.allOf(f1, f2, f3).whenComplete((v, error) -> {...})로
        //   셋 다 끝난 시점을 감지해서 emitter.send(이벤트 이름 "DONE") 보내고 emitter.complete() 호출

        return emitter;
    }
}
