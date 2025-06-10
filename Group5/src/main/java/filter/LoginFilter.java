//package filter;
//
//import java.io.IOException;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
///**
// * Servlet Filter implementation class LoginFilter
// */
//@WebFilter(urlPatterns={"/*"})
//public class LoginFilter extends HttpFilter implements Filter {
//       
//    /**
//     * @see HttpFilter#HttpFilter()
//     */
//    public LoginFilter() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		// place your code here
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        // セッション取得（なければnull）
//        HttpSession session = req.getSession(false);
//
//        // ログインユーザー情報の有無で判定
//        boolean loggedIn = (session != null && session.getAttribute("loginUser") != null);
//
//        // ログイン画面や静的リソースは除外
//        String uri = req.getRequestURI();
//        boolean loginPage = uri.endsWith("C001.jsp") || uri.endsWith("C001Servlet");
//        boolean staticResource = uri.matches(".*(\\.css|\\.js|\\.png|\\.jpg|\\.ico)$");
//
//        if (loggedIn || loginPage || staticResource ) {
//            // ログイン済み、またはログイン画面・静的リソースならそのまま進む
//            chain.doFilter(request, response);
//        } else {
//            // 未ログインならログイン画面へリダイレクト
//            res.sendRedirect(req.getContextPath() + "/C001.jsp");
//        }
//    }
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
