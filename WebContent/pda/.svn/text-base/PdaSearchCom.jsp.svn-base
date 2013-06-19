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
		if(rEntity != null){
			if(rEntity.getRowCnt() > 0){
				for(int i = 0; rEntity.getRowCnt() > i; i++){
					out.print("<tr><td>");
					if(i == 0){
						//out.print("LOG1|"+rEntity.getRowCnt()+"|");
					}
						out.print("1|");
						out.print(rEntity.getValue(i,"NAME")+"|");
						out.print(rEntity.getValue(i,"REP_NM_KOR")+"|");
						out.print(rEntity.getValue(i,"COI_WRT_NUM")+"|");
						out.print(rEntity.getValue(i,"REP_MOBILE")+"|");
						out.print(rEntity.getValue(i,"ADDR_TEL_NUM")+"|");
						out.print(rEntity.getValue(i,"ADDR_FAX_NUM")+"|");
						out.print(rEntity.getValue(i,"ADDR_POST_NUM")+" ");
						out.print(rEntity.getValue(i,"ADDR_ADDR")+" ");
						out.print(rEntity.getValue(i,"ADDR_DETAIL_ADDR")+"|");
						out.print(rEntity.getValue(i,"MANA_NUM")+"|\n");				
						out.print("</td></tr>");				
				}
				out.print("<tr><td>END</td></tr>");				
			}else{
				out.print("blank");
			}
			//out.print(" Please input company name. ");			
		}
		
%>
</table>