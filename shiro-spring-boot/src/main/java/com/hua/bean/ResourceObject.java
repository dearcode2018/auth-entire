/**
  * @filename ResourceObject.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @type ResourceObject
 * @description 资源对象
 * @author qianye.zheng
 */
@Data
@AllArgsConstructor
public class ResourceObject {
    
    private String id;
    
     /* 资源名称(标识符) */
    private String name;
    
}
