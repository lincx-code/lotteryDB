//JavaScript file for custom report page

$(document).ready(function () {
	"use strict";
	$('.datepicker').datetimepicker({
		value : '2015-12-21',
		format : 'Y-m-d H:i:s'
	});

// Show more options depends on report type
	var selectedReport = $("select option:selected").val();
	if (selectedReport === "actionLog") {
		$('#hiddenDate').slideDown("slow");
		$('#actionOptions').slideDown("slow");
		$('#userOptions').slideDown("slow");
	} else {
		$('#hiddenDate').hide();
		$('#actionOptions').hide();
		$('#userOptions').hide();
	}

	$('.custColumnsCheck').click(function () {
		if ($(this).is(":checked")) {
			$(this).parent().find(".customColumns").fadeIn("fast");
		} else if ($(this).is(":not(:checked)")) {
			$(this).parent().find(".customColumns").fadeOut("fast");
		}
	});
	
	$(".scrollContent").mCustomScrollbar({
    	theme:"dark" });

//Search user function
$('#searchUsers').keyup(function() {
		var valThis = this.value.toLowerCase();

		// prevent Enter key function
		$('#searchUsers').keypress(function(event) {
			if (event.keyCode === 10 || event.keyCode === 13) {
				event.preventDefault();
			}
		});

		$('.scrollContent p').each(function() {
			var user = ($(this).text()).toLowerCase();
			if ((user.toLowerCase()).indexOf(valThis) !== -1) {
				$(this).fadeIn(500);
			} else {
				$(this).fadeOut(300);
			}
		});

	});

//By default, custom table name field is hidden;
$('#tableNameField').hide();
	$('#tableNameCheck').click(function(){
		if( $(this).is(':checked')) {
        	$('#tableNameField').slideDown("slow");
    	} else {
        	$('#tableNameField').slideUp("slow");
    	}
	});		

});
