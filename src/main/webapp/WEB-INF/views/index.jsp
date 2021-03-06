<!DOCTYPE html>
<html>
<head>
    <title>Cross origin interoperability test</title>
    <!--
    <link rel="stylesheet" type="text/css" href="lib/css/jasmine.css">
    -->
    <link rel="stylesheet" type="text/css" href="css/app.css">
    
    
</head>
<body>
<div id="configuration">
    <label for="origin">Origin: </label><input id="origin" value="" />
    <label for="endpoint">Endpoint: </label><input id="endpoint" value="http://env-0159192.j.layershift.co.uk/crossOrigin/server/" />
    
    <label for="CSP">CSP: </label>
    <textarea id="CSP" rows="3" cols="60"></textarea>
    <label for="CSP">Report only CSP: </label>
    <input type="checkbox" id="reportCSP">
    <input id="setHeader" type="button" value="Set Header" />
    <input id="start" type="button" value="Start" />
    <input id="reset" type="button" value="Reset" />
</div>
<div id="test-cases">
    <div id="JSON"><a href="http://en.wikipedia.org/wiki/JSON">JSON</a></div>
    <div id="JSONP"><a href="http://en.wikipedia.org/wiki/JSONP">JSONP</a></div>
    <div id="JSON-CORS"><a href="http://en.wikipedia.org/wiki/Cross-origin_resource_sharing">Cross-origin_resource_sharing (CORS)</a></div>
    <div id="JSON-POSTMESSAGES"><a href="http://en.wikipedia.org/wiki/Cross-document_messaging">Cross-document_messaging (postMessage)</a> <a href="https://developer.mozilla.org/en-US/docs/DOM/window.postMessage">-- window.postmessage doc</a></div>
    <div id="IMAGE">Image</div>
    <div id="IMAGE-REDIRECT">Image thought a redirect</div>
    <div id="COOKIE"><a href="http://en.wikipedia.org/wiki/HTTP_cookie">Cookie</a></div>
    <div id="COOKIE-THIRD-PARTY"><a href="http://en.wikipedia.org/wiki/HTTP_cookie#Privacy_and_third-party_cookies">Third party cookies</a></div>
    <br>
    
</div>
<div id="CSP"><a href="https://developer.mozilla.org/en-US/docs/Security/CSP/Using_Content_Security_Policy">CSP</a></div>

<div id="csp-tests">
	<div>
		<b>CSP tests: </b> below are a few sample csp-header configurations that are of testing interest 
	</div>
	<ul>
		<li>This settings should break the image cross-origin loading - - <b>default-src *; img-src 'self'</b> <input type="button" value="setHeader" onclick="COPYCSP('default-src *\nimg-src \'self\'')"></li>
		<li>
			<a href="/invalidate-session">Invalidate session</a>
		</li>
	</ul>
		
</div>
</body>
<script src="lib/js/jquery.min.js"></script>
<script src="lib/js/jquery.cookie.js"></script>
<!--
<script src="lib/js/jasmine.js"></script>
<script src="lib/js/jasmine-html.js"></script>
-->
<script src="js/server.js"></script>
</html>