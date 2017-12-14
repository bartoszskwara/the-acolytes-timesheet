package pl.lso.kazimierz.theacolytestimesheet.model.dto.activity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewActivity {

    @NotNull(message = "Name cannot be null")
    @Size(min=1, message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Value cannot be null")
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
