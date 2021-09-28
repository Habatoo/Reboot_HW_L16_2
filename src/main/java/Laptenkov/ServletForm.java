package Laptenkov;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Сервлет - работает с формой (два текстовых поля)
 * и позволяет добавлять посты в ленте и отображать их после
 * отправки формы
 * @author habatoo
 */
@WebServlet("/")
public class ServletForm extends HttpServlet {

    /**
     * Метод GET метод возвращает значения из сессии по имени параметра
     * при запросе GET по адресу http://localhost:8080/HW_16/
     * @param request запрос по адресу http://localhost:8080/HW_16/
     * @param response ответ при запросе по адресу http://localhost:8080/HW_16/
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try (ServletOutputStream outputStream = response.getOutputStream())
        {
            response.setContentType("text/html");
            HttpSession session = request.getSession(true);

            outputStream.println("<form action=\"post\">");
            outputStream.println("    User FirstName:<input type=\"text\" name=\"userFirstName\"/><br/>");
            outputStream.println("    User LastName:<input type=\"text\" name=\"userLastName\"/><br/>");
            outputStream.println("    <input type=\"submit\" value=\"submit\"/>");
            outputStream.println("</form>");

            String firstName = request.getParameter("userFirstName");
            String lastName = request.getParameter("userLastName");

            session.setAttribute(firstName, lastName);

            Enumeration keys = session.getAttributeNames();
            while (keys.hasMoreElements())
            {
                String key = (String) keys.nextElement();
                outputStream.println("User FirstName: " + key + ", LastName: " + session.getValue(key) + "<br>");
            }

        } catch (IOException exp){
            exp.printStackTrace();
        }

    }

    /**
     * Метод POST метод устанавливает по имени параметра
     * и сохраняет значение.
     * @param request запрос по адресу http://localhost:8080/HW_16/
     * @param response ответ при запросе по адресу http://localhost:8080/HW_16/
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
