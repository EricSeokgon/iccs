<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Map"%>
<%@page import="devpia.dextupload.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Date"%>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.cfg.Config" %>
<%@ page import="sp.bbs.*" %>

<SCRIPT language="JavaScript" type="text/javascript">

function goUrl() {
	var fm = document.fmWrite;
	
	fm.action = "../bbs/BbsAction.do";
	
	fm.submit();
}

</SCRIPT>

<%
	PrintWriter w = response.getWriter();
	
	FileUpload fileUpload = new FileUpload(request, response);

	StatusEnt status = (StatusEnt)session.getAttribute("status");
	
	try {
		String strPath = request.getRealPath("");
		String strPath_tmp = request.getRealPath("/");

		// 정품 혹은 평가판의 만료일을 판단하기 위한 라이센스 파일의 위치를 지정합니다.
		fileUpload.setLicenseFilePath(strPath_tmp + File.separator + "dextuploadj.config");
		
		//fileUpload.setMaxFileLength(5*1024*1024);
		//fileUpload.setMaxTotalLength(10*1024*1024);
		
		fileUpload.SetProgress(Integer.parseInt(request.getQueryString()));
		
		fileUpload.setAutoMakeFolder(true);
		
		fileUpload.UploadStart(strPath);
	
		
		//-----------------
		String cmd = KJFUtil.print(fileUpload.getParameter("cmd"));	     
		String index2 = KJFUtil.print(fileUpload.getParameter("INDEX2"));
		String sctextvalue = KJFUtil.print(fileUpload.getParameter("scTEXTVALUE"));  
		String subject = KJFUtil.print(fileUpload.getParameter("SUBJECT"));      
		String nowpage = KJFUtil.print(fileUpload.getParameter("nowPage"));      
		String mode = KJFUtil.print(fileUpload.getParameter("mode"));         
		String scsgg_cd = KJFUtil.print(fileUpload.getParameter("scSGG_CD"));     
		String ct_id = KJFUtil.print(fileUpload.getParameter("CT_ID"));        
		String depth = KJFUtil.print(fileUpload.getParameter("DEPTH"));        
		String scsd_cd = KJFUtil.print(fileUpload.getParameter("scSD_CD"));      
		String rowperpage = KJFUtil.print(fileUpload.getParameter("rowPerPage"));   
		String url = KJFUtil.print(fileUpload.getParameter("URL"));          
		String index1 = KJFUtil.print(fileUpload.getParameter("INDEX1"));       
		String content = KJFUtil.print(fileUpload.getParameter("CONTENT"));      
		String filecnt = KJFUtil.print(fileUpload.getParameter("fileCnt"));      
		String scslct_gubun = KJFUtil.print(fileUpload.getParameter("scSLCT_GUBUN")); 
		String user_name = KJFUtil.print(fileUpload.getParameter("USER_NAME")); 
		String board_seq = KJFUtil.print(fileUpload.getParameter("BOARD_SEQ")); 
		//-----------------
		
		String tmp_dir = Config.props.get("BBS_FILE_DIR");
		String CT_Dir = tmp_dir + ct_id + "\\";
		
		content = content.replaceAll("<div>&nbsp;</div>","<br/>");
		content = content.replaceAll("&nbsp;","@@@@@");
		
		content = content.replaceAll("\"","'");
		
	%>
	
	<form name="fmWrite" method="post" >
		<input type="hidden" name="cmd" value="<%=cmd%>"/>
		<input type="hidden" name="INDEX2" value="<%=index2%>"/>
		<input type="hidden" name="scTEXTVALUE" value="<%=sctextvalue%>"/>
		<input type="hidden" name="SUBJECT" value="<%=subject%>"/>
		<input type="hidden" name="nowPage" value="<%=nowpage%>"/>
		<input type="hidden" name="mode" value="<%=mode%>"/>
		<input type="hidden" name="scSGG_CD" value="<%=scsgg_cd%>"/>
		<input type="hidden" name="CT_ID" value="<%=ct_id%>"/>
		<input type="hidden" name="DEPTH" value="<%=depth%>"/>
		<input type="hidden" name="scSD_CD" value="<%=scsd_cd%>"/>
		<input type="hidden" name="rowPerPage" value="<%=rowperpage%>"/>
		<input type="hidden" name="URL" value="<%=url%>"/>
		<input type="hidden" name="INDEX1" value="<%=index1%>"/>
		<input type="hidden" name="CONTENT" value="<%=content%>"/>
		<input type="hidden" name="fileCnt" value="<%=filecnt%>"/>
		<input type="hidden" name="scSLCT_GUBUN" value="<%=scslct_gubun%>"/>
		<input type="hidden" name="USER_NAME" value="<%=user_name%>"/>
		<input type="hidden" name="BOARD_SEQ" value="<%=board_seq%>"/>
	
	
	
	등록중.....

	<%
		FileItem[] value = fileUpload.getFileItemValues("BBS_FILE");

		//실제 파일명과, 물리명을 배열로 BbsAction 으로 넘기기 위해..
		int cnt=0;
		String str = "";	//파일확장자명
		String sys_file_name = ""; 
		
		out.println("<input type='hidden' name='fileCnt' value='"+value.length+"'/> ");
			
		for (int i = 0; i < value.length; i++) 
		{
			if(value[i] != null)
			{
				if(value[i].IsUploaded())
				{
					str =  value[i].getFileName().substring(value[i].getFileName().lastIndexOf(".")); //파일 확장자명
					sys_file_name = Math.round(Math.random()*9999999) +"_"+ new Date().getTime()+str;
					
					out.println("<input type='hidden' name='file_real_Name"+cnt+"' value='"+value[i].getFileName()+"'/> ");
					out.println("<input type='hidden' name='file_sys_Name"+cnt+"' value='"+sys_file_name+"'/> ");
					out.println("<input type='hidden' name='file_size"+cnt+"' value='"+value[i].length()+"'/> ");
					
					value[i].SaveAs(CT_Dir,sys_file_name ,true);
				}
				else
				{
					out.println("<input type='hidden' name='file_yn' value='N'/> ");	
				}
				
			}
			cnt++;
		}
	
			
%>
	</form>
	
<%	
	}
	catch (DEXTUploadException ex) {
		w.println(ex.getMessage());
	} 
	catch (Exception ex) {
		w.println(ex.getMessage());
	} 
	finally {
		fileUpload.dispose();
	%>
		<SCRIPT> goUrl(); </SCRIPT>
	<%
	}
%>
