/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package fr.norad.bootstrap.sample.war.cli;

import java.net.URL;
import java.security.ProtectionDomain;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.xerial.snappy.Snappy;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    public void run(String[] args) throws Exception {
        Snappy.maxCompressedLength(4242);
        Snappy.getNativeLibraryVersion();
        System.out.println("OKWAR!!");

        final Server server = new Server();
        SocketConnector connector = new SocketConnector();

        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(54789);
        server.setConnectors(new Connector[] { connector });

        WebAppContext context = new WebAppContext();
        context.setServer(server);
        context.setContextPath("/");
        ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        context.setDescriptor(location.toExternalForm() + "/WEB-INF/web.xml");
        context.setWar(location.toExternalForm());

        server.setHandler(context);

        server.start();
        server.setStopAtShutdown(true);
        server.join();
    }

}
