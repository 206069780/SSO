package com.senrui.sso.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationContextHelper implements ApplicationContextAware{
	private ApplicationContext applicationContext;
	private static ApplicationContextHelper instance = null;
	private ApplicationContextHelper(){
		
	}

	//实例化  单采用单例模式
	public static ApplicationContextHelper getInstance(){
		if(instance == null)
			instance = new ApplicationContextHelper();
		return instance;
	}
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		instance = this;
		this.applicationContext = arg0;
	}
	/**
	 * 获取ApplicationContext
	 * @return
	 */
	public ApplicationContext getApplicationContext(){
		checkApplicationContext();
		return this.applicationContext;
	}
	/**
	 * 根据BeanName获取Bean
	 * @param <T>
	 * @param beanName
	 * @return
	 */
	public <T> T getBean(String beanName){
		return (T) this.applicationContext.getBean(beanName);
	}
	/**
	 * 获取Bean
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T> T getBean(Class<T> clazz){
		return (T) this.applicationContext.getBean(clazz);
	}
	/**
	 * 获取相同了类型的bean
	 * @param clazz
	 * @return
	 */
	public <T> Map<String,T>getBeansOfType(Class<T> clazz){
		return (Map<String,T>)this.applicationContext.getBeansOfType(clazz);
	}
	/**
	 * 创建bean
	 * @param clazz
	 * @return
	 */
	public <T> T createBean(Class<T> clazz){
		return this.getApplicationContext().getAutowireCapableBeanFactory().createBean(clazz);
	}
	/**
	 * 清除ApplicationContext
	 */
	public void clearApplicationContext(){
		this.applicationContext = null;
	}
	/**
	 * 检查
	 */
	private void checkApplicationContext() {
		if(this.applicationContext == null){
			System.out.println("applicationContext未注入，请检查是否添加正确注解");
		}
	}
}
