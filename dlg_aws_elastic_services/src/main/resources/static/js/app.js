jQuery(document).ready(function($) {
	
$('#meetingroomDetails').on('click', function() {
	alert("Gajula roomFinder");
	$.ajax({            
		url: 'http://localhost:8080/getPolicyDetailsFromAwsbucket',
		type: 'GET',
		contentType: 'application/json',
	}).success(function(data){
		alert("Gajula roomFinder"+data);
		$('#roomFinder').html('<table><thead><tr><th>Meeting Room Details</th> ');
		$('#roomFinder').append('<th>Contact Name</th><th>City</th><th>Country</th></tr></table>');
		$.each(data, function(index, element) {
			$('#roomFinder').append('<tr><td>' + element.policyId + '</td><td>' + element.policyName + '</td></tr>');
		});
	}).error(function(){
		alert('Service Error');
	});
});	

});
