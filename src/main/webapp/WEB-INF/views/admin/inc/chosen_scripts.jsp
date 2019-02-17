<%
/**
 * chosen_scripts.jsp
 *
 * Author: pixelcave
 *
 * All vital JS scripts are included here
 *
 */
%>



<script type="text/javascript">
	$(document).ready(function() {

		//country
		$('select[name="chosenCountryId"]').chosen().change( function() {
            
		if($(this).val()!=0)
		{ 
			 $('div#countryDiv').removeClass('has-success has-error');
				   $('div#countryDiv').find("div.help-block").remove();
			}
		});
		
		//city
		$('select[name="chosenCityId"]').chosen().change( function() {
           
		if($(this).val()!=0)
		{ 
			 $('div#cityDiv').removeClass('has-success has-error');
				   $('div#cityDiv').find("div.help-block").remove();
			}
		});
		
		//area
		$('select[name="chosenAreaId"]').chosen().change( function() {
           
		if($(this).val()!=0)
		{ 
			 $('div#areaDiv').removeClass('has-success has-error');
				   $('div#areaDiv').find("div.help-block").remove();
			}
		});
		
		//currency
		$('select[name="chosenCurrencyId"]').chosen().change( function() {
           
		if($(this).val()!=0)
		{ 
			 $('div#currencyDiv').removeClass('has-success has-error');
		     $('div#currencyDiv').find("div.help-block").remove();
		}
		});
		
		//currency
		$('select[name="chosenBrandId"]').chosen().change( function() {
	           
			if($(this).val()!=0)
			{ 
				 $('div#brandDiv').removeClass('has-success has-error');
			     $('div#brandDiv').find("div.help-block").remove();
			}
			});
		
		//timeZone
		$('select[name="chosenTimeZone"]').chosen().change( function() {
	           
			if($(this).val()!=0)
			{ 
				 $('div#timeZoneDiv').removeClass('has-success has-error');
			     $('div#timeZoneDiv').find("div.help-block").remove();
			}
			});
			
				//typeOFSlot
		$('select[name="chosenTypeOfSlot"]').chosen().change( function() {
	           
			if($(this).val()!='')
			{ 
				 $('div#typeOfSlotDiv').removeClass('has-success has-error');
			     $('div#typeOfSlotDiv').find("div.help-block").remove();
			}
			});
				
				
		$('select[name="chosenPlace"]').chosen().change( function() {
	           
			if($(this).val()!=0)
			{ 
				 $('div#placeDiv').removeClass('has-success has-error');
			     $('div#placeDiv').find("div.help-block").remove();
			}
			});
		
		
		$('select[name="chosenCompanyId"]').chosen().change( function() {
	           
			if($(this).val()!=0)
			{ 
				 $('div#companyDiv').removeClass('has-success has-error');
			     $('div#companyDiv').find("div.help-block").remove();
			}
			});
		
		$('select[name="corporateId"]').chosen().change( function() {
	           
			if($(this).val()!=0)
			{ 
				 $('div#corpVoucherDiv').removeClass('has-success has-error');
			     $('div#corpVoucherDiv').find("div.help-block").remove();
			}
			});
	});
	
	
</script>
