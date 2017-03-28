<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta charset="utf-8">
        <link href="${pageContext.request.contextPath }/css/public.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath }/css/houtai.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath }/css/smartMenu.css" type="text/css" rel="stylesheet">
        <title>后台管理网站</title>
    </head>
    <body>
    	<div id="admin">
    		<div class="ad-menu" id="ad-menu">
                <div class="ad-logo"><img src="${pageContext.request.contextPath }/image/m_logo.png" height="103" width="130"></div>
                <div class="ad-list">
                    <ul>
                        <li>
                            <div class="li-item"><em class="scm li-ico ic1"></em>管理员管理<span class="scm arrow"></span></div>
                            <dl>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/people/listAdmin.do" class="dd-item">管理员列表<span class="scm dd-ar"></span></a>
                                   
                                </dd>
                                <c:if test="${admin.name == 'admin' }">
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/people/registerUI.do" class="dd-item">添加管理员列表<span class="scm dd-ar"></span></a>
                                   
                                </dd>
                                </c:if>
                            </dl>
                        </li>
                        <li>
                            <div class="li-item"><em class="scm li-ico ic2"></em>历届活动管理<span class="scm arrow"></span></div>
                            <dl>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/history/adminList.do" class="dd-item">历届活动列表<span class="scm dd-ar"></span></a>
                                </dd>
                              	<dd>
                                    <a target="content" href="${pageContext.request.contextPath }/history/addUI.do" class="dd-item">历届活动添加<span class="scm dd-ar"></span></a>
                                </dd>
                            </dl>
                        </li>
                        <li>
                            <div class="li-item"><em class="scm li-ico ic3"></em>病症信息管理<span class="scm arrow"></span></div>
                            <dl>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/disease/list.do" class="dd-item">病症信息列表<span class="scm dd-ar"></span></a>
                                   
                                </dd>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/disease/addUI.do" class="dd-item">添加病症信息<span class="scm dd-ar"></span></a>
                                   
                                </dd>
                               
                            </dl>
                        </li>
                        <li>
                            <div class="li-item"><em class="scm li-ico ic4"></em>活动管理<span class="scm arrow"></span></div>
                            <dl>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/activity/list.do?type=admin" class="dd-item">活动列表<span class="scm dd-ar"></span></a>
                                </dd>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/activity/addUI.do" class="dd-item">添加活动<span class="scm dd-ar"></span></a>
                                </dd>
                            </dl>
                        </li>
                        <li>
                            <div class="li-item"><em class="scm li-ico ic5"></em>二维码管理<span class="scm arrow"></span></div>
                            <dl>
                                <dd>
                                    <a target="content" href="${pageContext.request.contextPath }/qrcode/list.do" class="dd-item">二维码<span class="scm dd-ar"></span></a>
                                </dd>
                            </dl>
                        </li>
                      
                       
                    </ul>
                </div>
            </div>
			
			
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/contabs.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/maintabs.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-smartMenu-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.nicescroll.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $(".ad-menu").niceScroll({cursorborder:"0 none",cursorcolor:"#1a1a19",cursoropacitymin:"0",boxzoom:false});
            })
        </script>
    </body>
</html>
		
		