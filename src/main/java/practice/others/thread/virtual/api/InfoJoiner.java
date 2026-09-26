package practice.others.thread.virtual.api;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class InfoJoiner implements Joiner<Information, Information> {

  private final Collection<Information> results = new ConcurrentLinkedQueue<>();
  private final Collection<Throwable> errors = new ConcurrentLinkedQueue<>();

  @Override
  public Information result() throws Throwable {
    if (results.isEmpty()) {
      RuntimeException noInfoResult = new RuntimeException("No info result");
      errors.forEach(noInfoResult::addSuppressed);
      throw noInfoResult;
    }

    return results.stream()
                  .max(Comparator.comparing(info -> info.infolist().size()))
                  .orElseThrow();
  }

  @Override
  public boolean onComplete(Subtask<? extends Information> subtask) {
    switch (subtask.state()) {
      case SUCCESS -> results.add(subtask.get());
      case FAILED -> errors.add(subtask.exception());
      default -> throw new IllegalStateException();
    }

    return false;
  }
}
