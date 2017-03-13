package system.distribution.contents;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class SystemLog {
	private static final String DELIMITER = "|";

	/**
	 * セッションとともにログを出力する
	 * >DateTime|SessionID|ObjectName|Message
	 * @param request	リクエスト
	 * @param message	ログに出力する任意のメッセージ
	 * @param appendix	クラス名などの付加情報
	 * @param request	セッション生成のもとになるリクエスト
	 */
	public static void printlnWithSession(
			HttpServletRequest request,
			String message,
			Object object
			) {
		String sessionID = null;
		if(request.getSession(false) != null) {
			sessionID = request.getSession(false).getId();
		}
		String messageToBePrinted = ""
				+ getStrCurrentDateTime()
				+ DELIMITER
				+ sessionID
				+ DELIMITER
				+ object.getClass().getName()
				+ DELIMITER
				+ message ;
		System.out.println(messageToBePrinted);
	}

	/**
	 * ログ出力
	 * >DateTime|ObjectName|Message
	 * @param message	ログに出力する任意のメッセージ
	 * @param appendix	クラス名などの付加情報
	 * @param request	セッション生成のもとになるリクエスト
	 */
	public static void println(
			String message,
			Object object
			) {
		String messageToBePrinted = ""
				+ getStrCurrentDateTime()
				+ DELIMITER
				+ object.getClass().getName()
				+ DELIMITER
				+ message ;

		System.out.println(messageToBePrinted);
	}

	/**
	 * 時刻取得
	 * @return String "yyyyMMdd_HHmmss.SSS"
	 */
	public static String getStrCurrentDateTime() {
		final String DATE_FORMAT = "yyyyMMdd_HHmmss.SSS";
		Date dateCurrent = new Date();
		SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat(DATE_FORMAT);
		String strCurrentDateTime =
				simpleDateFormat.format(dateCurrent);
		return strCurrentDateTime;
	}
}
