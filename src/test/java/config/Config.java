package config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "classpath:config.properties"
})

public interface Config extends org.aeonbits.owner.Config {

    @Key("env")
    String env();
}

