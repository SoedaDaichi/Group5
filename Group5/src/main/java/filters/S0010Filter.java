package filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.loginAccount;

/**
 * Servlet Filter implementation class S0030Filter
 */
@WebFilter(urlPatterns = { "/S0010.html", "/S0011.html" })
public class S0010Filter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public S0010Filter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);
		loginAccount loginAccount = (loginAccount) session.getAttribute("loginAccount");
		String uri = req.getRequestURI();

		if (loginAccount.getAuthority() == 0 || loginAccount.getAuthority() == 2) {
			System.out.println("S0010Filter: 不正");
			session.removeAttribute("loginAccount");
			Map<String, String> errors = new HashMap<>();
			errors.put("account", "不正なアクセスを確認しました。");
			session.setAttribute("errors", errors);
			res.sendRedirect("C001.html");
			return;
		}

		// 売上登録系のsession破棄
		boolean isTargetPage = uri.matches(".*/S001[0-1]\\.(html|jsp)$");
		String[] sales_sessionKeys = { "Register_salesform", "Register_salesdata" };

		if (session != null && !isTargetPage) {
			for (String sales_sessionKey : sales_sessionKeys) {
				if (session.getAttribute(sales_sessionKey) != null) {
					session.removeAttribute(sales_sessionKey);
					System.out.println("売上登録系: " + sales_sessionKey + "を削除。");
				}
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
