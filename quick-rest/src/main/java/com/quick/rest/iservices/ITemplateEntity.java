package com.quick.rest.iservices;

import com.quick.rest.models.request.EntityTemplateDTO;

public interface ITemplateEntity {
    Boolean generate(EntityTemplateDTO entityTemplateDTO);
}
