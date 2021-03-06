
$().ready(function() {
    reset();
    $("#origin").val(document.location.href);
    
});


$('#reset').click(function() {
    reset();
    resetHeaders();
    document.location="invalidate-session";
});

$('#start').click(function() {
    reset();
    
    JSON(endpoint() + 'json/', '#JSON');
    JSON(endpoint() + 'json/?origin=' +origin(), '#JSON-CORS');
    JSON(endpoint() + 'jsonp/?callback=?', '#JSONP');

    IMAGE(endpoint() + 'image/', '#IMAGE');
    IMAGE(endpoint() + 'image/redirect/', '#IMAGE-REDIRECT');

    COOKIE(endpoint() + 'cookie/', '#COOKIE', 'DATA');
    COOKIE(endpoint() + 'cookie/?domain=' + origin(), '#COOKIE-THIRD-PARTY', 'MYDATA');
    POSTMESSAGE(endpoint()+'postmessage/?origin=' +origin(), '#JSON-POSTMESSAGES');
    
});

$('#setHeader').click(function(){
	
	SETCSP(origin()+'headers/');
	
	if(origin()!=endpoint())
		SETCSP(endpoint()+'headers/');
	
});

function COPYCSP(cspHeader){
	$('#CSP').val($('#CSP').val()+cspHeader);
}

function SETCSP(url){
	
	var name=$('#reportCSP:checked').length?"X-Content-Security-Policy-Report-Only":"X-Content-Security-Policy";
	
	//var json='[{"name":"X-Content-Security-Policy", "value":"img-src \'self\'"},{"name":"X-Content-Security-Policy", "value":"script-src \'self\'"}]';
	var headers=cspParameters().split("\n");
	var headerValue="";
	for (var i=0; i<headers.length;i++) {
		headerValue+=" "+headers[i]+";";
	}
	
	$.post(url,{'headers':'[{"name":"'+name+'", "value":"'+headerValue+'"}]'}, function(){location.reload(true);});
}

function COOKIE(url, id, name) {
    var img = $("<img src='"+url+"' style='display:none' width='0' height='0' tabindex='-1' title='empty'></img>");
    img.load(function () {
        var data = $.parseJSON($.cookie(name));
        showResult(id, function() {return data.payload == 'hello';});
    });
    $(id).append(img);
}


function POSTMESSAGE(url, id){
	
	function receiveMessage(event){
		showResult(id, function() {return event.data.payload == 'hello';});
	}
	
	window.addEventListener("message", receiveMessage, false);
	
	
	var iframe=$("<iframe src='"+url+"' style='display:none'></iframe>");

	$(id).append(iframe);
}

function reset() {
    $('#test-cases div').removeClass().addClass('unknown');
}

function resetHeaders(){
	$.get(origin()+"headers/reset/");
    $.get(endpoint()+"headers/reset/");
}
function endpoint() {
    return $('#endpoint').val();
}

function origin() {
    return $('#origin').val();
}

function cspParameters(){
	return $('#CSP').val();
}

function IMAGE(url, id) {
    var img = new Image();

    img.onload = function () {
        // valid images have a height!
        if (this.height != '0') {
            showResult(id, function() {return true;});
        }
        else {
            showResult(id, function() {return false;});
        }
    }

    // on error gets called if the users isn't logged in
    img.onerror = function () {
        showResult(id, function() {return false;});
    }

    img.src = url;

}

function JSON(endPoint, id) {

    $.getJSON(endPoint, function(data) {
        showResult(id, function() {return data.payload == 'hello';});
    }).error(function() {
        showResult(id, function() {return false;});
    });
}

function showResult(id, success) {
    $(id).removeClass('unknown');
    if (success()) {
        $(id).addClass('success')
    } else {
        $(id).addClass('failure')
    }
}