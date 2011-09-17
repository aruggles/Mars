<%@ include file="../includes/head.jsp" %>
    <div id="container">
        <%@include file="../includes/header.jsp" %>
        <div id="main" role="main">
            <s:if test="error">
	            <p class="error">${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
	        </s:if>
	        <div>
	            <p>Login Form:</p>
	            <form id="login" action="<c:url value="/login-process" />" method="post">
	                <ol>
	                    <li>
	                        <label for="j_username">User:</label>
	                        <input type="text" name="j_username" id="j_password" value="${SPRING_SECURITY_LAST_USERNAME}" />
	                    </li>
	                    <li>
	                        <label for="j_password">Password:</label>
	                        <input type="password" name="j_password" id="j_password" />
	                    </li>
	                    <li>
	                        <input type="submit" value="Login" />
	                    </li>
	                </ol>
	            </form>
	        </div>
	        <div>
	            <p>Register Form:</p>
	            <form id="register" action="<c:url value="/api/register" />" onsubmit="return submitJson('register');" method="post">
	                <ol>
	                    <li>
	                        <label for="username">User:</label>
	                        <input type="text" name="username" id="password" value="" />
	                    </li>
	                    <li>
	                        <label for="password">Password:</label>
	                        <input type="password" name="password" id="password" />
	                    </li>
	                    <li>
	                        <input type="submit" value="Register"/>
	                    </li>
	                </ol>
	            </form>
	        </div>
        </div>
        <%@include file="../includes/footer.jsp" %>
    </div>
<%@include file="../includes/scripts.jsp" %>