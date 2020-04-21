import Mock from 'mockjs'

export default Mock
// ---------------------------------------
// 模拟获取博客列表
/*
initBlogsListPretend() {
  this.blogs = [
    {
      id: 1,
      title: 'OAuth 2.0 认证的原理与实践',
      summary: '使用 OAuth 2.0 认证的的好处是显然易见的。你只需要用同一个账号密码，就能在各个网站进行访问，而免去了在每个网站都进行注册的繁琐过程。 本文将介绍OAuth 2.0 的原理，并基于 Spring Security 和 GitHub 账号，来演示 OAuth 2.0 的认证的过程。',
      userId: '2',
      author: '一舞翩跹',
      createTime: "2019-06-07T14:36:50",
      readSize: 120,
      commentSize: 20,
      voteSize: 12,
      catalogId: 2,
      content: null,
      htmlContent: null
    },
    {
      id: 2,
      title: 'OAuth 2.0 认证的原理与实践',
      summary: '使用 OAuth 2.0 认证的的好处是显然易见的。你只需要用同一个账号密码，就能在各个网站进行访问，而免去了在每个网站都进行注册的繁琐过程。 本文将介绍OAuth 2.0 的原理，并基于 Spring Security 和 GitHub 账号，来演示 OAuth 2.0 的认证的过程。',
      userId: '2',
      author: '一舞翩跹',
      createTime: "2019-06-07T14:36:50",
      readSize: 2019,
      commentSize: 20,
      voteSize: 11,
      catalogId: 2,
      content: null,
      htmlContent: null
    }
  ]
}
// 模拟登录
loginPretend() {
  console.log('')
  const user = {
    "id": 2,
    "username": "nanocore",
    "password": "$2a$10$60JDi32udQFZFi89ZcubvOafo73evIeFQJhalCYEUqhzKx8i5JN52",
    "nickname": "一舞翩跹",
    "email": "pianxian@game.com",
    "avatar": null,
    "createTime": {
      "year": 2019,
      "monthValue": 5,
      "dayOfMonth": 22,
      "hour": 8,
      "minute": 0,
      "second": 0,
      "nano": 0,
      "month": "MAY",
      "dayOfWeek": "WEDNESDAY",
      "dayOfYear": 142,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "lastLoginTime": {
      "year": 2019,
      "monthValue": 6,
      "dayOfMonth": 5,
      "hour": 15,
      "minute": 46,
      "second": 27,
      "nano": 842848000,
      "month": "JUNE",
      "dayOfWeek": "WEDNESDAY",
      "dayOfYear": 156,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "enabled": true,
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "authorities": null
  }
  this.$store.dispatch('initUser', user)
  this.$router.push({path: '/index'})
}
initUserPretend() {
  this.user =
    {
      "id": this.$route.params.id,
      "username": "nanocore",
      "password": "$2a$10$60JDi32udQFZFi89ZcubvOafo73evIeFQJhalCYEUqhzKx8i5JN52",
      "nickname": "一舞翩跹",
      "email": "pianxian@game.com",
      "avatar": null,
      "createTime": {
        "year": 2019,
        "monthValue": 5,
        "dayOfMonth": 22,
        "hour": 8,
        "minute": 0,
        "second": 0,
        "nano": 0,
        "month": "MAY",
        "dayOfWeek": "WEDNESDAY",
        "dayOfYear": 142,
        "chronology": {
          "id": "ISO",
          "calendarType": "iso8601"
        }
      },
      "lastLoginTime": {
        "year": 2019,
        "monthValue": 6,
        "dayOfMonth": 5,
        "hour": 15,
        "minute": 46,
        "second": 27,
        "nano": 842848000,
        "month": "JUNE",
        "dayOfWeek": "WEDNESDAY",
        "dayOfYear": 156,
        "chronology": {
          "id": "ISO",
          "calendarType": "iso8601"
        }
      },
      "enabled": true,
      "accountNonExpired": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true,
      "authorities": null
    }
}
initBlogPretend() {
  this.blog = {
    "id": 2,
    "title": "OAuth 2.0 认证的原理与实践",
    "summary": "使用 OAuth 2.0 认证的的好处是显然易见的。你只需要用同一个账号密码，就能在各个网站进行访问，而免去了在每个网站都进行注册的繁琐过程。本文将介绍 OAuth 2.0 的原理，并基于 Spring Security 和 GitHub 账号，来演示 OAuth 2.0 的认证的过程。",
    "userId": 2,
    "author": "一舞翩跹",
    "createTime": "2019-06-07T14:36:50",
    "readSize": 122,
    "commentSize": 20,
    "voteSize": 12,
    "catalogId": 4,
    "content": "ccc",
    "htmlContent": "<h2 id=\"什么是-oauth-20\">什么是 OAuth 2.0</h2>" +
      "<p>OAuth 2.0 的规范可以参考 ： <a href=\"https://tools.ietf.org/html/rfc6749\">RFC 6749</a></p>" +
      "<p>OAuth 是一个开放标准，允许用户让第三方应用访问该用户在某一网站上存储的私密的资源（如照片，视频，联系人列表），而无需将用户名和密码提供给第三方应用。目前，OAuth 的最新版本为2.0</p>" +
      "<p>OAuth允许用户提供一个令牌，而不是用户名和密码来访问他们存放在特定服务提供者的数据。每一个令牌授权一个特定的网站（例如，视频编辑网站)在特定的时段（例如，接下来的2小时内）内访问特定的资源（例如仅仅是某一相册中的视频）。这样，OAuth允许用户授权第三方网站访问他们存储在另外的服务提供者上的信息，而不需要分享他们的访问许可或他们数据的所有内容。</p>" +
      "<h2 id=\"oauth-20-的核心概念\">OAuth 2.0 的核心概念</h2>" +
      "<p>OAuth 2.0 主要有4类角色：</p>" +
      "<ul>" +
      "  <li>resource owner：资源所有者，指终端的“用户”（user）</li>" +
      "  <li>resource server：资源服务器，即服务提供商存放受保护资源。访问这些资源，需要获得访问令牌（accesstoken）。它与认证服务器，可以是同一台服务器，也可以是不同的服务器。如果，我们访问新浪博客网站，那么如果使用新浪博客的账号来登录新浪博客网站，那么新浪博客的资源和新浪博客的认证都是同一家，可以认为是同一个服务器。如果，我们是新浪博客账号去登录了知乎，那么显然知乎的资源和新浪的认证不是一个服务器。</li>" +
      "  <li>client：客户端，代表向受保护资源进行资源请求的第三方应用程序。</li>" +
      "  <li>authorization server： 授权服务器， 在验证资源所有者并获得授权成功后，将发放访问令牌给客户端</li>" +
      "</ul>" +
      "<h2 id=\"oauth-20-的认证流程\">OAuth 2.0 的认证流程</h2>" +
      "<p>认证流程如下：</p>" +
      "<ul>" +
      "  <li>（A）用户打开客户端以后，客户端请求资源所有者（用户）的授权。</li>" +
      "  <li>（B）用户同意给予客户端授权。</li>" +
      "  <li>（C）客户端使用上一步获得的授权，向认证服务器申请访问令牌。</li>" +
      "  <li>（D）认证服务器对客户端进行认证以后，确认无误，同意发放访问令牌。</li>" +
      "  <li>（E）客户端使用访问令牌，向资源服务器申请获取资源。</li>" +
      "  <li>（F）资源服务器确认令牌无误，同意向客户端开放资源。</li>" +
      "</ul>" +
      "<p>其中，用户授权有四种模式：</p>" +
      "<ul>" +
      "  <li>授权码模式（authorization code）</li>" +
      "  <li>简化模式（implicit）</li>" +
      "  <li>密码模式（resource owner password credentials）</li>" +
      "  <li>客户端模式（client credentials）</li>" +
      "</ul>" +
      "<h2>源码</h2>" +
      "<ul>" +
      "  <li>《Spring Security 教程》：" +
      "    <a href=\"https://github.com/waylau/spring-security-tutorial/tree/master/samples/oauth2-github\">https://github.com/waylau/spring-security-tutorial/tree/master/samples/oauth2-github</a>" +
      "  </li>" +
      "</ul>",
    "tags": [
      {
        "id": 5,
        "name": "Spring",
        "blogId": null
      },
      {
        "id": 6,
        "name": "测试",
        "blogId": null
      }
    ]
  }
},
*/
