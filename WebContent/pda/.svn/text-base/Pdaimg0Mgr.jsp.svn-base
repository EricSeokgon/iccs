<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.io.*"%>
<%@ page import="kjf.ops.*"%>
<%
ReportEntity rEntity = (ReportEntity) request.getAttribute("rEntity");
String gugun = request.getParameter("gugun");
if (rEntity != null) {
	if (rEntity.getRowCnt() > 0) {
		response.setContentType("image/jpeg");
		File file = new File("/data/webroot/ICCSM/cytyseal/" + gugun + "/" + rEntity.getValue(0, "CYTYSEAL"));
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
	}
}
%>


