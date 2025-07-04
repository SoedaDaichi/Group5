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

import form.LoginAccount;

/**
 * Servlet Filter implementation class S0020Filter
 */
@WebFilter("/*")
public class S0020Filter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public S0020Filter() {
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
		boolean authorityUrlCheck = uri.matches(".*/S002[3-5]\\.(html|jsp)$");

		// 権限なしアカウント排除処理
		if (session != null) {
			LoginAccount loginAccount = (LoginAccount) session.getAttribute("loginAccount");
			if (loginAccount != null && authorityUrlCheck
					&& (loginAccount.getAuthority() == 0 || loginAccount.getAuthority() == 2)) {
				System.out.println("S0020Filter: 不正");
				session.removeAttribute("loginAccount");
				Map<String, String> errors = new HashMap<>();
				errors.put("account", "不正なアクセスを確認しました。");
				session.setAttribute("errors", errors);
				res.sendRedirect(req.getContextPath() + "/C001.html");
				return;
			}
		}

		// 商品検索系のsession破棄
		boolean isTargetPage = uri.matches(".*/S002[1-5]\\.(html|jsp)$");
		String[] SessionKeys = {"ssform", "salesList", "sale_id", "salesdata", "salesform"};
		
		if (session != null && !isTargetPage) {
			for (String salesSessionKey : SessionKeys) {
				if (session.getAttribute(salesSessionKey) != null) {
					session.removeAttribute(salesSessionKey);
					System.out.println("売上検索系: " + salesSessionKey + "を削除。");
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
