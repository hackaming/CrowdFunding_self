<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- start header -->

  <div id="header">
    <div class="inner clearfix">
      <h1>
        <a href="${ctx}/" onclick="ga('send', 'event', 'top', 'click', 'header_logo', true);">
          <img src="${ctx}/img/header-logo.png" alt="">
        </a>
      </h1>
      <div class="header-right">
        <div class="header-right-top clearfix">
        	
                <!-- Countdown dashboard start -->
                <div id="countdown_dashboard">
                    <p class="livetime">
                    距离第一期项目开始众筹还剩：
                    </p>
                    <div class="countdown">
                        <div class="dash days_dash">
                            <span class="dash_title">
                                天
                            </span>
                            <div class="digit">
                                0
                            </div>
                            <div class="digit">
                                0
                            </div>
                        </div>
                        <div class="dash hours_dash">
                            <span class="dash_title">
                                时
                            </span>
                            <div class="digit">
                                0
                            </div>
                            <div class="digit">
                                0
                            </div>
                        </div>
                        <div class="dash minutes_dash">
                            <span class="dash_title">
                                分
                            </span>
                            <div class="digit">
                                0
                            </div>
                            <div class="digit">
                                0
                            </div>
                        </div>
                        <div class="dash seconds_dash">
                            <span class="dash_title">
                                秒
                            </span>
                            <div class="digit">
                                0
                            </div>
                            <div class="digit">
                                0
                            </div>
                        </div>
                    </div>
                </div>
          <!-- <p class="header-money">
            <span class="header-money-title">已筹资金累计</span><br />
            <span id="top_amount">￥ ---,---,---</span>
          </p> -->
          <p class="link">
            <a target="_blank" href="#wx-modal" role="button" data-toggle="modal"> <img alt="weibo" src="${ctx}/img/ico-weibo.png"> </a> 
            <a target="_blank" href="#sina-modal" role="button" data-toggle="modal"> <img alt="weixin" src="${ctx}/img/ico-weixin.png"> </a>
          </p>
        </div>
        <div class="header-right-mid">
          <ul id="gnavi" class="clearfix">
            <li><a href="${ctx}/cncrowd" onclick="ga('send', 'event', 'concept', 'click', 'header_nav', true);"><img src="${ctx}/img/common/gnavi01_off.png" alt="中筹网金"></a></li>
            <li><a href="${ctx}/howto" onclick="ga('send', 'event', 'start', 'click', 'header_nav', true);"><img src="${ctx}/img/common/gnavi02_off.png" alt="投资教室"></a></li>
            <li><a href="${ctx}/items" onclick="ga('send', 'event', 'products_lst', 'click', 'header_nav', true);"><img src="${ctx}/img/common/gnavi03_off.png" alt="投资项目"></a></li>
            <li><a href="${ctx}/transfer" onclick="ga('send', 'event', 'faq', 'click', 'header_nav', true);"><img src="${ctx}/img/common/gnavi04_off.png" alt="转让中心"></a></li>
            <li><a href="${ctx}/club" onclick="ga('send', 'event', 'seminar', 'click', 'header_nav', true);"><img src="${ctx}/img/common/gnavi05_off.png" alt="中筹会"></a></li>
            <li><a href="${ctx}/company?id=1" onclick="ga('send', 'event', 'company', 'click', 'header_nav', true);"><img src="${ctx}/img/common/gnavi06_off.png" alt="企业介绍"></a></li>
          </ul>
        </div>
          
					
           <% 
          	Object s = (Object)session.getAttribute("user");
          	pageContext.setAttribute("s", s);
          	
          	
          	 String path = request.getContextPath();
          	 String basePath = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+path+"/";
          	pageContext.setAttribute("basePath",basePath);
          %>
					
          <div class="header-right-btm clearfix" style=" <c:if test="${s==null}"> display:none;  </c:if>"  >
            <ul id="gnavi02" class="clearfix">
              <li>
                <a href="${basePath}user/center" rel="nofollow" onclick="ga('send', 'event', 'account', 'click', 'header_nav', true);">
                  <img src="${ctx}/img/common/label_user.png" alt="用户中心">         
                </a>
              </li>
              <li>
                <a href="${basePath}user/logout" rel="nofollow" onclick="ga('send', 'event', 'mypage', 'click', 'header_nav', true);">
                  <img src="${ctx}/img/common/label_exit.png" alt="退出">
                </a>
              </li>
            </ul>
          </div>
          
          <div class="header-right-btm clearfix" style=" <c:if test="${s!=null}"> display:none;  </c:if>" >
            <ul id="gnavi02" class="clearfix">
              <li>
                <a href="${basePath}login" rel="nofollow" onclick="ga('send', 'event', 'account', 'click', 'header_nav', true);">
                  <img src="${ctx}/img/common/gnavi07_on.png" alt="登录账户">
                </a>
              </li>
              <li>
                <a href="${basePath}reg" rel="nofollow" onclick="ga('send', 'event', 'mypage', 'click', 'header_nav', true);">
                  <img src="${ctx}/img/common/gnavi09_off.png" alt="注册账户">
                </a>
              </li>
            </ul>
          </div>  
          <div id="wx-modal" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			    <h3 id="myModalLabel">微信二维码</h3>
			  </div>
			  <div class="modal-body text-center">
			    <img src="${ctx}/img/wx.jpg" width=300>
			  </div>
			</div>
          <div id="sina-modal" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			    <h3 id="myModalLabel">微博二维码</h3>
			  </div>
			  <div class="modal-body text-center">
			    <img src="${ctx}/img/sina.png" width=300>
			  </div>
			</div>
      </div>
    </div>
  </div>
  <!-- end header -->
