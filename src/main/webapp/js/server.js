
$().ready(function() {
    reset();
});

$('#reset').click(function() {
    reset();
});

$('#start').click(function() {
    reset();
    JSON(endpoint() + '/server/json/', '#JSON');
    JSON(endpoint() + '/server/json/?origin=' +origin(), '#JSON-CORS');
    JSON(endpoint() + '/server/jsonp/?callback=?', '#JSONP');

    IMAGE(endpoint() + '/server/image/', '#IMAGE');
    IMAGE(endpoint() + '/server/image/redirect/', '#IMAGE-REDIRECT');

    COOKIE(endpoint() + '/server/cookie/', '#COOKIE', 'DATA');
    COOKIE(endpoint() + '/server/cookie/?domain=' + origin(), '#COOKIE-DOMAIN', 'MYDATA');
});

function COOKIE(url, id, name) {
    var img = $("<img src='"+url+"' style='display:none' width='0' height='0' tabindex='-1' title='empty'></img>");
    img.load(function () {
        var data = $.parseJSON($.cookie(name));
        showResult(id, function() {return data.payload == 'hello';});
    });
    $(id).append(img);
}

function reset() {
    $('#test-cases div').removeClass().addClass('unknown');
}

function endpoint() {
    return $('#endpoint').val();
}

function origin() {
    return $('#origin').val();
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