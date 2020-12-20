/**
 * 描述: 
 * JwtTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.auth;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64UrlCodec;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JwtTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class JwtTest extends BaseTest {

    // 秘钥
    String secret = "KA032IDS";
    
    /**
     * 
     * 描述: 
     * @author qye.zheng
     * 
     */
    //@DisplayName("test")
    @Test
    public void testGenerateToken() {
        try {
            String token = Jwts.builder().setHeaderParam("type", "JWT")
            .setSubject("主题or非敏感业务参数").setIssuedAt(new Date())
            .setNotBefore(new Date())
            .setExpiration(DateTimeUtil.parseDateTime("2021-12-07 13:07:21"))
            .signWith(SignatureAlgorithm.HS512, secret).compact();
            
            // 
            System.out.println(token);
            
        } catch (Exception e) {
            log.error("test =====> ", e);
        }
    }	
	
    /**
     * 
     * 描述: 
     * @author qye.zheng
     * 
     */
    //@DisplayName("test")
    @Test
    public void testGetChaim() {
        try {
            String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiLkuLvpophvcumdnuaVj-aEn-S4muWKoeWPguaVsCIsImlhdCI6MTYwNzMxODg1NCwibmJmIjoxNjA3MzE4ODU0LCJleHAiOjE2Mzg4NTM2NDF9.caJynfYmUyNpTqDAWAsIBLoOt1sHD6ObKArAkqYcYIJuACIeP-Z60vMSEe4kbAoO168IIEiJ7LvfPvrR__6Mmg";
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            
            assertTrue(null != claims.getSubject());
            System.out.println(claims.getSubject());
            
            // 判断Claims 是否有效
            if (null != claims && new Date().before(claims.getExpiration())) {
                System.out.println("令牌有效");
            }
            
        } catch (Exception e) {
            log.error("test =====> ", e);
        }
    }
    
    /**
     * 
     * 描述: 
     * @author qye.zheng
     * 
     */
    //@DisplayName("test")
    @Test
    public void testBase64UrlDecode() {
        try {
            /**
             * 通过Base64Url解码器  可以解码
             * 因此jwt的header/payload默认是不加密的
             * 不能直接传输秘密数据
             */
            String encoded = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ";
            Base64UrlCodec codec = new Base64UrlCodec();
            System.out.println(codec.decodeToString(encoded));
            
            encoded = "eyJzdWIiOiLkuLvpophvcumdnuaVj-aEn-S4muWKoeWPguaVsCIsImlhdCI6MTYwNzMxODg1NCwibmJmIjoxNjA3MzE4ODU0LCJleHAiOjE2Mzg4NTM2NDF9";
            System.out.println(codec.decodeToString(encoded));
            
            
        } catch (Exception e) {
            log.error("test =====> ", e);
        }
    }
    
    
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testTemp")
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testCommon")
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testSimple")
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
		assumeFalse(false);
		assumeTrue(true);
		assumingThat(true, null);
	}

}
