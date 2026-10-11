package com.pfe.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import com.pfe.backend.dto.request.TacheRequest;
import com.pfe.backend.dto.response.TacheResponse;
import com.pfe.backend.model.Tache;
import com.pfe.backend.repository.StageRepository;
import com.pfe.backend.repository.TacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    private final StageRepository stageRepository;

    public List<TacheResponse> getByStage(Long stageId) {
        return tacheRepository.findByStageId(stageId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TacheResponse create(TacheRequest request) {
        Tache t = new Tache();
        t.setDescription(request.getDescription());
        t.setDateDebut(request.getDateDebut());
        t.setDateFin(request.getDateFin());
        t.setStage(stageRepository.findById(request.getStageId())
                .orElseThrow(() -> new RuntimeException("Stage non trouvé")));
        return toResponse(tacheRepository.save(t));
    }

    public void delete(Long id) {
        tacheRepository.deleteById(id);
    }

    private TacheResponse toResponse(Tache t) {
        TacheResponse res = new TacheResponse();
        res.setId(t.getId());
        res.setDescription(t.getDescription());
        res.setDateDebut(t.getDateDebut());
        res.setDateFin(t.getDateFin());
        return res;
    }
}
