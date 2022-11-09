package com.quick.rest.iservices;

import com.quick.rest.models.request.BusinessTemplateDTO;

import java.io.File;
import java.io.IOException;

public interface ITemplateBusinessService {
    Boolean generate(BusinessTemplateDTO businessTemplateDTO);
    StringBuilder createTemplate(File rawTemplate, BusinessTemplateDTO businessTemplateDTO) throws IOException;
}
