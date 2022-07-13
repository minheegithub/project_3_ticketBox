//1인당 판매 좌석수를 4개로 제한시킨다.
//선택한 좌석 번호를 controller에 보낸다. 잔여좌석을 계산하여 mainframe view 우측에 출력한다.
//+선택한 좌석을 결제 종료 후 화면에서 좌석도로 다시 볼 수 있을까? DB에 저장후 회차 선택 시 선택되었던 좌석은 selected로 채워지도록 해야 한다.
//할인 수단 선택 화면과 연결시킨다. request객체. 우선 controller를 만들자. 그리고 DB랑 어떻게 연동시킬 것인지 고민하자.
//당신이 선택한 좌석에 예상 결제 금액 계속 보여주기. 근데 그게 이제 controller에서 받은 값을 뿌려주는 것이다.

var settings = {
        rows: 10, //1층은 10줄이다.
        cols: 10,
        
        rows2: 5, //2층은 5줄이다.
        cols2: 10,        	

        //시리얼 7자리를 위해 prefix 사용.
        floorCssPrefix1: '1F',
        floorCssPrefix2: '2F', //2층의 경우 모두 S석이다.
        
        //A,B,C 구역으로 나누게 된다.
        //area에 따른 css를 쓸지는 모르겠지만 일단 css.
        areaCssPrefix1: 'A', 
        areaCssPrefix2: 'B',
        areaCssPrefix3: 'C',
        
        //좌석 색상 뿐만 아니라 가격도 다르게 매겨져야 하므로 class 태그를 사용한다. seat을 default(S)로 하고 다른 클래스 태그를 부여하는 것으로 하자.
        classCssPrefix1: 'VIP',
        classCssPrefix2: 'R',
        classCssPrefix3: 'S',
        
        seatWidth: 20, //좌석 한 칸의 크기
        seatHeight: 20, //
        seatCss: 'seat',
        selectedSeatCss: 'selectedSeat',
        disableSeatCss: 'disableSeat', //시야 방해석이라서 선택하지 않고 공간으로 두는 css를 적용한다.
        selectingSeatCss: 'selectingSeat'
    };


//1. 블록 표시하기
var bookedSeats = ['seat 1FB0111', 'seat 1FA0909', 'seat 1FC0521']; /*예매된 좌석 번호를 임의로 지정해 주었음. */
//시야 제한석이라서 화면에 보이지 않게 될 좌석 배열.
var disableSeats = ['seat 1FA0101','seat 1FA0102','seat 1FA0103','seat 1FA0104','seat 1FC0127','seat 1FC0128','seat 1FC0129','seat 1FC0130',
					'seat 1FA0201','seat 1FA0202','seat 1FA0203', 'seat 1FC0228','seat 1FC0229','seat 1FC0230',
					'seat 1FA0301','seat 1FA0302', 'seat 1FC0329','seat 1FC0330',
					'seat 1FA0401','seat 1FC0430'];
var str= []; //좌석 list를 보내는 것을 넣는다.

