<script setup>
import Buttons from './components/elementUI/Buttons.vue'
import Tables from './components/elementUI/Tables.vue'
</script>
<template>

    <body>
        <div class="main_contain">
            <span>
                <h3 class="title">焦点访谈</h3>
                <b>2023年3月15日</b>
            </span>
            <p>
                <b style="color: green;">你好</b> 本课程基于主流的前后端分离的开发模式进行设计和讲解，基于主流的SpringBoot来讲解整个Web开发的知识点，
            </p>
            <p id="second_para">
                参照企业开发模式，需求分析-表结构设计-接口文档-功能接口实现-测试-联调，理论与实践相结合，通过案例贯穿整个课程体系，学以致用。
                <a href="baidu.com" class="link">百度</a>
            </p>
            <hr>
                <Tables></Tables>
            <br>
            <img src="./assets/2429192510468259840.jpg">
            <br>
            <!-- action填表单提交到的目标url，一般是后端提供，不写默认到本页面
         ；method，get方法将表单数据直接加载url后面，post将表单数据封装在请求体中 -->
            <form action="" method="get">
                <!-- 表单标签：<input> -->
                <div class="form_span">
                    <input type="text" name="username" placeholder="请输入用户名">
                    <br>
                    <input type="password" name="password" placeholder="请输入密码">
                </div>
                <div class="form_span_1">
                    <input type="radio" name="sex" value="male">男
                    <br>
                    <input type="radio" name="sex" value="female">女
                    <br>
                    <input type="checkbox" name="hobby" value="reading">阅读
                    <input type="checkbox" name="hobby" value="swimming">游泳
                    <br>
                    <Buttons />
                    <input type="submit" value="提交">
                </div>


            </form>
            <p id="plast">
                责任编辑：A
            </p>
        </div>
    </body>
</template>