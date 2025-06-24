package controller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import daos.C002Dao;


/**
* Servlet implementation class C002Servlet
*/
@WebServlet("/C002.html")
public class C002Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public C002Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		C002Dao dao = new C002Dao();
		int kotoshi = java.time.Year.now().getValue();
		int month = java.time.LocalDate.now().getMonthValue();

		        int nennkannSales = dao.getNennkannSales(kotoshi);
		        Map<String, Integer> categorySalesMap = dao.getCategorySales();
		        
//		        ObjectMapper mapper = new ObjectMapper();
//		        String categorySalesJson = mapper.writeValueAsString(categorySalesMap);
//		        
		        StringBuilder jsonBuilder = new StringBuilder();
		        jsonBuilder.append("{");

		        int count = 0;
		        for (Map.Entry<String, Integer> entry : categorySalesMap.entrySet()) {
		            jsonBuilder.append("\"")
		                       .append(entry.getKey().replace("\"", "\\\""))
		                       .append("\":")
		                       .append(entry.getValue());

		            if (++count < categorySalesMap.size()) {
		                jsonBuilder.append(",");
		            }
		        }

		        jsonBuilder.append("}");

		        String categorySalesJson = jsonBuilder.toString();

		        

		        request.setAttribute("monthSales", monthSales);
		        request.setAttribute("nennkannSales", nennkannSales);
		      //  request.setAttribute("CategorySales", CategorySales);
		        request.setAttribute("categorySalesJson", categorySalesJson);

			if (++count < categorySalesMap.size()) {
				jsonBuilder.append(",");
			}
		}

		jsonBuilder.append("}");

		String categorySalesJson = jsonBuilder.toString();

		request.setAttribute("monthSales", monthSales);
		request.setAttribute("nennkannSales", nennkannSales);
		//  request.setAttribute("CategorySales", CategorySales);
		request.setAttribute("categorySalesJson", categorySalesJson);

		request.getRequestDispatcher("/C002.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//C002Dao dao = new C002Dao();
//Map<String, Integer> categorySalesMap = dao.getCategorySales();
//
//ObjectMapper mapper = new ObjectMapper();
//String categorySalesJson = mapper.writeValueAsString(categorySalesMap);
//

//request.setAttribute("categorySalesJson", categorySalesJson);
