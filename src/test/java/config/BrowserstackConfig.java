package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:configs/browserstack.properties",
        "classpath:configs/${platform}.properties"
})

public interface BrowserstackConfig extends org.aeonbits.owner.Config {

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();

    @Key("project")
    String projectName();

    @Key("build")
    String buildName();

    @Key("name")
    String testName();

    @Key("baseURL")
    String baseUrl();

    @Key("app")
    String appUrl();

    @Key("session")
    String sessionUrl();
}
