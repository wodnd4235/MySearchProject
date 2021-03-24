$(function(){
	$('body').find('.search').on('click', function(){
		var word = $('input[name = keyword]').val();
		/*if(word.trim() == ''){
			alert('검색어를 입력해주세요.');
			$('input[name = keyword]').focus();
			return false;
		}*/
		// ajax 통신
		$.ajax({
		    type : "POST",           
		    url : "/search/area",   
		    data: $([name=major]).serialize(),
	  		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		    success : function(res){
				$("#searchList").empty();
				$('#searchList').show();
				var html =''
				html += '<h3>';
				
				if(res.placeList.length > 0){
					
					for(var i=0; i<res.placeList.length; i++){
						
						html +=	i+1+'.' + res.placeList[i];
						html +=	'<br/>';
						
					}
										
				}else{
					html += '검색 내역이 없습니다.';
				}
				
				html += '</h3>';
				$("#searchList").append(html);
					
		    },
		    error : function(e){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
				alert(e.responseJSON.message);
		    }
		});
		
		
		
	});
});