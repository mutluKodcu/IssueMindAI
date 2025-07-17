package com.uyg5.dmtbkts.insightgeneratorservice.service;

import com.uyg5.dmtbkts.insightgeneratorservice.model.InsightResult;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class InsightService {

    public InsightResult analyze(String module, String analysisText) {
        InsightResult result = new InsightResult();
        result.setModule(module);

        // Basit skor hesaplama ve öneri örneği
        double score = new Random().nextDouble() * 10;
        result.setScore(score);

        if (analysisText.toLowerCase().contains("refactor")) {
            result.setSuggestion("Kod yapısı karmaşık, refactor önerilir.");
        } else {
            result.setSuggestion("Önemli bir iyileştirme önerisi bulunamadı.");
        }

        return result;
    }
}