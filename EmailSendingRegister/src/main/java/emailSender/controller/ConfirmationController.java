package emailSender.controller;

import emailSender.dto.UserDto;
import emailSender.service.EmailService;
import emailSender.service.SignInService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/confirmation")
public class ConfirmationController extends HttpServlet {


    private EmailService emailService;
    private SignUpController signUpController;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        emailService = springContext.getBean(EmailService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        Optional<UserDto> user = signUpController.ac(token);
        if (user.isPresent()) {
            req.setAttribute("message", "user " + user.get().getName() + " activated");
        } else req.setAttribute("message", "user not found");
        req.getRequestDispatcher("Confirmation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
