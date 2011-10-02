package net.awired.bootstrap.karaf;

import javax.servlet.ServletContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SystemActivator implements BundleActivator {
    private BundleContext bundleContext;
    private ServletContext servletContext;

    public SystemActivator() {
    }

    public SystemActivator(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        this.bundleContext = bundleContext;
        if (servletContext != null) {
            servletContext.setAttribute(BundleContext.class.getName(), bundleContext);
        }

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        this.bundleContext = null;
    }

    public Bundle[] getBundles() {
        if (bundleContext != null) {
            return bundleContext.getBundles();
        }
        return null;
    }

    public BundleContext getContext() {
        return bundleContext;
    }

}