//[블록A 1층] ------------------------------------------------------------------------------------------------------------//
function blockA1 (already,blocked){ 
	
	var className;	

	for (i=1;i<=settings.rows;i++){ //여기서 rows는 10
		for(j=1;j<=settings.cols;j++){ //여기서 cols는 10
			
			//string으로 구성된 7자리 좌석 시리얼 넘버. 예시 1FA0101  1층 A구역 1열 1번  ----> 그런데 class 이름에  따라 style을 부가하기 위해서 앞에 seat을 붙여줌
			//한 자리 수일 경우 앞에 0을 붙여준다. 	 value.toString().length<2?'0'+value:value
			//문자열 연결 방식
					
			className = settings.seatCss.concat(" ",settings.floorCssPrefix1,settings.areaCssPrefix1, i.toString().length<2?'0'+i:i, j.toString().length<2?'0'+j:j);
			console.log(className); //seat 1FA0101
			
			//clasName을 잘라서 화면에 나올 때는 보기 좋도록 수정한다.
			var floor = className.toString().slice(5,6); //층
			var area  = className.toString().slice(7,8); //구역
			var line =  className.toString().slice(8,10); //열 번호
			var number = className.toString().slice(10,12); //왼쪽으로부터 몇 번째 칸인지 번지를 의미.			
			var seat_info = floor+"층"+area+"구역"+line+"열"+number+"번";
			console.log(seat_info);
			
			//VIP석 지정 
			if(parseInt(line)>=6 && parseInt(line)<=10 && (parseInt(number)>=8 && parseInt(number)<=10) && ($.inArray(className,already) == -1))
			{
				className += ' ' + settings.classCssPrefix1;
				seat_info += ' '+  settings.classCssPrefix1;
				console.log(className)
				console.log("title에 들어갈 이름: "+seat_info);
			}			
			else if ($.isArray(already) && $.inArray(className,already) != -1) {   
															//선택 불가 좌석 목록에 속해 있다면
                className += ' ' + settings.selectedSeatCss; //selectedSeat이라는 태그가 뒤에 붙는다.
                console.log(className)
            }
			else if($.inArray(className,blocked) !=-1){ //시야 방해석 해당 좌석이라면 화면에 출력하지 않는다. 배열에서 빼기.
				className += ' ' + settings.disableSeatCss; //disableSeat이라는 태그가 뒤에 붙는다. 배경색과 똑같은 색으로 처리. 없는 것처럼.
				console.log(className)
			}
			//1층 A구역 VIP석 외에는 R석이다.
			else{
				className += ' ' + settings.classCssPrefix2; 
				console.log(className)
				seat_info += ' '+  settings.classCssPrefix2;
				console.log("title에 들어갈 이름: "+seat_info);
			}
			
			
            str.push('<li class="' + className + '"' + 
            		'style="top:' + (i * settings.seatHeight).toString() + 'px;left:' 
            					  + (j * settings.seatWidth).toString() + 'px">'+
            					'<a title="' +seat_info+ '">'+' '+ '</a>'  //좌석 정보가 나오도록 title을 쓰긴 했는데 내용이 없어서 그런지 작동하지 않는다.
            					+'</li>'); //float:left -> 가로 방향으로 좌석을 배치한다. li 특성.
           }
		
		}
	str.push('<li style="left:220px; top:0px;""><img src="images/seat_number.png" width="30px"; height="240px"></li>');
	console.log(str);
	$('#place').html(str.join('')); //place라는 id 태그에 붙는다. 해당 태그는 ul이다.
	
};
blockA1(bookedSeats,disableSeats); //실행--------------------//


