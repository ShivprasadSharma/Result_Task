<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<style>
		.qq{
		     background-color: #F4F6F6; padding: 13px; border: 2px solid #2faf2f; border-top:none; border-radius: 4px; box-sizing: border-box; margin-bottom: 16px;   
		}  
		.head
		{
		   height: 35px; box-shadow: 0px 6px 10px -2px rgba(0, 0, 0, 0.6); text-align: center;background-color: #4CAF50;color: white;
		}
	</style>
	<body class="hold-transition skin-blue fixed sidebar-mini">
		<div class="wrapper">
		  <div class="content-wrapper" >
		  <div class="" style="overflow: auto; height:89%; ">
		  	<div class="col-md-12" style="padding-top: 27px;">
		  	  <c:if test="${not empty educationDtl}">
		    	<c:forEach var="eduDtl" items="${educationDtl}">
		    	    <section style="padding-top: 13px" >
		    	    	<div class="head"><h4 align="center">${eduDtl.standard}  ${eduDtl.semester.sem_name}</h4></div>
		    	    </section>
		    		<section class="content-header " >
		      	 		<div class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">
  		  		 			 <div class="col-sm-12 " >
  		  		 	 			<form:form modelAttribute="eduDtls"  method="POST">
	  		  		 	 			<div class="row  form-group" style="font-family: fantasy">
	  		  		 	 				<div class="col-md-6" >
	  		  		 	 				 <form:hidden path="edu_id" value="${eduDtl.edu_id}"/>
		  		  		 	 			   <form:hidden  path="clientName.id" value="${eduDtl.clientName.id}" />
		  		  		 	 			  
		  		  		 	 			   <form:label path="standard">Year : </form:label>
		  		  		 	 				 <form:input class="form-control" path="standard" value="${eduDtl.standard}" disabled="true"/>
		  		  		 	 				<c:if test="${not empty eduDtl.semester}"> 
		  		  		 	 			   		<form:label path="semester.semid">Semester : </form:label>
		  		  		 	 			   			<form:hidden  path="semester.semid" value="${eduDtl.semester.semid}"/>
		  		  		 	 				 		<form:input class="form-control" path="semester.sem_name" value="${eduDtl.semester.sem_name}"/>
		  		  		 	 				</c:if>
	  		  		 	 				</div>
	  		  		 	 				<div class="col-md-6">
	  		  		 	 				  <form:label path="persentage">Marks in % :</form:label>
		  		  		 	 				 <form:input class="form-control" path="persentage" value="${eduDtl.persentage}"/>
			  		  		 	 		   <c:if test="${(eduDtl.standard ne 'SSC') and (eduDtl.standard ne 'HSC') and (eduDtl.standard ne 'DIPLOMA')}">		
			  		  		 	 			 <c:set var="selectedvalue" value="${eduDtl.resultModel.result_id  }" /> 
			  		  		 	 			   <form:label path="resultModel">Result : </form:label>
			  		  		 	 				<form:select path="resultModel.result_id" class="form-control">
			  		  		 	 				 	<c:forEach var="result" items="${resultType}">
			  		  		 	 				 		<form:option value="${result.result_id}" selected="${result.result_id == selectedvalue ? 'selected':''}" >${result.result_type}</form:option>
			  		  		 	 				 	</c:forEach>
			  		  		 	 				</form:select> 
			  		  		 	 			</c:if>		 
		  		  		 	 			</div>
		  		  		 	 		</div>   
	  		  		 	 			<div align="right">
	  		  		 	 			  <form:button value="Submit"  onclick="form.action='update';" class="btnn btn-success" >Submit</form:button>
	  		  		 	 			  <form:button  onclick="form.action='remove';" class="btnn btn-danger" >Remove</form:button>
	  		  		 	 			</div>
	  		  		 	 			   	 
  		  		 	 			</form:form>
  		  		 	 		 </div>
  		  		  		</div>	
			  		</section>
		    	   </c:forEach>
		    	 </c:if>
		       </div>
		    </div>  	
		  </div>
		</div>	
	</body>
</html>