$('input[type="file"]').on('change',function(e){
	var nameFile = e.target.files[0].name;
	$('#labelInputFile').text(nameFile);
})
