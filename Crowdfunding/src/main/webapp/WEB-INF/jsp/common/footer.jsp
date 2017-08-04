<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="footer">
  <div class="bg-polygon">
  </div>
  <div class="footer-inner clearfix">
    <div class="contact-address-box">
      <div class="customer-support">
        <ul class="link sitemap">
          <li>
            <a href="${ctx}/cncrowd">
              中筹网金
            </a>
          </li>
          <li>
            <a href="#footer">
              联系我们
            </a>
          </li>
          <li>
            <a href="${ctx}/howto">
              投资教室
            </a>
          </li>
          <li>
            <a href="${ctx}/reg">
              注册账户
            </a>
          </li>
          <li>
            <a href="${ctx}/items">
              投资项目
            </a>
          </li>
          <li>
            <a href="${ctx}/faq">
              常见问题(Q&amp;A)
            </a>
          </li>
          <li>
            <a href="${ctx}/company?id=1">
              企业介绍
            </a>
          </li>
          <li>
            <a href="${ctx}/company?id=5">
              法律协议
            </a>
          </li>
        </ul>
      </div>
      <div class="company-info  clearfix">
        <div style=" width:200px; float:left;">
          <p class="footer-title">
            众筹谈判
          </p>
          <p class="tel">
            03-6447-0011
          </p>
          <p>
            ［电话接听时间］工作日 8:40-17:40
          </p>
          <p>
            <br>
            <br>
          </p>
          <p class="text">
            中筹（上海）互联网金融有限公司
            <br>
            <!--电话号码：021-6181-3682（［电话接受时间］周六，周日除外 8:40-17:40）-->
            上海市长宁区延安西路1118号龙之梦大厦2202室
          </p>
        </div>
        <div class="mhm-lesson">
          <img src="${ctx}/img/top/mhm.jpg">
        </div>
      </div>
      <div class="copyright-box">
        <p class="copyright">
          © 2014 中筹金融. All rights reserved.
        </p>
      </div>
    </div>
  </div>
  
  <script language="javascript" type="text/javascript">
      $(document).ready(function() {
          $('#countdown_dashboard').countDown({
              targetDate: {
                  'day': 1,
                  'month': 11,
                  'year': 2014,
                  'hour': 0,
                  'min': 0,
                  'sec': 0
              },

              onComplete: function() {
                  $('#enterLiveMeeting').slideDown();
                  $('#waitLiveMeeting').hide();
              }

          });
      });
  </script>
</div>