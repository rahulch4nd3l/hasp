<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Dashboard - Technician</title>
    
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/ionicons.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/Contact-Form-Clean.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/Login-Form-Dark.css"/>">
</head>

<body id="page-top" style="min-height:100%; max-height:auto;" onload = "toast()">
<%@ page import="com.rahul.service.userService" %>	

<script>
	function toast() {
		if(${up} == false)
			alert("${message}");
		
	}
	

</script>
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-mobile-alt"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>HASP</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item"><a class="nav-link" href="dashboard"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icon-tabler-dashboard" style="margin-right: 5px;">
                                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                                <circle cx="12" cy="13" r="2"></circle>
                                <line x1="13.45" y1="11.55" x2="15.5" y2="9.5"></line>
                                <path d="M6.4 20a9 9 0 1 1 11.2 0Z"></path>
                            </svg><span>Dashboard</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="servicepage"><i class="fas fa-table"></i><span>Service History</span></a></li>
                    <li class="nav-item"></li>
                    <li class="nav-item"></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle me-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <ul class="navbar-nav flex-nowrap ms-auto">
                            <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><i class="fas fa-search"></i></a>
                                <div class="dropdown-menu dropdown-menu-end p-3 animated--grow-in" aria-labelledby="searchDropdown">
                                    <form class="me-auto navbar-search w-100">
                                        <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                                            <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <div class="d-none d-sm-block topbar-divider"></div>
                            <li class="nav-item dropdown no-arrow">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><span class="d-none d-lg-inline me-2 text-gray-600 small">Hello!&nbsp;${tech.firstName}</span><img class="border rounded-circle img-profile" src="../assets/img/avatars/avatar5.jpeg"></a>
                                    <div class="dropdown-menu shadow dropdown-menu-end animated--grow-in"><a class="dropdown-item" href="profilepage"><i class="fas fa-user fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Profile</a><a class="dropdown-item" href="settings"><i class="fas fa-cogs fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Settings</a>
                                        <div class="dropdown-divider"></div><a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="container-fluid" style="max-height:70%;">
                    <h2>Pending Requests</h2>
                    <section style="height: auto;margin: 0px;margin-top: 10px;">
                        <div class="card shadow">
                            <div class="card-header py-3">
                                <p class="text-primary m-0 fw-bold">List of Requests</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">
                                    <table class="table my-0" id="dataTable">
                                        <thead>
                                            <tr>
                                                <th>Client Name</th>
                                                <th>Schedule Info</th>
                                                <th>City</th>
                                                <th>Client Phone</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var = "serv" items = "${serviceData}" >
                                        
                                            <tr>
                                                <td>${serv.name}</td>
                                                <td>${serv.date}<br></td>
                                                <td>${serv.city}</td>
                                                <td>${serv.phone}</td>
                                            </tr>
                                            
                                        </c:forEach>  
                                            
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td><strong>Client Name</strong></td>
                                                <td><strong>Schedule Info</strong></td>
                                                <td><strong>City</strong></td>
                                                <td style="font-weight: bold;">Client Phone</td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="container-fluid" style="margin-bottom:0%; padding-top:10px">
                    <h2>Update</h2>
                    <section style="height: 256px;margin: 0px;margin-top: 10px;">
                        <form method="post" action = "updateStatus">
                            <h2 class="text-center">Enter Details</h2>
                            <div class="mb-3"><input class="form-control" type="text" name="client" placeholder="Enter Client name"></div>
                            <div class="mb-3"><select class="form-select" name="serviceType" value="${tech.service}">
                                    <option value="undefined" selected="">Select Service Type</option>
                                    <option value="fan">Fan</option>
                                    <option value="ac">AC</option>
                                    <option value="tv">TV</option>
                                    <option value="electrical">Electrical Wiring</option>
                                    <option value="washingMachine">Washing Machine</option>
                                </select></div>
                            <div class="mb-3"><input class="form-control" type="date" name="date"></div>
                            <div class="mb-3"><button class="btn btn-primary" type="submit">Change Status</button></div>
                        </form>
                    </section>
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright � Rahul Chandel 2022</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
    <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../assets/js/theme.js"></script>
</body>

</html>