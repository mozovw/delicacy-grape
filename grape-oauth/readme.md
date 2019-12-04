### 密码模式


```$xslt
localhost:8080/oauth/token?client_id=client_id_1&client_secret=123456&grant_type=password&username=admin&password=123456
{
    "access_token": "1e8ada36-ed82-49fc-9ca7-ff897d4bf2b2",
    "token_type": "bearer",
    "refresh_token": "9ba4580d-3497-4634-9696-af51a8b9bd41",
    "expires_in": 4,
    "scope": "all"
}
```

### 简化模式
```$xslt
1、如下地址输入，会重定向 http://localhost:8080/login
http://localhost:8080/oauth/authorize?response_type=token&client_id=client_id_1&redirect_uri=http://example.com&scope=write

2、http://localhost:8080/login 输入账号密码，然后再次输入
http://localhost:8080/oauth/authorize?response_type=token&client_id=client_id_1&redirect_uri=http://example.com&scope=write

3、获取token
http://example.com/#access_token=3194ab36-e019-4557-8007-7a7fb2482542&token_type=bearer&expires_in=99http://example.com/#access_token=3194ab36-e019-4557-8007-7a7fb2482542&token_type=bearer&expires_in=99
 
```


### 授权码模式

```$xslt
1、如下地址输入，会重定向 http://localhost:8080/login
http://localhost:8080/oauth/authorize?response_type=code&client_id=client_id_1&redirect_uri=http://example.com&scope=write

2、http://localhost:8080/login 输入账号密码，然后再次输入
http://localhost:8080/oauth/authorize?response_type=code&client_id=client_id_1&redirect_uri=http://example.com&scope=write

3、http://example.com/?code=n9WlYp 获取授权码

4、http://localhost:8080/oauth/token?grant_type=authorization_code&client_id=client_id_1&client_secret=123456&redirect_uri=http://example.com&code=n9WlYp
{
    "access_token": "812403de-548d-4939-b0fa-57b1ec8abb11",
    "token_type": "bearer",
    "refresh_token": "8a8a7ebb-6f73-452d-9582-2fbf4ff80815",
    "expires_in": 99,
    "scope": "write"
}
```

### 客户端模式

```
localhost:8080/oauth/token?client_id=client_id_1&client_secret=123456&grant_type=client_credentials
{
    "access_token": "c8ae1362-2084-4dbc-986c-847b7fe66f6c",
    "token_type": "bearer",
    "expires_in": 4,
    "scope": "all"
}
```

### refresh token

```$xslt
$ curl -X GET "localhost:8080/oauth/token?client_id=client_id_1&client_secret=123456&grant_type=refresh_token&refresh_token=fca82077-720e-46da-9c88-c2f221b0cb46"

{"access_token":"0274a51f-f61b-4bb0-9ae4-b99ef508c91e","token_type":"bearer","refresh_token":"fca82077-720e-46da-9c88-c2f221b0cb46","expires_in":99,"scope":"read write"}

```

### 使用token获取资源

```$xslt
$ curl  -H "Authorization:bearer 284a5718-0a80-4eab-9d04-1bda3b6ceb62" -X GET  http://localhost:8080/user/get
{"error":"invalid_token","error_description":"Invalid access token: 284a5718-0a80-4eab-9d04-1bda3b6ceb62"}
或者
$ curl -X GET  http://localhost:8080/user/get?access_token=0f22ff90-1834-4654-9576-8737ec84d61e

```




