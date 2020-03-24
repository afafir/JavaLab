package emailSender.controller;

import emailSender.dto.SignInDto;
import emailSender.dto.UserDto;
import emailSender.service.SignInService;
import emailSender.util.exception.AuthenticationException;
import emailSender.util.exception.ConfirmationException;
import emailSender.util.exception.UserNotFoundException;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/signIn")
public class SignInController  extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        signInService = springContext.getBean(SignInService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("SignIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInDto dto = SignInDto.builder()
                .name(req.getParameter("name"))
                .password(req.getParameter("pass"))
                .build();
        try {
            UserDto userDto = signInService.signIn(dto);
            req.getSession(true);
            req.getSession().setAttribute("user", userDto);
            req.getRequestDispatcher("MainPage.jsp").forward(req, resp);
        } catch (AuthenticationException | UserNotFoundException | ConfirmationException e){
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("SignIn.jsp").forward(req, resp);
        }
    }
}
