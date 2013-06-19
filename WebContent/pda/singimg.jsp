<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="kjf.ops.*" %>
<%@ page isErrorPage="true" %>
<%
	response.setContentType("image/jpeg");
	String city = request.getParameter("city");
	String gugun = request.getParameter("gugun");
	String ub_code = request.getParameter("ub_code");
	String filename request.getParameter("f_name");
	
	String dirs = "/data/webroot/ICCSM/usebefore/"+city+"/"+gugun+"/"+ub_code+"/"+filename;
	File file = new File(dirs);
	//File file = new File("D:\\cytyseal\\"+gugun+"\\"+rEntity.getValue(0,"CYTYSEAL"));
	int fileSize = (int) file.length(); //파일 크기
	//파일 스트림을 저장하기 위한 바이트 배열 생성.
	byte bytestream[] = new byte[fileSize];
	out.clear();
	//파일 객체를 스트림으로 불러온다.
	BufferedInputStream Bfin = new BufferedInputStream(
			new FileInputStream(file));
	BufferedOutputStream outs = new BufferedOutputStream(
			response.getOutputStream());
	int read = 0;
	while ((read = Bfin.read(bytestream)) != -1) {
		outs.write(bytestream, 0, read);
	}
	outs.close();
	Bfin.close();
%>
