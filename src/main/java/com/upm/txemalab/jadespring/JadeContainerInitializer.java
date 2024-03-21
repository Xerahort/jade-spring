package com.upm.txemalab.jadespring;

import com.upm.txemalab.jadespring.agents.AgPlatform;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log
@Component
public class JadeContainerInitializer implements CommandLineRunner {


    @Override
    public void run(String... args) {

        System.setProperty("java.awt.headless", "false");

        Profile profile = new ProfileImpl(), agentProfile = new ProfileImpl();

        // Configura el perfil del contenedor principal JADE.
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        profile.setParameter(Profile.CONTAINER_NAME, "Main-Container");
        profile.setParameter(Profile.GUI, "true");
        AgentContainer mainContainer = jade.core.Runtime.instance().createMainContainer(profile);

        // Configura el perfil del contenedor de agentes.
        agentProfile.setParameter(Profile.MAIN_HOST, "localhost");
        agentProfile.setParameter(Profile.MAIN_PORT, "1099");
        agentProfile.setParameter(Profile.CONTAINER_NAME, "SpringBoot-Container");
        AgentContainer springContainer = jade.core.Runtime.instance().createAgentContainer(agentProfile);

        if (mainContainer == null || springContainer == null) throw new IllegalStateException("Some container could not be initialized");

        try {
            springContainer.createNewAgent("John Doe", AgPlatform.class.getName(), null).start();
        } catch (StaleProxyException e) {
            log.severe(e.getMessage());
        }

    }
}
