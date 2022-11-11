<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Listar empresas e saldo das contas</title>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.jqueryui.min.css">
	<style>
		@import url('https://fonts.googleapis.com/css?family=Roboto');

:root {
	--main-theme-color: #171616;
	--main-dark-color:#02be3b;
  }
  /* html,body,* { box-sizing: border-box;}
  html,body   { height: 100%; text-align: center;} */
  body        { padding: 2rem; background: #89898990;margin: 40px;}

div.box{
	display: grid;
  	grid-template-columns: 600px 600px ;
  	grid-gap: 300px;
	/* position: absolute;
	top: 50%;
	left: 50%; */
	/* width: 100%; */
	/* padding: 40px; */
	/* transform: translate(-50%, -50%); */
	/* background: rgba(0,0,0,.5);
	box-sizing: border-box;
	box-shadow: 0 15px 25px rgba(0,0,0,.6);
	border-radius: 10px;
	margin: 3em; */
}

div.divTable{
	border-radius: 5px;
  	padding: 20px;
}
	</style>
</head>
<body>
<div class="box">
	@foreach ($companies as $company)
	<div class="divTable">
	<span>{{$company->legal_person_name}}</span>
	<table id="{{$company->id}}" class="tableCompanies" style="width: 100%;">
		<thead>
			<tr>
				<th>Tipo de operação</th>
				<th>Entrada/Saída</th>
				<th>Valor</th>
		  </tr>
		</thead>
		<tbody>
		  @foreach ($company->cnabFiles as $cnabFile)



		<tr>

			@switch($cnabFile->typeTransaction->transaction_code)
				@case(1)
						<td>Debito</td>
					@break
				@case(2)
						<td>Boleto</td>
					@break
				@case(3)
						<td>Financiamento</td>
					@break
				@case(4)
						<td>Crédito</td>
					@break
				@case(5)
						<td>Recebimento Empréstimo</td>
					@break
				@case(6)
						<td>Vendas</td>
					@break
				@case(7)
						<td>Recebimento TED</td>
					@break
				@case(8)
						<td>Recebimento DOC</td>
					@break
				@case(9)
						<td>Aluguel</td>
					@break

			@endswitch

			@switch($cnabFile->typeTransaction->operation_type)

				@case("input")
					<td>Entrada +</td>
					@break
				@case("output")
					<td>Saída -</td>
					@break
				@default
			@endswitch


			<td>{{$cnabFile->transaction_value}}</td>
		</tr>

		@endforeach
		<tfoot>
			<tr>
				<th>Saldo da conta: <br> {{"R$ ". number_format($company->account_balance,2)}} </th>
				<th></th>
				<th></th>

			</tr>
		</tfoot>
		</tbody>
	</table>
</div>
	@endforeach
</div>
<script src="https://code.jquery.com/jquery-3.6.1.slim.min.js" integrity="sha256-w8CvhFs7iHNVUtnSP0YKEg00p9Ih13rlL9zGqvLdePA=" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function () {
    $('table.tableCompanies').DataTable({
  dom: 'rtip',
  "pageLength": 15,
  paging: true,
  lengthChange: false,
  searching: true,
  ordering: true,
  info: false,
  autoWidth: false,
  fixedHeader: {
        footer: true
    }
});
	});
</script>
</body>
</html>
