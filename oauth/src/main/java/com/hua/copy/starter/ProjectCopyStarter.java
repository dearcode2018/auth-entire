/**
 * 描述: 
 * ProjectCopyStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.copy.starter;

import org.junit.Test;

import com.hua.copy.driver.ProjectCopyDriver;

/**
 * 描述: 项目模板拷贝 - 启动器
 * @author  qye.zheng
 * 
 * ProjectCopyStarter
 */
public final class ProjectCopyStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		ProjectCopyDriver.templateCopy();
		
	}

}
