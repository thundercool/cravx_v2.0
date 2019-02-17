

<%@page import="com.astrika.abg.model.User"%>
<%@page import="com.astrika.abg.model.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.HashMap"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>


<%


HashMap<String,String> template=new HashMap<String,String>();

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);

template.put("name", "CRAVX");
template.put("version", "1.1");
template.put("author", "cravx");
template.put("robots", "noindex, nofollow");
template.put("title", "CRAVX- An Annual Premium Paid Membership Program");
template.put("description", "CRAVX is an Annual Premium Paid Membership Program developed by  Astrika Infotech Private Limited.");
    // 'navbar-default'         for a light header
    // 'navbar-inverse'         for a dark header
template.put("header_navbar", "navbar-default");
    // ''                       empty for a static header
    // 'navbar-fixed-top'       for a top fixed header / fixed sidebars
    // 'navbar-fixed-bottom'    for a bottom fixed header / fixed sidebars
template.put("header", "");
    // ''                                               for a full main and alternative sidebar hidden by default (> 991px)
    // 'sidebar-visible-lg'                             for a full main sidebar visible by default (> 991px)
    // 'sidebar-partial'                                for a partial main sidebar which opens on mouse hover, hidden by default (> 991px)
    // 'sidebar-partial sidebar-visible-lg'             for a partial main sidebar which opens on mouse hover, visible by default (> 991px)
    // 'sidebar-alt-visible-lg'                         for a full alternative sidebar visible by default (> 991px)
    // 'sidebar-alt-partial'                            for a partial alternative sidebar which opens on mouse hover, hidden by default (> 991px)
    // 'sidebar-alt-partial sidebar-alt-visible-lg'     for a partial alternative sidebar which opens on mouse hover, visible by default (> 991px)
    // 'sidebar-partial sidebar-alt-partial'            for both sidebars partial which open on mouse hover, hidden by default (> 991px)
    // 'sidebar-no-animations'                          add this as extra for disabling sidebar animations on large screens (> 991px) - Better performance with heavy pages!
template.put("sidebar", "sidebar-visible-lg sidebar-no-animations");
    // ''                       empty for a static footer
    // 'footer-fixed'           for a fixed footer
template.put("footer", "footer-fixed");
    // ''                       empty for default style
    // 'style-alt'              for an alternative main style (affects main page background as well as blocks style)
template.put("main_style", "");
    // 'night', 'amethyst', 'modern', 'autumn', 'flatie', 'spring', 'fancy', 'fire' or '' leave empty for the Default Blue theme
template.put("theme","");
    // ''                       for default content in header
    // 'horizontal-menu'        for a horizontal menu in header
    // This option is just used for feature demostration and you can remove it if you like. You can keep or alter header's content in page_head.jsp
template.put("header_content", "");
//template.put("active_page,basename($_SERVER['PHP_SELF']));
    		

User user;
Long moduleId;
try {
user = (User) SecurityContextHolder.getContext()
.getAuthentication().getPrincipal();
moduleId = user.getUserId();

} catch (Exception e) {

user = null;
moduleId = null;
} 



%>
<!-- This property is used to check whether logged in user is a corporate or an individual in membershipform.jsp  -->
<c:set var="visitor" value="<%= user%>"></c:set>
<c:set var="contexturl" value="/"></c:set>
