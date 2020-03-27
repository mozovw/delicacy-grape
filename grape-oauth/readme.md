
### 密码模式

```$xslt
localhost:8080/oauth/token?client_id=client_id_1&client_secret=123456&grant_type=password&username=admin&password=123456
{
	access_token: "b909b1e6-bf10-4c13-bef7-bfca64211feb",
	token_type: "bearer",
	refresh_token: "521c491d-ef73-45af-aefb-36c5c66cbe89",
	expires_in: 3599,
	scope: "read write"
}
```

### 简化模式
```$xslt
1、http://localhost:8080/login 输入账号密码

2、再次输入如下地址，获取token
http://localhost:8080/oauth/authorize?response_type=token&client_id=client_id_1&redirect_uri=http://example.com&scope=write 
地址栏重定向如下
http://example.com/#access_token=2820363f-2e2f-4485-8892-64683895675e&token_type=bearer&expires_in=3512

```


### 授权码模式

```$xslt
1、http://localhost:8080/login 输入账号密码

2、再次输入如下地址，获取code
http://localhost:8080/oauth/authorize?response_type=code&client_id=client_id_1&redirect_uri=http://example.com&scope=write
地址栏重定向如下
http://example.com/?code=n9WlYp 

3、http://localhost:8080/oauth/token?grant_type=authorization_code&client_id=client_id_1&client_secret=123456&redirect_uri=http://example.com&code=n9WlYp
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
	access_token: "097db1fd-20a1-4a8f-a7a9-8d500309b12e",
	token_type: "bearer",
	expires_in: 3590,
	scope: "read write"
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

