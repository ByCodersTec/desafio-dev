<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="{{ asset('css/uploadFormStyle.css') }}">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
		integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
		crossorigin="anonymous" referrerpolicy="no-referrer" />
	<title>Upload de arquivo</title>
</head>

<body>
	<div id="box">
		<a id="indexCompanies" href="{{route("index.companies")}}">Listar importações</a>
		@if($errors->any())
			<ul>
				@foreach($errors->all() as $error)
					<li class="error">{{ $error }}</li>
				@endforeach
			</ul>
		@endif
		<div id="title">
			<h1>Upload de arquivo</h1>
			<h2>CNAB</h2>
		</div>
		<div id="form">

			<form action="{{ route('upload.cnab.file.web') }}" method="POST"
				enctype="multipart/form-data">
				@csrf
				<label id="labelInputFile" for="textFileInput"><i class="fa-solid fa-upload"></i> Upload</label>
				<input type="file" name="cnab_file" id="textFileInput">
				<button name="submitForm" type="submit">Enviar</button>
			</form>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.1.slim.min.js"
		integrity="sha256-w8CvhFs7iHNVUtnSP0YKEg00p9Ih13rlL9zGqvLdePA=" crossorigin="anonymous"></script>
	<script src="{{ asset('js/uploadLabelScript.js') }}"></script>
</body>

</html>
