<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.io.*" %>
<%
	ReportEntity rEntity =(ReportEntity)request.getAttribute("rEntity");
%>
<table>
<% 
		if(rEntity != null){if(rEntity.getRowCnt() > 0){
				for(int i = 0; rEntity.getRowCnt() > i; i++){
					out.print("<tr><td>");
					if(i == 0){
						//out.print("LOG1|"+rEntity.getRowCnt()+"|");
					}
						out.print("2|");
						out.print(rEntity.getValue(i,"NM_KOR")+"|");
						out.print(rEntity.getValue(i,"ENGINEER_SSN")+"|");
						out.print(rEntity.getValue(i,"TMP_GRADE")+"|");
						out.print(rEntity.getValue(i,"CORP_NM")+"|"+"\n");									
						out.print("</td></tr>");
				}			
				out.print("<tr><td>END</td></tr>");				
			}else{
				out.print("blank");
			}		
		}
%>
</table>