//[블록A 2층]--------------------------------------------------------------------------------------------------------------------------//
function blockA2 (already){
	
	var className;	
	
	for (i=1;i<=settings.rows2;i++){ //여기서 rows는 5
		for(j=1;j<=settings.cols2;j++){ //여기서 cols는 10
															//2F
			className = settings.seatCss.concat(" ",settings.floorCssPrefix2,settings.areaCssPrefix1, i.toString().length<2?'0'+i:i, j.toString().length<2?'0'+j:j);
			console.log(className);
			
			//clasName을 잘라서 화면에 나올 때는 보기 좋도록 수정한다.
			var floor = className.toString().slice(5,6); //층
			var area  = className.toString().slice(7,8); //구역
			var line =  className.toString().slice(8,10); //열 번호
			var number = className.toString().slice(10,12); //왼쪽으로부터 몇 번째 칸인지 번지를 의미.			
			var seat_info = floor+"층"+area+"구역"+line+"열"+number+"번";
			console.log(seat_info);
			
			if ($.isArray(already) && $.inArray(className,already) != -1) {   
															//선택 불가 좌석 목록에 속해 있다면
                className += ' ' + settings.selectedSeatCss; //selectedSeat이라는 태그가 뒤에 붙는다.
                console.log(className)
            }
			else{
				className += ' ' + settings.classCssPrefix3;
				seat_info += ' '+  settings.classCssPrefix3;
				console.log("title에 들어갈 이름: "+seat_info);
				
			}
			//2층은 시야 방해되는 곳이 없다고 가정한다.
			var new_height = i*settings.seatHeight+240;
			console.log(new_height);
            str.push('<li class="' + className + '"' + 
            		'style="top:'+(new_height).toString() + 'px;left:' 
            					  + (j * settings.seatWidth).toString() + 'px">'+
            					'<a title="' +seat_info+ '"></a>'  
            					+'</li>'); //float:left -> 가로 방향으로 좌석을 배치한다. li 특성.  
           
            console.log(seat_info);
        }
		
		}
	str.push('<li style="left:220px; top:241px;"><img src="images/seat_number_2.png" width="30px"; height="140px"></li>'); //통로에 열번호 이미지 삽입.
	console.log(str);
	$('#place').html(str.join('')); //place라는 id 태그에 붙는다. 해당 태그는 ul이다.
};
blockA2(bookedSeats);
//[블록 B 1층]-----------------------------------------------------------------------------------------------------------//
function blockB (already){
var className;
		
	for (i=1;i<=settings.rows;i++){ //여기서 rows는 10
		for(j=1;j<=settings.cols;j++){ //여기서 cols는 10
			
			className = settings.seatCss.concat(" ",settings.floorCssPrefix1,settings.areaCssPrefix2, 
					i.toString().length<2?'0'+i:i, 10+j);
			console.log(className);
			
			//clasName을 잘라서 화면에 나올 때는 보기 좋도록 수정한다.
			var floor = className.toString().slice(5,6); //층
			var area  = className.toString().slice(7,8); //구역
			var line =  className.toString().slice(8,10); //열 번호
			var number = className.toString().slice(10,12); //왼쪽으로부터 몇 번째 칸인지 번지를 의미.			
			var seat_info = floor+"층"+area+"구역"+line+"열"+number+"번";
			console.log(seat_info);
			
			//VIP석 지정 
			if(parseInt(line)>=6 && parseInt(line)<=10  && ($.inArray(className,already) == -1)){
				className += ' ' + settings.classCssPrefix1;
				console.log(className)
				seat_info += ' '+  settings.classCssPrefix1;
				console.log("title에 들어갈 이름: "+seat_info);
			}			
			else if ($.isArray(already) && $.inArray(className,already) != -1) {   
															//선택 불가 좌석 목록에 속해 있다면
                className += ' ' + settings.selectedSeatCss; //selectedSeat이라는 태그가 뒤에 붙는다.
                console.log(className)
            }
			//블록 B와 2층에는 시야 방해되는 곳이 없다. 따라서 빈 좌석 없음. 
			//1층 A구역 VIP석 외에는 R석이다.
			else{
				className += ' ' + settings.classCssPrefix2; 
				console.log(className)
				seat_info += ' '+  settings.classCssPrefix2;
				console.log("title에 들어갈 이름: "+seat_info);
			}
			var new_width = j*settings.seatHeight+230;
			
            str.push('<li class="' + className + '"' + 
            		'style="top:' + (i * settings.seatHeight).toString() + 'px;left:' 
            					  + (new_width).toString() + 'px">'+
            					'<a title="' +seat_info+ '">'+' '+ '</a>'  
            					+'</li>'); //float:left -> 가로 방향으로 좌석을 배치한다. li 특성.
           }
		
		}
	str.push('<li style="left:450px; top:0px;""><img src="images/seat_number.png" width="30px"; height="240px"></li>'); //통로에 열번호 이미지 삽입.
	console.log(str);
	$('#place').html(str.join('')); //place라는 id 태그에 붙는다. 해당 태그는 ul이다.
};
blockB(bookedSeats);

//[블록B 2층]---------------------------------------------------------------------------------------------------//
function blockB2 (already){
	var className;		
		
		for (i=1;i<=settings.rows2;i++){ //여기서 rows는 10
			for(j=1;j<=settings.cols2;j++){ //여기서 cols는 10
										
				className = settings.seatCss.concat(" ",settings.floorCssPrefix2,settings.areaCssPrefix2, 
						i.toString().length<2?'0'+i:i, j+10);
				console.log(className);
				
				//clasName을 잘라서 화면에 나올 때는 보기 좋도록 수정한다.
				var floor = className.toString().slice(5,6); //층
				var area  = className.toString().slice(7,8); //구역
				var line =  className.toString().slice(8,10); //열 번호
				var number = className.toString().slice(10,12); //왼쪽으로부터 몇 번째 칸인지 번지를 의미.			
				var seat_info = floor+"층"+area+"구역"+line+"열"+number+"번";
				console.log(seat_info);
				
				if ($.isArray(already) && $.inArray(className,already) != -1) {   
																//선택 불가 좌석 목록에 속해 있다면
	                className += ' ' + settings.selectedSeatCss; //selectedSeat이라는 태그가 뒤에 붙는다.
	                console.log(className)
	            }
				else{
					className += ' ' + settings.classCssPrefix3;
					seat_info += ' '+  settings.classCssPrefix3;
					console.log("title에 들어갈 이름: "+seat_info);
				}
				
				var new_height = i*settings.seatHeight+240;
				var new_width = j*settings.seatHeight+230;
	            str.push('<li class="' + className + '"' + 
	            		'style="top:' + (new_height).toString() + 'px;left:' 
	            					  + (new_width).toString() + 'px">'+
	            					'<a title="' +seat_info+ '">'+' '+ '</a>'  
	            					+'</li>'); //float:left -> 가로 방향으로 좌석을 배치한다. li 특성.
	           }
			
			}
		console.log(str);
		str.push('<li style="left:23px; top:220px;"><img src="images/floor1.png" width="650px"; height="36px"></li>'); //2층 표시 이미지 삽입.
		str.push('<li style="left:450px; top:241px;"><img src="images/seat_number_2.png" width="30px"; height="140px"></li>'); //2층 열번호 이미지.
		$('#place').html(str.join('')); //place라는 id 태그에 붙는다. 해당 태그는 ul이다.
	};
