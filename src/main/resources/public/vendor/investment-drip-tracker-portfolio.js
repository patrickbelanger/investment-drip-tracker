const apiPortfolioDelete	= "api/portfolio/";
const apiPortfolioPost	 	= "api/portfolio/";
const apiPortfolioGet 		= "api/portfolio/all";
const apiPortfolioPut 		= "api/portfolio/";
const itemMenuHtml			= '<li>' +
							  	'	<a href="javascript:void(0);">' +
							  	'		<span>%s</span>' +
								'	</a>';
								'</li>'; 

$(function () {
	updatePortfolio(renderPortfoliosList);
	setTimeout(function () { $('.page-loader-wrapper').fadeOut(); }, 50);
});

function updatePortfolio(callback) {
	doGet(apiPortfolioGet, function(data) {
		callback(data);
	});
}

function renderPortfoliosList(data) {
	for (var i = 0; i < data.length; i++) {
		$("#menu-portfolios").append(
			itemMenuHtml.replace("%s", "<b>" + data[i].accountType + "</b><br>" + data[i].accountTypeOtherDescription)
		);
		$("#portfolio-accountType").html(data[i].accountType);
		$("#portfolio-accountTypeOtherDescription").html(data[i].accountTypeOtherDescription);
		$
	}
}