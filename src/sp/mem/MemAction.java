package sp.mem; 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.action.KJFAction;
import sp.mem.cmd.MemCCReRegCmd;
import sp.mem.cmd.MemCCReRegOKCmd;
import sp.mem.cmd.MemIDSearchCmd;
import sp.mem.cmd.MemInfoUOKCmd;
import sp.mem.cmd.MemInfoVCmd;
import sp.mem.cmd.MemMobileRegCmd;
import sp.mem.cmd.MemMobileRegOKCmd;
import sp.mem.cmd.MemMobileTimeOverCmd;
import sp.mem.cmd.MemPWSearchCmd;
import sp.mem.cmd.MemPasswdChangeCmd;
import sp.mem.cmd.MemPasswdChangeOKCmd;
import sp.mem.cmd.MemPubResNumChangeCmd;
import sp.mem.cmd.MemRegBusinessCmd;
import sp.mem.cmd.MemRegCUDCmd;
import sp.mem.cmd.MemRegDuplCmd;
import sp.mem.cmd.MemRegPrivateCmd;
import sp.mem.cmd.MemRegSuccessCmd;

/**
 * <p>Title       : ȸ������ Action</p>
 * <p>Description : ȸ������ ó���� �Ѵ�.</p>
 * <p>Copyright   : Copyright(c) 2007 PKT. All rights reserved.</p>
 * 
 * NOTE : ȸ������ ó���� �Ѵ�. 
 * 
 * @version 1.0
 * @author  PKT
 */
public class MemAction extends KJFAction{
	
    /** ������� class�� */
	private HashMap<String, KJFCommand> commands;
	
    /**
     * ����Command���� �����Ѵ�.
     * @param ActionMapping mapping,
     *         ActionForm form,
     *         HttpServletRequest request,
     *         HttpServletResponse response
     * @return  ActionForward    
     */
	public String executeAction(ActionMapping mapping,
							            ActionForm form,
							            HttpServletRequest request,
							            HttpServletResponse response) throws Exception{
					
			initCommands();		
            KJFCommand cmd = lookupCommand(request.getParameter("cmd"));         
	
	        return cmd.execute(request, form) ; 
	} 
	
	
    /**
     * Command���� ����Ѵ�.
     * @param 
     * @return       
     */
    private void initCommands(){

        commands = new HashMap<String, KJFCommand>();
      
        // ȸ������
        commands.put("MemRegPrivate",       new MemRegPrivateCmd());        // ���� ȸ������ ��� ȭ��
        commands.put("MemRegBusiness",      new MemRegBusinessCmd());       // ��� ȸ������ ��� ó��
        
        commands.put("MemRegPriC",          new MemRegCUDCmd());            // ���� ȸ�� ���� ó��
        commands.put("MemRegBusC",          new MemRegCUDCmd());            // ��� ȸ�� ���� ó��        
        commands.put("MemRegDupl",          new MemRegDuplCmd());           // ȸ�����Ե� ����
        commands.put("MemRegSuccess",       new MemRegSuccessCmd());        // ȸ������ �Ϸ�
        
        commands.put("MemIDSearch",         new MemIDSearchCmd());          // ���̵� ã��
        commands.put("MemPWSearch",         new MemPWSearchCmd());          // ��й�ȣ ã��
        
        commands.put("MemPasswdChange",     new MemPasswdChangeCmd());      // ��й�ȣ ����
        commands.put("MemPasswdChangeU",    new MemRegCUDCmd());            // ��й�ȣ ����
        commands.put("MemPasswdChangeOK",   new MemPasswdChangeOKCmd());    // ��й�ȣ ���� �Ϸ�
        
        commands.put("MemMobileReg",        new MemMobileRegCmd());         // ����ϼ���
        commands.put("MemMobileRegU",       new MemRegCUDCmd());            // ����ϼ��� ��� 
        commands.put("MemMobileRegOK",      new MemMobileRegOKCmd());       // ����� ���� ��� �Ϸ�
        commands.put("MemMobileTimeOver",   new MemMobileTimeOverCmd());    // ����� �ð�����
        
        commands.put("MemCCReReg",          new MemCCReRegCmd());      	    // �������� ���ϵ��ȭ��
        commands.put("MemCCReRegU",         new MemRegCUDCmd());      	    // �������� ���� ó��
        commands.put("MemCCReRegOK",        new MemCCReRegOKCmd());         // �������� ���� ó��
               
        commands.put("MemInfoV",            new MemInfoVCmd());             // ȸ������ ����         
        commands.put("MemInfoU",            new MemRegCUDCmd());            // ȸ������ ����
        commands.put("MemInfoUOK",          new MemInfoUOKCmd());           // ȸ������ ���� �Ϸ� 
        
        commands.put("MemInfoD",            new MemRegCUDCmd());            // ȸ��Ż�� ó��
        
        commands.put("MemPubResNumU",       new MemPubResNumChangeCmd());   // �������ü ��Ͽ��� Ȯ��ó��
    }	
	
	
    /**
     * ��û���� ����� Command instance�� ���Ѵ�.
     * @param cmd ��ɸ�
     * @return Command class instance
     */
    private KJFCommand lookupCommand(String cmd) throws Exception {
        
        if (commands.containsKey(cmd)) {
            return (KJFCommand)commands.get(cmd);
            
        } else {
            throw new Exception("Invalid Command Identifier");
        }
    }
}
