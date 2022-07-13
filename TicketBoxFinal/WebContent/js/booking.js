
$(function(){

	//date의 value 값을 오늘 날자로 설정함.
	$(function(){
		document.getElementById('input_date').valueAsDate= new Date();
		console.log(document.getElementById('input_date'));
	});
	
	//실시간으로 내용을 보여줄 수 있게

})

function showSeat(data){
		           var name = $('input[name=MovieName]').val();
	               var date = $('#input_date').val();
	               var time = data;
	               
	               console.log("영화제목 : " + name);
	               console.log("상영날짜 : " + date);
	               console.log("상영시간 : " + time);
	               
	        
	               $.ajax({
	                   url : 'showSeat',
	                   type : 'post',
	                   data : {
	                      MovieName : name, //var name 값.
	                      MovieDate : date,
	                  	  MovieTime : time
	                   },
	                   success : function(data) {// ajax 통신이 성공했다면 ? 아래 기능 수행
	                	 
	                    console.log("ajax 통신 성공");
	                    console.log(data);
	                    if(data!=null){
                 	      	$("#vip").html("vip 잔여석 : " + data.vip); //여기서 data는 showSeat.java에서 pw.print(json.toString());으로 넘어온 값.
	                    	$("#r").html("R 잔여석 : " + data.r);
	                    	$("#s").html("S 잔여석 : " + data.s);
	                    }
	                   },
	                   error : function(data, status, opt) {
	                      alert("code:"+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+opt);
	                   }
	                }); //ajax-end
	              }