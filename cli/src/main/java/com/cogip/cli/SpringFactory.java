package com.cogip.cli;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
public class SpringFactory implements CommandLine.IFactory {
    private final ApplicationContext context;

    public SpringFactory(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <K> K create(Class<K> cls) throws Exception {
        return context.getBean(cls);
    }
}