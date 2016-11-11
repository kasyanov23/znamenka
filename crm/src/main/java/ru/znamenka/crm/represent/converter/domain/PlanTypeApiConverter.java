package ru.znamenka.crm.represent.converter.domain;


import org.springframework.stereotype.Component;
import ru.znamenka.jpa.represent.converter.ApiConverter;
import ru.znamenka.crm.represent.domain.PlanTypeApi;
import ru.znamenka.jpa.model.DutyPlanType;

@Component
public class PlanTypeApiConverter implements ApiConverter<DutyPlanType, PlanTypeApi> {
    @Override
    public Class<DutyPlanType> getEntityType() {
        return DutyPlanType.class;
    }

    @Override
    public Class<PlanTypeApi> getApiType() {
        return PlanTypeApi.class;
    }

    @Override
    public DutyPlanType convertTo(PlanTypeApi source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlanTypeApi convert(DutyPlanType source) {
        return new PlanTypeApi()
                .setId(source.getId())
                .setName(source.getName());
    }
}
