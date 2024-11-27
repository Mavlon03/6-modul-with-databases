package uz.pdp.task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Integer id;
    private String name;
    private Integer courseId;

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
