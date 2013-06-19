package kjf.util;

import kjf.ops.ReportEntity;

import java.util.List;

/**
 * <p>Subsystem     :  </p>
 * <p>Title         : 기본적인 그리드 유틸함수 </p>
 * <p>Description   : 기본적인 그리드 유틸함수를 구현하여 제공. </p>
 * <p>관련 TABLE      : </p>
 * @author 김경덕
 * @version 1.0 2003.05.29 <br/>
 */
public class KJFGrid {
	
	private ReportEntity rEntity;
	private String columTitle[];	        
	private String colum[]; 	         
	private String columWidth[]; 
	private String columAlign[]; 	
	private String objName;
	private String gridHeight;	
	private List fieldName;
	private List fieldValue;	
	
	public KJFGrid(	ReportEntity arg_rEntity, 
					String arg_totaColum[],
					String arg_objName,
					String arg_gridHeight ) {
		
		
		rEntity = arg_rEntity;
		
		columTitle = new String[arg_totaColum.length];	        
		colum 		= new String[arg_totaColum.length];         
		columWidth = new String[arg_totaColum.length];
		columAlign = new String[arg_totaColum.length];
		
		objName=arg_objName;
		gridHeight =arg_gridHeight;

		fieldName = arg_rEntity.getFieldName();
		fieldValue = arg_rEntity.getFieldValue();
		
		try{
		for(int i=0; i< arg_totaColum.length ; i++){
			String ls_tmp[] = KJFUtil.str2strs(arg_totaColum[i],"|");
			columTitle[i]=ls_tmp[0];
			colum[i]= ls_tmp[1];
			columWidth[i]= "";
			columAlign[i]= "";			
			if(ls_tmp.length >2){
				String flag = ls_tmp[2].substring(0,2);
				String value= ls_tmp[2].substring(2, ls_tmp[2].length());
				if(flag.equalsIgnoreCase("w-")){
					columWidth[i]= value;
				}else if(flag.equalsIgnoreCase("a-")) {
					columAlign[i]= value;					
				}
			}//end if(ls_tmp.length ==3)

			if(ls_tmp.length >3){
				String flag = ls_tmp[3].substring(0,2);
				String value= ls_tmp[3].substring(2, ls_tmp[3].length());
				if(flag.equalsIgnoreCase("w-")){
					columWidth[i]= value;
				}else if(flag.equalsIgnoreCase("a-")) {
					columAlign[i]= value;					
				}
			}//end if(ls_tmp.length ==4)					
		}//for(int i=0; i< arg_totaColum.length ; i++)
		} catch(Exception e){
			e.printStackTrace();
		}
			
	}	
	
	public KJFGrid(	ReportEntity arg_rEntity, 
					String arg_columTitle[],
					String arg_colum[],
					String arg_columWidth[],
					String arg_columAlign[],
					String arg_objName,
					String arg_gridHeight) {
		
		rEntity = arg_rEntity;

		columTitle =arg_columTitle;
		colum =arg_colum;
		columWidth =arg_columWidth;
		columAlign =arg_columAlign;

		objName=arg_objName;
		gridHeight =arg_gridHeight;

		fieldName = arg_rEntity.getFieldName();
		fieldValue = arg_rEntity.getFieldValue();

	}
	
	
	public String toString() {
		
		StringBuffer rtnBuf = new StringBuffer();		
		StringBuffer columBuf = new StringBuffer();
		StringBuffer styleBuf = new StringBuffer();
		
		try {
			
			for(int j=0; j < colum.length ; j++){					
				columBuf.append("\""+KJFUtil.print(columTitle[j])+"\"");
				//colum width, align 
				styleBuf.append("#"+objName+" .dyn-column-"+j);
				styleBuf.append("{");
				if(!KJFUtil.isEmpty(columWidth[j]))styleBuf.append("width: "+KJFUtil.print(columWidth[j])+"px; ");
				if(!KJFUtil.isEmpty(columAlign[j]))styleBuf.append("text-align: "+KJFUtil.print(columAlign[j])+"; ");
				styleBuf.append("}\n");
				//colum width,align end	
				if(j+1 < colum.length) columBuf.append(",");
			}			
			
			int cnt =fieldName.size();
			Integer rowField[] = new Integer[cnt];
			if( fieldValue.size() > 0){
				for(int i=0;i<cnt;i++){
					for(int j=0; j < colum.length ; j++){		
						if(KJFUtil.print(colum[j]).equalsIgnoreCase(KJFUtil.print(rEntity.getName(i)))){
							rowField[j]= new Integer(i);
						}
					}
				}			
			}
			
			StringBuffer rowBuf = new StringBuffer();
			
			for(int j=0;j<fieldValue.size();j++){
			    List value = rEntity.getRow(j);  
				  cnt =	colum.length;
				  for(int k=0;k<cnt;k++){
					if(k==0) rowBuf.append("[");
					  rowBuf.append("\""+KJFUtil.print((String)value.get(rowField[k].intValue()))+"\"");
					if(k+1 < cnt) rowBuf.append(",");
					else rowBuf.append("]");
				  }
				  if (j+1 < fieldValue.size() )rowBuf.append(", ");
				  rowBuf.append("\n");
			} 	
			
		      rtnBuf.append("\r\n\r\n");
		      rtnBuf.append("<style>\r\n#");
		      rtnBuf.append(objName);
		      rtnBuf.append(" { height: "+gridHeight+"px; border: 2px inset; background: white}\r\n#");
		      rtnBuf.append(objName);
		      rtnBuf.append(" .dyn-controls-grid {height: 100%; font-family: \"Verdana\"; font-size: 11px;}\r\n");
		      rtnBuf.append(styleBuf.toString());
		      rtnBuf.append("\r\n.dyn-grid-column {border-right: 1px solid threedlightshadow;}\r\n.dyn-grid-row {border-bottom: 1px solid threedlightshadow;}\r\n\r\n");
		      rtnBuf.append("</style>\r\n");
		      rtnBuf.append("<script>\r\n\r\n\tvar gr_"+objName+"_head = [");
		      rtnBuf.append(columBuf.toString());
		      rtnBuf.append("];\r\n\tvar gr_"+objName+"_rowData = [\r\n\t");
		      rtnBuf.append(rowBuf.toString());
		      rtnBuf.append("\r\n\t];\r\n\r\n\tvar "+objName+" = new JsGrid.Controls.Grid;\r\n    "+objName+".setId(\"");
		      rtnBuf.append(objName);
		      rtnBuf.append("\");\r\n\t"+objName+".setRowProperty(\"count\", ");
		      rtnBuf.append(fieldValue.size());
		      rtnBuf.append(");\r\n\t"+objName+".setColumnProperty(\"count\", ");
		      rtnBuf.append(columTitle.length);
		      rtnBuf.append(");\r\n\r\n\t"+objName+".setColumnProperty(\"texts\" , gr_"+objName+"_head );\r\n\t"+objName+".setColumnProperty(\"tooltip\", function(i){return gr_"+objName+"_head[i]});\r\n\r\n\t"+objName+".setColumnHeaderHeight(\"23px\");\r\n\t"+objName+".setRowHeaderWidth(\"0px\");\r\n\r\n\t"+objName+".setDataProperty(\"text\", function(i, j){return gr_"+objName+"_rowData[i][j]});\r\n\r\n ");
		      rtnBuf.append("document.write("+objName+");\r\n    ");
		      rtnBuf.append("</script>\r\n\r\n\r\n\r\n        ");
		
		} catch(Exception e){
			e.printStackTrace();
		}		

		return rtnBuf.toString();
	}  
}
