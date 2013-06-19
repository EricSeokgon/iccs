/*---------------------------------------------------------------------------------------------
@ 파일 이름: common_util.js
@ 파일 설명: 다음 기능 들을 모아놓은 js include 파일 ( UI 와 독립적인 기능들의 모음 )
 - validation check 기능
 - isNumeric, isHangul 등 데이터 체크 기능
 - popup 띄우기, popup resize 기능
@ 작성자: 김강열
  ---------------------------------------------------------------------------------------------*/
// for flicker err. by faustkim 2008.05.15
try { document.execCommand("BackgroundImageCache",false,true);} catch(err) {}

// Validation Check 에 관련된 UI 에 독립적인 함수들 모음  ###########################################################################
function isValid_corp_no(no)
{
	var regStr = /^[0-9]{13}$/;
    if( !regStr.test(no) ) {
         return false;
    }

    if( '0000000000000' == no
     ) { return false; }

     // 2009.02.12 법인등록번호 유효성 체크 로직 disable
     return true;

    var str = no.toString();
     a = str.substring(0, 1);
     b = str.substring(1, 2);
     c = str.substring(2, 3);
     d = str.substring(3, 4);
     e = str.substring(4, 5);
     f = str.substring(5, 6);
     g = str.substring(6, 7);
     h = str.substring(7, 8);
     i = str.substring(8, 9);
     j = str.substring(9, 10);
     k = str.substring(10, 11);
     l = str.substring(11, 12);
     m = str.substring(12, 13);

     var sum=a*1+b*2+c*1+d*2+e*1+f*2+g*1+h*2+i*1+j*2+k*1+l*2;
     var tmp = 0;

     tmp = 10 - (sum%10);
     if( 10 == tmp) tmp = 0;

     if( tmp == m) { return true; }
     else { return false; }
}

function isValid_bizr_no(no)
{
     var regStr = /^[0-9]{10}$/;
     if( !regStr.test(no) ) {
         return false;
     }

     if( '0000000000' == no
      || '4444444444' == no
      || '8888888888' == no
     ) { return false; }

	var str = no.toString();
	a = str.substring(0, 1);
	b = str.substring(1, 2);
	c = str.substring(2, 3);
	d = str.substring(3, 4);
	e = str.substring(4, 5);
	f = str.substring(5, 6);
	g = str.substring(6, 7);
	h = str.substring(7, 8);
	i = str.substring(8, 9);
	j = str.substring(9, 10);

    var sum = 0;
    sum += a*1%10;
    sum += b*3%10;
    sum += c*7%10;
    sum += d*1%10;
    sum += e*3%10;
    sum += f*7%10;
    sum += g*1%10;
    sum += h*3%10;
    sum += i*5%10 + Math.floor(i*5/10);
    sum += j*1%10;

    if( sum %10 == 0 ) { return true; }
    else { return false; }
}

function isValid_uniq_no(no)
{
	return isValid_bizr_no(no);
}


// 주민번호 7번째 자리의 규칙 ########################
// 1800년대: 남자 9, 여자 0
// 1900년대: 남자 1, 여자 2
// 2000년대: 남자 3, 여자 4
// 2100년대: 남자 5, 여자 6
// 외국인 등록번호: 남자 7, 여자 8

// 주민번호, 외국인 등록번호의  validation 체크 함수
function isValid_socno(socno)
{
	var socnoStr = socno.toString();
     a = socnoStr.substring(0, 1);
     b = socnoStr.substring(1, 2);
     c = socnoStr.substring(2, 3);
     d = socnoStr.substring(3, 4);
     e = socnoStr.substring(4, 5);
     f = socnoStr.substring(5, 6);
     g = socnoStr.substring(6, 7);
     h = socnoStr.substring(7, 8);
     i = socnoStr.substring(8, 9);
     j = socnoStr.substring(9, 10);
     k = socnoStr.substring(10, 11);
     l = socnoStr.substring(11, 12);
     m = socnoStr.substring(12, 13);
	 month = socnoStr.substring(2,4);
	 day = socnoStr.substring(4,6);
	 socnoStr1 = socnoStr.substring(0, 7);
	 socnoStr2 = socnoStr.substring(7, 13);

	 // 월,일 Validation Check
	 if(month <= 0 || month > 12) { return false; }
	 if(day <= 0 || day > 31) { return false; }

	 // 주민등록번호에 공백이 들어가도 가입이 되는 경우가 발생하지 않도록 한다.
	 if (isNaN(socnoStr1) || isNaN(socnoStr2))  { return false; }

     temp=a*2+b*3+c*4+d*5+e*6+f*7+g*8+h*9+i*2+j*3+k*4+l*5;
     temp=temp%11;
     temp=11-temp;
     temp=temp%10;

     if(temp == m) {
        return true;
     } else {
        return false;
     }
}

