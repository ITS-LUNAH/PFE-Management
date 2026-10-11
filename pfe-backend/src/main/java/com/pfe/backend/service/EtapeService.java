package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.EtapeRequest;
import com.pfe.backend.dto.response.EtapeResponse;
import com.pfe.backend.model.Etape;
import com.pfe.backend.repository.EtapeRepository;
import com.pfe.backend.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtapeService {

    private final EtapeRepository etapeRepository;
    private final StageRepository stageRepository;

    public List<EtapeResponse> getByStage(Long stageId) {
        return etapeRepository.findByStageId(stageId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EtapeResponse create(EtapeRequest request) {
        Etape e = new Etape();
        e.setNom(request.getNom());
        e.setDatePrevue(request.getDatePrevue());
        e.setDateRealisation(request.getDateRealisation());
        e.setStage(stageRepository.findById(request.getStageId())
                .orElseThrow(() -> new RuntimeException("Stage non trouvé")));
        return toResponse(etapeRepository.save(e));
    }

    public void delete(Long id) {
        etapeRepository.deleteById(id);
    }

    private EtapeResponse toResponse(Etape e) {
        EtapeResponse res = new EtapeResponse();
        res.setId(e.getId());
        res.setNom(e.getNom());
        res.setDatePrevue(e.getDatePrevue());
        res.setDateRealisation(e.getDateRealisation());
        return res;
    }
}
