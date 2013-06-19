<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kjf.util.KJFFile"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="kjf.ops.*"%>
<%@ page import="kjf.util.*"%>
<%
	String result = "SIGNERR|";
	System.out.println("ST=================================================================");
	try{
		response.setContentType("image/jpeg");
		String attach_file = request.getParameter("attach_file");
		String city = request.getParameter("city");
		String gugun = request.getParameter("gugun");
		String id = request.getParameter("id");
		String ub_code = request.getParameter("ub_code");
		String to_data = KJFDate.getCurTime("yyyy-MM-dd");
		
		System.out.println("attach_file : "+attach_file);
		
		String dirs = "/data/webroot/ICCSM/usebefore/"+city+"/"+gugun+"/"+ub_code+"/";
		//String dirs = "D:\\cytyseal\\"+filename;
		
		InputStream _istream = request.getInputStream();

		String confPath = config.getServletContext().getRealPath("");
		
		String filePath = dirs ;
		System.out.println("filePath --->[" + filePath + "]");	
		//OutputStream s = new BufferedOutputStream(new FileOutputStream(filePath));
		OutputStream s = new FileOutputStream(filePath);
		/*
		int ch = -1;
		
		while ((ch = _istream.read()) != -1) {
			s.write(ch);
		}
		*/
		
		byte[] buffer = new byte[8192];
		int size = buffer.length;
		
		while (true) {
			int n = _istream.read(buffer);
			
			if (n <= 0) {
				break;
			}
			
			s.write(buffer, 0, n);
		}
		
		s.close();
		
	   ReportDAO    rDAO  = new ReportDAO();	   
	   String SEQ = KJFDBUtil.getMaxID("SEQ","PT_UB_FILE");
	   String SQL = "INSERT INTO PT_UB_FILE (SEQ,RECV_NUM,SIDO_CODE,SIGUNGU_CODE,FILE_NM,WRT_ID,INS_DT) VALUES ("+SEQ+",'"+ub_code+"','"+city+"','"+gugun+"','"+filename+"','"+id+"','"+to_data+"')";
	   rDAO.execute(SQL);
	   result = "OK|";
  }catch(Exception e){
	  System.out.println("sing err : "+e);	  
  }
  finally {
	  System.out.println("EN=================================================================");
	  out.print(result);
  }
  
	
		
%>