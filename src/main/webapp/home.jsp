<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Image Drop</title>

<link href="stylesheets/bootstrap.min.css" rel="stylesheet">
<link href="stylesheets/sb-admin-2.css" rel="stylesheet">
<link href="stylesheets/font-awesome.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="" rel="stylesheet" type="text/css">


</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				</button>
				<a class="navbar-brand" href="index.html">Image Drop</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="logout.action"><i class="fa fa-sign-out fa-fw"></i>
								Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="createNew.action"><i
								class="fa fa-dashboard fa-fw"></i>Upload</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<s:set var="userId" value="user.userId" />
						
						<s:iterator value="mediaList" status="mediaListStatus" var="media">
							<div class="row">
								<s:property value="#ownerId"/>
								<div class="col-lg-8 col-lg-offset-2">
									<div class="panel panel-default">
										<div class="panel-heading"><s:property value="fileName"/></div>
										<div class="panel-body">
											<a href="showMedia.action?mediaId=<s:property value="mediaId"/>"><img src=<s:property value="filePath" /> class="img-responsive" alt="Cinque Terre" width="30%" height="30%"></a>
										</div>
										<div class="panel-footer">
											<s:property value="firstName"/><br>
											<s:if test="ownerProp == 1">
												<a href="deleteMedia.action?mediaId=<s:property value="mediaId"/>" class="btn btn-info" role="button">Delete</a>
											</s:if>
										</div>
									</div>
								</div>
							</div>
						</s:iterator>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->


	<!-- jQuery -->
	<script src="javascripts/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="javascripts/bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="javascripts/sb-admin-2.js"></script>

</body>

</html>
