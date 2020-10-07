function calculaPrecio() {
	
	
	var priceRev = $("#priceRev").val();
	$("#tablePrice tbody").remove();
	if(priceRev == 0 || priceRev == ''){
		
		$("#tablePrice")
		.append(
				'<tbody><tr>'+
					'<td colspan="4">Teclea el precio para realizar el cálculo</td>'+
				'</tr></tbody>');
		
		alert('Debes capturar el precio del producto');
		
		return;
	}
	
	var priceCoco= priceRev*2;
	$('#tablePrice')
			.append(
					'<tbody><tr>'+
						'<td>elcoco.mx</td>'+
						'<td>'+priceRev+'</td>'+
						'<td class="text-danger">100%<i class="mdi mdi-arrow-up"></i></td>'+
						'<td>'+priceCoco+
						'</td>'+
					'</tr></tbody>');
	
	
	var priceML= priceCoco;
	$('#tablePrice')
			.append(
					'<tbody><tr>'+
						'<td>elcoco.mx</td>'+
						'<td>'+priceCoco+'</td>'+
						'<td class="text-danger">100%<i class="mdi mdi-arrow-up"></i></td>'+
						'<td>'+priceCoco+
						'</td>'+
					'</tr></tbody>');

}