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
	public static final String CONTEXTPATH_WEBSERVLET = "ControllerServlet";
	private static final long serialVersionUID = 1L;
	private ModelRegisterContents mrc = null;
	private static final String NAME_CONTENTS_ROOT = "contents";
	private static final String PATH_INDEX = "/index.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {
		if( mrc == null ) {
			String pathContext = this.getServletContext().getRealPath(NAME_CONTENTS_ROOT);
			SystemLog.printlnWithSession(request, pathContext +" starts", this);
			mrc = new ModelRegisterContents(pathContext);
		}
		String keywordFilename = (String) request.getParameter(Constants.PARAMETER_KEYWORD);
		SystemLog.printlnWithSession(
				request,
				Constants.PARAMETER_KEYWORD +":\""+ keywordFilename +"\"",
				this );
		Map<String, String> mapContents =
				mrc.searchOnFilename(keywordFilename);
		request.setAttribute(Constants.PARAMETER_MAPFILENAMEDLPATH, mapContents);
		RequestDispatcher dispathcer =
				request.getRequestDispatcher(PATH_INDEX);
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
