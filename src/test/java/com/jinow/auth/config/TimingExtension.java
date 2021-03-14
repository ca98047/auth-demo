package com.jinow.auth.config;

import com.jinow.auth.annotation.SlowTest;
import com.jinow.auth.exception.TestTimeoutException;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());
    private static final String START_TIME = "start time";
    private static final long TRESHOLD = 1000;

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        SlowTest annotation = testMethod.getAnnotation(SlowTest.class);
        if (!ObjectUtils.isEmpty(annotation)) {
            return;
        }

        long startTime = getStore(context).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;

        if (duration > TRESHOLD) {
            throw new TestTimeoutException(String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
        }

        logger.info(() ->
                String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}
