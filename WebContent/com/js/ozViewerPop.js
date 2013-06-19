var ozFramePop_width = 800;
var ozFramePop_height = 0;
//var ozServer = "localhost";
var ozServer = "127.0.0.1";
//var ozServer = "www.net.go.kr";
document.writeln('<iframe id="ozFramePop" width="' + ozFramePop_width + '" height="' + ozFramePop_height + '" frameborder=0 scrolling=no marginwidht=0 marginheight=0></iframe>');
document.writeln('<iframe name="ozLog" width="0" height="0" frameborder=0 scrolling=no marginwidht=0 marginheight=0></iframe>');

function ozViewerCreatePop(reportname,odinames,args){
	
	ozFramePop.document.writeln('<OBJECT  ID="ZTransferX" width = "0" height = "0" ');
	ozFramePop.document.writeln(' CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" ');
	ozFramePop.document.writeln(' codebase="http://' + ozServer + '/iccsOz/ozviewer/ZTransferX.cab#version=2,0,1,2">');
	ozFramePop.document.writeln('<PARAM NAME="download.Server" VALUE="http://' + ozServer + '/iccsOz/oz_iccsmviewer/">');
	ozFramePop.document.writeln('<PARAM NAME="download.Port" VALUE="80">');
	ozFramePop.document.writeln('<PARAM NAME="download.Instruction" VALUE="ozrviewer.idf">');
	ozFramePop.document.writeln('<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">');
	ozFramePop.document.writeln('<PARAM NAME="install.NameSpace" VALUE="oz">');
	ozFramePop.document.writeln('</OBJECT>');
	ozFramePop.document.writeln('');
	ozFramePop.document.writeln('<OBJECT id = "ozviewer1"');
	ozFramePop.document.writeln('   CLASSID="CLSID:64DA633F-E73B-4344-83BF-48483346CD53" width="' + ozFramePop_width + '" height="' + ozFramePop_height + '">');
	ozFramePop.document.writeln('   <param name="connection.servlet" value="http://' + ozServer + '/iccsOz/server">');
	ozFramePop.document.writeln('   <param name="viewer.configmode" value="html">');
	ozFramePop.document.writeln('   <param name="viewer.isframe" value="true">');
	ozFramePop.document.writeln('   <param name="viewer.framewidth" value="880">');
	ozFramePop.document.writeln('   <param name="viewer.frameheight" value="720">');
	ozFramePop.document.writeln('   <param name="viewer.framex" value="72">');
	ozFramePop.document.writeln('   <param name="viewer.framey" value="15">');
	ozFramePop.document.writeln('   <param name="viewer.zoom" value="130">');
	ozFramePop.document.writeln('   <param name="toolbar.save" value="false">');
	ozFramePop.document.writeln('   <param name="connection.reportname" value="' + reportname + '">');
	ozFramePop.document.writeln('   <param name="odi.odinames" value="' + odinames + '">');
	ozFramePop.document.writeln('   <param name="odi.' + odinames + '.pcount" value="' + args.length + '">');
	for(i=0;i<args.length;i++){
		j = i + 1;
		ozFramePop.document.writeln('   <param name="odi.' + odinames + '.args' + j + '" value="' + args[i] + '">');
	}	
	ozFramePop.document.writeln('</OBJECT>');	
	ozFramePop.document.close();
}

function ozViewerLog(oForm){
	var fm = oForm
	fm.target = "ozLog";
	fm.submit();
}