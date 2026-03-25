package com.example.demo;

public record HetatmNeighbor(
        String pdbId,
        String hetatmName,
        String hetatmResname,
        String hetatmChain,
        String hetatmElement,
        String atomName,
        String atomChain,
        Integer atomResseq,
        String atomSeqContext,
        Double distance,
        String atomKey
) {}
