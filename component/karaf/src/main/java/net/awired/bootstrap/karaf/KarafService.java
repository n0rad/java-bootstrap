package net.awired.bootstrap.karaf;

import java.util.Arrays;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarafService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    // conf
    private String karafRoot;
    private String karafHome;
    private ServletContext servletContext;

    // run
    private KarafMain main;
    private Framework osgi;
    private SystemActivator systemActivator;

    public KarafService(String karafRoot) {
        this.karafHome = karafRoot;
        this.karafRoot = karafRoot;
    }

    public KarafService(String karafRoot, String karafHome) {
        this.karafHome = karafHome;
        this.karafRoot = karafRoot;
    }

    ////////////////////////////////////////////////////////////////////

    public void start() {
        //        String home = System.getProperty(ApplicationHelper.HOME_KEY) + "/karaf";
        //    new File(home + "/data").mkdirs();
        //    new File(home + "/deploy").mkdirs();
        System.setProperty("karaf.home", karafRoot);
        System.setProperty("karaf.base", karafHome);
        //        System.setProperty("karaf.data", karafHome + "/data");
        //        System.setProperty("karaf.history", karafHome + "/data/history.txt");
        //        System.setProperty("karaf.instances", karafHome + "/instances");
        System.setProperty("karaf.startLocalConsole", "false");
        System.setProperty("karaf.startRemoteShell", "true");
        System.setProperty("karaf.lock", "false");

        if (servletContext != null) {
            System.setProperty(
                    Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
                    "org.osgi.framework; version=1.4.0, "
                            + "org.osgi.framework.hooks.service, "
                            + "org.osgi.framework.launch, "
                            + "org.osgi.service.condpermadmin; version=1.0.0, "
                            + "org.osgi.service.packageadmin; version=1.2.0, "
                            + "org.osgi.service.permissionadmin; version=1.2.0, "
                            + "org.osgi.service.startlevel; version=1.1.0, "
                            + "org.osgi.service.url; version=1.0.0,"
                            + "org.osgi.service.application;version=\"1.0\", "
                            + "org.osgi.service.cm;version=\"1.2\", "
                            + "org.osgi.service.component;version=\"1.0\", "
                            + "org.osgi.service.deploymentadmin;version=\"1.0\", "
                            + "org.osgi.service.deploymentadmin.spi;uses:=\"org.osgi.service.deploymentadmin\";version=\"1.0\", "
                            + "org.osgi.service.device;version=\"1.1\", "
                            + "org.osgi.service.event;version=\"1.1\", " + "org.osgi.service.http;version=\"1.2\", "
                            + "org.osgi.service.io;version=\"1.0\", " + "org.osgi.service.log;version=\"1.3\", "
                            + "org.osgi.service.metatype;version=\"1.1\", "
                            + "org.osgi.service.monitor;version=\"1.0\", "
                            + "org.osgi.service.prefs;version=\"1.1\", "
                            + "org.osgi.service.provisioning;version=\"1.1\", "
                            + "org.osgi.service.upnp;version=\"1.1\", "
                            + "org.osgi.service.useradmin;version=\"1.1\", "
                            + "org.osgi.service.wireadmin;version=\"1.0\", "
                            + "info.dmtree.notification;version=\"1.0\", "
                            + "info.dmtree.notification.spi;uses:=\"info.dmtree.notification\";version=\"1.0\", "
                            + "info.dmtree.registry;uses:=\"info.dmtree.notification\";version=\"1.0\", "
                            + "info.dmtree.security;version=\"1.0\", " + "info.dmtree.spi;version=\"1.0\", "
                            + "org.osgi.util.gsm;version=\"1.0\", " + "org.osgi.util.measurement;version=\"1.0\", "
                            + "org.osgi.util.mobile;version=\"1.0\", "
                            + "org.osgi.util.position;uses:=\"org.osgi.util.measurement\";version=\"1.0\", "
                            + "org.osgi.util.tracker;version=\"1.3.3\", " + "org.osgi.util.xml;version=\"1.0\","
                            + "javax.servlet;javax.servlet.http;version=2.5");
        }

        main = new KarafMain(new String[0]);

        systemActivator = new SystemActivator(servletContext);

        Properties p = new Properties();
        p.put("felix.systembundle.activators", Arrays.asList(systemActivator));

        main.setAdditionalProperties(p);

        LOG.info("Starting Karaf with root : " + karafRoot + " and home : " + karafHome);
        try {
            main.launch();
            osgi = main.getFramework();
        } catch (Exception e) {
            LOG.error("Cannot start karaf container", e);
        }
    }

    public void stop() {
        LOG.info("Shutting down karaf");
        try {
            main.destroy();
        } catch (Exception e) {
            LOG.warn("Problem when shutting down karaf", e);
        }
    }

    //////////////////////////////////////////////////////:

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
