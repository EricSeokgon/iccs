package sp.util;

import crosscert.Certificate;

public class CertificateVerify {
    
    /**
     * 인증서 검증
     * 
     * @return
     * @throws Exception
     */
    public static String getPoliciesInfo() throws Exception {      
        
        StringBuffer policies = new StringBuffer();
        
        // 개인상호연동용(범용)                            
        policies.append("1.2.410.200004.5.2.1.2"    + "|");   // 한국정보인증               개인                                             
        policies.append("1.2.410.200004.5.1.1.5"    + "|");   // 한국증권전산               개인                                             
        policies.append("1.2.410.200005.1.1.1"      + "|");   // 금융결제원                    개인                                             
        policies.append("1.2.410.200004.5.3.1.4"    + "|");   // 한국전산원                    개인(국가기관, 공공기관 및 법인의 소속직원 등)   
        policies.append("1.2.410.200004.5.4.1.1"    + "|");   // 한국전자인증               개인                                             
        policies.append("1.2.410.200012.1.1.1"      + "|");   // 한국무역정보통신     개인           
        
        // 개인 용도제한용 인증서정책(OID)    용도  공인인증기관
        policies.append( "1.2.410.200004.5.4.1.101|");        // 은행거래용/보험용      한국전자인증
        policies.append( "1.2.410.200004.5.4.1.102|");        // 증권거래용                        한국전자인증
        policies.append( "1.2.410.200004.5.4.1.103|");        // 신용카드용                        한국전자인증
        policies.append( "1.2.410.200004.5.4.1.104|");        // 전자민원용                        한국전자인증
        policies.append( "1.2.410.200004.5.2.1.7.1|");        // 은행거래용/보험용       한국정보인증
        policies.append( "1.2.410.200004.5.2.1.7.2|");        // 증권거래용/보험용       한국정보인증
        policies.append( "1.2.410.200004.5.2.1.7.3|");        // 신용카드용                         한국정보인증
        policies.append( "1.2.410.200004.5.1.1.9|");          // 증권거래용/보험용       한국증전산
        policies.append( "1.2.410.200004.5.1.1.9.2|");        // 신용카드용                         한국증전산
        policies.append( "1.2.410.200005.1.1.4|");            // 은행거래용/보험용       금융결제원
        policies.append( "1.2.410.200005.1.1.6.2|");          // 신용카드용                         금융결제원
        policies.append( "1.2.410.200012.1.1.101|");          // 은행거래용/보험용       한국무역정보통신
        policies.append( "1.2.410.200012.1.1.103|");          // 증권거래용/보험용       한국무역정보통신
        policies.append( "1.2.410.200012.1.1.105|");           // 신용카드용                         한국무역정보통신
        
        // 법인상호연동용(범용)
        policies.append("1.2.410.200004.5.2.1.1"    + "|");   // 한국정보인증                   법인
        policies.append("1.2.410.200004.5.1.1.7"    + "|");   // 한국증권전산                   법인, 단체, 개인사업자
        policies.append("1.2.410.200005.1.1.5"      + "|");   // 금융결제원                        법인, 임의단체, 개인사업자
        policies.append("1.2.410.200004.5.3.1.1"    + "|");   // 한국전산원                        기관(국가기관 및 비영리기관)
        policies.append("1.2.410.200004.5.3.1.2"    + "|");   // 한국전산원                        법인(국가기관 및 비영리기관을  제외한 공공기관, 법인)
        policies.append("1.2.410.200004.5.4.1.2"    + "|");   // 한국전자인증                   법인, 단체, 개인사업자
        policies.append("1.2.410.200012.1.1.3"      + "|");   // 한국무역정보통신         법인  
        
        return policies.toString();
    }
    
    
    /**
     * 인증서 정보 추출 결과 출력
     * 
     * @param CCertificate
     * @throws Exception
     */
    public static void printCCertificateResult(Certificate CCertificate) throws  Exception {
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("version;          "+  CCertificate.version               );
        System.out.println("serial;           "+  CCertificate.serial                );
        System.out.println("issuer;           "+  CCertificate.issuer                );
        System.out.println("subject;          "+  CCertificate.subject               );
        System.out.println("subjectAlgId;     "+  CCertificate.subjectAlgId          );
        System.out.println("from;             "+  CCertificate.from                  );
        System.out.println("to;               "+  CCertificate.to                    );
        System.out.println("signatureAlgId;   "+  CCertificate.signatureAlgId        );
        System.out.println("pubkey;           "+  CCertificate.pubkey                );
        System.out.println("signature;        "+  CCertificate.signature             );
        System.out.println("issuerAltName;    "+  CCertificate.issuerAltName         );
        System.out.println("subjectAltName;   "+  CCertificate.subjectAltName        );
        System.out.println("keyusage;         "+  CCertificate.keyusage              );
        System.out.println("policy;           "+  CCertificate.policy                );
        System.out.println("basicConstraint;  "+  CCertificate.basicConstraint       );
        System.out.println("policyConstraint; "+  CCertificate.policyConstraint      );
        System.out.println("distributionPoint;"+  CCertificate.distributionPoint     );
        System.out.println("authorityKeyId;   "+  CCertificate.authorityKeyId        );
        System.out.println("subjectKeyId;     "+  CCertificate.subjectKeyId          );     
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}
