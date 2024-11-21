package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;
//자주 사용하는 Javascript의 함수를 클래스로 정의

public class JSFunction {

	/*
	 * 매서드 생성시 statuc을 통해 정적메서드로 정의하면 해당 클래스의 인스턴스를 생성하지 않고도 클래스명으로 즉시 메서드를 호출할 수
	 * 있다.
	 */

	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = "" + "<script>" + "    alert('" + msg + "');" + "    location.href='" + url + "';"
					+ "</script>";
			out.println(script);
		} catch (Exception e) {
		}
	}

	public static void alertBack(String msg, JspWriter out) {
		/*
		 * Java클래스에서는 JSP의 내장객체를 즉시 사용할 수 없으므로 반드시 매개변수로 전달받아 사용해야 한다. 여기서는 웹브라우저에 문자열을
		 * 출력하기 위해 out 내장객체를 JSPWriter 타입으로 받은 후 사용하고 있다.
		 */
		try {
			String script = "" + "<script>" + "    alert('" + msg + "');" + "    hsitory.back();" + "</script>";
			out.println(script);
		} catch (Exception e) {
		}
	}

	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" + "<script>" + "alert('" + msg + "');" + "location.href='" + url + "';" + "</script>";
			writer.print(script);
		} catch (Exception e) {
		}
	}

///////////////////////////////////////////////////////

	/*
	 * 서블릿에서 JavaScript를 실행할수 있도록 정의한 메서드. JSP로 포워드 하지 않고 즉시 출력해야 하므로 컨텐츠 타입을 먼저
	 * 설정하고 있다.
	 */

	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			// 경고창을 듸우고 특정페이지로 이동할 수 있는 JS코드 작성
			String script = "" + "<script>" + "   alert('" + msg + "');" + " history.back();" + "</script>";
			writer.print(script);
		} catch (Exception e) {
		}
	}
}
