package com.project.DTO;

import com.project.entities.utils.ActionState;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class DTOActionInsert {

    private String title;
    private String manager;
    private ActionState state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

}
