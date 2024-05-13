package com.cgi.example.petstore.logging.mdc;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import org.springframework.stereotype.Component;

@WebFilter("/api/v1/*")
@Component
public class ClearMappedDiagnosticContextWhenDone implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    try {
      filterChain.doFilter(request, response);
    } finally {
      MappedDiagnosticContextKey.clearAll();
    }
  }
}