function isValid_fgnno(socno)
{
	var total =0;
	var parity = 0;

	var fgnNo = new Array(13);

	for(i=0;i < 13;i++) fgnNo[i] = parseInt(socno.charAt(i));

	if(fgnNo[11] < 6) return false;

	if((parity = fgnNo[7]*10 + fgnNo[8])&1) return false;


	var weight = 2;

	for(i=0,total=0;i < 12;i++)
	{
		var sum = fgnNo[i] * weight;
		total += sum;

		if(++weight > 9) weight=2;
	}

	if((total = 11 - (total%11)) >= 10) total -= 10;
	if((total += 2) >= 10) total -= 10;
	if(total != fgnNo[12]) return false;

	return true;
}

function isValid_id( str )
{
     // check whether input value is included space or not
     if( str == ""){
     	alert("아이디를 입력하세요.");
     	return false;
     }

	// 아이디 가운데 빈 공간이 없도록 체크한다.
     var retVal = checkSpace( str );
     if( retVal ) {
         alert("아이디는 빈 공간 없이 연속된 영문 소문자와 숫자만 사용할 수 있습니다.");
         return false;
     }

     // 아이디는 '-' 로 시작할 수 없다.
	if( str.charAt(0) == '_') {
		alert("아이디의 첫문자는 '_'로 시작할수 없습니다.");
		return false;
	}

     // 길이와 허용 문자를 체크한다.
     var isID = /^[a-z0-9_]{3,12}$/;
     if( !isID.test(str) ) {
         alert("아이디는 3~12자의 영문 소문자와 숫자,특수기호(_)만 사용할 수 있습니다.");
         return false;
     }

	 var isNum = /\d/;
     var i;
     var cnt = 0;
     for( i=0; i < str.length; i++) {
     	if( isNum.test( str.substring( i, i+1 ) ) ) {
     		cnt++;
     	}
     	if( cnt > 7 ) {
     		alert("같은 문자가 7개 이상 사용되면 안됩니다.");
     		return false;
     	}
     }

     return true;
}

function isValid_passwd( str )
{
     var cnt = 0;
     if( str == ""){
     	alert("비밀번호를 입력하세요.");
     	return false;
     }

    /* check whether input value is included space or not  */
     var retVal = checkSpace( str );
     if( retVal ) {
         alert("비밀번호에는 공백이 있으면 안됩니다.");
         return false;
     }
			if( str.length < 6 ){
				alert("비밀번호는 6~16자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있습니다.");
				return false;
			}
     for( var i=0; i < str.length; ++i)
     {
         if( str.charAt(0) == str.substring( i, i+1 ) ) ++cnt;
     }
     if( cnt == str.length ) {
         alert("보안상의 이유로 한 문자로 연속된 비밀번호는 허용하지 않습니다.");
         return false;
     }

     var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{6,16}$/;
     if( !isPW.test(str) ) {
         alert("비밀번호는 6~16자의 영문 대소문자와 숫자, 특수문자를 사용할 수 있습니다.");
         return false;
     }
     return true;
}

