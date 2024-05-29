package config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "classpath:configs/config.properties",
        "classpath:configs/credentials.properties"
})

public interface Config extends org.aeonbits.owner.Config {

    @Key("env")
    String env();

    @Key("login")
    String login();

    @Key("password")
    String password();

}

