package com.saas.common.jpa;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询工具类
 */
public class Query {

    /**
     * 查询参数
     */
    private Map<String, Object> searchParams = new HashMap<>();

    public Query() {
    }

    public Query(Map<String, Object> searchParams) {
        this.searchParams = searchParams;
    }

    public Query like(String key, Object value) {
        searchParams.put("LIKE_" + key, value);
        return this;
    }

    public Query eq(String key, Object value) {
        searchParams.put("EQ_" + key, value);
        return this;
    }

    public Query gt(String key, Object value) {
        searchParams.put("GT_" + key, value);
        return this;
    }

    public Query lt(String key, Object value) {
        searchParams.put("LT_" + key, value);
        return this;
    }

    public Query gte(String key, Object value) {
        searchParams.put("GTE_" + key, value);
        return this;
    }

    public Query lte(String key, Object value) {
        searchParams.put("LTE_" + key, value);
        return this;
    }

    public Query noteq(String key, Object value) {
        searchParams.put("NOTEQ_" + key, value);
        return this;
    }

    public Query llike(String key, Object value) {
        searchParams.put("LLIKE_" + key, value);
        return this;
    }

    public Query rlike(String key, Object value) {
        searchParams.put("RLIKE_" + key, value);
        return this;
    }

    public Query clike(String key, Object value) {
        searchParams.put("CLIKE_" + key, value);
        return this;
    }

    public Query isnull(String key, Object value) {
        searchParams.put("ISNULL_" + key, value);
        return this;
    }

    public Query notnull(String key, Object value) {
        searchParams.put("NOTNULL_" + key, value);
        return this;
    }

    public Query in(String key, Object value) {
        searchParams.put("IN_" + key, value);
        return this;
    }

    public Map<String, Object> getSearchParams() {
        return searchParams;
    }
}
