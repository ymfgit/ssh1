<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		//1.点击delete时，弹出确定要删除xx信息吗？若确定则删除，取消，则不删除
		$(".delete").click(function(){
			var lastName = $(this).next(":input").val();
			var flag = confirm("确定要删除"+lastName+"的信息吗？");
			if(flag){
				var $tr = $(this).parent().parent();
				//删除，使用ajax的方式
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					//若data返回值为1，则删除成功，删除当前行
					if(data==1){
						alert("删除成功！！")
						$tr.remove();
					}else{
						//若data返回值不为1，删除失败
						alert("删除失败！！");
					}
					
				});
			}
			return false;
		});
	})
</script>

</head>
<body>
	<h3>Employee list page</h3>
	<s:if test="#request.employees==null||#request.employees.size==0">
		没有任何员工信息
	</s:if>
	
	<s:else>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<td>ID</td>
				<td>LASTNAME</td>
				<td>EMAIL</td>
				<td>BIRTH</td>
				<td>CREATETIME</td>
				<td>DEPT</td>
				<td>DELETE</td>
				<td>EDIT</td>
			</tr>
			<s:iterator value="#request.employees">
				<tr>
					<td>${id }</td>
					<td>${lastName }</td>
					<td>${email }</td>
					<td>
						<s:date name="birth" format="yyyy-MM-dd"/>
					</td>
					<td>
						<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/>
					</td>
					<td>${dept.deptName }</td>
					<td>
						<a href="emp-delete?id=${id }" class="delete">DELETE</a>
						<input type="hidden" value="${lastName }"/>
					</td>
					<td>
						<a href="emp-input?id=${id }" >EDIT</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:else>
</body>
</html>