# auth-entire
`描述: 认证授权`


## jwt
`https://github.com/jwtk/jjwt` 
##### 组成
header.payload.signature

##### header部分
{
  "alg":"签名算法:HS256",
  "typ":"JWT，注意是typ不是type"
}

##### payload部分 # 官方定义了一些，可以根据系统来自定义
{
  "iss":"签发人",
  "exp":"过期时间",
  "sub":"主题",
  "aud":"受众/听众"
  "nbf":"not before : 生效启示时间",
  "iat":"issued at 签发时间",
  "jti":"jwt id",
  // 其他自定义字段...
}

##### signature部分
对前2部分的签名，防止数据篡改.
服务器指定一个秘钥(secret)，只有服务器，不能泄露给客户端
使用header里面指定的签名算法(默认是HMAC SHA256)
HMACSHA256(base64UrlEncode(header部分) + "." + base54UrlEncode(payload部分), secret)

##### 签名生成
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  "密钥"
)

#####  传输规范
1) 可以放在cookie中，每次请求自动发送，但是若客户端不支持cookie或者跨域，则行不通
2) 常用做法是，放在http的头信息中，以 Authorization 为key.
curl -X -H 'Authorization:'
3) 跨域时，可以将JWT放在post请求的数据体中
4) JWT默认是不加密的，生成原始token之后，可以用再加密一次，没有加密的是不能传输秘密数据
可以对payload中的key对应的value进行单独加密，然后放在JWT中传输
5) 为了减少盗用，JWT应使用HTTPS协议来传输
6) JWT一旦签发了，就无法在使用过程中废止某个token，若需要废止则需要服务端额外的逻辑，例如再维护一套简单的token逻辑.
7)



---
## oauth
#####  描述
description



---

