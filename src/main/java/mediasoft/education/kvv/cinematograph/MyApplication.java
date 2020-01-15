package mediasoft.education.kvv.cinematograph;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/app")
public class MyApplication extends Application {
    public MyApplication(@Context ServletConfig servletConfig){
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("Todo API");
        beanConfig.setBasePath("/cinematograph/app");
        beanConfig.setResourcePackage("mediasoft.education.kvv.cinematograph");
        beanConfig.setScan(true);
    }
}
