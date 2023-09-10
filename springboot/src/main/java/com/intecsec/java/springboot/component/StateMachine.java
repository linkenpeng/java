package com.intecsec.java.springboot.component;

import lombok.Data;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-10 22:53
 **/
@Data
public class StateMachine<S, E> {

    public void sendEvent(Events event) {

    }

    private ExtendedState extendedState;
}
