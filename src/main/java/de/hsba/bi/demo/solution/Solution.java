package de.hsba.bi.demo.solution;

import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
public class Solution {
    @Id
@GeneratedValue
@Getter
private Long id;

    @Setter
    @Getter
    @Basic (optional = false)
    private String comment;

    @Setter
    @Getter
    private String mark;
}
