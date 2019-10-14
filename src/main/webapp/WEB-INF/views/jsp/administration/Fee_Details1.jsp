
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="addFeeDetails" value="/web/taskforce/service/student/SaveFeedtl" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<style>
 .yello_hr {
   height: 2px;
   color: #ffe63b;
   background-color: #ffe63b;	
  }	
  body{
   background: #7d7e7d;
  }
  .container{
    padding-top:10px;
    color:#fff;
   }	
   input[type=text],input[type=email], input[type=date],input[type=number],select, textarea {
      width: 100%;
      padding: 4px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
      margin-top: 10px;
      margin-bottom: 14px;
      resize: vertical;
      background-color: #f2f2f2;
}
.catagory{

    font-size:13px;

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
tr:nth-child(even){background-color: #f2f2f2}
</style>
<body class="hold-transition skin-blue fixed sidebar-mini">
 <div class="wrapper" >
  <div class="content-wrapper">
	  <section class="content">  
	    <div class="box-body">
	       <span class="pull-left" ><font size="5">Add Or Update Fee Details</font></span>
	    </div>
	    <div class="box" style="margin-left:10px ; margin-right:10px">
	        <div class="box-body">
	       
	         <c:set var="feeDetail" value="${feeDetails}"></c:set>
	          <form action="${addFeeDetails}" method="post" id="FeeDetails">
	            <input type="hidden" name="stud_id" value="${ClientId}">
	            <input type="hidden" name="mid" value="${mid}">
  					<div class="row">
  					 <div class="col-sm-4"><b>Total Fee </b></div>
  					</div>
  					<div class="row">
  						<div class="col-sm-3"><input type="text"  name="total_fee" value="${feeDetail.total_fee }" placeholder="Enter Total Fee.."   required> </div>	
    				</div>
  					<hr>
  						<div class="row catagory">
  						   
    						<div class="col-sm-3"><b>Installment1 : </b></div>
    						<div class="col-sm-3"><b>Installment2 : </b></div>
    						<div class="col-sm-3"><b>Installment3 : </b></div>
    						<div class="col-sm-3"><b>Installment4 : </b></div>
  						</div>
  						
  						<div class="row">
			    			 <div class="col-sm-3"><input type="text" name="installment1" value="${feeDetail.installment1}" required placeholder="Enter installment1 Of Stud.."> </div>	
			    			 <div class="col-sm-3"><input type="text"  name="installment2" value="${feeDetail.installment2}" required placeholder="Enter installment2 Of Stud.."> </div>	
			    			 <div class="col-sm-3"><input type="text"  name="installment3" value="${feeDetail.installment3}" required placeholder="Enter installment3 Of Stud.."> </div>	
			    			 <div class="col-sm-3"><input type="text"  name="installment4" value="${feeDetail.installment4}" required placeholder="Enter installment4 Of Stud.."> </div>	
	   						
		       			</div>
  						<div class="row catagory">
  						   
    						<div class="col-sm-3"><b>Category :</b></div>
    						<div class="col-sm-3"><b>Outstanding :</b></div>
    						<div class="col-sm-3"><b>Paid :</b></div>
    						<div class="col-sm-3"><b>Remaining :</b></div>
  						</div>
  						
   						<div class="row">
			    			 <div class="col-sm-3"><input type="text" name="category" value="${feeDetail.category}" required placeholder="Enter Category Of Stud.."> </div>	
			    			 <div class="col-sm-3"><input type="text"  name="outstanding" value="${feeDetail.outstanding }"  required placeholder="Enter Outstanding Fee.."> </div>	
			    			 <div class="col-sm-3"><input type="text"  name="paid" value="${feeDetail.paid }" required placeholder="Enter Paid Fee.."> </div>	
			    			 <div class="col-sm-3"><input type="text"  name="remaining" value="${feeDetail.remaining }" required placeholder="Enter Remaining Fee.."> </div>	
	   						
		       			</div>
		       			<hr>
		       		<div class="row">
  						    <div class="col-sm-5"></div>
    						
    						<div class="col-sm-4">
    						<input type="submit" onclick="return Validate()" value="Submit">&nbsp;&nbsp;&nbsp;
    					    <input type="reset" name="resetBtn" value="Clear" class="" ></div>
  						</div>
		       </form>
		      </div>
		  </div>
	   </section> 
     </div>
  </div>
 


<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
 </body>
</html>
