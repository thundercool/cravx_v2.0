<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html class="no-js">
<head>
    <meta charset="utf-8">

    <title>CravX</title>

    <meta name="description" content="ProUI is a Responsive Bootstrap Admin Template created by pixelcave and published on Themeforest.">
    <meta name="author" content="pixelcave">
    <meta name="robots" content="noindex, nofollow">

    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Icons -->
    <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
    <link rel="shortcut icon" href="../../resources/img/favicon.png">
    <link rel="apple-touch-icon" href="../../resources/img/icon57.png" sizes="57x57">
    <link rel="apple-touch-icon" href="../../resources/img/icon72.png" sizes="72x72">
    <link rel="apple-touch-icon" href="../../resources/img/icon76.png" sizes="76x76">
    <link rel="apple-touch-icon" href="../../resources/img/icon114.png" sizes="114x114">
    <link rel="apple-touch-icon" href="../../resources/img/icon120.png" sizes="120x120">
    <link rel="apple-touch-icon" href="../../resources/img/icon144.png" sizes="144x144">
    <link rel="apple-touch-icon" href="../../resources/img/icon152.png" sizes="152x152">
    <link rel="apple-touch-icon" href="../../resources/img/icon180.png" sizes="180x180">
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/plugins.css">
    <link rel="stylesheet" href="../../resources/css/main.css">
    <link rel="stylesheet" href="../../resources/css/themes.css">
    <script src="../../resources/js/vendor/modernizr-respond.min.js"></script>
</head>

<body>
    
    <div id="page-wrapper">
          <div id="page-container" class="sidebar-partial sidebar-visible-lg sidebar-no-animations">
             <div id="sidebar">
                <!-- Wrapper for scrolling functionality -->
                <div id="sidebar-scroll">
                    <!-- Sidebar Content -->
                    <div class="sidebar-content">
                        <!-- Brand -->
                        <a href="index.html" class="sidebar-brand">
                            <i class="gi gi-flash"></i><span class="sidebar-nav-mini-hide"><strong>Super</strong>Admin</span>
                        </a>
                        <!-- END Brand -->

                        <!-- User Info -->
                        <div class="sidebar-section sidebar-user clearfix sidebar-nav-mini-hide">
                            <div class="sidebar-user-avatar">
                                <a href="page_ready_user_profile.html">
                                    <img src="../../resources/img/placeholders/avatars/avatar2.jpg" alt="avatar">
                                </a>
                            </div>
                            <div class="sidebar-user-name">${user.loginId}</div>
                            <div class="sidebar-user-links">
                                <a href="page_ready_user_profile.html" data-toggle="tooltip" data-placement="bottom"
                                    title="Profile"><i class="gi gi-user"></i></a>
                                
                               <c:url value="/logout" var="logoutUrl" />
								<form id="logout" action="${logoutUrl}" method="post">
								</form>
                                  <a href="javascript:document.getElementById('logout').submit()">Logout</a>
                            </div>
                        </div>
                        <!-- END User Info -->


                        <!-- Sidebar Navigation -->
                        <ul class="sidebar-nav">
                            <li>
                                <a href="index.html" class=" active"><i class="gi gi-stopwatch sidebar-nav-icon"></i><span
                                        class="sidebar-nav-mini-hide">Dashboard</span></a>
                            </li>
                            
                              <li>
                                <a href="#" class="sidebar-nav-menu"><i class="fa fa-plus sidebar-nav-indicator sidebar-nav-mini-hide"></i><i
                                        class="gi gi-shopping_cart sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">Card </span></a>
                                <ul>
                                      <li>
                                        <a href="/ManageCard/Card/AddCard">Add Cards</a>
                                    </li>
                                    <li>
                                        <a href="/ManageCard/Card/AssignCard">Assign Cards</a>
                                    </li>
                                </ul>
                            </li>
                           
                            <li>
                                <a href="#" class="sidebar-nav-menu"><i class="fa fa-plus sidebar-nav-indicator sidebar-nav-mini-hide"></i><i
                                        class="gi gi-certificate sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">Masters 
                                        </span></a>
                                <ul>
                                   <li><a href="/User/UserList">User Management</a></li>
									<li><a href="/ManageLocation/StateList">State</a></li>
									<li><a href="/ManageLocation/CityList">City</a></li>
									<li><a href="/ManageLocation/AreaList">Area</a></li>
									<li><a href="/ManageCompany/CompanyList">Company List</a></li>
									<li><a href="/ManageBrand/BrandList">Brand List</a></li>
									<li><a href="/ManageRestaurant/RestaurantList">Restaurant List</a></li>
                                </ul>
                            </li>
                           
                                
                        </ul>
                  
                    </div>
                   
                </div>
             
            </div>
        
            <div id="main-container">
                <!-- Page content -->
                <div id="page-content">
                <c:if test="${success}">
                <div class="alert alert-success">
                <strong>Success!</strong>${cardCount} Card Assigned Successfully!!!!!
                </div>
                </c:if>
                <div class="block">
                                    <!-- Select Components Title -->
                                    <div class="block-title">
                                        <div class="block-options pull-right">
                                            <a href="javascript:void(0)" class="btn btn-alt btn-sm btn-default" data-toggle="tooltip" title="" data-original-title="Settings"><i class="gi gi-cogwheel"></i></a>
                                        </div>
                                        <h2><strong>Assign</strong> Cards</h2>
                                    </div>
                                    <!-- END Select Components Title -->

                                    <!-- Select Components Content -->
                                    <form:form action="/ManageCard/Card/SaveAssignCard" method="post" modelAttribute="cardMaster" class="form-horizontal form-bordered">
                                        <fieldset>
