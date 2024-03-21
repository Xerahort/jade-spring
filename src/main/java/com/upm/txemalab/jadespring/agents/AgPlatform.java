package com.upm.txemalab.jadespring.agents;

import jade.core.Agent;
import lombok.extern.java.Log;

@Log
public class AgPlatform extends Agent {

    private static final String ANSI_CYAN = "\u001b[36m";

    protected void setup() {
        log.info(ANSI_CYAN + this.getLocalName() + ": I'm alive!!!");
    }
}
