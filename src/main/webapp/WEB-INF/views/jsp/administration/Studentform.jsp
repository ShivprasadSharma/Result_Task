<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<style>
body {font-family: Arial, Helvetica, sans-serif;
   background-color: #d3efc6;
}

input[type=text],input[type=email], input[type=date],input[type=number],select, textarea ,input[type=file]{
    width: 100%;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 5px;
    margin-bottom: 14px;
    resize: vertical;
    background-color: #f2f2f2;
     
}
.Submitbtn{
    background-color: #FFF;
    color: white;
    margin-left: 70%;
    margin-top: 30px;

}
input[type=submit], input[type=reset] {
	    background-color: #4CAF50;
	    color: white;
	    padding: 8px 15px;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	    width:110px;
	     font-size: 12px;
}
.container {
	  max-width: 95%;
	  padding: 1em 3em 2em 3em;
	  margin: 0em auto;
	  font-size: 20px;
	  background-color: #fff;
	  border-radius: 4.2px;
	  box-shadow: 0px 5px 15px -2px rgba(0, 0, 0, 0.2);
	  margin-left:20px;
	  margin-top:-15px;
}
.container1 {
	  max-width: 35em;
	  padding: 1em 1em 2em 3em;
	  margin: 0em auto;
	  font-size: 35px;
	  background-color: #fff;
	  border-radius: 4.2px;
	  box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);
	  margin-left:39em;
	  margin-top:-59.2em;
	  height:58.8em;
	}
ixmg {
	  border-radius: 50%;
	}
	th {
	  text-align: left;
	}

.row {
    font-size: 12px;
	padding: 0px 25px;
}
.left {
	  position: absolute;
	  top: 0;
	  left:20px;
	  box-sizing: border-box;
	  padding: 40px;
	  width: 500px;
	  height: 400px;
}
.right {
	  position: absolute;
	  top: 0;
	  right: 0;
	  box-sizing: border-box;
	  padding: 40px;
	  width: 300px;
	  height: 400px;
	  background: url('https://goo.gl/YbktSj');
	  background-size: cover;
	  background-position: center;
	  border-radius: 0 2px 2px 0;
	}
.head
{
	  width: 98%;
	  height: 35px;
	  border-radius: 5px;
	  box-shadow: 0px 6px 10px -2px rgba(0, 0, 0, 0.6);
	  padding: 1px 10px;
	  text-align: center;
	  background-color: #4CAF50;
	  color: white;
	  margin-top:10px;
}
</style>
</head>
<c:url var="updateStudentProfile" value="/web/taskforce/serives/student/updateStudentProfile" />
<body class="hold-transition skin-blue sidebar-mini" >
 <c:set var="parent" value="${parentprofile}"></c:set>
<div class="wrapper ">
 <div class="content-wrapper myStyle" >
   <form:form action="${updateStudentProfile}" method="post" modelAttribute="student">
