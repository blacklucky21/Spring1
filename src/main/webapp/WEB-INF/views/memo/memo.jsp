<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	#memoForm{width: 420px; margin: auto;}
	.table{text-align: center; margin: auto;}
	.table th, td{padding: 5px;}
</style>
<title>Insert title here</title>
</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<br><br>
	
    <form action="${contextPath}/memo/insertMemo.do" method="post" id="memoForm">
        <input type="text" name="memo" placeholder="메모" required/>&nbsp;
        <input type="password" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
        <button type="submit">저장</button>
    </form>
    
    <br><br>
    
    <!-- 메모목록 -->
    <table class="table">
        <tr>
            <th>번호</th>
            <th width="500px;">메모</th>
            <th width="300px;">날짜</th>
            <th>삭제</th>
        </tr>
       
        
        	<c:forEach var="mm" items="${ list }">
        	 <tr>
            <th>${mm.MEMONO}</th>
            <th width="500px;">${mm.MEMO}</th>
            <th width="300px;">${mm.MEMODATE}</th>
            <th><button type="button" style="cursor:pointer" onclick="deleteMemo('${mm.MEMONO}')">삭제</button></th>
        </tr>
        	</c:forEach>
    </table>
    <form name="memoDelForm" action="deleteMemo.do" method="post">
    	<input type="hidden" name="no">
    	<input type="hidden" name="password">
    
    </form>
    
    <script>
    
    function deleteMemo(no){
    	var delmemo = document.memoDelForm;
    	delmemo.no.value = no;
    	delmemo.password.value = prompt("비밀번호 함 쳐봐라");
    	delmemo.submit();
    	
    }
    
    </script>
</body>
</html>