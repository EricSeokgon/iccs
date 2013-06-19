/**
* File name   : kjs.js 
* @author     :����
* @Version    :1.4
*
* @History
* 2002.11.? �ʱ�����   ����
* 2003.09.18 kjsOnlyCheck �߰�
* 2004.03.11 ������
* 2005.04.07 �߰�
* 2005.04.14 envetnKeeper ����..
* 2006.08.09 ��� �߰�
* 2006.09.19 �ʼ� �Է� ��׶�� Į�� �߰�
* 2006.12.13 money ���� stripMask �� ó�� �ȵǴ� ���� ����(���ν�)
* 
* ����
*       - required, required="�����޼���" , slct_required , moveFocus ='focus�� �̵��� obj name'                           
*         �ʵ带 �ʼ��Է����� �����.        
*                                                                                              
*       - mask="999-999",  stripMask(mask�� ���� ��쿡�� ���= submit �� ����ũ�� Ǯ���鼭 ���� �Ѿ��. 
*          onkeyup="ee_on_keyup(this)"  ���� ���
*                                                                                                                  
*       - minlength ="7"                                                                       
*         �ּ��ڸ��� �̻� �Է�   
*                                                         
*       - date, date='9999-99-99',   dateErrMsg='���� �޼���'                                                   
*          ���ڸ� �Է��ϴ� �ʵ��� ��� ����Ѵ�.                                      
*        
*       - isLower='�񱳵� obj name'  isLowerErrMsg='���� �޼���'
*          �񱳵Ǵ� obj value������ ũ�� ���� �޼����� ����Ѵ�. 
*
*       - isHigher='�񱳵� obj name'  isHigherErrMsg='���� �޼���'
*          �񱳵Ǵ� obj value������  ������ ���� �޼����� ����Ѵ�.
*
*       - isSame='�񱳵� obj name'  isSameErrMsg='���� �޼���'
*          �񱳵Ǵ� obj value���� ���� ������ ���� �޼����� ����Ѵ�.
*
*       - upper   onkeyup="ee_on_keyup(this)"  ���� ���
*         �Էµ� value�� �빮�ڷ� �ٲ۴�.
*
*       - lower    onkeyup="ee_on_keyup(this)"  ���� ���
*         �Էµ� value�� �ҹ��ڷ� �ٲ۴�.
*
*       - isImage,   isImageErrMsg='���� �޼���'
*         file �� �ʵ忡�� �̹�������, jpg,gif ����üũ
*
*       - number  onkeyup="ee_on_keyup(this)"  ���� ���
*         ���ڸ� �Է°����ϴ�.
*
*       - entSubmit='form Name' onkeyup ="ee_on_keyup(this)"
*         �ؽ�Ʈ �ڽ����� ���͸� ��������� �ع� ���� submit ��Ų��.
*
*       - envetnKeeper ����
*          ����� ����
*           1. mask, date , number,upper, lower, money �ϰ�� onkeyup="ee_on_keyup(this)"
*           2. entSubmit �ϰ��     onkeypress ="ee_on_keypress(this)"
* 
*       - money      onkeyup="ee_on_keyup(this)"  ���� ���
*         �ݾ� ǥ��. ex 3,000
* 
*       - email
* 		  �̸��� üũ 
* 
*       -jumin, jumin='2��° �ֹ� ��ȣ ���ڸ�  obj name'
* 			1.�ϳ��� �ʵ�� �� ���ÿ��� mask='999999-9999999' �� �Բ� ���ش�.
*           2.�ΰ��� �ʵ�� ���ÿ��� �ֹ� ��ȣ ���ڸ� �ʵ� �̸��� �����ش�. 
* 
*       -csn, csn='2��° obj name, 3��° obj name'
*        ����� ��Ϲ�ȣ üũ
* 			1.�ϳ��� �ʵ�� �� ���ÿ��� mask='999-99-99999' �� �Բ� ���ش�.
*           2.3���� �ʵ�� ���ÿ��� ����� ��ȣ 2���� 3��° �ʵ� �̸��� �޸�(,)�� ���� �Ͽ� �����ش�. 
* 
*       - alpha   
* 			������ �Է� ���� 
* 
*       -  alpha_numbur   
*           ���� ���ڸ� �Է� ����
*/

