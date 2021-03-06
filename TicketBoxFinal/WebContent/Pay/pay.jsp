<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	
	 
    /* 
     String name = request.getParameter("name");
     String email = request.getParameter("email");
     String phone = request.getParameter("phone");
     String address = request.getParameter("address");
     String stotalPrice = request.getParameter("totalPrice");
     int totalPrice = Integer.parseInt(stotalPrice); 
    
     System.out.println("name: "+name);
    System.out.println("email: "+email);
     System.out.println("phone: "+phone);
     System.out.println("address: "+address);
     System.out.println("stotalPrice: "+stotalPrice);
     System.out.println("totalPrice: "+totalPrice); 
  */
  String pname = (String)session.getAttribute("pname");
  String name = (String)session.getAttribute("name");
  String email = (String)session.getAttribute("email");
  String phone = (String)session.getAttribute("phone");
  String address = (String)session.getAttribute("address");
  
  int total_pay = (Integer)session.getAttribute("total_pay");
  String format_pay = Integer.toString(total_pay); //천 단위 맞춰주기.
  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>

    <script>
    $(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp19710690'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
            pg : 'jtnet',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '<%=pname%>',
            amount : '<%=format_pay%>',
            buyer_email : '<%=email %>',
            buyer_name : '<%=name %>',
            buyer_tel : '<%=phone%>',
            buyer_addr : '<%= address%>',
            buyer_postcode : '123-456',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        /* merchant_uid : rsp.merchant_uid
                        paid_amount : rsp.paid_amount
                		apply_num : rsp.apply_num */
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });
                //성공시 이동할 페이지
                location.href='./ticketEnd/ticketEnd.jsp?msg='+msg; 
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="<%=request.getContextPath()%>/order/payFail";
                alert(msg);
            }
        });
        
    });
  
    </script>

</body>
</html>