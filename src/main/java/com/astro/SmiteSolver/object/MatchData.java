package com.astro.SmiteSolver.object;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "match_data")
public class MatchData {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private LocalDate date;

    private Map<Integer, Integer> winsPerSideSelectionHighMMR;
    private Map<Integer, Integer> winsPerSideSelectionMediumMMR;
    private Map<Integer, Integer> winsPerSideSelectionLowMMR;


    public MatchData() { }

}
