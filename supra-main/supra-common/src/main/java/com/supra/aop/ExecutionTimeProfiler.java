package com.supra.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ExecutionTimeProfiler {

	 //@Pointcut("execution(* com.supra.implementation.*.*(..))")
	 //public void businessMethods() { }

	@Pointcut("within(com.supra.implementation.*ServiceImpl)")
	public void serviceLayer() {
	}

	@Pointcut("within(com.supra.implementation.*DAOImpl)")
	public void DAOLayer() {
	}

	
	/*  @Around("businessMethods()") 
	  public Object profile2(ProceedingJoinPoint pjp) throws Throwable {
	  	long start = System.currentTimeMillis();
	  	System.out.println("businessMethods."); 
	  	Object output =	  	pjp.proceed(); 
	  	System.out.println("Method execution completed."); 
	  	long 	elapsedTime = System.currentTimeMillis() - start;
	  	System.out.println("MethbusinessMethods execution time: " + elapsedTime +" milliseconds."); 
	  	return output; 
	  	}*/
	

	// @Around("businessMethods()")
	@Around("serviceLayer()")
	// @Around("DAOLayer()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Method method = getCurrentMethod(pjp);
		System.out.println("Going to call the method." + method.getName());

		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");

		return output;

	}

	private Method getCurrentMethod(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		return signature.getMethod();
	}

}