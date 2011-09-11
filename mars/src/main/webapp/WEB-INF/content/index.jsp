<%@ include file="../includes/head.jsp" %>
    <div id="container">
        <%@ include file="../includes/header.jsp" %>
        <div id="main" role="main">
            <div id="loginstatus">
                Logged in as: <sec:authentication property="principal.username"/>
            </div>
            <a href="<c:url value="/logout"/>">Logout</a>
        </div>
        <%@ include file="../includes/footer.jsp" %>
    </div>
<%@ include file="../includes/scripts.jsp" %>