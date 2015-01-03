// Activates the Carousel
$('.carousel').carousel({
	interval : 5000
})

// Activates Tooltips for Social Links
$('.tooltip-social').tooltip({
	selector : "a[data-toggle=tooltip]"
})

$(function() {
	var offset = 200;
	var backToTop = $('#back-to-top');

	backToTop.hide();

	$(window).scroll(function() {
		if ($(this).scrollTop() > offset) {
			backToTop.fadeIn();
		} else {
			backToTop.fadeOut();
		}
	});

	backToTop.click(function(e) {
		e.preventDefault();
		$('body, html').animate({
			scrollTop : 0
		}, 500);
	});
});