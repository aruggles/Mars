/* Author: 

*/
$.ajaxSetup({
	'beforeSend' : function(xhr) {
	    xhr.setRequestHeader("Accept", "application/json");
	} 
});

function submitJson(form) {
	var $form = $('#' + form);
	var data = JSON.stringify($form.serializeObject());
	console.log('Action: ' + $form.attr('action'));
	console.log('Data: ' + data);
	$.ajax({
	    "url": $form.attr('action'),
	    "type": "POST",
	    "contentType":"application/json",
	    "data": data,
	    success: function(data) {
	    	console.log('Data is ' + data);
	    }
	});
	return false;
}






















