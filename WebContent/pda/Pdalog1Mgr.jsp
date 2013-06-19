<%@ page contentType="text/html; charset=utf-8" %><%@ page import="java.util.*"%><%@ page import="kjf.util.*" %><%@ page import="kjf.ops.*" %><%@ page import="java.util.Vector" %><%@ page import="java.io.*" %><%ReportEntity rEntity =(ReportEntity)request.getAttribute("rEntity");%><%if(rEntity != null){if(rEntity.getRowCnt() > 0){
				for(int i = 0; rEntity.getRowCnt() > i; i++){
					if(i == 0){
						out.print("LOG1|"+rEntity.getRowCnt()+"|");
					}
						out.print(rEntity.getValue(i,"SIDO_CODE")+"|");
						out.print(rEntity.getValue(i,"SIGUNGU_CODE")+"|");
						out.print(rEntity.getValue(i,"RECV_NUM")+"|");
						out.print(rEntity.getValue(i,"CIV_RECV_NUM")+"|");
						out.print(rEntity.getValue(i,"APPLPER_NM")+"|");
						out.print(rEntity.getValue(i,"APPLPER_REP")+"|");
						out.print(rEntity.getValue(i,"APPLPER_ADDR")+"|");
						out.print(rEntity.getValue(i,"APPLPER_DETAILADDR")+"|");
						out.print(rEntity.getValue(i,"APPLPER_TELNUM")+"|");
						out.print(rEntity.getValue(i,"OPE_NAME")+"|");
						out.print(rEntity.getValue(i,"COI_WRT_NUM")+"|");
						out.print(rEntity.getValue(i,"OPE_REP")+"|");
						out.print(rEntity.getValue(i,"OPE_TELNUM")+"|");
						out.print(rEntity.getValue(i,"OPE_ADDR")+"|");
						out.print(rEntity.getValue(i,"OPE_DETAILADDR")+"|");
						out.print(rEntity.getValue(i,"RECV_DT")+"|");
						out.print(rEntity.getValue(i,"INSP_SPOT_NM")+"|");
						out.print(rEntity.getValue(i,"INSP_SPOT_ADDR")+"|");
						out.print(rEntity.getValue(i,"INSP_SPOT_DETAILADDR")+"|");
						out.print(rEntity.getValue(i,"NM")+"|");
						out.print(rEntity.getValue(i,"WORK_ITEM")+"|\n"); 
				}
				out.print("END");
			}else{
				out.print("LOG1|");
				out.print("END");
			}}
%>