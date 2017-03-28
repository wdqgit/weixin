<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta charset="utf-8">
        <link href="${pageContext.request.contextPath }/css/public.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath }/css/houtai.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath }/css/smartMenu.css" type="text/css" rel="stylesheet">
        <title>义诊微信号后台管理平台</title>
        <script type="text/javascript">
        function exitsystem(){
        	window.parent.location="${pageContext.request.contextPath}/people/logout.do?type=admin"; 
        }
        </script>
    </head>
    <body>
		<div class="ad-comment-box" id="ad-comment" style="margin-left:0px;">
                <div class="ad-top-comment">
                    <div class="ad-message">
                       
                        <div class="ad-top-right">
                            
                            <div class="ad-welcom">
                                <div class="ad-wel-img"><img src="${pageContext.request.contextPath }/image/min_logo.jpg" height="36" width="36"></div>
                                <div class="ad-wel-text">
                                    <div class="font-wel">欢迎您！<strong>${admin.name }</strong></div>
                                    <div class="font-wel"><a href="javascript:exitsystem();"><strong>【退出】</strong></a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                </div>
                
    		</div>
	
	 <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/contabs.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/maintabs.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-smartMenu-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.nicescroll.min.js"></script>
      
	
	
	
	
	</body>
	</html>