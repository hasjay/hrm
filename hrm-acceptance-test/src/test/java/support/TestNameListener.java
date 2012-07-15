package support;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class TestNameListener implements TestExecutionListener{

	public static String testName = "";
	@Override
	public void afterTestClass(TestContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTestMethod(TestContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTestClass(TestContext context) throws Exception {
		testName = context.getTestClass().getName();
	}

	@Override
	public void beforeTestMethod(TestContext context) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void prepareTestInstance(TestContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