<!--                                             <legend><i class="fa fa-angle-right"></i> Select Card Type</legend>
 -->                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="example-chosen">Select Card Type</label>
                                                <div class="col-md-6">
                                                    <form:select id="example-chosen" path="cardType" class="select-chosen" data-placeholder="Choose a Country.." style="width: 250px; display: none;">
                                                      <form:option value="">Select Card Type</form:option>	
						                          	  <form:options items="${cardTypeList}" itemLabel="name"></form:options>	
                                                      </form:select>
<!--                                                       <div class="chosen-container chosen-container-single" style="width: 100%;" title="" id="example_chosen_chosen"><a class="chosen-single chosen-default" tabindex="-1"><span>Choose a Country..</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off"></div><ul class="chosen-results"><li class="active-result" data-option-array-index="1" style="">United States</li><li class="active-result" data-option-array-index="2" style="">United Kingdom</li><li class="active-result" data-option-array-index="3" style="">Afghanistan</li><li class="active-result" data-option-array-index="4" style="">Aland Islands</li><li class="active-result" data-option-array-index="5" style="">Albania</li><li class="active-result" data-option-array-index="6" style="">Algeria</li><li class="active-result" data-option-array-index="7" style="">American Samoa</li><li class="active-result" data-option-array-index="8" style="">Andorra</li><li class="active-result" data-option-array-index="9" style="">Angola</li><li class="active-result" data-option-array-index="10" style="">Anguilla</li><li class="active-result" data-option-array-index="11" style="">Antarctica</li><li class="active-result" data-option-array-index="12" style="">Antigua and Barbuda</li><li class="active-result" data-option-array-index="13" style="">Argentina</li><li class="active-result" data-option-array-index="14" style="">Armenia</li><li class="active-result" data-option-array-index="15" style="">Aruba</li><li class="active-result" data-option-array-index="16" style="">Australia</li><li class="active-result" data-option-array-index="17" style="">Austria</li><li class="active-result" data-option-array-index="18" style="">Azerbaijan</li><li class="active-result" data-option-array-index="19" style="">Bahamas</li><li class="active-result" data-option-array-index="20" style="">Bahrain</li><li class="active-result" data-option-array-index="21" style="">Bangladesh</li><li class="active-result" data-option-array-index="22" style="">Barbados</li><li class="active-result" data-option-array-index="23" style="">Belarus</li><li class="active-result" data-option-array-index="24" style="">Belgium</li><li class="active-result" data-option-array-index="25" style="">Belize</li><li class="active-result" data-option-array-index="26" style="">Benin</li><li class="active-result" data-option-array-index="27" style="">Bermuda</li><li class="active-result" data-option-array-index="28" style="">Bhutan</li><li class="active-result" data-option-array-index="29" style="">Bolivia, Plurinational State of</li><li class="active-result" data-option-array-index="30" style="">Bonaire, Sint Eustatius and Saba</li><li class="active-result" data-option-array-index="31" style="">Bosnia and Herzegovina</li><li class="active-result" data-option-array-index="32" style="">Botswana</li><li class="active-result" data-option-array-index="33" style="">Bouvet Island</li><li class="active-result" data-option-array-index="34" style="">Brazil</li><li class="active-result" data-option-array-index="35" style="">British Indian Ocean Territory</li><li class="active-result" data-option-array-index="36" style="">Brunei Darussalam</li><li class="active-result" data-option-array-index="37" style="">Bulgaria</li><li class="active-result" data-option-array-index="38" style="">Burkina Faso</li><li class="active-result" data-option-array-index="39" style="">Burundi</li><li class="active-result" data-option-array-index="40" style="">Cambodia</li><li class="active-result" data-option-array-index="41" style="">Cameroon</li><li class="active-result" data-option-array-index="42" style="">Canada</li><li class="active-result" data-option-array-index="43" style="">Cape Verde</li><li class="active-result" data-option-array-index="44" style="">Cayman Islands</li><li class="active-result" data-option-array-index="45" style="">Central African Republic</li><li class="active-result" data-option-array-index="46" style="">Chad</li><li class="active-result" data-option-array-index="47" style="">Chile</li><li class="active-result" data-option-array-index="48" style="">China</li><li class="active-result" data-option-array-index="49" style="">Christmas Island</li><li class="active-result" data-option-array-index="50" style="">Cocos (Keeling) Islands</li><li class="active-result" data-option-array-index="51" style="">Colombia</li><li class="active-result" data-option-array-index="52" style="">Comoros</li><li class="active-result" data-option-array-index="53" style="">Congo</li><li class="active-result" data-option-array-index="54" style="">Congo, The Democratic Republic of The</li><li class="active-result" data-option-array-index="55" style="">Cook Islands</li><li class="active-result" data-option-array-index="56" style="">Costa Rica</li><li class="active-result" data-option-array-index="57" style="">Cote D'ivoire</li><li class="active-result" data-option-array-index="58" style="">Croatia</li><li class="active-result" data-option-array-index="59" style="">Cuba</li><li class="active-result" data-option-array-index="60" style="">Curacao</li><li class="active-result" data-option-array-index="61" style="">Cyprus</li><li class="active-result" data-option-array-index="62" style="">Czech Republic</li><li class="active-result" data-option-array-index="63" style="">Denmark</li><li class="active-result" data-option-array-index="64" style="">Djibouti</li><li class="active-result" data-option-array-index="65" style="">Dominica</li><li class="active-result" data-option-array-index="66" style="">Dominican Republic</li><li class="active-result" data-option-array-index="67" style="">Ecuador</li><li class="active-result" data-option-array-index="68" style="">Egypt</li><li class="active-result" data-option-array-index="69" style="">El Salvador</li><li class="active-result" data-option-array-index="70" style="">Equatorial Guinea</li><li class="active-result" data-option-array-index="71" style="">Eritrea</li><li class="active-result" data-option-array-index="72" style="">Estonia</li><li class="active-result" data-option-array-index="73" style="">Ethiopia</li><li class="active-result" data-option-array-index="74" style="">Falkland Islands (Malvinas)</li><li class="active-result" data-option-array-index="75" style="">Faroe Islands</li><li class="active-result" data-option-array-index="76" style="">Fiji</li><li class="active-result" data-option-array-index="77" style="">Finland</li><li class="active-result" data-option-array-index="78" style="">France</li><li class="active-result" data-option-array-index="79" style="">French Guiana</li><li class="active-result" data-option-array-index="80" style="">French Polynesia</li><li class="active-result" data-option-array-index="81" style="">French Southern Territories</li><li class="active-result" data-option-array-index="82" style="">Gabon</li><li class="active-result" data-option-array-index="83" style="">Gambia</li><li class="active-result" data-option-array-index="84" style="">Georgia</li><li class="active-result" data-option-array-index="85" style="">Germany</li><li class="active-result" data-option-array-index="86" style="">Ghana</li><li class="active-result" data-option-array-index="87" style="">Gibraltar</li><li class="active-result" data-option-array-index="88" style="">Greece</li><li class="active-result" data-option-array-index="89" style="">Greenland</li><li class="active-result" data-option-array-index="90" style="">Grenada</li><li class="active-result" data-option-array-index="91" style="">Guadeloupe</li><li class="active-result" data-option-array-index="92" style="">Guam</li><li class="active-result" data-option-array-index="93" style="">Guatemala</li><li class="active-result" data-option-array-index="94" style="">Guernsey</li><li class="active-result" data-option-array-index="95" style="">Guinea</li><li class="active-result" data-option-array-index="96" style="">Guinea-bissau</li><li class="active-result" data-option-array-index="97" style="">Guyana</li><li class="active-result" data-option-array-index="98" style="">Haiti</li><li class="active-result" data-option-array-index="99" style="">Heard Island and Mcdonald Islands</li><li class="active-result" data-option-array-index="100" style="">Holy See (Vatican City State)</li><li class="active-result" data-option-array-index="101" style="">Honduras</li><li class="active-result" data-option-array-index="102" style="">Hong Kong</li><li class="active-result" data-option-array-index="103" style="">Hungary</li><li class="active-result" data-option-array-index="104" style="">Iceland</li><li class="active-result" data-option-array-index="105" style="">India</li><li class="active-result" data-option-array-index="106" style="">Indonesia</li><li class="active-result" data-option-array-index="107" style="">Iran, Islamic Republic of</li><li class="active-result" data-option-array-index="108" style="">Iraq</li><li class="active-result" data-option-array-index="109" style="">Ireland</li><li class="active-result" data-option-array-index="110" style="">Isle of Man</li><li class="active-result" data-option-array-index="111" style="">Israel</li><li class="active-result" data-option-array-index="112" style="">Italy</li><li class="active-result" data-option-array-index="113" style="">Jamaica</li><li class="active-result" data-option-array-index="114" style="">Japan</li><li class="active-result" data-option-array-index="115" style="">Jersey</li><li class="active-result" data-option-array-index="116" style="">Jordan</li><li class="active-result" data-option-array-index="117" style="">Kazakhstan</li><li class="active-result" data-option-array-index="118" style="">Kenya</li><li class="active-result" data-option-array-index="119" style="">Kiribati</li><li class="active-result" data-option-array-index="120" style="">Korea, Democratic People's Republic of</li><li class="active-result" data-option-array-index="121" style="">Korea, Republic of</li><li class="active-result" data-option-array-index="122" style="">Kuwait</li><li class="active-result" data-option-array-index="123" style="">Kyrgyzstan</li><li class="active-result" data-option-array-index="124" style="">Lao People's Democratic Republic</li><li class="active-result" data-option-array-index="125" style="">Latvia</li><li class="active-result" data-option-array-index="126" style="">Lebanon</li><li class="active-result" data-option-array-index="127" style="">Lesotho</li><li class="active-result" data-option-array-index="128" style="">Liberia</li><li class="active-result" data-option-array-index="129" style="">Libya</li><li class="active-result" data-option-array-index="130" style="">Liechtenstein</li><li class="active-result" data-option-array-index="131" style="">Lithuania</li><li class="active-result" data-option-array-index="132" style="">Luxembourg</li><li class="active-result" data-option-array-index="133" style="">Macao</li><li class="active-result" data-option-array-index="134" style="">Macedonia, The Former Yugoslav Republic of</li><li class="active-result" data-option-array-index="135" style="">Madagascar</li><li class="active-result" data-option-array-index="136" style="">Malawi</li><li class="active-result" data-option-array-index="137" style="">Malaysia</li><li class="active-result" data-option-array-index="138" style="">Maldives</li><li class="active-result" data-option-array-index="139" style="">Mali</li><li class="active-result" data-option-array-index="140" style="">Malta</li><li class="active-result" data-option-array-index="141" style="">Marshall Islands</li><li class="active-result" data-option-array-index="142" style="">Martinique</li><li class="active-result" data-option-array-index="143" style="">Mauritania</li><li class="active-result" data-option-array-index="144" style="">Mauritius</li><li class="active-result" data-option-array-index="145" style="">Mayotte</li><li class="active-result" data-option-array-index="146" style="">Mexico</li><li class="active-result" data-option-array-index="147" style="">Micronesia, Federated States of</li><li class="active-result" data-option-array-index="148" style="">Moldova, Republic of</li><li class="active-result" data-option-array-index="149" style="">Monaco</li><li class="active-result" data-option-array-index="150" style="">Mongolia</li><li class="active-result" data-option-array-index="151" style="">Montenegro</li><li class="active-result" data-option-array-index="152" style="">Montserrat</li><li class="active-result" data-option-array-index="153" style="">Morocco</li><li class="active-result" data-option-array-index="154" style="">Mozambique</li><li class="active-result" data-option-array-index="155" style="">Myanmar</li><li class="active-result" data-option-array-index="156" style="">Namibia</li><li class="active-result" data-option-array-index="157" style="">Nauru</li><li class="active-result" data-option-array-index="158" style="">Nepal</li><li class="active-result" data-option-array-index="159" style="">Netherlands</li><li class="active-result" data-option-array-index="160" style="">New Caledonia</li><li class="active-result" data-option-array-index="161" style="">New Zealand</li><li class="active-result" data-option-array-index="162" style="">Nicaragua</li><li class="active-result" data-option-array-index="163" style="">Niger</li><li class="active-result" data-option-array-index="164" style="">Nigeria</li><li class="active-result" data-option-array-index="165" style="">Niue</li><li class="active-result" data-option-array-index="166" style="">Norfolk Island</li><li class="active-result" data-option-array-index="167" style="">Northern Mariana Islands</li><li class="active-result" data-option-array-index="168" style="">Norway</li><li class="active-result" data-option-array-index="169" style="">Oman</li><li class="active-result" data-option-array-index="170" style="">Pakistan</li><li class="active-result" data-option-array-index="171" style="">Palau</li><li class="active-result" data-option-array-index="172" style="">Palestinian Territory, Occupied</li><li class="active-result" data-option-array-index="173" style="">Panama</li><li class="active-result" data-option-array-index="174" style="">Papua New Guinea</li><li class="active-result" data-option-array-index="175" style="">Paraguay</li><li class="active-result" data-option-array-index="176" style="">Peru</li><li class="active-result" data-option-array-index="177" style="">Philippines</li><li class="active-result" data-option-array-index="178" style="">Pitcairn</li><li class="active-result" data-option-array-index="179" style="">Poland</li><li class="active-result" data-option-array-index="180" style="">Portugal</li><li class="active-result" data-option-array-index="181" style="">Puerto Rico</li><li class="active-result" data-option-array-index="182" style="">Qatar</li><li class="active-result" data-option-array-index="183" style="">Reunion</li><li class="active-result" data-option-array-index="184" style="">Romania</li><li class="active-result" data-option-array-index="185" style="">Russian Federation</li><li class="active-result" data-option-array-index="186" style="">Rwanda</li><li class="active-result" data-option-array-index="187" style="">Saint Barthelemy</li><li class="active-result" data-option-array-index="188" style="">Saint Helena, Ascension and Tristan da Cunha</li><li class="active-result" data-option-array-index="189" style="">Saint Kitts and Nevis</li><li class="active-result" data-option-array-index="190" style="">Saint Lucia</li><li class="active-result" data-option-array-index="191" style="">Saint Martin (French part)</li><li class="active-result" data-option-array-index="192" style="">Saint Pierre and Miquelon</li><li class="active-result" data-option-array-index="193" style="">Saint Vincent and The Grenadines</li><li class="active-result" data-option-array-index="194" style="">Samoa</li><li class="active-result" data-option-array-index="195" style="">San Marino</li><li class="active-result" data-option-array-index="196" style="">Sao Tome and Principe</li><li class="active-result" data-option-array-index="197" style="">Saudi Arabia</li><li class="active-result" data-option-array-index="198" style="">Senegal</li><li class="active-result" data-option-array-index="199" style="">Serbia</li><li class="active-result" data-option-array-index="200" style="">Seychelles</li><li class="active-result" data-option-array-index="201" style="">Sierra Leone</li><li class="active-result" data-option-array-index="202" style="">Singapore</li><li class="active-result" data-option-array-index="203" style="">Sint Maarten (Dutch part)</li><li class="active-result" data-option-array-index="204" style="">Slovakia</li><li class="active-result" data-option-array-index="205" style="">Slovenia</li><li class="active-result" data-option-array-index="206" style="">Solomon Islands</li><li class="active-result" data-option-array-index="207" style="">Somalia</li><li class="active-result" data-option-array-index="208" style="">South Africa</li><li class="active-result" data-option-array-index="209" style="">South Georgia and The South Sandwich Islands</li><li class="active-result" data-option-array-index="210" style="">South Sudan</li><li class="active-result" data-option-array-index="211" style="">Spain</li><li class="active-result" data-option-array-index="212" style="">Sri Lanka</li><li class="active-result" data-option-array-index="213" style="">Sudan</li><li class="active-result" data-option-array-index="214" style="">Suriname</li><li class="active-result" data-option-array-index="215" style="">Svalbard and Jan Mayen</li><li class="active-result" data-option-array-index="216" style="">Swaziland</li><li class="active-result" data-option-array-index="217" style="">Sweden</li><li class="active-result" data-option-array-index="218" style="">Switzerland</li><li class="active-result" data-option-array-index="219" style="">Syrian Arab Republic</li><li class="active-result" data-option-array-index="220" style="">Taiwan, Province of China</li><li class="active-result" data-option-array-index="221" style="">Tajikistan</li><li class="active-result" data-option-array-index="222" style="">Tanzania, United Republic of</li><li class="active-result" data-option-array-index="223" style="">Thailand</li><li class="active-result" data-option-array-index="224" style="">Timor-leste</li><li class="active-result" data-option-array-index="225" style="">Togo</li><li class="active-result" data-option-array-index="226" style="">Tokelau</li><li class="active-result" data-option-array-index="227" style="">Tonga</li><li class="active-result" data-option-array-index="228" style="">Trinidad and Tobago</li><li class="active-result" data-option-array-index="229" style="">Tunisia</li><li class="active-result" data-option-array-index="230" style="">Turkey</li><li class="active-result" data-option-array-index="231" style="">Turkmenistan</li><li class="active-result" data-option-array-index="232" style="">Turks and Caicos Islands</li><li class="active-result" data-option-array-index="233" style="">Tuvalu</li><li class="active-result" data-option-array-index="234" style="">Uganda</li><li class="active-result" data-option-array-index="235" style="">Ukraine</li><li class="active-result" data-option-array-index="236" style="">United Arab Emirates</li><li class="active-result" data-option-array-index="237" style="">United Kingdom</li><li class="active-result" data-option-array-index="238" style="">United States</li><li class="active-result" data-option-array-index="239" style="">United States Minor Outlying Islands</li><li class="active-result" data-option-array-index="240" style="">Uruguay</li><li class="active-result" data-option-array-index="241" style="">Uzbekistan</li><li class="active-result" data-option-array-index="242" style="">Vanuatu</li><li class="active-result" data-option-array-index="243" style="">Venezuela, Bolivarian Republic of</li><li class="active-result" data-option-array-index="244" style="">Viet Nam</li><li class="active-result" data-option-array-index="245" style="">Virgin Islands, British</li><li class="active-result" data-option-array-index="246" style="">Virgin Islands, U.S.</li><li class="active-result" data-option-array-index="247" style="">Wallis and Futuna</li><li class="active-result" data-option-array-index="248" style="">Western Sahara</li><li class="active-result" data-option-array-index="249" style="">Yemen</li><li class="active-result" data-option-array-index="250" style="">Zambia</li><li class="active-result" data-option-array-index="251" style="">Zimbabwe</li></ul></div></div>
 -->                                                </div>
                                            </div>
                                        
                                        </fieldset>
                                        
                                         <div class="form-group">
                                          <label class="col-md-4 control-label" for="example-chosen">No Of Cards</label>
                                         <div class="col-md-6">
                                            <label class="sr-only" for="example-if-email">No Of Cards</label>
                                            <input type="number" id="example-if-email" name="cardCount" class="form-control" placeholder="Enter Card Count..">
                                        </div>
                                        </div>
                                      
                                        <div class="form-group form-actions">
                                            <div class="col-md-8 col-md-offset-4">
                                                <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-angle-right"></i> Submit</button>
                                                <button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-repeat"></i> Reset</button>
                                            </div>
                                        </div>
                                    </form:form>
                                    <!-- END Select Components Content -->
                                </div>
                                <!-- END Inline Form Block -->
                </div>
                <!-- END Page Content -->

                <!-- Footer -->
                <footer class="clearfix">
                    <div class="pull-right">
                        CravX <i class="fa fa-heart text-danger"></i> by <a href="http://goo.gl/vNS3I" target="_blank">Foodies</a>
                    </div>
                    <div class="pull-left">
                        <span id="year-copy"></span> &copy; <a href="http://goo.gl/TDOSuC" target="_blank"></a>
                    </div>
                </footer>
                <!-- END Footer -->
            </div>
            <!-- END Main Container -->
        </div>
        <!-- END Page Container -->
    </div>
    <!-- END Page Wrapper -->

    <!-- Scroll to top link, initialized in js/app.js - scrollToTop() -->
    <a href="#" id="to-top"><i class="fa fa-angle-double-up"></i></a>

    <!-- User Settings, modal which opens from Settings link (found in top right user menu) and the Cog link (found in sidebar user info) -->
    <div id="modal-user-settings" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header text-center">
                    <h2 class="modal-title"><i class="fa fa-pencil"></i> Settings</h2>
                </div>
                <!-- END Modal Header -->

                <!-- Modal Body -->
                <div class="modal-body">
                    <form action="index.html" method="post" enctype="multipart/form-data" class="form-horizontal form-bordered"
                        onsubmit="return false;">
                        <fieldset>
                            <legend>Vital Info</legend>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Username</label>
                                <div class="col-md-8">
                                    <p class="form-control-static">Admin</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-email">Email</label>
                                <div class="col-md-8">
                                    <input type="email" id="user-settings-email" name="user-settings-email" class="form-control"
                                        value="admin@example.com">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-notifications">Email
                                    Notifications</label>
                                <div class="col-md-8">
                                    <label class="switch switch-primary">
                                        <input type="checkbox" id="user-settings-notifications" name="user-settings-notifications"
                                            value="1" checked>
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend>Password Update</legend>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-password">New Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="user-settings-password" name="user-settings-password"
                                        class="form-control" placeholder="Please choose a complex one..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="user-settings-repassword">Confirm New
                                    Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="user-settings-repassword" name="user-settings-repassword"
                                        class="form-control" placeholder="..and confirm it!">
                                </div>
                            </div>
                        </fieldset>
                        <div class="form-group form-actions">
                            <div class="col-xs-12 text-right">
                                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-sm btn-primary">Save Changes</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- END Modal Body -->
            </div>
        </div>
    </div>
    <!-- END User Settings -->

    <!-- Remember to include excanvas for IE8 chart support -->
    <!--[if IE 8]><script src="js/helpers/excanvas.min.js"></script><![endif]-->

    <!-- Include Jquery library from Google's CDN but if something goes wrong get Jquery from local file -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>
        !window.jQuery && document.write(decodeURI('%3Cscript src="js/vendor/jquery-1.11.2.min.js"%3E%3C/script%3E'));
    </script>

    <!-- Bootstrap.js, Jquery plugins and Custom JS code -->
    <script src="../../resources/js/vendor/bootstrap.min.js"></script>
    <script src="../../resources/js/plugins.js"></script>
    <script src="../../resources/js/app.js"></script>

    <!-- Google Maps API + Gmaps Plugin, must be loaded in the page you would like to use maps -->
    <script src="//maps.google.com/maps/api/js?sensor=true"></script>
    <script src="../../resources/js/helpers/gmaps.min.js"></script>

    <!-- Load and execute javascript code used only in this page -->
    <script src="../../resources/js/pages/index.js"></script>
    <script>
        $(function () {
            Index.init();
        });
    </script>
</body>

</html>