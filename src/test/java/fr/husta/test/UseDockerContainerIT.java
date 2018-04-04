package fr.husta.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Test using a Docker container.
 */
public class UseDockerContainerIT {

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

        System.out.println("tomcatContainerId : " + tomcatContainerId);
        System.out.println("tomcatContainerIp : " + tomcatContainerIp);
        System.out.println("tomcatContainerPort : " + tomcatContainerPort);
        System.out.println("dockerMachineIp : " + dockerMachineIp);
    }

    @Test
    public void checkSocket() throws IOException {
        String localContainerIp = (dockerMachineIp.isEmpty() ? tomcatContainerIp : dockerMachineIp);
        URL url = new URL("http://" + localContainerIp + ":" + tomcatContainerPort);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        assertThat(httpURLConnection.getResponseCode()).isEqualTo(200);
    }

}
