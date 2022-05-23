package MyFitnessJourney.VTTP.Project.Fitness.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;

@Component
public class AuthenticationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse)response;
        HttpServletRequest httpReq = (HttpServletRequest)request;

        HttpSession sess = httpReq.getSession();
        String username = (String)sess.getAttribute("username");

        // System.out.println(">>>> requested url: " + httpReq.getRequestURL().toString());
        // System.out.println(">>>> httpResp: " + httpResp.toString());
        // System.out.println(">>>> username: " + username);
        UserModel user = (UserModel)sess.getAttribute("user");
        System.out.println(">>>> user: " + user.getUsername());

        if ((username == null) || (username.trim().length() <= 0)) {
            httpResp.sendRedirect("/");
            return;
        }

        chain.doFilter(request, response);
        
    }
    
}
