package practice.others.thread.virtual;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class UserFilter implements Filter {

  public static final ThreadLocal<String> USER_TL = new  ThreadLocal<>();
  public static final ScopedValue<String> USER_SV = ScopedValue.newInstance();

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    String user = ((HttpServletRequest) request).getHeader("user");
    USER_TL.set(user);

    ScopedValue.where(USER_SV, user).run(() -> {
      try {
        chain.doFilter(request, response);
      } catch (IOException | ServletException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
