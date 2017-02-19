package system.distribution.contents;

import java.io.IOException;
import java.util.HashMap;
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
	private Map<String,String> mapContents =	// 登録コンテンツ<ファイル名, DLリンク>
			new HashMap<String,String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        ModelRegisterContents.CONTEXTPATH_CONTENTS_ROOT =	// コンテンツディレクトリのURL
        		"/ContentsDistributionSystem/recochoku";
        mapContents = ModelRegisterContents.registorContents();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {
		String keywordFile = (String) request.getParameter("keyword");
		System.out.println(keywordFile);	// TODO ログ
		Map<String, String> mapSearchResult =
				ModelRegisterContents.searchOnFilename(keywordFile, mapContents);
		request.setAttribute("namesFile", mapSearchResult);
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
