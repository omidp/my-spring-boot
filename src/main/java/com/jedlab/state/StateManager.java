package com.jedlab.state;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.transaction.annotation.Transactional;

@WithStateMachine
public class StateManager
{

    private static final Logger logger = Logger.getLogger(StateManager.class.getName());
    
    @StatesOnTransition(source=States.START, target=States.GLobalEligibility)
    @Transactional
    public void eligibilityTransition(@EventHeaders Map<String, Object> headers, 
            ExtendedState extendedState,
            StateMachine<String, String> stateMachine, 
            Message<String> message, Exception e)
    {
        logger.info("eligibilityTransition");
    }
    
    
    @StatesOnTransition(source=States.GLobalEligibility, target=States.DeclareService)
    @Transactional
    public void declareTransition(@EventHeaders Map<String, Object> headers, 
            ExtendedState extendedState,
            StateMachine<String, String> stateMachine, 
            Message<String> message, Exception e)
    {
        logger.info("declareTransition"
                + "");
    }
    
    @StatesOnTransition(source=States.DeclareService, target=States.DeployService)
    @Transactional
    public void deployTransition(@EventHeaders Map<String, Object> headers, 
            ExtendedState extendedState,
            StateMachine<String, String> stateMachine, 
            Message<String> message, Exception e)
    {
        logger.info("deployTransition"
                + "");
    }
    
}