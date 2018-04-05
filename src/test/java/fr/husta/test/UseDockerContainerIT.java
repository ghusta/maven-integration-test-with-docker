package fr.husta.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Test using a Docker container.
 */
public class UseDockerContainerIT {

    private static final Logger log = LoggerFactory.getLogger(UseDockerContainerIT.class);

    private static String tomcatContainerId;
    private static String tomcatContainerIp;
    private static int tomcatContainerPort;
    private static String dockerMachineIp;

    @BeforeClass
    public static void setUpClass() throws Exception {
        tomcatContainerId = System.getProperty("tomcat.container.id");
        tomcatContainerIp = System.getProperty("tomcat.container.ip");
        tomcatContainerPort = Integer.parseInt(System.getProperty("tomcat.container.port", "-1"));
        dockerMachineIp = System.getProperty("docker-machine.ip");

        log.info("tomcatContainerId : " + tomcatContainerId);
        log.info("tomcatContainerIp : " + tomcatContainerIp);
        log.info("tomcatContainerPort : " + tomcatContainerPort);
        log.info("dockerMachineIp : " + dockerMachineIp);
    }

    @Test
    public void checkSocket() throws IOException {
        String localContainerIp = (dockerMachineIp.isEmpty() ? tomcatContainerIp : dockerMachineIp);
        URL url = new URL("http://" + localContainerIp + ":" + tomcatContainerPort);
        log.info("URL to test : " + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        assertThat(httpURLConnection.getResponseCode()).isEqualTo(200);
    }

}