<%--   <input type="hidden" name="comClientName_id" value="${sessionScope.user.comClientName.id} "/> 
 --%>    <input type="hidden" value="${student.id}"  name="client_id" > 
         <input type="hidden" value="${stud.studentId}"  name="studentId" > 
    <!-- Main content -->
   		 <section class="content " >
   			<div class="container">
			<hr>
  			<div class="head">
  				<h4><b>Personal.</b></h4>
  			</div>
  			<hr>
  		
  			 		 	  <div class="row">
		         			 <div class="col-sm-3"><b>First Name:</b> </div> 
		         			 <div class="col-sm-3"><b>Middle Name:</b> </div>
		         			 <div class="col-sm-3"><b>Last Name: </b> </div>
		         			 <div class="col-sm-3"><b>Mothers Name : </b></div>
		    			 </div>
		    			 <div class="row">
		       				 <div class="col-sm-3"><input type="text" id="name" name="firstName" value="${student.firstName}" required> </div>
		       				 <div class="col-sm-3"><input type="text" id="name" name="middleName"   value="${student.middleName}"     required> </div>
		       				 <div class="col-sm-3"><input type="text" id="name" name="lastName" value="${student.lastName}" required> </div>
		       				 <div class="col-sm-3"><input type="text" id="name" name="motherName"value="${student.motherName} "> </div>
		       			 </div>
		   				 <div class="row">
		         			 <div class="col-sm-3"><b>Contact No:</b> </div>
		         			 <div class="col-sm-3"><b>Contact No 2:</b> </div>
		         			 <div class="col-sm-3"><b>Date Of Birth:</b> </div>
		         			 <div class="col-sm-3"><b>Email:</b> </div> 
		         			
		         			 </div>
		         			
		    			 <div class="row">
		    				 <div class="col-sm-3"><input type="text" name="contactNos" value="${student.contactNos}"  required>  </div>
		    				 <div class="col-sm-3"><input type="text" name="contactNo2" value="${student.contactNo2}" >  </div>
		         			<div class="col-sm-3">
		                    <input type="date"  name="dateOfBirth" value="00/00/00"  id="datepicker" >
		         			</div>
		         			<div class="col-sm-3"><input type="email" name="emailId" value="${student.emailId}" required > </div>
		         			
		         			</div>
		    			
		    			 <div class="row"> 
		    			
		    			
		    			   <c:choose>
		    			  <c:when test="${empty parentprofile }">
		    			  <div class="col-sm-3"><input type="hidden" name="parentContactNo1"  placeholder="parent contact no.." required > </div>
			         	 <div class="col-sm-3"><input type="hidden" name="parentEmail"  placeholder="parent email.." > </div>
						</c:when> 
			         		     
		    		     		 <c:otherwise>
		    		     		  <c:forEach var="p" items="${parent}">
							     <div class="col-sm-3"><input type="hidden" name="parentContactNo1" value="${p.contact_no1}" placeholder="parent contact no.." required > </div>
			         		      <div class="col-sm-3"><input type="hidden" name="parentEmail" value="${p.email}" placeholder="parent email.." > </div>
			         		    </c:forEach> 
							    </c:otherwise>
							     </c:choose> 
					         </div> 
		    			 
		    			 <div class="row">
		    				  <div class="col-sm-3"><b>Address:</b> </div>
		    			 </div>
		      			 <div class="row"> 
		      			 <c:forEach var="a" items="${student.comClientAddresses}">
		      			 <div class="col-sm-12"><input type="text"  name="address1" value="${a.address1}" required>  </div>
		         		 	
          
            
		          			   </div>
		   				 <div class="row">
   				    		 <div class="col-sm-3"><b>Postalcode:</b> </div>
		         			 <div class="col-sm-3"><b>City: </b></div> 
		         			 <div class="col-sm-3"><b>State: </b></div>	
		         			 <div class="col-sm-3"><b>Country:</b> </div> 
		         		 </div>
		    			 <div class="row">
		    			 <div class="col-sm-3"><input type="text" id="s1" name="postalCode" value="${a.postalCode}"required> </div>	
		       				 <div class="col-sm-3"><input type="text" id="city" name="city" value="${a.city}"required> </div>
		       				 <div class="col-sm-3"><input type="text" id="s" name="state" value="${a.state}"required> </div>
		       				 <div class="col-sm-3"><input type="text" id="ci" name="country" value="${a.country}"required> </div>		   				
		   				 </div>
		   				    </c:forEach>
		 			     	 <!-- <div class="row">
		 			     	 <div class="col-sm-3"><b>Handicaped: </b> </div>
		 					
		   				 </div>
		    			 <div class="row">
		    				 <div class="col-sm-3"><input type="radio" name="isHandicaped" value=1 > Yes. &nbsp;&nbsp;&nbsp;<input type="radio" name="isHandicaped" value=0 >  No. </div>
		    			 </div>   -->
		    			
	  		   
  			<hr>
			<!-- <div class="head">
				<h4><b>Academic.</b></h4>
			</div>
  			<hr> -->
	       				 <div class="row">
		         			 <div class="col-sm-3"><b>Branch:</b> </div> 
		         			<div class="col-sm-3"><b>Year: </b></div>
		         			 
		         			 <div class="col-sm-3"><b>Standard/Division:</b> </div>
		    			 </div>
		    			 <div class="row">
		       				<div class="col-sm-3">
								<select  name="branch" >
								  <c:forEach var="item" items="${dep}">
								      <c:if test="${item.dep_id eq stud.branch}">
								        <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
								      </c:if>
								  </c:forEach>
							  	  <c:forEach var="item" items="${dep}">
                                      <c:if test="${item.dep_id ne 0  &&  item.dep_id ne stud.branch}">
				                       <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
			                          </c:if>
			                      </c:forEach>
								</select>
		     			 	 </div>
		     			 	 
		     			 	  <div class="col-sm-3">
					      		 <select  name="year">
					      		 <c:if test="${stud.year eq 1}">
					      		   <option value="1">FE</option>
					      		 </c:if>
					      		 <c:if test="${stud.year eq 2}">
					      		   <option value="2">SE</option>
					      		 </c:if>
					      		 <c:if test="${stud.year eq 3}">
					      		   <option value="3">TE</option>
					      		 </c:if>
					      		 <c:if test="${stud.year eq 4}">
					      		   <option value="4">BE</option>
					      		 </c:if>
								  <option value="1">FE</option>
								  <option value="2">SE</option>
								  <option value="3">TE</option>
								  <option value="4">BE</option>
								</select>
		       				 </div>
