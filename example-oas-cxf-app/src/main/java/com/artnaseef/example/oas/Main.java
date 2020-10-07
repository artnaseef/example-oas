package com.artnaseef.example.oas;

import com.artnaseef.example.oas.impl.HelloApiImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

/**
 * Created by art on 5/4/20.
 */
public class Main {

    public static final int DEFAULT_PORT = 9000;

    private int port;

    public static void main(String[] args) {
        Main instance = new Main();

        instance.instanceMain(args);
    }

    public void instanceMain(String[] args) {

        this.port = this.determineServerPort();

        this.setupCxf();
    }

    //========================================
    // INTERNALS: CXF SETUP
    //----------------------------------------

    private void setupCxf() {
        JAXRSServerFactoryBean serverFactoryBean = new JAXRSServerFactoryBean();

        this.configureResources(serverFactoryBean);
        serverFactoryBean.setAddress("http://localhost:" + this.port);

        Bus bus = serverFactoryBean.getBus();

        BindingFactoryManager manager = bus.getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory factory = new JAXRSBindingFactory();
        factory.setBus(bus);
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);

        serverFactoryBean.create();
    }

    private void configureResources(JAXRSServerFactoryBean serverFactoryBean) {
        HelloApiImpl helloApiImpl = new HelloApiImpl();

        // Configure the CXF server to use HelloApiImpl.class
        serverFactoryBean.setResourceClasses(HelloApiImpl.class);

        // And configure it to use our instance instead of creating a new one itself
        serverFactoryBean.setResourceProvider(HelloApiImpl.class, new SingletonResourceProvider(helloApiImpl));
    }

    private int determineServerPort() {
        String portString = System.getProperty("server.port");
        if ( isNullOrEmpty(portString) ) {
            portString = System.getenv("SERVER_PORT");
        }

        if (! isNullOrEmpty(portString)) {
            return Integer.parseInt(portString);
        }

        return DEFAULT_PORT;
    }

    private boolean isNullOrEmpty(String string) {
        return ( (string == null ) || ( string.isEmpty() ) );
    }
}
