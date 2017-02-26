package system.distribution.contents;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModelRegisterContents mrc = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        mrc = new ModelRegisterContents();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {
		String keywordFilename = (String) request.getParameter("keyword");
		SystemLog.printlnWithSession(
				request,
				"RetrievalKeyword:\""+ keywordFilename +"\"",
				this );
		Map<String, String> mapContents =
				mrc.searchOnFilename(keywordFilename);
		request.setAttribute("mapFilenameDLPath", mapContents);
		RequestDispatcher dispathcer =
				request.getRequestDispatcher("/index.jsp");
		dispathcer.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {
		doGet(request, response);
	}
}
