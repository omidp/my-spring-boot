package com.jedlab.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jedlab.state.Events;
import com.jedlab.state.StateManager;
import com.jedlab.state.StateValueHolder;
import com.jedlab.state.States;

@Controller
public class HelloController
{
    private static final Logger logger = Logger.getLogger(HelloController.class.getName());
    
    @Autowired
    StateMachine<States, Events> stateMachine;

    @RequestMapping("/")
    public String index(Map<String, Object> model)
    {
        model.put("message", "Hello Statemachine is running check console log");
        stateMachine.start();
        Map<String, Object> headers = new HashMap<>();
        headers.put(StateValueHolder.class.getName(), new StateValueHolder(UUID.randomUUID().toString()));
        GenericMessage<Events> ge = new GenericMessage<Events>(Events.Eligibility, headers);
        stateMachine.sendEvent(ge);
        //
        StateValueHolder stv = (StateValueHolder) stateMachine.getExtendedState().getVariables().get(StateValueHolder.class.getName());
        if(stv != null)
            logger.info("variable comes from state machine : " + stv.getMessageId());
        //
        GenericMessage<Events> de = new GenericMessage<Events>(Events.Declare, headers);
        stateMachine.sendEvent(de);
        //
        stateMachine.stop();
        return "hello";
    }

}
