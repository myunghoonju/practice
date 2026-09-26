package practice.others.thread.virtual.api;

import java.util.List;

import org.springframework.stereotype.Service;

import practice.others.thread.virtual.UserFilter;

@Service
public class FakeClient {

  public Talk talk(String speaker) {
    System.out.println("FakeClient talk tl: " + UserFilter.USER_TL.get()); //null
    System.out.println("FakeClient talk sv: " + UserFilter.USER_SV.get()); // value sent from request header

    Util.threadSleep(1_000);
    System.out.println(Thread.currentThread());
    return new Talk("talk for speaker " + speaker);
  }

  public Information infoGoogle(String speaker) {
    Util.threadSleep(500);
    return new Information(List.of(speaker, "27.06.1989"));
  }

  public Information infoLinkedIn(String speaker) {
    Util.threadSleep(500);
    return new Information(List.of(speaker, "27.06.1989", "Backend"));
  }

  public Information infoFaceBook(String speaker) {
    throw new RuntimeException("invalid account");
  }
}