<!-- 		       				 <div class="col-sm-3"><input type="text"  name="grade" placeholder="Enter grade.." required> </div>
 --><!--  						 <div class="col-sm-3"><input type="text"  name="rollNo" placeholder="Enter roll no.." > </div>
 -->		       				<div class="col-sm-3"> <!-- <input type="text"  name="standard" placeholder="Enter standard.." required>  -->
		       				 	    	<select  name="standard" >	
		       				 	    	<c:if test="${stud.standard eq 1}">
					      		               <option value="1">A</option>
					      		               <c:set var='divsn' value="A" />
					      		        </c:if>
					      		        <c:if test="${stud.standard eq 2}">
					      		               <option value="2">B</option>
					      		               <c:set var='divsn' value="B" />
					      		        </c:if>
					      		        <c:if test="${stud.standard eq 3}">
					      		               <option value="3">C</option>
					      		               <c:set var='divsn' value="C" />
					      		        </c:if>
					      		        <c:if test="${stud.standard eq 4}">
					      		               <option value="4">D</option>
					      		               <c:set var='divsn' value="D" />
					      		        </c:if>
					      		        <c:if test="${stud.standard eq 5}">
					      		               <option value="5">E</option>
					      		               <c:set var='divsn' value="E" />
					      		        </c:if>
					      		        <c:if test="${stud.standard eq 6}">
					      		               <option value="6">F</option>
					      		               <c:set var='divsn' value="F" />
					      		        </c:if>
					      		         <c:if test="${stud.standard eq 7}">
					      		               <option value="7">G</option>
					      		               <c:set var='divsn' value="G" />
					      		        </c:if>
		       				 	    	
		       				 	    		<option class="yearoption" value=1> A</option>
		       				 	    		<option class="yearoption" value=2> B</option>
		       				 	    		<option class="yearoption" value=3> C</option>
		       				 	    		<option class="yearoption" value=4> D</option>
		       				 	    		<option class="yearoption" value=5> E</option>
		       				 	    		<option class="yearoption" value=6> F</option>
		       				 	    		<option class="yearoption" value=7> G</option>
		       				 	    		</select>
		       				 	    		</div>
		    			   </div>
		   			  
		      			   <div class="row">
		      			     <div class="col-sm-3"><b>Roll No:</b> </div> 
		         			 <div class="col-sm-3"><b>University PRN No:</b> </div> 
		         			 <div class="col-sm-3"><b>Practical Batch:</b> </div> 
		         			 <!-- <div class="col-sm-3"><b>Registration No:</b> </div>  -->
		    			   </div>
		    			   <div class="row">
		    			   	 <div class="col-sm-3"><input type="text" value="${stud.rollNo}" name="rollNo" placeholder="Enter roll no.." > </div>
		       				 <div class="col-sm-3"><input type="text" value="${stud.universityEnrollNo}"  name="universityEnrollNo" placeholder="Enter PRNroll no.." > </div>
