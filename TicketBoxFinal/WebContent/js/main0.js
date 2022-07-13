$(function(){
	var status;

	$(window).resize(function(){
		var w=$(this).width();
		// console.log("w : "+w);

		if(w <= 850){
			status="mobile";
		}else{
			status="pc";
			if($(".mobile_nav").hasClass("active") == true){
				$(".mobile_nav").removeClass("active");
				$(".mobile_nav .sub").css({"display":"none"});
				$(".transparency").removeClass("active");
				$(".container").removeClass("active");
			}
		}
	});
	$(window).trigger("resize");

	$(".nav ul").hover(
		function(){
			if(status == "pc"){
				$(this).addClass("over");
			}
		},
		function(){
			if(status == "pc"){
				$(this).removeClass("over");
			}
		}
	);
	$(".mobile_tab").click(function(e){
		e.preventDefault();
		$(".mobile_nav").addClass("active");
		$(".transparency").addClass("active");
		$(".container").addClass("active");
	});
	$(".mobile_nav > ul > li > a").click(function(e){
		e.preventDefault();
		if(status == "mobile"){
			if($(this).next(".sub").css("display") == "none"){
				$(".mobile_nav .sub").slideUp(300);
				$(this).next(".sub").slideDown(300);
			}else{
				$(this).next(".sub").slideUp(300);
			}
		}
	});
	$(".transparency").click(function(){
		$(".mobile_nav").removeClass("active");
		$(".mobile_nav .sub").css({"display":"none"});
		$(".transparency").removeClass("active");
		$(".container").removeClass("active");
	});
});