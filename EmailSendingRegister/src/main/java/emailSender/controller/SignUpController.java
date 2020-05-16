package emailSender.controller;

import emailSender.dto.SignUpDto;
import emailSender.service.SignUpService;
import emailSender.util.exception.EmailExistException;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/signUp")
public class SignUpController extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        signUpService = springContext.getBean(SignUpService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpDto dto = SignUpDto.builder().name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("pass")).build();
        try {
            signUpService.SignUp(dto);
        } catch (EmailExistException exception) {
            req.setAttribute("message", exception.getMessage());
            req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
        }
        req.setAttribute("message", "We sent you an confirmation mail at " + dto.getEmail());
        req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
    }
}
