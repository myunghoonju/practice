package practice.others.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class SseSectionController {

    // 한 섹션이 이 시간 안에 못 끝나면 TimeoutException으로 실패 처리 — emitter 자체의 10초 타임아웃보다 짧게 잡아서
    // "섹션 하나가 멈춤" 상황과 "emitter 통째로 끊김" 상황을 구분한다.
    private static final long SECTION_TIMEOUT_MS = 3_000L;

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    @GetMapping(value = "/sse/sections", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sections() {
        SseEmitter emitter = new SseEmitter(10_000L);

        // TODO 1: FAST(100ms), MEDIUM(500ms), SLOW(2000ms) 3개의 CompletableFuture를 만드세요.
        //   - CompletableFuture.supplyAsync(() -> { Thread.sleep(지연시간); return "이름 + 결과"; }, executor)
        //   - supplyAsync의 람다는 체크 예외(InterruptedException)를 못 던지므로,
        //     내부에서 try-catch 하거나 Thread.sleep을 감싸는 헬퍼를 만드세요.
        CompletableFuture<String> fast = CompletableFuture.supplyAsync(() -> aFuture(100L, "FAST"), executor)
                .orTimeout(SECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        CompletableFuture<String> medium = CompletableFuture.supplyAsync(() -> aFuture(500L, "MEDIUM"), executor)
                .orTimeout(SECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        CompletableFuture<String> slow = CompletableFuture.supplyAsync(() -> aFuture(2000L, "SLOW"), executor)
                .orTimeout(SECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);

        // TODO 2: 각 future에 .whenComplete((result, error) -> {...})를 등록해서
        //   완료되는 즉시 emitter.send(SseEmitter.event().name("SECTION").data(result)) 호출
        //   (error가 null이 아니면 이 섹션만 실패 처리)
        CompletableFuture<Void> fastSent = sendWhenReady(emitter, fast);
        CompletableFuture<Void> mediumSent = sendWhenReady(emitter, medium);
        CompletableFuture<Void> slowSent = sendWhenReady(emitter, slow);

        // TODO 3: CompletableFuture.allOf(f1, f2, f3).whenComplete((v, error) -> {...})로
        //   셋 다 끝난 시점을 감지해서 emitter.send(이벤트 이름 "DONE") 보내고 emitter.complete() 호출
        //   주의: allOf는 "SECTION 전송까지 끝난" future(fastSent/mediumSent/slowSent)를 기다려야 함 —
        //   원본 fast/medium/slow를 기다리면, DONE 콜백과 SECTION 전송 콜백이 같은 future에 나란히 걸려서
        //   실행 순서가 보장되지 않고(DONE이 먼저 실행되면 emitter.complete() 후 SECTION 전송이 조용히 실패함)
        CompletableFuture.allOf(fastSent, mediumSent, slowSent).whenComplete((_, _) -> {
            try {
                emitter.send(SseEmitter.event().name("DONE").data("completed"));
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }

    private CompletableFuture<Void> sendWhenReady(SseEmitter emitter, CompletableFuture<String> future) {
        return future.handle((result, error) -> {
            if (error != null) {
                log.info("섹션 실패: {}", error.getMessage());
                return null;
            }

            try {
                emitter.send(SseEmitter.event().name("SECTION").data(result));
            } catch (IOException e) {
                log.info("emitter send 실패: {}", e.getMessage());
            }
            return null;
        });
    }

    private String aFuture(long time, String name) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        return name + " completed";
    }
}
