package filter;

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
 * Servlet Filter implementation class S0040Filter
 */
@WebFilter("/*")
public class S0040Filter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public S0040Filter() {
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
		String uri = req.getRequestURI();
		boolean authorityUrlCheck = uri.matches(".*/S004[2-4]\\.(html|jsp)$");

		// 権限なしアカウント排除処理
		if (session != null) {
			loginAccount loginAccount = (loginAccount) session.getAttribute("loginAccount");
			if (loginAccount != null && authorityUrlCheck
					&& (loginAccount.getAuthority() == 0 || loginAccount.getAuthority() == 1)) {
				System.out.println("S0040Filter: 不正");
				session.removeAttribute("loginAccount");
				Map<String, String> errors = new HashMap<>();
				errors.put("account", "不正なアクセスを確認しました。");
				session.setAttribute("errors", errors);
				res.sendRedirect(req.getContextPath() + "/C001.html");
				return;
			}
		}

		// 商品検索系のsession破棄
		boolean isTargetPage = uri.matches(".*/S004[1-4]\\.(html|jsp)$");
		String[] accounts_sessionKeys = { "asform", "accountList", "account_id", "accoutns", "accountsdata" };

		if (session != null && !isTargetPage) {
			for (String accounts_sessionKey : accounts_sessionKeys) {
				if (session.getAttribute(accounts_sessionKey) != null) {
					session.removeAttribute(accounts_sessionKey);
					System.out.println("アカウント検索系: " + accounts_sessionKey + "を削除。");
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
