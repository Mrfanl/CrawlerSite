/**
 * 
 */
$(document).ready(function(){
	
	if(window.location.pathname==="/CrawlerSite/index.do"){
		$('#page-header').text('教务处讯息');
		crwal("/CrawlerSite/jwc/crawl.do");
	}
		
	
	if(window.location.pathname==="/CrawlerSite/newsacademy.do"){
		$('#page-header').text('新传讯息');
		crwalaca("/CrawlerSite/academy/crawl.do",0);
	}
		
	if(window.location.pathname==="/CrawlerSite/computeracademy.do"){
		$('#page-header').text('计科院讯息');
		crwalaca("/CrawlerSite/academy/crawl.do",1);
	}
	if(window.location.pathname==="/CrawlerSite/literatureacademy.do"){
		$('#page-header').text('文学院讯息');
			crwalaca("/CrawlerSite/academy/crawl.do",2);
		}
	
})

function crwal(url){
	console.log(url);
  	$.ajax({
		url:url,
		type:'POST',
		data:'',
		cache:false,
		success:function(data){
			console.log(data);
			for(var i=0;i<data.length;i++){
				console.log(data[i]['diegest']);
			    var html = '<li class="list-group-item">'+
		    	'<a href="#" data-toggle="modal" data-target="#more-frame" onclick="lookMore(\''+data[i]['url']+'\')">'+data[i]['diegest']+'</a>'+
		    	'</li>';
			    if(i<12)
			    	$("#new-list").append(html);
			    else{
			    	if(i>=12*2)
			    		$("#new-list").append('<li class="list-group-item"><span style="color:blue"><空></span></li>')
			    	$("#info-list").append(html);
			    }
			    	
			}
		},
		error:function(data){
			console.log("error");
		}
	})
}
function lookMore(url){
	$(".modal-body").html("");
	var html = '<div class="embed-responsive embed-responsive-16by9">'+
	  '<iframe class="embed-responsive-item" src="'+url+'"></iframe>'+
	'</div>';
	$(".modal-body").append(html);
}

function crwalaca(url,id){
	console.log(url);
  	$.ajax({
		url:url,
		type:'POST',
		data:{"id":id},
		cache:false,
		success:function(data){
			console.log(data);
			for(var i =0;i<data['news'].length;i++){
				 var html = '<li class="list-group-item">'+
			    	'<a href="" data-toggle="modal" data-target="#more-frame" onclick="lookMoreaca('+(i+1)+')">'+data['news'][i]+'</a>'+
			    	'</li>';
				$("#new-list").append(html);
			}
			for(var i =0;i<data['info'].length;i++){
				 var html = '<li class="list-group-item">'+
			    	'<a href="" data-toggle="modal" data-target="#more-frame" onclick="lookMoreaca('+(data['news'].length+i+1)+')">'+data['info'][i]+'</a>'+
			    	'</li>';
				$("#info-list").append(html);
			}
		},
		error:function(data){
			console.log("error");
		}
	})
}

function lookMoreaca(id){
	var pid = 0;
	if(window.location.pathname==="/CrawlerSite/newsacademy.do")
		pid = 0;
	if(window.location.pathname==="/CrawlerSite/computeracademy.do")
		pid = 1;
	if(window.location.pathname==="/CrawlerSite/literatureacademy.do")
		pid = 2;
	$.ajax({
		url:'/CrawlerSite/academy/lookmore.do',
		type:'POST',
		data:{'pid':pid,"id":id},
		cache:false,
		success:function(data){
			$(".modal-body").html("");
			$(".modal-body").append(data['article']);
		}
	})
}

  
   
    