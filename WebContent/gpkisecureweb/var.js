//------------------------------------------- [Init �� �ʿ��� ����] --------------------------------------------------------------------//
// ����������(Base64Encode)
var ServerCert   		= "MIID5TCCAs2gAwIBAgIQSeVDQQFSDP/GiOpr6qlM7zANBgkqhkiG9w0BAQUFADBQMQswCQYDVQQGEwJLUjEcMBoGA1UEChMTR292ZXJubWVudCBvZiBLb3JlYTENMAsGA1UECxMER1BLSTEUMBIGA1UEAxMLQ0ExMzEwMDAwMDEwHhcNMDkwNDE1MDIxNTI5WhcNMTEwNzE1MDIxNTI5WjBdMQswCQYDVQQGEwJLUjEcMBoGA1UECgwTR292ZXJubWVudCBvZiBLb3JlYTEYMBYGA1UECwwPR3JvdXAgb2YgU2VydmVyMRYwFAYDVQQDDA1TVlI2MjYwMDAwMDU4MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgEwAK26Fqo2kWnh7ywL8fYl3rNW5K1S/FgNocnZQ/GtlwFd5O7ZadHIeUHMwhhKOFg/Q8sKZQsdegRIiO8dT+GleDW4n4VPnIAhXSL8B703l5oWyqi+z/fRDURzzNdocDZHAkD4wre2te5UteOJamQVMNfjvJO2Z+aXspgIr4dzdAgMBAAGjggExMIIBLTAfBgNVHSMEGDAWgBQBzxeuBI2HzLdVC3Fsa0ic5rGylTAdBgNVHQ4EFgQU8UuZCHbA4jEd/llmzqJSpsX1+XEwDgYDVR0PAQH/BAQDAgUgMBgGA1UdIAQRMA8wDQYJKoMaho0hAgECMAAwgYgGA1UdHwSBgDB+MHygeqB4hnZsZGFwOi8vY2VuLmRpci5nby5rcjozODkvY249Y3JsMDAyMyxjbj1DQTEzMTAwMDAwMSxvdT1HUEtJLG89R292ZXJubWVudCBvZiBLb3JlYSxjPUtSP2NlcnRpZmljYXRlUmV2b2NhdGlvbmxpc3Q7YmluYXJ5MDYGCCsGAQUFBwEBBCowKDAmBggrBgEFBQcwAYYaaHR0cDovL2d2YS5ncGtpLmdvLmtyOjgwODAwDQYJKoZIhvcNAQEFBQADggEBAC3CA9f/tPzZPc6CNs9kjn+DeJSO6TxAOa+dz9npQB833jXi0/q3pKhipengQ6MLlGgG9NRdP1gaoyGEQINe+OA7TuRf8y8jOVqBS2Y66Iout87IQTyo/G000OIKfrt/6fHx7qlJ5xOQTmHdu1Mr5vGB0qsX3ugi8PVmzpLfx7wz9dZ9tvz93CiBTDS4p5VIWbfPh1NUvBR55cDrIq/QABmleMmBGfuFViVQOHdCleL1CvhmnFVNVq1oNPC/S6i7lfwKtLEvYRmhN5PSWkeLXlTwol/uZRS3J032S0tXLGRsFB62KV7k71GEo7uIXlUK4z4gOjqehQgLpltgyZ8gX3I=";

var AlgoMode 			= 0x30;				         			// �Ϻ�ȣ �˰��� (
												// 0x20 : SYM_ALG_3DES_CBC, 
												// 0x30 : SYM_ALG_SEED_CBC, 
												// 0x40 : SYM_ALG_NEAT_CBC, 
												// 0x50 : SYM_ALG_ARIA_CBC,
			         								// 0x60 : SYM_ALG_NES_CBC)

var WorkDir		        = "GPKISecureWeb";						// �۾����丮(��� ��ġ�� ��ġ) 		

var GNCertType  		= 0x00;				         			// GPKI, NPKI ������ ��� : 0x00, GPKI �� : 0x01, NPKI �� :0x02	
var ValidCertInfo 		= "";

//var ValidCertInfo 		= "1 2 410 100001 2 2 1|1 2 410 100001 2 1 2|";			// Ư���������� �ε� �� ��쿡 ��å�ڵ带 �����Ѵ�.	
var ValidCertInfo 		= "";			// Ư���������� �ε� �� ��쿡 ��å�ڵ带 �����Ѵ�.	


var ReadCertType 		= 0x01; 				        		// ����������� : 0x01, ��ȣŰ�й�� ������ : 0x02

var KeyStrokeType 		= 0x00;			 	         			// Ű���� ���� API (0x01 : softcamp, 0x00 : �������)

var CertOption			= 0x01;							        // 0x00 : �α����� �����������θ� �����Ѵ�. (�ش��������� �ε��Ѵ�.) 

// SiteID
var SiteID 			= "Test_GPKI";			        		        // SiteID :���������� ȹ���ϴ� Ű��

// ����ÿ� 
// [setup.conf : ��ġ���� ��ġ����]

// GPKIInstaller ����
		
var ServerAddr 			= "10.1.1.1:8080"; 				
var ConfigFilePath 		= "/gpkisecureweb/setup/setup.conf";

// [��ġ ���� ��� ����(html���ο��� ����)]

var SetupOffLineFilePath        = "/gpkisecureweb/setup/install_off_v1.0.2.8.exe";

// [��ġ�Ϸ��� �̵��� ������ ����]
var ServiceStartPageURL		= "/index.html";

var UbikeyVersion = "1.0.3.1";
var UbikeyPopupURL = "http://www.gpki.go.kr/wire/infovine/download.html";
var UbikeyWParam = "SITE_NAME|";
var UbikeylParam = "DREAMSECURITY|KEYBOARD_SECURITY_COMP_CODE";
//---------------------------------------------- [ActiveX ������ȣ] --------------------------------------------------------------------//

// [ActiveX Object �ױ� ����]																					

var CodeBase_GPKIInstaller	= " CODEBASE='http://10.1.1.1:8080/gpkisecureweb/setup/GPKIInstaller.cab#version=1,0,1,3'";

var Object_GPKIInstaller 	= "<OBJECT ID ='GPKIInstaller' CLASSID = 'CLSID:531BBB4D-B043-4D70-8A88-0A416C7F7CD0' width = 0 height =0";
Object_GPKIInstaller            += CodeBase_GPKIInstaller;
Object_GPKIInstaller            += "></OBJECT >";
