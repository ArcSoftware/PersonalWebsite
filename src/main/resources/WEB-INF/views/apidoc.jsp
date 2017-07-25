<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	response.setHeader("X-Frame-Options", "ALLOWALL");
	response.setHeader("Access-Control-Allow-Origin: ", "*");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Example API</title>
	<link rel="icon" type="image/png" href="${contextPath}/resources/images/favicon-32x32.png" sizes="32x32"/>
	<link rel="icon" type="image/png" href="${contextPath}/resources/images/favicon-16x16.png" sizes="16x16"/>
	<link href='${contextPath}/resources/css/typography.css' media='screen' rel='stylesheet' type='text/css'/>
	<link href='${contextPath}/resources/css/reset.css' media='screen' rel='stylesheet' type='text/css'/>
	<link href='${contextPath}/resources/css/screen.css' media='screen' rel='stylesheet' type='text/css'/>
	<link href='${contextPath}/resources/css/reset.css' media='print' rel='stylesheet' type='text/css'/>
	<link href='${contextPath}/resources/css/print.css' media='print' rel='stylesheet' type='text/css'/>

	<script src='${contextPath}/resources/lib/object-assign-pollyfill.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/jquery.slideto.min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/jquery.wiggle.min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/handlebars-4.0.5.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/lodash.min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/backbone-min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/swagger-ui.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/highlight.9.1.0.pack.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/highlight.9.1.0.pack_extended.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/jsoneditor.min.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/marked.js' type='text/javascript'></script>
	<script src='${contextPath}/resources/lib/swagger-oauth.js' type='text/javascript'></script>

	<!-- Some basic translations -->
	<!-- <script src='${contextPath}/resources/lang/translator.js' type='text/javascript'></script> -->
	<!-- <script src='${contextPath}/resources/lang/ru.js' type='text/javascript'></script> -->
	<!-- <script src='${contextPath}/resources/lang/en.js' type='text/javascript'></script> -->

	<script type="text/javascript">

        <%if("true".equalsIgnoreCase(request.getParameter("X-Frame"))){%>
        var siteDomain = "${siteDomain}";

        if (siteDomain == "localhost" || siteDomain == "" || siteDomain == null) {

        }
        else {
            document.domain = "${siteDomain}";
        }
        <%}%>


        $(function () {
            var url = window.location.search.match(/url=([^&]+)/);
            if (url && url.length > 1) {
                url = decodeURIComponent(url[1]);
            } else {
                url = "/v2/api-docs/";
                //url = "/uconsole/resources/swagger.json";
            }

            hljs.configure({
                highlightSizeThreshold: 5000
            });

            // Pre load translate...
            if (window.SwaggerTranslator) {
                window.SwaggerTranslator.translate();
            }
            window.swaggerUi = new SwaggerUi({
                url: url,
                validatorUrl: null,
                responseContentType: "application/json",
                dom_id: "swagger-ui-container",
                <%if("true".equalsIgnoreCase(request.getParameter("disabledSubmit"))){%>
                supportedSubmitMethods: [],
                <%}else{%>
                supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
                <%}%>
                onComplete: function (swaggerApi, swaggerUi) {
                    if (typeof initOAuth == "function") {
                        initOAuth({
                            clientId: "your-client-id",
                            clientSecret: "your-client-secret-if-required",
                            realm: "your-realms",
                            appName: "your-app-name",
                            scopeSeparator: " ",
                            additionalQueryStringParams: {}
                        });
                    }

                    if (window.SwaggerTranslator) {
                        window.SwaggerTranslator.translate();
                    }

                    var basicAuthUI =
                        '<h4 style="margin-bottom: 4px; font-weight: 800">API Auth for v1 calls</h4><hr/><div class="input" style="margin-bottom: 10px"><label for="input_username" style="display: inline-block; width: 100px; font-size: 14px">Username</label> <input placeholder="username" id="input_username" name="username" type="text" size="10" style="width: 200px"></div>' +
                        '<div class="input"><label for="input_apiKey" style="display: inline-block; width: 100px; font-size: 14px;">API Key</label> <input placeholder="api key" id="input_apiKey" name="apiKey" type="apiKey" size="10" style="width: 200px"></div><hr/>';
                    $(basicAuthUI).insertBefore('#resources_container');
                    $('#input_username').change(addAuthorization);
                    $('#input_apiKey').change(addAuthorization);

                },
                onFailure: function (data) {
                    log("Unable to Load SwaggerUI");
                },
                docExpansion: "none",
                defaultModelRendering: 'schema',
                showRequestHeaders: false
            });

            window.swaggerUi.load();

            function log() {
                if ('console' in window) {
                    console.log.apply(console, arguments);
                }
            }

            function addAuthorization() {
                var username = $('#input_username').val();
                var apiKey = $('#input_apiKey').val();
                if (username && username.trim() != "" && apiKey && apiKey.trim() != "") {
                    var basicAuth = new SwaggerClient.PasswordAuthorization('basic', username, apiKey);
                    window.swaggerUi.api.clientAuthorizations.add("basicAuth", basicAuth);
                    console.log("authorization added: username = " + username + ", apiKey = " + apiKey);
                }
            }





        });


        (function () {
            $(function () {
                var basicAuthUI =
                    '<div class="input"><input placeholder="username" id="input_username" name="username" type="text" size="10"></div>' +
                    '<div class="input"><input placeholder="apiKey" id="input_apiKey" name="apiKey" type="apiKey" size="10"></div>';
                $(basicAuthUI).insertBefore('#resources_container');
                //$("#input_apiKey").hide();
                $('#input_username').change(addAuthorization);
                $('#input_apiKey').change(addAuthorization);

                console.log("Done");
            });

            function addAuthorization() {
                var username = $('#input_username').val();
                var apiKey = $('#input_apiKey').val();
                if (username && username.trim() != "" && apiKey && apiKey.trim() != "") {
                    var basicAuth = new SwaggerClient.PasswordAuthorization('basic', username, apiKey);
                    window.swaggerUi.api.clientAuthorizations.add("basicAuth", basicAuth);
                    console.log("authorization added: username = " + username + ", apiKey = " + apiKey);
                }
            }
        })();
	</script>
</head>
<body class="swagger-section">
<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<%--<form id="auth">--%>
	<%--Username:&nbsp;<div class="input"><input placeholder="username" id="input_username" name="username" type="text" size="20"></div>--%>
	<%--API Key:&nbsp;&nbsp;<div class="input"><input placeholder="apiKey" id="input_apiKey" name="apiKey" type="password" size="20"></div>--%>
<%--</form>--%>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
