package com.example.demo.service.implement;

import com.example.demo.dao.TagFinanceDAO;
import com.example.demo.dao.implement.TagFinanceDAOImpl;
import com.example.demo.dto.request.TagfinanceRequest;
import com.example.demo.dto.response.TagFinanceResponse;
import com.example.demo.entity.TagFinance;
import com.example.demo.service.TagFinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagFinanceServiceImpl implements TagFinanceService {
    private final TagFinanceDAO tagFinanceDAO = new TagFinanceDAOImpl();

    @Override
    public List<TagFinanceResponse> getAllTagFinance() {

        List<TagFinance> tagFinances = tagFinanceDAO.getAllTagFinance();
        List<TagFinanceResponse> tagFinancesResponse = new ArrayList<>();
        String name;
        String description;
        TagFinanceResponse tagFinanceResponse;

        for (TagFinance tagFinance : tagFinances) {
            name = tagFinance.getName();
            description = tagFinance.getDescription();
            tagFinanceResponse = new TagFinanceResponse(name, description);
            tagFinancesResponse.add(tagFinanceResponse);
        }
        return tagFinancesResponse;
    }

    @Override
    public TagFinanceResponse getTagFinanceById(int id) {
        TagFinance tagFinance = tagFinanceDAO.getTagFinanceById(id);
        String name = tagFinance.getName();
        String description = tagFinance.getDescription();
        TagFinanceResponse tagFinanceResponse = new TagFinanceResponse(name, description);
        return tagFinanceResponse;
    }

    @Override
    public TagFinanceResponse createTagFinance(TagfinanceRequest tagfinanceRequest) {
        TagFinance tagFinance = new TagFinance(
                tagfinanceRequest.getName(),
                tagfinanceRequest.getDescription());
        TagFinanceResponse tagFinanceResponse = new TagFinanceResponse(
                tagFinanceDAO.createTagFinance(tagFinance));
        return tagFinanceResponse;
    }


    @Override
    public TagFinanceResponse updateTagFinance(TagfinanceRequest tagfinanceRequest, @PathVariable(name = "id") int id) {
        TagFinance tagfinanceUpdate = new TagFinance(
                tagfinanceRequest.getName(),
                tagfinanceRequest.getDescription());
        tagFinanceDAO.updateTagFinance(tagfinanceUpdate, id);
        TagFinanceResponse tagFinanceResponse = new TagFinanceResponse(
                tagfinanceUpdate.getName(),
                tagfinanceUpdate.getDescription());
        return tagFinanceResponse;
    }

    @Override
    public void deleteTagFinance(int id) {
        tagFinanceDAO.deleteTagFinance(id);
    }
}