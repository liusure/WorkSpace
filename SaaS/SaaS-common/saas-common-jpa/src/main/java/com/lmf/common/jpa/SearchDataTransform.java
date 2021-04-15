package com.saas.common.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class SearchDataTransform {

  /**
   * 查询数据类型
   */
  public enum SearchDataType {
    String, Date, Boolean
  }

  private String fieldName;
  private SearchDataType dataType;
  private String attachMent;

  public SearchDataTransform(String fieldName, SearchDataType dataType, String attachMent) {
    super();
    this.fieldName = fieldName;
    this.dataType = dataType;
    this.attachMent = attachMent;
  }

  public static void dateTypeTransform(Map<String, SearchFilter> filters, List<SearchDataTransform> dataType) {

    if (dataType == null || dataType.isEmpty() || filters == null || filters.isEmpty()) {
      return;
    }

    dataType.forEach((dataTransform) -> {
      SearchFilter filter = filters.get(dataTransform.fieldName);
      if (filter != null) {
        switch (dataTransform.dataType) {
          case Date:
            try {
              filter.value = (new SimpleDateFormat(dataTransform.attachMent)).parse(filter.value.toString());
            } catch (ParseException e) {
              e.printStackTrace();
            }
            break;
          case Boolean:
            if (filter.value instanceof String) {
              filter.value = "true".equalsIgnoreCase(filter.value.toString());
            }
            break;
          default:
            break;
        }
      }
    });

  }
}
