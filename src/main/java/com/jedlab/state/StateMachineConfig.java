package com.jedlab.state;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events>
{

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception
    {
        config.withConfiguration()
        //.autoStartup(true)
        .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception
    {
        states.withStates().initial(States.START).end(States.END).states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception
    {
        transitions.withExternal()
        .source(States.START).target(States.GLobalEligibility).event(Events.Eligibility)
        .and()
        .withExternal()
        .source(States.DeclareService)
                .target(States.DeployService).event(Events.Declare)
        .and()
        .withExternal()
        .source(States.DeclareService)
                .target(States.DeployService).event(Events.Deploy);
    }

    @Bean
    public StateMachineListener<States, Events> listener()
    {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to)
            {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}