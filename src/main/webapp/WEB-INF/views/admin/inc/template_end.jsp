<%
/**
 * template_end.jsp
 *
 * Author: pixelcave
 *
 * The last block of code used in every page of the template
 *
 * We put it in a separate file for consistency. The reason we
 * separated template_scripts.jsp and template_end.jsp is for enabling us
 * put between them extra javascript code needed only in specific pages
 *
 */
%>
	 <script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
		  ga('create', 'UA-50409364-1', 'gourmet7.com');
		  ga('send', 'pageview');
		
	</script>
	<script>
	$("#submit_leaddetails").click(function(){
		
		 var mobile = $('#mobile1').val();
		
		 var condition = 0;
		 if(jQuery.trim(mobile).length <=0 ){
			 condition=1; 
		 }
		 
		
		 if(condition==1){
				$("#enteredError").text("Please enter mobile number to check lead");
				$("#enteredError").css("display","block");
				return false;
			}else{
				$("#enteredError").text(" ");
			}
		
		 
		 $("#LeadUser_Form").submit();
	 });
   
	
	</script>
    </body>
</html>