function isValid_email( str )
{
     /* check whether input value is included space or not  */
     if(str == ""){
     	alert("이메일 주소를 입력하세요.");
     	return false;
     }
     var retVal = checkSpace( str );
     if( retVal ) {
         alert("이메일 주소를 빈공간 없이 넣으세요.");
         return false;
     }

     if( -1 == str.indexOf('.') ) {
     	alert("이메일 형식이 잘못 되었습니다.");
        return false;
     }

     /* checkFormat */
     var isEmail = /[-!#$%&'*+\/^_~{}|0-9a-zA-Z]+(\.[-!#$%&'*+\/^_~{}|0-9a-zA-Z]+)*@[-!#$%&'*+\/^_~{}|0-9a-zA-Z]+(\.[-!#$%&'*+\/^_~{}|0-9a-zA-Z]+)*/;
     if( !isEmail.test(str) ) {
         alert("이메일 형식이 잘못 되었습니다.");
         return false;
     }
     if( str.length > 60 ) {
         alert("이메일 주소는 60자까지 유효합니다.");
         return false;
     }

     return true;
}

function isValid_email2(word){
	for (var i=0; i< word.length; i++){
		var checkStr = word.charAt(i);

		if("@" == checkStr){
			return false
		}
	}
	return true;
}

function isValid_name(str)
{
    str = str.replace(/(^\s*)|(\s*$)/g, "");
     if( str == '' ){
     	 alert("이름을 입력하세요");
         return false;
     }

     var retVal = checkSpace( str );
     if( retVal ){
         alert("이름은 띄어쓰기 없이 입력하세요.");
         return false;
     }
     if( !isHangul(str) ) {
         alert("이름을 한글로 입력하세요.");
         return false;
     }
     if( str.length > 10 ) {
         alert("이름은 7자까지만 사용할 수 있습니다.");
         return false;
     }
     return true;
}

function isValid_ForeignName( str )
{
    /* check format */
    var isENAME = /^[A-Z  ]{3,40}$/;

    if( !isENAME.test( str ) ) { return false; }

    return true;
}
// Validation Check 에 관련된 UI 에 독립적인 함수들 모음  ###########################################################################

// 데이터 판단을 위한 UI 에 독립적인 함수들 모음 ######################################################################################
function isXP()
{
	var agent = window.navigator.userAgent;
	if(agent.indexOf("MSIE")!= -1 && agent.indexOf("5.1") !=-1)
	    return true;     //SP1
    else
        return false;
}

function isXPSP2()
{
    var tmp_MSIE = window.navigator.userAgent.indexOf("MSIE");
    if(tmp_MSIE && window.navigator.userAgent.indexOf("SV1") > tmp_MSIE){
        return true;     //SP2
    }else{
        return false;
    }
}

function isIE7()
{
	var agent = window.navigator.userAgent;
	if(agent.indexOf("MSIE 7")!= -1)
	    return true;
    else
        return false;
}

function isIE8()
{
	var agent = window.navigator.userAgent;
	if(agent.indexOf("MSIE 8")!= -1)
	    return true;
    else
        return false;
}

function isIE()
{
	var agent = window.navigator.userAgent;
	if(agent.indexOf("MSIE")!= -1)
	    return true;
    else
        return false;
}

function isHangul(s)
{
     var len;
     len = s.length;

     for (var i = 0; i < len; i++)  {
         if (s.charCodeAt(i) != 32 && (s.charCodeAt(i) < 44032 || s.charCodeAt(i) > 55203))
         		return false;
     }

     return true;
}

// space 가 있으면 true, 없으면 false
function checkSpace( str )
{
     if(str.search(/\s/) != -1){
     	return true;
     } else {
        return false;
     }
}
//데이터 판단을 위한 UI 에 독립적인 함수들 모음 ######################################################################################

//팝업에 관련된 UI 에 독립적인 함수들 모음  ################################################################################
 function winOpenAtCenter(sURL, sWindowName, w, h, sScroll) {
	  // 화면 중앙으로 Popup 띄우기.. 스크롤바는 옵션..
	  // ex)
	  //      openWin("test.asp", "winTest", 400, 300);     ☞ 스크롤바 없음
	  //      openWin("test.asp", "winTest", 400, 300, "yes");  ☞ 스크롤바 있음
	  //      openWin("test.asp", "winTest", 400, 300, "auto"); ☞ 스크롤바 자동

	  var x = (screen.width - w) / 2;
	  var y = (screen.height - h) / 2;

	  if (sScroll==null) sScroll = "no";

	  var sOption = "";
	  sOption = sOption + "toolbar=no, channelmode=no, location=no, directories=no, resizable=no, menubar=no";
	  sOption = sOption + ", scrollbars=" + sScroll + ", left=" + x + ", top=" + y + ", width=" + w + ", height=" + h;

	  var win = window.open(sURL, sWindowName, sOption);
	  return win;
}

function winOpenAtCenter2(sURL, sWindowName, w, h, sScroll) {
	var win2 = winOpenAtCenter(sURL, sWindowName, w, h, sScroll);
};

function window_resize(winWidth, winHeight) {

	// 스크롤 없애기
	document.body.style.overflow='hidden';

	var clintAgent = navigator.userAgent;
	var foxHeight = 80;
	var ieHeight = 60;
	var ie7Height = 80;
	var ie8Height = 90;

	if ( clintAgent.indexOf("MSIE") != -1 )	// IE 일 경우
	{
		var res = isIE7();
		if( isIE7() ) {	window.resizeTo(winWidth, winHeight + ie7Height); }
		else if( isIE8() ) { window.resizeTo(winWidth, winHeight + ie8Height); }
		else { window.resizeTo(winWidth, winHeight + ieHeight); }
	}
	else	// IE 가 아닐 경우
	{
		window.resizeTo(winWidth, winHeight + foxHeight);
	}
}
// 팝업에 관련된 UI 에 독립적인 함수들 모음  ################################################################################

// 기타 미분류 함수들 모음 ##############################################################################################
function setCookie(name, value, expiredays, domain) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	if ( domain=="" ) domain=".nid.naver.com";
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	                  + (domain ? "domain="+domain : "" ) + ";"
}