// ���������� ����� ����
// ( ) [ ] { } < > " ' ` ~  $ ! # % ^ & @  , . ; :  \ / |  * = - ? ''
var KJS_DEFAULT_SPECIAL_CHAR  = /(\(|\)|\[|\]|\{|\}|\<|\>|\"|\'|\`|\~|\$|\!|\#|\%|\^|\&|\@|\,|\.|\;|\:|\\|\/|\||\*|\=|\-|\?|\s)*/g; 
var KJS_DEFAULT_BG_COLOR="#FFFFFF";
var KJS_ERROR_BG_COLOR="#ECE7F9";

function beforeKjs(){
    return true;
}
//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : kjsSubmit(form_name)
// ��  �� : �Է°� Submit�� ����üũ�� ���� ȣ��Ǵ� �Լ�
//          
function kjsSubmit(form_name){
//alert("fire_kjs����");
    eval("mForm=document."+form_name);
    mForm.initialize = fm_initialize;
    mForm.initialize();    
    mForm.beforeSubmit = em_beforeSubmit;
    if(mForm.beforeSubmit()){
        if(!beforeKjs())  return; //kjsó���� �����ؾ��ϴ� �Լ�
        mForm.submit();
    }

}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : kjsOnlyCheck(form_name)
// ��  �� : �Է°�  ����üũ�� ���� ȣ��Ǵ� �Լ�(submit�� ��Ű�� �ʴ´�.)
//          validation üũ�� �����̸� ture, ���и� false�� ��ȯ�Ѵ�.
function kjsOnlyCheck(form_name){
    eval("mForm=document."+form_name);
    mForm.initialize = fm_initialize;
    mForm.initialize();    
    mForm.beforeSubmit = em_beforeSubmit;
    if(mForm.beforeSubmit()){
        if(!beforeKjs())  return false; //kjsó���� �����ؾ��ϴ� �Լ�
        return true;
    }
    return false;
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : fm_initialize(form_name)
// �޼ҵ�� : element.initialize()
// ��  �� : ��ü element ��ü�� �ʱ�ȭ
//    
function fm_initialize(){
//alert("fm_initialize����");
    for(var idx=0; idx < this.elements.length ; idx++){
        this.elements[idx].initialize = em_initialize;
        this.elements[idx].initialize(this);
    }
    
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_initialize()
// �޼ҵ�� : element.initialize(parent)
// ��    �� : element�� method, attribute�� redefine�Ѵ�.
//
function em_initialize(parent){
    //alert("em_initialize ����");
    var sMsg;
    this.parent = parent;
    this.AKey = new Array;
    this.setMessage = em_set_message;
    this.lowering = em_lowering;
    this.uppering = em_uppering;
    this.masking = em_masking;
    this.unmasking = em_unmasking;
    this.isAttribute = em_is_attribute;  // this.getAttribute(attr) != null
    this.keyFactory = em_key_factory; //���� element �ʱ�ȭ
    this.validate = em_validate;
    this.validate_display = em_validate_display;    
    

    this.keyFactory();
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_is_attribute()
// �޼ҵ�� : element.isAttribute()
// ��    �� : element�� attribute �˾Ƴ���.
//
function em_is_attribute(attr){
    return ( this.getAttribute(attr) != null ) ? true : false;
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_set_message()
// �޼ҵ�� : element.setMessage()
// ��    �� : �����޼����� set
//
function em_set_message(sMsg){
    this.setAttribute("msg",sMsg);
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_beforeSubmit()
// �޼ҵ�� : element.beforeSubmit()
// ��  �� : submit���� ��ó��
// 
function em_beforeSubmit(){
//alert("em_beforeSubmit ����");

    for(var i=0; i< this.elements.length; i++){        
        if (!this.elements[i].validate_display()) return false;
    } 

    for(var i=0; i< this.elements.length; i++){        
        if (this.elements[i].isAttribute("stripMask")) this.elements[i].unmasking();
    }
    
    return true;
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_validate_display()
// �޼ҵ�� : element.validate_display()
// ��  �� : �� �Է� ���� ���� �Է� üũ�� display
//          
function em_validate_display(){
//alert("em_validate_display ����");
    if (this.validate()){
        this.style.backgroundColor = KJS_DEFAULT_BG_COLOR;
        return true;
    } else {
        alert(this.getAttribute("msg") );       
        if (this.isAttribute("moveFocus")){
            eval("target=this.parent."+this.getAttribute("moveFocus"));
            target.style.backgroundColor = KJS_ERROR_BG_COLOR;
            target.focus();
        }else{
        	this.style.backgroundColor = KJS_ERROR_BG_COLOR;
            this.focus();
        }

        return false;
    }
}


//////////////////////////////////////////////////////////////////////////////////
// �޼ҵ�� : em_masking
// ���� : �Ϲ����� masking�� ����Ѵ�.
//
function em_masking(){

    var sStr = this.value.replace( KJS_DEFAULT_SPECIAL_CHAR ,"");
    var tStr="";
    var i;
    var j=0; 

    for(i=0; i< sStr.length; i++){
     tStr += sStr.charAt(i);
     j++;
     if (j < this.mask.length && this.mask.charAt(j)!="9") tStr += this.mask.charAt(j++);
    }   
    this.value= tStr;
}

//////////////////////////////////////////////////////////////////////////////////
// �޼ҵ�� : em_unmasking
// ���� : submit �ϱ� �� ���������� �����Ѵ�.�Ϲ����� unmasking�� ����Ѵ�.
// 
function em_unmasking(){
//alert("����");
    if (!this.isAttribute("mask") && !this.isAttribute("money"))  return;   
    var sStr = this.value;
    var tStr="";
    var i;
    if(this.isAttribute("money")){
    	tStr = sStr.replace(/,/gi,"");
    }
    else{
	    for(i=0; i< sStr.length; i++){
	     if (this.mask.charAt(i)=="9")  tStr += sStr.charAt(i);
	    }
    }
   
    this.value= tStr;
//alert(this.value); 
}

//////////////////////////////////////////////////////////////////////////////////
// �޼ҵ�� : em_lowering
// ���� : submit �ϱ� �� ��� ���ڸ� �ҹ��ڷ� �ٲ۴�.
// 
function em_lowering(){
    this.value=this.value.toLowerCase();
}


//////////////////////////////////////////////////////////////////////////////////
// �޼ҵ�� : em_uppering
// ���� : submit �ϱ� �� ��� ���ڸ� �빮�ڷ� �ٲ۴�.
// 
function em_uppering(){
    this.value=this.value.toUpperCase();
}






//////////////////////////////////////////////////////////////////////////////////
// �޼ҵ�� : isNum()
// �Ķ���� : strnum : �˻��� ����
// �� �� �� : boolean(true / false)
// ��    �� : strnum�� �������� �˻��Ѵ�.
function isNum (strnum){
  return (strnum.toString() && !/\D/.test(strnum));
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_key_factory()
// �޼ҵ�� : element.keyFactory()
// ��    �� : element�� type�� �־��� attribute�� ���� �ʿ���
//            key�� ��� �����ϰ� �Ѵ�.
//
function em_key_factory(){
//alert("em_key_factory����");

    switch (this.type) {

    case "password" :
    case "text" :
        if ( this.isAttribute("required") )     this.AKey[0]="required";
        if ( this.isAttribute("minlength") )    this.AKey[1]="minlength";
        if ( this.isAttribute("mask") )         this.AKey[2]="mask";
        if ( this.isAttribute("date") )         this.AKey[3]="date";
        if ( this.isAttribute("isLower") )      this.AKey[4]="isLower";
        if ( this.isAttribute("isHigher") )     this.AKey[5]="isHigher";
        if ( this.isAttribute("lower") )        this.AKey[6]="lower";
        if ( this.isAttribute("upper") )        this.AKey[7]="upper";
        if ( this.isAttribute("isSame") )       this.AKey[8]="isSame";
        if ( this.isAttribute("number") )       this.AKey[9]="number";
		if ( this.isAttribute("float") )		this.AKey[9]="float";
        if ( this.isAttribute("entSubmit") )    this.AKey[10]="entSubmit";
        if ( this.isAttribute("money") )        this.AKey[11]="money";
        if ( this.isAttribute("email") )        this.AKey[12]="email";
        if ( this.isAttribute("jumin") )        this.AKey[13]="jumin";
        if ( this.isAttribute("csn") )          this.AKey[14]="csn";
        if ( this.isAttribute("alpha") )        this.AKey[15]="alpha";
        if ( this.isAttribute("alpha_number"))  this.AKey[16]="alpha_number";

        
        break;
    
    case "textarea" :
        if ( this.isAttribute("required") )     this.AKey[0]="required";

        break;

    case "checkbox" :

    case "file" :
        if ( this.isAttribute("required") )     this.AKey[0]="required";
        if ( this.isAttribute("isImage") )      this.AKey[1]="isImage";
        break;

    case "select-one" :
        if ( this.value=="slct_required" )      this.AKey[0]="slct_required";
        break;

    default :
        break;
    }    
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_validate()
// �޼ҵ�� : element.validate()
// ��  �� : �� �Է� ���� ���� �Է� üũ
//          Select�� �ʼ��Է¿��θ��� �˻��Ѵ�.
function em_validate(){
//alert("em_validate()����");

    for (var i=0;i < this.AKey.length; i++){
        switch(this.AKey[i]){
        case "required" :
            this.xo_required = ex_required;
            this.ikey="required";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_required())    return false;
            break;

        case "slct_required" :
            this.xo_slct_required = ex_slct_required;
            this.ikey="slct_required";
            if (!this.xo_slct_required())    return false;
            break;

        case "minlength" :
            this.xo_minlength = ex_minlength;
            this.ikey="minlength";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_minlength())    return false;
            break;

        case "mask" :
            this.masking();
            break;

        case "date" :
            this.xo_date = ex_date;
            this.ikey="date";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_date())    return false;
            break;

        case "isLower" :
            this.xo_isLower = ex_is_lower;
            this.ikey="isLower";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_isLower())    return false;
            break;

        case "isHigher" :
            this.xo_isHigher = ex_is_higher;
            this.ikey="isHigher";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_isHigher())    return false;
            break;

        case "lower" :
            this.lowering();
            break;

        case "upper" :
            this.uppering();
            break;

        case "isSame" :
            this.xo_isSame = ex_is_same;
            this.ikey="isSame";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_isSame())    return false;
            break;

        case "isImage" :
            this.xo_isImage = ex_is_image;
            this.ikey="isImage";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_isImage())    return false;
            break;

        case "number" :
            this.xo_number = ex_number;
            this.ikey="number";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_number())    return false;
            break;

        case "money" :        
            this.money = em_money_masking;
            this.money();
            this.xo_numrep = ex_numrep;
            this.filter = /,/g;
            if (!this.xo_numrep())    return false;            
            break;
            
        case "email" :        
            this.xo_email = ex_email;
            if (!this.xo_email())    return false;            
            break;
            
        case "jumin" :
            this.xo_jumin = ex_jumin;
            this.ikey="jumin";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_jumin())    return false;
            break;
            
        case "csn" :
            this.xo_csn = ex_csn;
            this.ikey="csn";
            this.ikey_value = this.getAttribute(this.ikey);
            if (!this.xo_csn())    return false;
            break; 
            
        case "alpha" :        
            this.xo_alpha = ex_alpha
            if (!this.xo_alpha())    return false;            
            break; 
            
        case "alpha_number" :        
            this.xo_alpha_number = ex_alpha_number
            if (!this.xo_alpha_number())    return false;            
            break;                                                           

        }//end  switch(this.AKey)                                              
    }
    return true;
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_alpha_number()
// ��  �� : ���� ���� �Է��� üũ �Ѵ�. 
// ����: ex) alpha_number  
//
function ex_alpha_number()
{

  var flag = true; 
  var msg = "";

  if (!this.parent.value) return flag; //�Է°� ���� ���� Pass

  var tsTarget = this.parent.value;

  var regExp_alpha_numeric = /^[a-zA-Z0-9]+$/;

  if(!regExp_alpha_numeric.test(tsTarget)) {
    flag = false;
    msg = "����  Ȥ�� ���ڸ� �Է� ���� �մϴ�.";
    this.setMessage(msg);
  }

  return flag;

}
//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_alpha()
// ��  �� : ���� �Է��� üũ �Ѵ�. 
// ����: ex) alpha  
//
function ex_alpha()
{

  var flag = true; 
  var msg = "";

  if (!this.value) return flag; //�Է°� ���� ���� Pass

  var tsTarget = this.value;

  var regExp_alphabetic = /^[a-zA-Z]+$/;
  if(!regExp_alphabetic.test(tsTarget)) {
    flag = false;
    msg = "������ �Է� �����մϴ�.";
    this.setMessage(msg);
  }

  return flag;

}
//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_csn()
// ��  �� : �ֹ� ��ȣ üũ 
// ����: ex) csn, csn='2��° obj name, 3��° obj name'  
//         
function ex_csn(){
//alert("ex_csn ����");
    
  var flag = true; 
  var msg = "";
  var sum = 0;

  if (!this.value) return flag; //�Է°��� ���� ���� Pass
  
  var csNumber;
  alert(this.ikey_value);
  
  if(this.ikey_value) {
  	var tmp = this.ikey_value.split(",");	
  	eval("target2=this.parent."+tmp[0]);
  	eval("target3=this.parent."+tmp[1]);
  	csNumber = this.value + target2.value+ target3.value;

  }else {
  	csNumber = this.value.replace(/(\,|\.|\-|\/)/g,"");
  }
  	
  var checkArray = new Array(1,3,7,1,3,7,1,3,5);

  for(idx=0 ; idx < 9 ; idx++)
    sum += csNumber.charAt(idx) * checkArray[idx];

  sum = sum + ((csNumber.charAt(8) * 5 ) / 10);

  var nam = Math.floor(sum) % 10;

  var checkDigit = ( nam == 0 ) ? 0 : 10 - nam;

  if ( !isNum(csNumber) || csNumber.charAt(9) != checkDigit)  {
    flag = false;
    msg = "�Է��� ����� ��Ϲ�ȣ�� �߸��Ǿ����ϴ�.";
    this.setMessage(msg);
  }

  return flag;

  return flag;
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_jumin()
// ��  �� : �ֹ� ��ȣ üũ 
// ����: ex) jumin, jumin='2��° �ֹ� ��ȣ  obj name'  
//         
function ex_jumin(){
//alert("ex_jumin ����");

  var flag = true;
  var msg = "";
  var sum = 0;

  if (!this.value) return flag; //�Է°��� ���� ���� Pass

  var psNumber;

  if(this.ikey_value) {
  	eval("target=this.parent."+this.ikey_value);
  	psNumber = this.value + target.value;

  }else {
  	psNumber = this.value.replace(/(\,|\.|\-|\/)/g,"");
  }

  for (idx = 0, jdx=2; jdx < 10; idx++, jdx++) {
    sum = sum + ( psNumber.charAt(idx) * jdx );
  }

  for (idx = 8, jdx=2; jdx < 6; idx++, jdx++) {
    sum = sum + ( psNumber.charAt(idx) * jdx );
  }

  var nam = sum % 11;
  var checkDigit = 11 - nam ;

  if ( !isNum(psNumber) || psNumber.charAt(12) != checkDigit)  {
    flag = false;
    msg = "�Է��� �ֹε�Ϲ�ȣ�� �߸��Ǿ����ϴ�.";
    this.setMessage(msg);
  }

  return flag;
}
//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_email()
// ��  �� : email üũ
// ����: email
// 
function ex_email()
{
  //this core script from http://tech.irt.org/articles/js049/index.htm
  //customize by TKshin.

  var flag = true; //true when validation successful.
  var msg = "";

  if (!this.value) return flag; //�Է°� ���� ���� Pass

  var tsTarget = this.value;
  var regExpEmail = /^\w+((-|\.)\w+)*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]{2,4}$/;

  if(!regExpEmail.test(tsTarget)) {
    flag = false;
    msg = "�̸��� ������ �ƴմϴ�.";
    this.setMessage(msg);
  }

  return flag;

}
//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_money_masking()
// ��  �� : money�� ���õ� masking�� ���
// ����: money
// 
function em_money_masking() {

  var sMoney = this.value.replace(/,/g,"");
  var tMoney = "";
  var i;
  var j=0;
  var tLen =sMoney.length;

  if (tLen <= 3 ) return ;

  for(i=0;i<tLen;i++){
    if (i!=0 && ( i % 3 == tLen % 3) ) tMoney += ",";
    if(i < tLen ) tMoney += sMoney.charAt(i);
  }

  this.value = tMoney;
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_numrep()
// ��  �� : Ư�� ���͸� ������ ���� ���θ� üũ �Ѵ�.
// ����: 
//
function ex_numrep() {

  var sVal = this.value.replace(this.filter,"");
  sMsg ="���ڸ� �Է°����մϴ�.";
    if (!sVal) return true;
    if (!isNum(sVal)){
        this.setMessage(sMsg);
        return false;
    }
        
    return true;
}
//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_required()
// ��  �� : �ʼ��Է�üũ
// ����: required, required="message"
//          
function ex_required(){
sMsg ="�ʼ��Է��Դϴ�";
    var regExp_whiteSpace = /^[\s]*$/;
    if (regExp_whiteSpace.test(this.value)){
        if (this.ikey_value)    sMsg=this.ikey_value;
        this.setMessage(sMsg);
        this.value = this.value.replace( /(\s)/g,"");
        return false;
    }        
    return true;
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_slct_required()
// ��  �� : select box �ʼ�����üũ
// ����: option value="slct_required"
//          
function ex_slct_required(){
//alert("ex_slct_required ����");
    sMsg ="�ʼ� �����Դϴ�";
    this.setMessage(sMsg);
    return false;
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_minlength()
// ��  �� : �ּұ��� üũ
// ����: ex)minlength="7"
//         
function ex_minlength(){
//alert("ex_minlength ����");
    if (!this.value) return true; //�Է°� ���� ���� Pass
    var tsTarget = this.value;
    //Validation Logic for Min Legnth..
    if (tsTarget.length <  this.ikey_value ) {
        sMsg ="�Էµ� �׸��� �ڸ����� �ʹ� �۽��ϴ�. \n�ּ� " + this.ikey_value + "�ڸ��̻��Դϴ�.";
        this.setMessage(sMsg);
        return false;
    }
    return true;
}



//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_date()
// ��    �� :��¥ ����üũ üũ
// ���� :date=>yyyymmdd ����, date='9999-99-99'=> 'yyyy-mm-dd' �������� ��ȯ
function ex_date(){
    
    var flag = true;    
    if (!this.value) return flag; //�Է°��� ���� ���� Pass

    //Validation Logic for Date..
    var iYear = null;
    var iMonth = null;
    var iDay = null;
    var iDaysInMonth = null;

    var sDate=this.value.replace(/(\,|\.|\-|\/)/g,"");
    var sFormat="YYYYMMDD";  //�������� YYYYMMDD�� ���¸� �����Ѵ�. --;
    var aDaysInMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);

    //������ ��¥�� �Է��� ���� ����̴�.
    if ( sDate.length != 8 ) flag = false ;

    if (flag) {
    iYear  = eval(sDate.substr(0,4));
    iMonth = eval(sDate.substr(4,2));
    iDay   = eval(sDate.substr(6,2));
    if (!isNum(iYear) || !isNum(iMonth) || !isNum(iDay) )
      flag = false ;
    }

    if (flag) {
     iDaysInMonth = (iMonth != 2) ? aDaysInMonth[iMonth-1] : (( iYear%4 == 0 && iYear%100 != 0 || iYear % 400==0 ) ? 29 : 28 );
     if( iDay==null || iMonth==null || iYear==null  || iMonth > 13 || iMonth < 1 || iDay < 1 || iDay > iDaysInMonth )
      flag = false ;
    }

    if (!flag) {
    sMsg = "��¥�Է��� �߸��Ǿ����ϴ�.";
    if ( this.isAttribute("dateErrMsg") )     sMsg=this.dateErrMsg;
    this.setMessage(sMsg);
    }

    if ( flag && this.ikey_value){
        if (flag) this.value=this.value.replace(/(\,|\.|\-|\/)/g,"");
        this.mask=this.ikey_value;
        this.masking();  //���������� ������.. �������Ѵ�..
    }

    return flag;
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_is_lower()
// ��  �� : ������ �ʵ��� ���� ���Ͽ� �񱳵Ǵ� ������ ������ true�� ���� 
// ����: ex)isLower='�񱳵� obj name'  isLowerErrMsg='���� �޼���'
//         
function ex_is_lower(){
//alert("ex_is_lower ����");
    sMsg ="�Էµ� ���� �񱳵Ǵ� ������ Ŭ�� �����ϴ�.";
    if (!this.value) return true; //�Է°� ���� ���� Pass
    var srcValue = this.value.replace(KJS_DEFAULT_SPECIAL_CHAR,"");
    eval("target=this.parent."+this.ikey_value);
    var tgValue= target.value;
    tgValue = tgValue.replace(KJS_DEFAULT_SPECIAL_CHAR,"");

    if (!(tgValue=="" || tgValue==null)){
        if ( srcValue > tgValue ) {
            if ( this.isAttribute("isLowerErrMsg") )     sMsg=this.isLowerErrMsg;
            this.setMessage(sMsg);
            return false;
        }
    }

    return true;
}



//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_is_higher()
// ��  �� : ������ �ʵ��� ���� ���Ͽ� �񱳵Ǵ� ������ ũ�� true�� ���� 
// ����: ex)isHigher='�񱳵� obj name'  isHigherErrMsg='���� �޼���'
//         
function ex_is_higher(){
//alert("ex_is_higher ����");
    sMsg ="�Էµ� ���� �񱳵Ǵ� ������ ������ �����ϴ�.";
    if (!this.value) return true; //�Է°� ���� ���� Pass
    var srcValue = this.value.replace(KJS_DEFAULT_SPECIAL_CHAR,"");
    eval("target=this.parent."+this.ikey_value);
    var tgValue= target.value;
    tgValue = tgValue.replace(KJS_DEFAULT_SPECIAL_CHAR,"");

    if (!(tgValue=="" || tgValue==null)){
        if ( srcValue < tgValue ) {
            if ( this.isAttribute("isHigherErrMsg") )     sMsg=this.isHigherErrMsg;
            this.setMessage(sMsg);
            return false;
        }
    }

    return true;
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_is_same()
// ��  �� : ������ �ʵ��� ���� ���Ͽ� �񱳵Ǵ� ���� ������ true�� ���� 
// ����: ex)isSame='�񱳵� obj name'  isSameErrMsg='���� �޼���'
//         
function ex_is_same(){
//alert("ex_is_same ����");
    sMsg ="�Էµ� ���� �񱳵Ǵ� ���� ���� �ʽ��ϴ�.";
    if (!this.value) return true; //�Է°� ���� ���� Pass
    var srcValue = this.value.replace(KJS_DEFAULT_SPECIAL_CHAR,"");
    eval("target=this.parent."+this.ikey_value);
    var tgValue= target.value;
    tgValue = tgValue.replace(KJS_DEFAULT_SPECIAL_CHAR,"");

    if (!(tgValue=="" || tgValue==null)){
        if ( srcValue != tgValue ) {
            if ( this.isAttribute("isSameErrMsg") )     sMsg=this.isSameErrMsg;
            this.setMessage(sMsg);
            return false;
        }
    }

    return true;
}

function CheckImageFile(obj){
  var ImageFile = obj.value;
  var extFile = ImageFile.split("\\");
  var ImgInfo = extFile[extFile.length-1];
  var ext = ImgInfo.split(".");
    if (ext[1].toUpperCase() == "JPG" || ext[1].toUpperCase() == "GIF")
      return true;
    else
      return false;
  
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_is_image()
// ��  �� : file �� �ʵ忡�� jpg,gif ����üũ
// ����: ex)isImage,   isImageErrMsg='���� �޼���'
//         
function ex_is_image(){
//alert("ex_is_image ����");
    sMsg ="�̹��� ������ gif,jpg, jpeg, png �� �����մϴ�!";
    if (!this.value) return true; //�Է°� ���� ���� Pass

    var ImageFile = this.value;
    var extFile = ImageFile.split("\\");
    var ImgInfo = extFile[extFile.length-1];
    var ext = ImgInfo.split(".");
    if (ext[1].toUpperCase() == "JPG" || ext[1].toUpperCase() == "GIF"
        || ext[1].toUpperCase() == "JPEG" || ext[1].toUpperCase() == "PNG"){
      return true;
    }else{
            if ( this.isAttribute("isImageErrMsg") )     sMsg=this.isImageErrMsg;
            this.setMessage(sMsg);
      return false;
    }


}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ex_number()
// ��  �� : �����Է�üũ
// ����: required, required="message"
//          
function ex_number(){
sMsg ="���ڸ� �Է°����մϴ�.";
    if (!this.value) return true;
    if (!isNum(this.value)){
        this.setMessage(sMsg);
        return false;
    }
        
    return true;
}





//////////////////////////////////////////////////////////////����-�̺�Ʈ ���� ��ũ��Ʈ/////////////////////////////////////////////






//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : em_kek_initialize()
// �޼ҵ�� : element.initialize(parent)
// ��    �� : element�� method, attribute, event�� redefine�Ѵ�.
//
function em_kek_initialize(){
//alert("em_kek_initialize ����-3");
    var sMsg; 
    this.AKey = new Array;
    this.setMessage = em_set_message;
    this.keyupMasking = ee_keyup_masking;
    this.isAttribute = em_is_attribute;  // this.getAttribute(attr) != null
    this.keyFactory = em_key_factory; //���� element �ʱ�ȭ
    this.enterSubmit = ee_enter_submit;
    
    this.keyFactory();
}





//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ee_on_keyup()
// �޼ҵ�� : element.keyup(parent)
// ��    �� : element�� onkeyup�� event�� �����Ѵ�.
//
function ee_on_keyup(obj){

    obj.initialize = em_kek_initialize;
    obj.initialize();

    for (var i=0;i < obj.AKey.length; i++){
        switch(obj.AKey[i]){
        case "mask" :
            obj.ikey="mask";
            obj.filter=KJS_DEFAULT_SPECIAL_CHAR;
            obj.keyupMasking();
            break ;
        case "date" :
            obj.keyupDate = ee_keyup_date;
            obj.ikey="date";
            obj.ikey_value = obj.getAttribute(obj.ikey);
            obj.keyupDate();
            break ;

        case "number" :
            obj.keyupNumber = ee_keyup_number;
            obj.keyupNumber();
            break ;

        case "entSubmit" :
            obj.ikey="entSubmit";
            obj.ikey_value = obj.getAttribute(obj.ikey);
            obj.enterSubmit();
            break ;
            
            
        case "lower" :
            obj.lower= em_lowering;
            obj.lower();
            break ; 
            
        case "upper" :
            obj.upper= em_uppering;
            obj.upper();
            break ;
                                   
        case "money" :
            obj.keyupNumber = ee_keyup_number;
            obj.keyupNumber();
            obj.money = em_money_masking;
            obj.money();
            break;

		case "float" :
            obj.keyupNumber = ee_keyup_float;
            obj.keyupNumber();
            break ;
        }//end  switch(obj.AKey)                                              
    }


}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : skipKeyCode()
// ��    �� : skip�� keyCode check
//
function skipKeyCode(keyCode) {
    if (keyCode == 8 || keyCode == 9 || keyCode == 35 || keyCode == 36 ||
        keyCode == 37 || keyCode == 39 || keyCode == 46) {
        return true;
    } else {
        return false;
    }
}

//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ee_keyup_masking()
// �޼ҵ�� : element.keyupMasking(parent)
// ��    �� : element�� mask attribute�� onkeyup�� event�� �����Ѵ�.
//
function ee_keyup_masking(){

    var mask = this.getAttribute(this.ikey);
    var sMask = mask.replace( KJS_DEFAULT_SPECIAL_CHAR ,"");//���������� �����.
    if (skipKeyCode(event.keyCode)) return; //�ʿ��� Ű�� �ǳʶڴ�.
    if (!this.value) return;
    this.value = this.value.replace(this.filter , ""); //���ڸ� �Է°���
    this.value = this.value.replace(/([^0-9])/g, ""); //���ڸ� �Է°���

    if (this.value.length < 1) return;
    var tStr = "";
    var j=0; 
    for (var i = 0; i < this.value.length; i++) {
        if (i > sMask.length-1) { //�������ı��̺��� ��� ������.
            break;
        }
        tStr += this.value.charAt(i);
        j++;
        if (j < mask.length && mask.charAt(j)!="9") tStr += mask.charAt(j++);
        
    }
    this.value = tStr;
}



//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ee_keyup_date()
// �޼ҵ�� : element.keyupDate(parent)
// ��    �� : element�� date attribute�� onkeyup�� event�� �����Ѵ�.
//
function ee_keyup_date(){
    this.filter = /([^0-9])/g ; //���ڿ��� ����- ���ڸ� �Է°���
    if (this.ikey_value)    this.mask=this.ikey_value;        
    this.keyupMasking();
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ee_keyup_number()
// �޼ҵ�� : element.keyupNumber(parent)
// ��    �� : element�� number attribute�� onkeyup�� event�� �����Ѵ�.
//
function ee_keyup_number(){
    this.filter = /([^0-9])/g ; //���ڿ��� ����- ���ڸ� �Է°���
    
    if (skipKeyCode(event.keyCode)) return; //�ʿ��� Ű�� �ǳʶڴ�.
    if (!this.value) return;
    this.value = this.value.replace(this.filter , ""); //���ڸ� �Է°���
    this.focus();
}


//////////////////////////////////////////////////////////////////////////////////
// �Լ��� : ee_enter_submit()
// �޼ҵ�� : element.enterSubmit(parent)
// ��    �� : 
//
function ee_enter_submit(){
    
    if (event.keyCode==13)  kjsSubmit(this.ikey_value);

}

function ee_keyup_float(){
    this.filter = /([^.0-9])/g ; //���ڿ��� ����- ���ڸ� �Է°���
    
    if (skipKeyCode(event.keyCode)) return; //�ʿ��� Ű�� �ǳʶڴ�.
    if (!this.value) return;
    this.value = this.value.replace(this.filter , ""); //���ڸ� �Է°���
	if ((this.value.split(".")).length > 2)	this.value = this.value.substring(0,this.value.lastIndexOf("."));

    this.focus();
}