blockB2(bookedSeats);

//[블록C 1층]---------------------------------------------------------------------------------------------//
function blockC (already,blocked){
	var className;
			
		for (i=1;i<=settings.rows;i++){ //여기서 rows는 10
			for(j=1;j<=settings.cols;j++){ //여기서 cols는 10
				
				className = settings.seatCss.concat(" ",settings.floorCssPrefix1,settings.areaCssPrefix3, 
						i.toString().length<2?'0'+i:i, 20+j);
				console.log(className); //seat 1FC0126
				
				//clasName을 잘라서 화면에 나올 때는 보기 좋도록 수정한다.
				var floor = className.toString().slice(5,6); //층
				var area  = className.toString().slice(7,8); //구역
				var line =  className.toString().slice(8,10); //열 번호
				var number = className.toString().slice(10,12); //왼쪽으로부터 몇 번째 칸인지 번지를 의미.			
				var seat_info = floor+"층"+area+"구역"+line+"열"+number+"번"; //1층C구역01열26번
				console.log(seat_info);
				
				//VIP석 지정 
				if(parseInt(line)>=6 && parseInt(line)<=10 && (parseInt(number)>=21 && parseInt(number)<=23)  && ($.inArray(className,already) == -1)){
					className += ' ' + settings.classCssPrefix1;
					console.log(className)
					seat_info += ' '+  settings.classCssPrefix1;
					console.log("title에 들어갈 이름: "+seat_info);
				}					
				else if ($.isArray(already) && $.inArray(className,already) != -1) {   
																//선택 불가 좌석 목록에 속해 있다면
	                className += ' ' + settings.selectedSeatCss; //selectedSeat이라는 태그가 뒤에 붙는다.
	                console.log(className)
	            }
				else if($.inArray(className,blocked) !=-1){ //시야 방해석 해당 좌석이라면 화면에 출력하지 않는다.
					className += ' ' + settings.disableSeatCss; //disableSeat이라는 태그가 뒤에 붙는다. 배경색과 똑같은 색으로 처리. 없는 것처럼.
					console.log(className)
				}
				
				else{
					className += ' ' + settings.classCssPrefix2; 
					console.log(className) //seat 1FC0126 R
					seat_info += ' '+  settings.classCssPrefix2;
					console.log("title에 들어갈 이름: "+seat_info);
				}
				
				var new_width = j*settings.seatHeight+460;
				
	            str.push('<li class="' + className + '"' + 
	            		'style="top:' + (i * settings.seatHeight).toString() + 'px;left:' 
	            					  + (new_width).toString() + 'px">'+
	            					'<a title="' +seat_info+ '">'+' '+ '</a>'  
	            					+'</li>'); //float:left -> 가로 방향으로 좌석을 배치한다. li 특성.
	           }
			
			}
		console.log(str);
		$('#place').html(str.join('')); //place라는 id 태그에 붙는다. 해당 태그는 ul이다.
	};
blockC(bookedSeats,disableSeats);