function getCookie( name ) {
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length ) {
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
	        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
	            endOfCookie = document.cookie.length;
	        return unescape( document.cookie.substring( y, endOfCookie ) );
		}
		x = document.cookie.indexOf( " ", x ) + 1;
		if ( x == 0 )
		        break;
	}
	return "";
}


// 주민번호 입력할 때 자동으로 다음 input 으로 이동한다.
// 접근성을 고려해서 만든 스크립트 이다. - 2008.03.19
var next_go = true;
var cur_val = null;
function moveNext(id_from,id_to,maxSize) {

	var cur = document.getElementById(id_from).value;
	curSize = cur.length;
	numFlag = isNumeric(cur);

	if ( !numFlag && curSize >= 1 && cur != '00' &&  cur != '000') {
		alert('숫자를 넣어주세요');
		document.getElementById(id_from).value='';
		document.getElementById(id_from).focus();
		return false;
	}
	if (curSize == maxSize) {
		if(next_go || cur_val != cur)
		{
			cur_val = cur;
			next_go = false;
			document.getElementById(id_to).focus();
		}
		return true;
	}
	next_go = true;
}


// 전화번호를 입력할 때 숫자 키, Tab, Enter 등만 입력되도록 키 이벤트를 무효화 한다. - 2008.01.07
function num_only( Ev ){
    var evCode = ( window.netscape ) ? Ev.which : event.keyCode ;

    /// FF일 경우 Ev.which 값을,
    //  IE을 경우 event.keyCode 값을 evCode에 대입
    //  enter 가 동작하도록 evCode 13을 추가
    if ( ! ( evCode == 0 || evCode == 8 || ( evCode >= 48 && evCode <= 57 ) || (evCode == 13) ) ) {
    // 눌러진 키 코드가 숫자가 아닌 경우
    //    ( '0'은 FF에서 Tab 키,
    //      '8'은 FF에서 BackSpace가 먹히지 않아 삽입)
        if ( window.netscape ) {        // FF일 경우
            Ev.preventDefault() ;       // 이벤트 무효화
        } else {                        // IE일 경우
            event.returnValue=false;    // 이벤트 무효화
        }
    }
}


function trim(string)
{
	for(;string.indexOf(" ")!= -1;)
	{
	 	string = string.replace(" ","");
	}

	return string;
}

function containsCharsOnly(input,chars)
{
  for(var i=0; i< input.length; i++) {
    if(chars.indexOf(input.charAt(i)) == -1)
    return false;
  }
  return  true;
}

function isNumeric(input)
{
  var chars = "0123456789";
  return containsCharsOnly(input,chars);
}

var db = new Array("1","2","3");
var agent = window.navigator.userAgent;
var busy = false;
function check_num(frm, sel) {
  if( !busy && db[sel] != $(frm).value && $(frm).value !="") {
    busy = true;

      var str = $(frm).value;

        if( !isNumeric(str)) {
          if(agent.indexOf("Macintosh") != -1)
            {
                $(frm).value="";
                alert("숫자를 넣어주세요.");
                $(frm).value="";
								db[sel] = "";
            }
            else {
                alert("숫자를 넣어주세요.");
                $(frm).value="";
            }
        } else { db[sel] = $(frm).value; }

    busy = false;
    }
  else { setTimeout("check_num('"+frm+"','"+sel+"')",10); }
}

// 기타 미분류 함수들 모음 ##############################################################################################
