package com.quick.rest.models.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuickApiResponse implements Serializable {

  /**
   * Serial id
   */
  private static final long serialVersionUID = -2935285906179151532L;

  private String messageResponse;
}
