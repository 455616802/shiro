package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloShiro {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloShiro.class);

	public static void main(String[] args) {
		// 1、初始化securityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		// 2、获取当前用户
		Subject subject = SecurityUtils.getSubject();
		
		// 3、登录
		UsernamePasswordToken token = new UsernamePasswordToken("shiro","201314");
		try{
			subject.login(token);
		}catch(AuthenticationException ae){
			logger.info("login failure");
			return;
		}
		logger.info("login success："+subject.getPrincipal());
		
		// 4、注销
		subject.logout();

	}

}
