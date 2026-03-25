package com.example.demo;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hetatm-neighbors")
public class HetatmNeighborController {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HetatmNeighborController(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<HetatmNeighbor> getAll(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "length", defaultValue = "100") int length) {
        String sql = "SELECT pdb_id, hetatm_name, hetatm_resname, hetatm_chain, hetatm_element, " +
                "atom_name, atom_chain, atom_resseq, atom_seq_context, distance, atom_key " +
                "FROM public.hetatm_neighbors " +
                "LIMIT :length OFFSET :offset";
        Map<String, Object> params = Map.of("length", length, "offset", offset);
        return jdbcTemplate.query(
                sql,
                params,
                (rs, rowNum) -> new HetatmNeighbor(
                        rs.getString("pdb_id"),
                        rs.getString("hetatm_name"),
                        rs.getString("hetatm_resname"),
                        rs.getString("hetatm_chain"),
                        rs.getString("hetatm_element"),
                        rs.getString("atom_name"),
                        rs.getString("atom_chain"),
                        (Integer) rs.getObject("atom_resseq"),
                        rs.getString("atom_seq_context"),
                        (Double) rs.getObject("distance"),
                        rs.getString("atom_key")
                )
        );
    }
}
