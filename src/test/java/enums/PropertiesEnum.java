package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropertiesEnum {
    PLATFORM("platform");

    private final String value;
}
