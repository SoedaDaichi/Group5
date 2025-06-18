package controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import beans.Accounts;
//import services.S0041Service;
//


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Accounts;
import services.S0041Service;

/**
 * Servlet implementation class S0041Servlet
 */
@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0041Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		
//		HttpSession session = request.getSession();
//	    Integer account_id = (Integer) session.getAttribute("account_id");
//	    
////	    String keyword = request.getParameter("keyword"); 
////	    
////	    System.out.println("[S0041Servlet] account_id: " + account_id + ", keyword: " + keyword);
////	    
////	    List<Accounts> accountList;
////
////	    if (keyword != null) {
////	        // 通常の検索時
////	        S0041Service service = new S0041Service();
////	        accountList = service.getAccountsByUserId(account_id); // ←本来は keyword 使うべきだけど今は関係ない？
////
////	       // System.out.println("[S0041Servlet] 新規検索: " + accountList.size() + "件"); 
////	        System.out.println("[S0041Servlet] 検索結果件数: " + (accountList == null ? 0 : accountList.size()));
////	        
////	        
////	        System.out.println("[S0041Servlet] 保存前 keyword: " + keyword);
////	        System.out.println("[S0041Servlet] 保存前 accountList.size(): " + (accountList == null ? 0 : accountList.size()));
////	        
////	        
////	        // 検索結果をセッションに保存（戻る処理で使うため）
////	        session.setAttribute("searchKeyword", keyword);
////	        session.setAttribute("searchResultList", accountList);
////	        
////	        System.out.println("[S0041Servlet] セッションに保存した searchKeyword: " + session.getAttribute("searchKeyword"));
////	        System.out.println("[S0041Servlet] セッションに保存した searchResultList size: " + ((List<?>)session.getAttribute("searchResultList")).size());
////	        
////	        System.out.println("[S0041Servlet] セッションに保存: searchKeyword=" + keyword + ", searchResultList=" + (accountList == null ? 0 : accountList.size()));
////	        
////	    } 
//	    
//	    String keyword = request.getParameter("keyword");
//	    List<Accounts> accountList = null;
//	    
//	    System.out.println("[S0041Servlet] セッションID: " + session.getId());
//	    System.out.println("[S0041Servlet] account_id: " + account_id + ", keyword: " + keyword);
//
//
//	    if (keyword != null) {
//	        S0041Service service = new S0041Service();
////	        List<Accounts> accountList = service.getAccountsByUserId(account_id); 
//	        accountList = service.getAccountsByUserId(account_id);
//
//	        session.setAttribute("searchKeyword", keyword);
//	        session.setAttribute("searchResultList", accountList);
//	        
//	        System.out.println("[S0041Servlet] 検索結果件数: " + (accountList == null ? 0 : accountList.size()));
//	        System.out.println("[S0041Servlet] セッションに保存: searchKeyword=" + keyword + ", searchResultList=" + (accountList == null ? 0 : accountList.size()));
//	    }
//
//	    else {
//	        accountList = (List<Accounts>) session.getAttribute("searchResultList");
//	        keyword = (String) session.getAttribute("searchKeyword");
//	        
//	        System.out.println("[S0041Servlet] セッションから取得: " + (accountList == null ? "null" : accountList.size() + "件"));
//
//	        
//	        if (accountList == null) {
//	            S0041Service service = new S0041Service();
//	            accountList = service.getAccountsByUserId(account_id);
//	            
//	            System.out.println("[S0041Servlet] フォールバック検索: " + accountList.size() + "件");
//	            
//	            System.out.println("[S0041Servlet] 検索キーワード: " + keyword);
//	            System.out.println("[S0041Servlet] 検索結果数: " + (accountList == null ? "null" : accountList.size()));
//
//	        }
//	    }
//
//	    request.setAttribute("accountList", accountList);
//	    request.setAttribute("keyword", keyword);
//	    request.getRequestDispatcher("/S0041.jsp").forward(request, response);
//	}
//	    
//	    
//	    
////	    S0041Service service = new S0041Service();
////	    List<Accounts> accountList = service.getAccountsByUserId(account_id);
////
////	    session.setAttribute("searchKeyword", keyword);
////	    session.setAttribute("searchResultList", accountList);
////		
////		request.setAttribute("accountList", accountList);
////		request.getRequestDispatcher("/S0041.jsp").forward(request, response);
////		
//	
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//doGet(request, response);
//		
//		request.getRequestDispatcher("/S0042.jsp").forward(request, response);
//		
//	}
//
//}



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // セッションから検索結果とキーワードを取得
        @SuppressWarnings("unchecked")
        List<Accounts> accountList = (List<Accounts>) session.getAttribute("searchResultList");
        String keyword = (String) session.getAttribute("searchKeyword");

        // 取得後はセッションから削除（ワンタイム表示）
        session.removeAttribute("searchResultList");
        session.removeAttribute("searchKeyword");

        // リクエストにセットしてJSPへフォワード
        request.setAttribute("accountList", accountList);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/S0041.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Integer account_id = (Integer) session.getAttribute("account_id");

        String keyword = request.getParameter("keyword");

        // 検索処理（キーワードを利用した検索が必要ならサービスで処理）
        S0041Service service = new S0041Service();
        List<Accounts> accountList = service.getAccountsByUserId(account_id); // keyword使いたいならここで活用

        // 検索結果とキーワードをセッションに保存
        session.setAttribute("searchResultList", accountList);
        session.setAttribute("searchKeyword", keyword);

        // 結果表示用URLにリダイレクト（GETメソッドで結果表示）


response.sendRedirect("S0041.html");
    }
}

