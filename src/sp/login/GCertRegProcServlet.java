package sp.login ; 

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.util.KJFSec;

import com.dsjdf.jdf.Logger;
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.servlet.GPKIHttpServlet;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.util.GPKIUtil;

public class GCertRegProcServlet extends GPKIHttpServlet 
{

	/**
	* 생성자 
	* 
	*/
	public GCertRegProcServlet()
	{
		super();
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doPost(req, res);
	}

	/**
	* 암호화 된 데이터를 받아 클라이언트의 자바스크립트에서 복호화 함수를 호출하여 화면에 출력해준다 
	* 
	* @param req		GPKIHttpServletRequest.java에서 재정의한 request
	* @parm  res		GPKIHttpServletResponse.java에서 재정의한 response
	* @return			void
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		try {


			X509Certificate cert = null;
	
		    
			cert = ((GPKIHttpServletRequest)req).getSignerCert();
			
			//String subDN = URLEncoder.encode(KJFSec.encode(cert.getSubjectDN()),"utf-8");
			String subDN = cert.getSubjectDN();
			String id = ((GPKIHttpServletRequest)req).getParameter("id");
			
			System.out.println("++"+subDN);
			System.out.println("++"+id);
			
			//req.setAttribute("subDN", subDN);
			//req.setAttribute("id", id);
			
			
			res.addHeader("subDN", subDN);
			//res.sendRedirect("../login/LoginAction.do?cmd=GCertRegCUD&id="+id+"&subDN="+subDN);
			res.sendRedirect("../login/LoginAction.do?cmd=GCertRegCUD&id="+id);


			
	        //RequestDispatcher rd = getServletContext().getRequestDispatcher("/login/LoginAction.do?cmd=GCertRegCUD");
	        //rd.forward(req, res);

		
		} catch (Exception e) {
			Logger.err.println(this, e.getMessage());
			GPKIUtil.goErrorPage(e);
		}
	}
}
