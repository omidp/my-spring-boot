package com.jedlab.exceptions;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class JedlabFailureAnalyzer extends AbstractFailureAnalyzer<JedlabFailureException>
{

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, JedlabFailureException cause)
    {
        return new FailureAnalysis("this is a showcase of failure", "remove me", cause);
    }

}