//[블록C 2층]-------------------------------------------------------------------------------------------------//
function blockC2 (already){
	var className;		
		
		for (i=1;i<=settings.rows2;i++){ 
			for(j=1;j<=settings.cols2;j++){ 
										
				className = settings.seatCss.concat(" ",settings.floorCssPrefix2,settings.areaCssPrefix3, 
						i.toString().length<2?'0'+i:i, j+20);
				console.log(className);
				
				//clasName을 잘라서 화면에 나올 때는 보기 좋도록 수정한다.
				var floor = className.toString().slice(5,6); //층
				var area  = className.toString().slice(7,8); //구역
				var line =  className.toString().slice(8,10); //열 번호
				var number = className.toString().slice(10,12); //왼쪽으로부터 몇 번째 칸인지 번지를 의미.			
				var seat_info = floor+"층"+area+"구역"+line+"열"+number+"번";
				console.log(seat_info);
				
				if ($.isArray(already) && $.inArray(className,already) != -1) {   
																//선택 불가 좌석 목록에 속해 있다면
	                className += ' ' + settings.selectedSeatCss; //selectedSeat이라는 태그가 뒤에 붙는다.
	                console.log(className)
	            }
				else{
					className += ' ' + settings.classCssPrefix3;
					console.log(className)
					seat_info += ' '+  settings.classCssPrefix3;
					console.log("title에 들어갈 이름: "+seat_info);
				}
				
				var new_height = i*settings.seatHeight+240;
				var new_width = j*settings.seatHeight+460;
	            str.push('<li class="' + className + '"' + 
	            		'style="top:' + (new_height).toString() + 'px;left:' 
	            					  + (new_width).toString() + 'px">'+
	            					'<a title="' +seat_info+ '">'+' '+ '</a>'  
	            					+'</li>'); //float:left -> 가로 방향으로 좌석을 배치한다. li 특성.
	           }
			
			}
		console.log(str);
		$('#place').html(str.join('')); //place라는 id 태그에 붙는다. 해당 태그는 ul이다.
	};
blockC2(bookedSeats);



//좌석 선택 이벤트.-------------------------------------------------------------------------**//
$('.' + settings.seatCss).click(function () {
	if ($(this).hasClass(settings.selectedSeatCss)){ //버튼의 해당 클래스를 쫓아서 간다.
	    alert('다른 고객님이 결제 중인 좌석입니다.');
	}
	else if($(this).hasClass(settings.disableSeatCss)){
		alert('좌석을 선택해주세요.')
	}	
	
	else{		
		$(this).toggleClass(settings.selectingSeatCss); //선택 좌석은 토글 방식으로  
	    var cnt= 0;
	    var str = [];
	    
	    $.each($('#place li.'+ settings.selectingSeatCss + ' a'), function (index, value) {
	        str.push($(this).attr('title'));
	        cnt +=1;
	        if(cnt>3){	        	
	        	alert("최대 3매까지 구매 가능합니다.");	        	
	        	 //마지막에 넣은 str 선택 좌석을 array에서 뺀다. 	        	
	        	str.pop();
	        	//class 명 중 selectingSeat이라고 되어 있는 것을 지우는 방법을 찾아야 한다.**** 유일한 오류...        	
	        }
	    });	    
	    //str.join('\n')
	    console.log("메인화면에 나타날 좌석:"+str);
	    console.log("첫번째 좌석: "+str[0]);
	    console.log("두번째 좌석: "+str[1]);
	    console.log("세번쨰 좌석: "+str[2]);
	    $('#s_seat').html(str.join('  <br>')); //줄바꿈 join으로 들어간다. 문자열로 받았지만 html에 써지는 것은 text 타입이다.	    
	}
	///////////////////////////////////////////////////////////////////////
	function getInnerText(){
		
		var seatSelect = document.seatSelect; //form 태그와 연결.
		
		var element = document.getElementById('s_seat'); //s_seat이라는  div 태그 안의 텍스트 내용을 반환하도록.
		///return element.innerHTML; //문자열 	
		var temp;
		var temp2;
		var seats = []; //문자열 배열 형태로 넘겨줄 것이다.
		temp = element.innerText; //innerHTML
		temp2 = temp.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n'); //<br> 태그를 줄바꿈으로 바꿈.
		temp2.trim();	

		seats.push = temp.split('\r\n');
		return seats;		
		}
		
		var temp = getInnerText();
		console.log("값이 제대로 들어갔는가"+temp);
		//console.log("배열의 사이즈"+temp.length);
		//jquery
		var jb = $('#s_seat').text();
		$('#select_complete').val(jb);
	////////////////////////////////////////////////////
	
	});
	
	 
	///////////////////////////////////////////////////////////////////////////////////
	$('#btnShow').click(function () { 
	    var str = [];			
	    $.each($('#place li.' + settings.selectedSeatCss + ' a, #place li.'+ settings.selectingSeatCss + ' a'), function (index, value) {
	        str.push($(this).attr('title'));
	    });
	    alert(str.join('\n')); //줄바꿈 
	   
	
	})
	//사용자가 선택한 좌석
	$('#btnShowNew').click(function () { 
	    var str = [], item;
	    $.each($('#place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
	        item = $(this).attr('title');                   
	        str.push(item);                   
	    });
	    alert(str.join('\n')); //줄바꿈
	})