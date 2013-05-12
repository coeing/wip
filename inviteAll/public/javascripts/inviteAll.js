function showSmiley(style) {
	var imgElement = $("#decisionSmiley");

	var src = imgElement.attr("src").replace(/(.*)\/.*(\.png$)/i,
			'$1/' + style + '$2');
	imgElement.attr("src", src);
}

function changeDecision(option) {
	$("input[name='decision'][value='" + option + "']").click();
}

function changeRadio(name, option) {
	$("input[name='" + name + "'][value='" + option + "']").click();
}

function doClick(id) {
	$("#"+id).click();
}