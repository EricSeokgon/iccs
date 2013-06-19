<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="devpia.dextupload.*"%>
<%@ page import="sp.bbs.*" %>
<%@page import="java.io.PrintWriter"%>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);   
	if (request.getProtocol().equals("HTTP/1.1")) 
	{
	        response.setHeader("Cache-Control", "no-cache");
	}

	PrintWriter w = response.getWriter();

	Progress prog = null;
	
	try 
	{
		int Percent, TransferBytes, TotalBytes, BytesPerSec, ID;
		boolean bWorking;
		String strFileName;
	
		//GET 방식으로 전달된 진행 상태 아이디를 지정합니다.
		ID = Integer.parseInt(request.getQueryString());
		prog = Progress.GetProgress(ID);
	
		//현재 업로드 진행 상태 값을 읽습니다. 
		Percent = prog.getPercent();
		TransferBytes = prog.getTransferSize();
		TotalBytes = prog.getTotalSize();
		BytesPerSec = prog.getBytesPerSec();
		bWorking = prog.IsWorking();
		strFileName = prog.getFileName();

		int p_totalSize = (int) (TotalBytes / (1024 * 1024));
		
		
		w.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		w.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
		w.println("<HEAD>");
		w.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		w.println("<title>파일 업로드 중 입니다...</title>");
		w.println("<link href='../css/upload.css' rel='stylesheet' type='text/css' />");
		w.println("</HEAD>");

		if (bWorking == true) {
		//업로드 진행 상태를 출력합니다.
		w.println("<Meta HTTP-EQUIV=\"Refresh\" CONTENT=1>");
		w.println("<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'>");
		w.println("	<tr>");
		w.println("		<td align='center' style='padding-bottom:17px'><img src='../images/progressBar/upload_img.gif' width='256' height='139' /></td>");
		w.println("	</tr>");
		w.println("	<tr>");
		w.println("		<td><table border='0' align='center' cellpadding='0' cellspacing='0'>");
		w.println("			<tr>");
		w.println("				<td><img src='../images/progressBar/bar_l.gif' width='18' height='20' /></td>");
		w.println("				<td width='379'><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
		w.println("					<tr>");
		w.println("						<!-- 상태바 조절 여기td의 with=50%를 변경하심됩니다. --><td width='"+ Integer.toString((int) (3.55 * Percent)) +"' height='20' background='../images/progressBar/bar_load.gif'></td>");
		w.println("						<td background='../images/progressBar/bar_unload.gif'></td>");
		w.println("					</tr>");
		w.println("				</table></td>");
		w.println("				<td><img src='../images/progressBar/bar_r.gif' width='18' height='20' /></td>");
		w.println("			</tr>");
		w.println("		</table></td>");
		w.println("	</tr>");
		w.println("	<tr>");
		w.println("		<td><table border='0' align='center' cellpadding='0' cellspacing='0' class='detail'>");
		w.println("			<tr>");
		w.println("				<td><strong>전송 파일</strong> : " + strFileName + " </td>");
		w.println("			</tr>");
		w.println("			<tr>");
		w.println("				<td><strong>전송 속도</strong> : " 	+ Integer.toString((int) (BytesPerSec / 1024)) + " KB / sec </td>");
		w.println("			</tr>");
		w.println("			<tr>");
		w.println("				<td><strong>진행률</strong> "	+ Integer.toString((int) (TransferBytes / (1024 * 1024))) + " MB / " + Integer.toString((int) (TotalBytes / (1024 * 1024))) + " MB (" + Integer.toString(Percent) + "%) </td>");
		w.println("			</tr>");
		w.println("		</table></td>");
		w.println("	</tr>");
		w.println("	<tr>");
		w.println("		<td align='center' class='notice'>Click the <span>STOP button</span> on your browser to abort uploading. </td>");
		w.println("	</tr>");
		w.println("</table>");
		w.println("</body>");
		w.println("</html>");
		
	} else 
	{
		//업로드가 완료 또는 IE 중지 버튼 누를 시
		w.println("<body onload='javascript: top.window.close();'>");
		prog.dispose();
	}
		w.println("</body>");
		w.println("</HTML>");
} catch (Exception ex) {
	w.println(ex.getMessage());
}
%>

