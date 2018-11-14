
        <header>
            
        <div id="slide-out" class="side-nav sn-bg-4 fixed">
            <ul class="custom-scrollbar">
          
            <li class="logo-sn waves-effect">
                <div class="text-center">
                   <a href="#"><img src="${contextPath}/resources/img/iconium.jpg" class="img-fluid flex-center"></a>
                </div>
            </li>
            </ul>
                <ul>
            <sec:authorize access="hasAuthority('GLOBAL')">
            <li>
              
                         <select name="name" id="company-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here..">
                                <option value="" disabled selected>Select Company</option>
                                 <c:forEach var="company" items="${companiez}">
                                    <option value="${company.id}">${company.companyName}</option>
                                </c:forEach>
                         </select>              
            </li>
            
            <li><h6 class="waves-effect black-text">${companyName}</h6></li>
         </sec:authorize>
          <sec:authorize access="hasAuthority('SUPER_ADMIN')">
            <li>        
                         <select name="name" id="company-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here..">
                                <option value="" disabled selected>Select Company</option>
                                 <c:forEach var="company" items="${companiez}">
                                    <option value="${company.id}">${company.companyName}</option>
                                </c:forEach>
                         </select>
                 
              
            </li>
            <li><h6 class="waves-effect black-text">${companyName}</h6></li>
         </sec:authorize>
            </ul> 
            
            <ul class="custom-scrollbar">
            
            <sec:authorize access="hasAuthority('SUPER_ADMIN')">
            <li>
                <ul class="collapsible collapsible-accordion">
                    
                    <li><a class="collapsible-header waves-effect arrow-r ${sapActive}"><i class="fa fa-tachometer"></i>Super Admin Panel<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a href="${contextPath}/admin/gb/dashboard" class="waves-effect">Dashboard</a></li>            
                            </ul>
                        </div>
                    </li>
                    <c:if test="${not empty company}">
                   
                    
                    <li><a class="collapsible-header waves-effect arrow-r ${apActive}"><i class="fa fa-user"></i>Admin Panel<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a href="${contextPath}/admin/dashboard" class="waves-effect">Dashboard</a></li>                                
                            </ul>
                        </div>
                    </li>
                   
                    <li class="active"><a class="collapsible-header waves-effect arrow-r  ${upActive}"><i class="fa fa-user"></i>User Panel<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                 
                                    <c:choose>
                                        <c:when test="${sales}">
                                                 <li><a href="${contextPath}/user/dashboard" class="waves-effect">Sales</a></li> 
                                       </c:when>
                                        <c:otherwise>
                                           <li><a class="waves-effect">Sales Module Not Active</a></li> 
                                        </c:otherwise>
                    
                                    </c:choose>
                              
                            </ul>
                        </div>
                    </li>
                 
                  </c:if>
                                     
                </ul>
            </li>
             </sec:authorize>
            
         
            <li>
                <ul class="collapsible collapsible-accordion">
                    <sec:authorize access="hasAuthority('GLOBAL')">
                    <li><a class="collapsible-header waves-effect arrow-r ${sapActive}"><i class="fa fa-tachometer"></i>Global Panel<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                              <li><a href="${contextPath}/admin/gb/dashboard" class="waves-effect">Dashboard</a></li>                               
                            </ul>
                        </div>
                    </li>
                  </sec:authorize>
                    <c:if test="${not empty company}">
                   <sec:authorize access="hasAnyAuthority('GLOBAL', 'ADMIN')">
                    
                    <li><a class="collapsible-header waves-effect arrow-r ${apActive}"><i class="fa fa-user"></i>Admin Panel<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a href="${contextPath}/admin/dashboard" class="waves-effect">Dashboard</a></li>                                
                            </ul>
                        </div>
                    </li>
                   
                  </sec:authorize>
                    
                 <sec:authorize access="hasAnyAuthority('GLOBAL', 'ADMIN', 'USER')">
                    <li><a class="collapsible-header waves-effect arrow-r ${upActive}"><i class="fa fa-user"></i>User Panel<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                 
                                    <c:choose>
                                        <c:when test="${sales}">
                                                 <li><a href="${contextPath}/user/dashboard" class="waves-effect">Sales</a></li> 
                                       </c:when>
                                        <c:otherwise>
                                           <li><a class="waves-effect">Sales Module Not Active</a></li> 
                                        </c:otherwise>
                    
                                    </c:choose>
                              
                            </ul>
                        </div>
                    </li>
                  </sec:authorize>
                  </c:if>
                    
      
                </ul>
            </li>
            
            </ul>
            <div class="sidenav-bg mask-strong"></div>
        </div>
        
        <nav class="navbar fixed-top navbar-expand-lg scrolling-navbar double-nav">
            <!-- SideNav slide-out button -->
            <div class="float-left">
                <a href="#" data-activates="slide-out" class="button-collapse black-text"><i class="fa fa-bars"></i></a>
            </div>
            <!-- Breadcrumb-->
            <div class="breadcrumb-dn mr-auto">
                <h4 class="blue-text">&nbsp;  <strong><c:out value="${orgName}" /></strong></h4>
            </div>

            <!--Navbar links-->
            <ul class="nav navbar-nav nav-flex-icons ml-auto">

                 <li class="nav-item ml-3">
                            <a class="nav-link waves-effect waves-light dark-grey-text font-weight-bold" href="${contextPath}/home"><i class="fa fa-home blue-text"></i> Home</a>
                   </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle waves-effect" href="#" id="userDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-user blue-text"></i> <span class="clearfix d-none d-sm-inline-block">${accName}</span></a>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="${contextPath}/logout">Log Out</a>
                        <a class="dropdown-item" href="${contextPath}/user/account/">My account</a>
                    </div>
                </li>

            </ul>
           
        </nav>
      

    </header>
