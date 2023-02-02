$(document).ready(function(){
    //console.log(getCookie("myCollapseOpen"));
     console.log(localStorage.getItem("collapseOpen"));
     if(getCookie("myCollapseOpen") == null){
		setCookie('myCollapseOpen','false',7)
     }
    	 
    collapseMaintain();
    
    collapseLocalStorage();
	    	 
});

function collapseReloadcheck(){
	$("#home-collapse").collapse("show");
	$("#home-collapse2").collapse("show");
	
	localStorage.setItem("collapseOpen","true");
}

function collapseLocalStorage(){
	if(localStorage.getItem("collapseOpen") == "true"){
		$("#home-collapse").collapse("show");
		$("#home-collapse2").collapse("show");
		localStorage.setItem("collapseOpen","false");
	}
}

function homeCollapse1(){
	$("#home-collapse").collapse("show");
}

function seasonClick(){
		$("#myCollapse").collapse("toggle");
		
		if(getCookie("myCollapseOpen") === "true"){
			setCookie('myCollapseOpen','false',7);
		} else{
			setCookie('myCollapseOpen','true',7);
		}
		
		console.log(getCookie("myCollapseOpen"));
}

function collapseMaintain(){
  if(getCookie("myCollapseOpen") === "true"){
    $("#myCollapse").collapse("show");
  } else{
    $("#myCollapse").collapse("hide");
  }
}

function setCookie(name, value, exp) {
			console.log("setcookie");
			var date = new Date();
			date.setTime(date.getTime() + exp*24*60*60*1000);
			document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

function getCookie(name) {
			var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
			return value? value[2] : null;
}