package net.explorviz.extension.dummy.main;

import net.explorviz.extension.dummy.model.DummyModel;
import net.explorviz.extension.dummy.model.SubDummyModel;
import net.explorviz.extension.dummy.resources.TestResource;
import net.explorviz.shared.common.provider.GenericTypeFinder;
import net.explorviz.shared.common.provider.JsonApiListProvider;
import net.explorviz.shared.common.provider.JsonApiProvider;
import net.explorviz.shared.security.filters.AuthenticationFilter;
import net.explorviz.shared.security.filters.AuthorizationFilter;
import net.explorviz.shared.security.filters.CorsResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {

  public Application() {

    GenericTypeFinder.getTypeMap().put("DummyModel", DummyModel.class);
    GenericTypeFinder.getTypeMap().put("SubDummyModel", SubDummyModel.class);

    // register DI
    register(new DependencyInjectionBinder());

    // Security
    this.register(AuthenticationFilter.class);
    this.register(AuthorizationFilter.class);
    this.register(CorsResponseFilter.class);

    // register providers
    this.register(JsonApiProvider.class);
    this.register(JsonApiListProvider.class);

    // register all resources in the given package
    register(TestResource.class);
    
    // Starting point for your DI-based extension
    this.register(SetupApplicationListener.class);
  }
}
