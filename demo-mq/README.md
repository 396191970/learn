#功能
发送异步消息，并创建临时队列接收异步返回

<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="text/css" http-equiv="Content-Style-Type">
<title>MQ 双向队列</title>
</head>
<body>
<h1 align="center" class="root">
<a name="2573ubl864mftrvhebo4l1imd2">MQ 双向队列</a>
</h1>
<h2 class="topic">
<a name="7l7hhmo4dea1jqk8fqi01aqi94">发送端</a>
</h2>
<h3 class="topic">
<a name="1g9mc54pb69bva7lebk29cks68">&nbsp;创建连接</a>
</h3>
<h3 class="topic">
<a name="6219simjaobh7nhh8l3jlqo3at">&nbsp;创建session</a>
</h3>
<h3 class="topic">
<a name="2sc829tfvsmloakr3puv6vsntl">&nbsp;创建队列</a>
</h3>
<h3 class="topic">
<a name="46ho46jlakl63ob731hokc2uh6">&nbsp;创建临时队列</a>
</h3>
<p class="relationships">参见: <a href="#4ruob87frf620avei31iftiim2">创建队列</a>
</p>
<h3 class="topic">
<a name="6kqmsvrt4tlhsn31u4q7g9b00f">&nbsp;创建发送者，绑定发送队列</a>
</h3>
<h3 class="topic">
<a name="2nf0t92c54g00ohbdacf8744pr">&nbsp;创建发送消息，绑定临时队列</a>
</h3>
<div class="notesContainer">
<p>        msg.setJMSReplyTo(responseQueue);   //设置回复队列</p>
<p></p>
</div>
<h3 class="topic">
<a name="2mg2nn7rqmfvq6gvivu8903njb">&nbsp;发送消息</a>
</h3>
<h3 class="topic">
<a name="1ud82hrj435j74jh19ltu2pfd7">&nbsp;创建临时队列接受者，绑定消息监听</a>
</h3>
<h3 class="topic">
<a name="6mqt14lferbj2l2b5nadmaet58">&nbsp;异步消息监听处理回调消息</a>
</h3>
<h2 class="topic">
<a name="5mo9r9itt6sl62d027s346cg8m">接收端</a>
</h2>
<h3 class="topic">
<a name="7ps4pe246814icap2pv105kifk">&nbsp;创建连接</a>
</h3>
<h3 class="topic">
<a name="1h6td95pnsvob95cj3fnbe85sh">&nbsp;创建session</a>
</h3>
<h3 class="topic">
<a name="4ruob87frf620avei31iftiim2">&nbsp;创建队列</a>
</h3>
<p class="relationships">参见: <a href="#46ho46jlakl63ob731hokc2uh6">创建临时队列</a>
</p>
<h3 class="topic">
<a name="4j7p5buc8ei8ep03dq6f50134q">&nbsp;创建队列接受者，绑定消息监听</a>
</h3>
<h3 class="topic">
<a name="29abtkgh1vl8icv5mk9ngmgbug">&nbsp;异步消息监听处理回调消息</a>
</h3>
<h3 class="topic">
<a name="5l9dpuilf05n93o8lukh7gh3v1">&nbsp;&nbsp;业务处理</a>
</h3>
<h3 class="topic">
<a name="68f06lqf5cch4jh7io28gn7g4u">&nbsp;&nbsp;从队列消息中获取临时队列</a>
</h3>
<div class="notesContainer">
<p>Queue responseQueue = (Queue)message.getJMSReplyTo();  </p>
<p></p>
</div>
<h3 class="topic">
<a name="73qu9rvcbg98h45bs8988aji8m">&nbsp;&nbsp;创建临时发送者</a>
</h3>
<h3 class="topic">
<a name="4ccu161qtlofat4q3evuvjuhhl">&nbsp;&nbsp;发送回复消息</a>
</h3>
<h2 class="topic">
<a name="2v7k1jb2dsga1mafpgkp06rfj4">代码</a>
</h2>
<h3 class="topic">
<a name="21j0g59dflkentj1tqnvebc1ee">&nbsp;github</a>
</h3>
<div class="notesContainer">
<p>www</p>
</div>
</body>
</html>

#测试
http://127.0.0.1:43343/swagger-ui.html#/