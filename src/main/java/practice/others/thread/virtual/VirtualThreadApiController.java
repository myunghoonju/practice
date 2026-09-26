package practice.others.thread.virtual;

import static java.util.concurrent.StructuredTaskScope.open;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

import org.jspecify.annotations.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import practice.others.thread.virtual.api.FakeClient;
import practice.others.thread.virtual.api.InfoJoiner;
import practice.others.thread.virtual.api.Information;
import practice.others.thread.virtual.api.Speaker;
import practice.others.thread.virtual.api.Talk;

@RestController
public class VirtualThreadApiController {

  private final FakeClient fakeClient;

  public VirtualThreadApiController(FakeClient fakeClient) {
    this.fakeClient = fakeClient;
  }

  @GetMapping("/api/talk/{speaker}")
  public Talk talk(@PathVariable String speaker) {
    return fakeClient.talk(speaker);
  }

  @GetMapping("/api/speaker/{speaker}")
  public Speaker speaker(@PathVariable String speaker) throws InterruptedException {
    try (StructuredTaskScope<Object, Void> scope = open()) {
      System.out.println("main controller tl: " + UserFilter.USER_TL.get());
      System.out.println("main controller sv: " + UserFilter.USER_SV.get());
      Subtask<Talk> talk = scope.fork(() -> fakeClient.talk(speaker));
      Subtask<Information> info = scope.fork(() -> getBestInfo(speaker));

      scope.join();

      return new Speaker(talk.get(), info.get());
    }
  }

  private @NonNull Information getBestInfo(String speaker) throws InterruptedException {
    try(StructuredTaskScope<Information, Information> scope = open(new InfoJoiner())) {
      scope.fork(() -> fakeClient.infoGoogle(speaker));
      scope.fork(() -> fakeClient.infoFaceBook(speaker));
      scope.fork(() -> fakeClient.infoLinkedIn(speaker));

      return scope.join();
    }
  }
}
