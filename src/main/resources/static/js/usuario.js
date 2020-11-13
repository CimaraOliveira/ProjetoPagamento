$('.pass').keyup(function(){
	$('#senha1').val() === $('#senha2').val()
	    ? $('#senha3').removeAttr('readonly')
	    : $('#senha3').attr('readonly', 'readonly');
});