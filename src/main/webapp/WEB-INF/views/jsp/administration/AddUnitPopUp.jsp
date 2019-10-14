

<style>
.entry:not(:first-of-type)
{
    margin-top: 10px;
}

.glyphicon
{
    font-size: 12px;
}
input[type=text], input[type=email], input[type=date], input[type=number],
	select, textarea {
	width: 40%;
	padding: 4px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 5px;
	resize: vertical;
	background-color: #f2f2f2;}
</style>

<body class="hold-transition skin-blue sidebar-mini">
	
<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white; height:73%;width:100%;border:solid 2px white-space;overflow:scroll;overflow-x:hidden;overflow-y:scroll;"">
						

	 <div class="row">
  		       <div class="col-sm-12">
					<label>Select Subject :</label> <br>
					<select onclick="loadsub()">
							<option>m1</option>	
							<option>m2</option>	
							<option>m3</option>		      
					</select>
		       </div>		         
		 </div>
		 <br>
		  <div class="row">
		         <div class="col-sm-12">
		         <div class="container">
	<div class="row">
        <div class="control-group" id="fields">
            <label class="control-label" for="field1">Enter Unit/Chapter Name :</label>
            <div class="controls"> 
                <form role="form" autocomplete="off">
                    <div class="entry input-group col-xs-3">
                        <input type="text" class="form-control" name="fields[]" type="text" placeholder="Type something" />
                    	<span class="input-group-btn">
                            <button class="btn btn-success  btn-add" type="button">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
	</div>
</div>
		       </div>
		      
		 </div>
		 <div class="row" >
		     <div align="center" style="padding-top: 20px">
		    	      <input type="submit" onclick="return Validate()" value="Add" class="" />
		     </div>
		  </div>
		  		 						
							
							
  </div>

<script type="text/javascript">
$(function()
		{
		    $(document).on('click', '.btn-add', function(e)
		    {
		        e.preventDefault();

		        var controlForm = $('.controls form:first'),
		            currentEntry = $(this).parents('.entry:first'),
		            newEntry = $(currentEntry.clone()).appendTo(controlForm);

		        newEntry.find('input').val('');
		        controlForm.find('.entry:not(:last) .btn-add')
		            .removeClass('btn-add').addClass('btn-remove')
		            .removeClass('btn-success').addClass('btn-danger')
		            .html('<span class="glyphicon glyphicon-minus"></span>');
		    }).on('click', '.btn-remove', function(e)
		    {
				$(this).parents('.entry:first').remove();

				e.preventDefault();
				return false;
			});
		});
</script>
<script type="text/javascript">
function loadsub() {
	alert("hii");
var cid="${sessionScope.user.comClientName.id}";

$.ajax({
	type: "POST",
    url: "/"+location.pathname.split("/")[1]+"/web/service/taskforce/getsubjectbyid",
    data:'id='+ cid,
    dataType: 'json',
    success: function(data){
    if(data.length > 0)
    	{       
        	
    	},
     error:function(data){
    	    alert("error");
         }
    }
   });
 }
</script>

</body>

