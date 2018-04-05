package fr.husta.test;

import org.apache.commons.lang3.SystemUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        checkDockerDaemonSocket();

        tomcatContainerId = System.getProperty("tomcat.container.id");
        tomcatContainerIp = System.getProperty("tomcat.container.ip");
        tomcatContainerPort = Integer.parseInt(System.getProperty("tomcat.container.port", "-1"));
        dockerMachineIp = System.getProperty("docker-machine.ip");

        log.info("tomcatContainerId : " + tomcatContainerId);
        log.info("tomcatContainerIp : " + tomcatContainerIp);
        log.info("tomcatContainerPort : " + tomcatContainerPort);
        log.info("dockerMachineIp : " + dockerMachineIp);
        log.info("docker.host.address : " + System.getProperty("docker.host.address"));
    }

    @Test
    public void checkSocket() throws IOException {
        String localContainerIp = (dockerMachineIp.isEmpty() ? /*tomcatContainerIp*/ "localhost" : dockerMachineIp);
        URL url = new URL("http://" + localContainerIp + ":" + tomcatContainerPort);
        log.info("URL to test : " + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        assertThat(httpURLConnection.getResponseCode()).isEqualTo(200);
    }

    /**
     * See https://docs.docker.com/engine/reference/commandline/dockerd/#examples
     */
    private static void checkDockerDaemonSocket() {
        // requires either root permission, or docker group membership.
        final String unixDockerSocketFile = "/var/run/docker.sock";
        boolean exists = false;
        if (SystemUtils.IS_OS_LINUX) {
            exists = Files.exists(Paths.get(unixDockerSocketFile));
        }
        log.debug("Found Docker Daemon Socket ? " + exists);
    }

}