<%-- 		       				 <div class="col-sm-3"><input type="text" value="${stud.batch}"  name="batch" placeholder="Enter pract batch.." > </div>
 --%>		       				
                                      <div class="col-sm-3">
  		       				 	    	<select  name="batch" >	
  		       				 	    	    <option class="yearoption" value="${stud.batch}">${stud.batch}</option>
                                            <option class="yearoption" value='${divsn}1'>${divsn}1</option>
		       				 	    		<option class="yearoption" value='${divsn}2'>${divsn}2</option>
		       				 	    		<option class="yearoption" value='${divsn}3'>${divsn}3</option>
		       				 	    		<option class="yearoption" value='${divsn}4'>${divsn}4</option>
		       				 	    		</select>
                                        </div>

 
 
<!-- 		       				 <div class="col-sm-3"><input type="text"  name="registrationNo" placeholder="Enter reg no.." > </div>
 -->		       				 </div>
		       			 <!-- <div class="row">
		       				 <div class="col-sm-3"><h4><b>HSC/Diploma</b></h4> </div>
		       				 </div>
		       			     <div class="row">
		       				     <div class="col-sm-3"><b>Course Type : </b></div>
		       			     	 <div class="col-sm-3"><b>School/Institute Name : </b></div>
		       			     	
		       				 </div>
		       			
		       				 <div class="row">
		       					 <div class="col-sm-3"><input type="radio" name="courseType" value="HSC" required> 12th <input type="radio" name="courseType" value="Diploma"> Diploma </div>
		       					<div class="col-sm-9"><input type="text"  name="hscinstituteName" placeholder="Enter School/Institute Name.." required>  </div>
		       				 </div> 
		       				 <div class="row">
		       				     <div class="col-sm-3"><b>Board/University : </b></div>
		       				     <div class="col-sm-3"><b>Aggregate Marks(%) : </b></div>
		       				 </div>
							 <div class="row">
		       				     <div class="col-sm-3"><input type="text"  name="hscboard" placeholder="Enter Board.." required> </div>
		       				     <div class="col-sm-3"><input type="text"  name="hscaggregate" placeholder="Enter percentage.." required> </div>
                             </div>
                            
							 <div class="row">
		       				 <div class="col-sm-3"><h4><b>SSC</b></h4> </div>
		       				 </div>
		       			
		       				 <div class="row">
		       			     	 <div class="col-sm-3"><b>School Name : </b></div>
		       				 </div>
		       			
		       				 <div class="row">
		       					<div class="col-sm-9"> <input type="text"  name="sscschoolName" placeholder="Enter School/Institute Name.." required>  </div>
		       				 </div> 
		       				 <div class="row">
		       				     <div class="col-sm-3"><b>Board/University : </b></div>
		       				     <div class="col-sm-3"><b>Aggregate Marks (%) : </b></div>
		       				 </div>
							 <div class="row">
		       				     <div class="col-sm-3"><input type="text"  name="sscboard" placeholder="Enter Board.." required> </div>
		       				     <div class="col-sm-3"><input type="text"  name="sscaggregate" placeholder="Enter percentage.." required> </div>
                            </div>
		   					 <div class="row">
		     					 <div class="col-sm-3">  &nbsp; </div>
		 					</div> -->
		 					<div class="Submitbtn">
		     					<input type="submit" onclick="return Validate()" value="Submit" class="" >
		      					<input type="reset" name="resetBtn" value="Clear" class="" > 
		 				</div>
				</div>
		</section>
  
	</form:form>  
	
</div>       
 <!-- /Main Content -->
</div>
<script>


/* ....................................................... */
  $( function() {
	    $( "#datepicker1" ).datepicker();
	  } );


</script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>

