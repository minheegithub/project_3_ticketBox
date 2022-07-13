$(function(){
	var w=$(window).width();
	var h;
	var total=3;
	var current=0;
	var distance=new Array();
	$(".key_control li").eq(current).addClass("active");

	for(var i=0; i<total; i++){
		distance.push(i*w);
	}
	// console.log("distance : "+distance.join(", "));

	$('a[href=""]').click(function(e){
		e.preventDefault();
	});

	var id=setInterval(function(){
		rightMoving();
	}, 2500);

	$(window).resize(function(){
		setTimeout(function(){
			w=$(window).width();
			h=$(".keyvisual_inner img").height();
			// console.log("h : "+h);

			$(".keyvisual li").animate({width:w}, 0);
			$(".keyvisual").animate({height:h}, 0);

			distance=new Array();

			for(var i=0; i<total; i++){
				distance.push(i*w);
			}
			// console.log("distance : "+distance.join(", "));
		}, 100);
	});
	$(window).trigger("resize");

	$("nav > ul").hover(
		function(){
			$(this).addClass("active");
		},
		function(){
			$(this).removeClass("active");
		}
	);
	$("nav > ul > li > a").hover(
		function(){
			$(this).addClass("active");
		},
		function(){
			$(this).removeClass("active");
		}
	);
	$("nav a").focusin(function(){
		var parentUl=$(this).parents("nav").children("ul")
		if(parentUl.hasClass("active") == false){
			parentUl.addClass("active");
		}
	});
	$("nav > ul").focusout(function(){
		var parentUl=$(this).parents("nav").children("ul")
		if(parentUl.hasClass("active") == true){
			parentUl.removeClass("active");
		}
	});
	$(".key_control li, .control_btn .prev, .control_btn .next").hover(
		function(){
			clearInterval(id);
		},
		function(){
			id=setInterval(function(){
				rightMoving();
			}, 2500);
		}
	);

	function leftMoving(){
		$(".keyvisual ul li:last").clone().prependTo(".keyvisual ul");
		$(".keyvisual ul").stop().animate({"margin-left":"-="+w+"px"}, 0);
		$(".keyvisual ul").stop().animate({"margin-left":"+="+w+"px"}, 400, function(){
			$(".keyvisual ul li:last").remove();

			if(current > 0){
				current--;
			}else{
				current=total-1;
			}
			// console.log("current : "+current);
			$(".key_control li").removeClass("active");
			$(".key_control li").eq(current).addClass("active");

			var arrayData=distance.shift();
			distance.push(arrayData);
			// console.log("distance : "+distance.join(", "));
		});
	}
	function rightMoving(){
		$(".keyvisual ul li:first").clone().appendTo(".keyvisual ul");
		$(".keyvisual ul").stop().animate({"margin-left":"-="+w+"px"}, 300, function(){
			$(".keyvisual ul").css({"margin-left":"+="+w+"px"});
			$(".keyvisual ul li:first").remove();

			if(current < total-1){
				current++;
			}else{
				current=0;
			}
			// console.log("current : "+current);
			$(".key_control li").removeClass("active");
			$(".key_control li").eq(current).addClass("active");

			var arrayData=distance.pop();
			distance.unshift(arrayData);
			// console.log("distance : "+distance.join(", "));
		});
	}

	$(".control_btn .prev").click(function(){
		rightMoving();
	});
	$(".control_btn .next").click(function(){
		leftMoving();
	});
	$(".key_control li").click(function(){
		$(".key_control li").removeClass("active");
		$(this).addClass("active");

		var x=(-1)*distance[$(this).index()];
		// console.log("x : "+x);
		$(".keyvisual ul").stop().animate({"margin-left":x+"px"}, 300);
	});
});