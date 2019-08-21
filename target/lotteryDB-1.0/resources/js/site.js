// JavaScript function for all
$(document).ready(function () {
	//Required for strict JS code
	'use strict';

	var headerOffset = $('header').offset().top;
	
	$(window).scroll(function () {
		var header = $('header'),
			scroll = $(window).scrollTop();

		if (scroll > headerOffset) {
			header.addClass('fixed');
		} else {
			header.removeClass('fixed');
		}
	});

});
