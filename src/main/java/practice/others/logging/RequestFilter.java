package practice.others.logging;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class RequestFilter implements Filter {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        try {

            MDC.put("requestId", String.valueOf(atomicInteger.incrementAndGet()));

            chain.doFilter(req, res);
        } finally {
            //MDC.clear();
        }
    }
}
