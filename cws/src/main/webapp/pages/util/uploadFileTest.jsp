<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//设置无缓存
	response.addHeader("Progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma", "no-cache");   
	response.setHeader("Cache-Control", "no-store");  
	response.setHeader("Cache-Control", "must-revalidate");
%>

<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- Bootstrap styles -->
	<link rel="stylesheet" href="<%=basePath%>css/jqueryFileUpload/bootstrap/3.2.0/css/bootstrap.min.css">
	<!-- Generic page styles -->
	<link rel="stylesheet" href="<%=basePath%>css/jqueryFileUpload/css/style.css">

	<link rel="stylesheet" href="<%=basePath%>css/jqueryFileUpload/css/jquery.fileupload.css">

	<link rel="stylesheet" href="<%=basePath%>css/jqueryFileUpload/css/jquery.fileupload-ui.css">
	<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
	<link rel="stylesheet" href="<%=basePath%>css/jqueryFileUpload/blueimp-gallery.min.css">



	<script src="<%=basePath%>js/jquery-1.8.2.min.js"></script>

	<script src="<%=basePath%>js/jqueryFileUpload/vendor/jquery.ui.widget.js"></script>

	<script src="<%=basePath%>js/jqueryFileUpload/tmpl.min.js"></script>
	<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
	<script src="<%=basePath%>js/jqueryFileUpload/load-image.all.min.js"></script>
	<!-- The Canvas to Blob plugin is included for image resizing functionality -->
	<script src="<%=basePath%>js/jqueryFileUpload/canvas-to-blob.min.js"></script>
	<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
	<script src="<%=basePath%>css/jqueryFileUpload/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.iframe-transport.js"></script>
	<!-- The basic File Upload plugin -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.fileupload.js"></script>
	<!-- The File Upload processing plugin -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.fileupload-process.js"></script>
	<!-- The File Upload image preview & resize plugin -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.fileupload-image.js"></script>
	<!-- The File Upload audio preview plugin -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.fileupload-audio.js"></script>
	<!-- The File Upload video preview plugin -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.fileupload-video.js"></script>
	<!-- The File Upload validation plugin -->
	<script src="<%=basePath%>js/jqueryFileUpload/jquery.fileupload-validate.js"></script>
<body>
<div class="container">
	<!-- The file upload form used as target for the file upload widget -->
	<form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
		<!-- Redirect browsers with JavaScript disabled to the origin page -->
		<noscript><input type="hidden" name="redirect" value="https://blueimp.github.io/jQuery-File-Upload/"></noscript>
		<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
		<div class="row fileupload-buttonbar">
			<div class="col-lg-7">
				<!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
				<button type="submit" class="btn btn-primary start">
					<span>Start upload</span>
				</button>
				<button type="reset" class="btn btn-warning cancel">
					<span>Cancel upload</span>
				</button>
				<button type="button" class="btn btn-danger delete">
					<span>Delete</span>
				</button>
				<input type="checkbox" class="toggle">
				<span class="fileupload-process"></span>
			</div>
			<!-- The global progress state -->
			<div class="col-lg-5 fileupload-progress fade">
				<!-- The global progress bar -->
				<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
					<div class="progress-bar progress-bar-success" style="width:0%;"></div>
				</div>
				<!-- The extended global progress state -->
				<div class="progress-extended">&nbsp;</div>
			</div>
		</div>
		<!-- The table listing the files available for upload/download -->
		<table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
	</form>
</div>
</body>
</html>

<script type="text/javascript">
	/*
	 * jQuery File Upload Plugin JS Example
	 * https://github.com/blueimp/jQuery-File-Upload
	 *
	 * Copyright 2010, Sebastian Tschan
	 * https://blueimp.net
	 *
	 * Licensed under the MIT license:
	 * http://www.opensource.org/licenses/MIT
	 */

	/* global $, window */

	$(function () {
		'use strict';

		// Initialize the jQuery File Upload widget:
		$('#fileupload').fileupload({
			// Uncomment the following to send cross-domain cookies:
			//xhrFields: {withCredentials: true},
			url: 'server/php/'
		});

		// Enable iframe cross-domain access via redirect option:
		$('#fileupload').fileupload(
				'option',
				'redirect',
				window.location.href.replace(
						/\/[^\/]*$/,
						'/cors/result.html?%s'
				)
		);

		if (window.location.hostname === 'localhost') {
			// Demo settings:
			$('#fileupload').fileupload('option', {
				url: '//jquery-file-upload.appspot.com/',
				// Enable image resizing, except for Android and Opera,
				// which actually support image resizing, but fail to
				// send Blob objects via XHR requests:
				disableImageResize: /Android(?!.*Chrome)|Opera/
						.test(window.navigator.userAgent),
				maxFileSize: 999000,
				acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
			});
			// Upload server status check for browsers with CORS support:
			if ($.support.cors) {
				$.ajax({
					url: '//jquery-file-upload.appspot.com/',
					type: 'HEAD'
				}).fail(function () {
					$('<div class="alert alert-danger"/>')
							.text('Upload server currently unavailable - ' +
									new Date())
							.appendTo('#fileupload');
				});
			}
		} else {
			// Load existing files:
			$('#fileupload').addClass('fileupload-processing');
			$.ajax({
				// Uncomment the following to send cross-domain cookies:
				//xhrFields: {withCredentials: true},
				url: $('#fileupload').fileupload('option', 'url'),
				dataType: 'json',
				context: $('#fileupload')[0]
			}).always(function () {
				$(this).removeClass('fileupload-processing');
			}).done(function (result) {
				$(this).fileupload('option', 'done')
						.call(this, $.Event('done'), {result: result});
			});
		}

	});
</script>