import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Answers")
public class ShowAnswers extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Question currentQ;
	private int currentQID;

	public ShowAnswers() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String questionID = request.getParameter("id");
		currentQID = Integer.parseInt(questionID) - 1;
		currentQ = QuestionGateway.mock.get(currentQID);

		out.println("<html>");
		out.println("<body>");

		out.println("<p>Answers for question " + questionID + "</p>");

		int count = 0;
		List<String> answers = currentQ.answers;
		if (answers.size() > 0) {
			for (String s : answers) {
				count++;
				out.print("<p>" + count + "." + s + "</p>");
			}
		} else {
			out.print("<p> No answers yet, migos!</p>");
		}

		out.println("<form method=\"POST\" " + "action=\"Answers?id="
				+ questionID + "\">");
		out.println("<p><input type=\"text\" " + "name=\"theAnswer\" "
				+ "size=\"50\"> <input type=\"submit\" "
				+ "value=\"Add Answer\"></p>");
		out.println("</form>");
		out.println("<a href=\"Questions\"><< Back</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String theAnswer = request.getParameter("theAnswer");

		currentQ.answers.add(theAnswer);

		QuestionGateway.mock.set(currentQID, currentQ);

		String questionID = request.getParameter("id");
		currentQID = Integer.parseInt(questionID) - 1;

		currentQ = QuestionGateway.mock.get(currentQID);

		out.println("<html>");
		out.println("<body>");

		out.println("<p>Answers for question " + questionID + "</p>");

		int count = 0;
		List<String> answers = currentQ.answers;
		if (answers.size() > 0) {
			for (String s : answers) {
				count++;
				out.print("<p>" + count + "." + s + "</p>");
			}
		} else {
			out.print("<p> No answers yet, migos!</p>");
		}

		out.println("<form method=\"POST\" " + "action=\"Answers?id="
				+ questionID + "\">");
		out.println("<p><input type=\"text\" " + "name=\"theAnswer\" "
				+ "size=\"50\"> <input type=\"submit\" "
				+ "value=\"Add Answer\"></p>");
		out.println("</form>");
		out.println("<a href=\"Questions\"><< Back</a>");
		out.println("</body></html>");
	}
}