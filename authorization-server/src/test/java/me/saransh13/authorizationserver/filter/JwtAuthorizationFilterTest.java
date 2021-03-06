package me.saransh13.authorizationserver.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.saransh13.authorizationserver.util.JWTTokenProvider;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@ContextConfiguration(classes = {JwtAuthorizationFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JwtAuthorizationFilterTest {
    @MockBean
    private JWTTokenProvider jWTTokenProvider;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(mockHttpServletRequest, response, filterChain);
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal2() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.servlet.http.HttpServletRequest.getMethod()" because "request" is null
        //       at me.saransh13.authorizationserver.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:36)
        //   In order to prevent doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain).
        //   See https://diff.blue/R013 to resolve this issue.

        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(null, response, filterChain);
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal3() throws IOException, ServletException {
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal4() throws IOException, ServletException {
        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jWTTokenProvider).isTokenValid((String) any(), (String) any());
        verify(jWTTokenProvider).getSubject((String) any());
        verify(jWTTokenProvider).getAuthentication((String) any(), (HttpServletRequest) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal5() throws IOException, ServletException {
        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(false);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jWTTokenProvider).isTokenValid((String) any(), (String) any());
        verify(jWTTokenProvider).getSubject((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal6() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.apache.coyote.Response.isCommitted()" because the return value of "org.apache.catalina.connector.Response.getCoyoteResponse()" is null
        //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
        //       at org.apache.catalina.connector.Response.setStatus(Response.java:1507)
        //       at org.apache.catalina.connector.Response.setStatus(Response.java:1489)
        //       at me.saransh13.authorizationserver.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:37)
        //   In order to prevent doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain).
        //   See https://diff.blue/R013 to resolve this issue.

        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("OPTIONS");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal7() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because the return value of "javax.servlet.http.HttpServletRequest.getMethod()" is null
        //       at me.saransh13.authorizationserver.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:36)
        //   In order to prevent doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain).
        //   See https://diff.blue/R013 to resolve this issue.

        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn(null);
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal8() throws IOException, ServletException {
        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("OPTIONS");
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, mockHttpServletResponse, filterChain);
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        assertEquals(200, mockHttpServletResponse.getStatus());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal9() throws IOException, ServletException {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(mockHttpServletRequest, response, filterChain);
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
        assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
        assertFalse(mockHttpServletRequest.isAsyncSupported());
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal10() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.servlet.http.HttpServletRequest.getMethod()" because "request" is null
        //       at me.saransh13.authorizationserver.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:36)
        //   In order to prevent doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain).
        //   See https://diff.blue/R013 to resolve this issue.

        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(null, response, filterChain);
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal11() throws IOException, ServletException {
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal12() throws IOException, ServletException {
        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jWTTokenProvider).isTokenValid((String) any(), (String) any());
        verify(jWTTokenProvider).getSubject((String) any());
        verify(jWTTokenProvider).getAuthentication((String) any(), (HttpServletRequest) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal13() throws IOException, ServletException {
        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(false);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
        verify(jWTTokenProvider).isTokenValid((String) any(), (String) any());
        verify(jWTTokenProvider).getSubject((String) any());
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal14() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.apache.coyote.Response.isCommitted()" because the return value of "org.apache.catalina.connector.Response.getCoyoteResponse()" is null
        //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
        //       at org.apache.catalina.connector.Response.setStatus(Response.java:1507)
        //       at org.apache.catalina.connector.Response.setStatus(Response.java:1489)
        //       at me.saransh13.authorizationserver.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:37)
        //   In order to prevent doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain).
        //   See https://diff.blue/R013 to resolve this issue.

        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("OPTIONS");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDoFilterInternal15() throws IOException, ServletException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because the return value of "javax.servlet.http.HttpServletRequest.getMethod()" is null
        //       at me.saransh13.authorizationserver.filter.JwtAuthorizationFilter.doFilterInternal(JwtAuthorizationFilter.java:36)
        //   In order to prevent doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain).
        //   See https://diff.blue/R013 to resolve this issue.

        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn(null);
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, response, filterChain);
    }

    /**
     * Method under test: {@link JwtAuthorizationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal16() throws IOException, ServletException {
        when(jWTTokenProvider.getAuthentication((String) any(), (HttpServletRequest) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        when(jWTTokenProvider.isTokenValid((String) any(), (String) any())).thenReturn(true);
        when(jWTTokenProvider.getSubject((String) any())).thenReturn("Hello from the Dreaming Spires");
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("OPTIONS");
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        jwtAuthorizationFilter.doFilterInternal(defaultMultipartHttpServletRequest, mockHttpServletResponse, filterChain);
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
        assertEquals(200, mockHttpServletResponse.getStatus());
    }
}

