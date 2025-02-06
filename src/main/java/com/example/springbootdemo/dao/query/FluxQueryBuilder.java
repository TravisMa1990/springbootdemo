package com.example.springbootdemo.dao.query;

import java.util.ArrayList;
import java.util.List;

public class FluxQueryBuilder {
    private List<FluxQuery> queries = new ArrayList<>();

    public FluxQueryBuilder from(String bucket) {
        queries.add(new From(bucket));
        return this;
    }

    public FluxQueryBuilder range(String start, String stop) {
        queries.add(new Range(start, stop));
        return this;
    }

    public FluxQueryBuilder range(String start) {
        queries.add(new Range(start));
        return this;
    }

    public FluxQueryBuilder filter(String name, String value) {
        queries.add(new Filter(name, value));
        return this;
    }

    public FluxQueryBuilder last() {
        queries.add(new Last());
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
//      按照类型排序 queries  From -> Range -> Filter -> Last
        queries.sort((o1, o2) -> {
            if (o1 instanceof From) {
                return -1;
            }
            if (o2 instanceof From) {
                return 1;
            }
            if (o1 instanceof Last) {
                return 1;
            }
            if (o2 instanceof Last) {
                return -1;
            }
            return 0;
        });
        for (FluxQuery query : queries) {
            sb.append(query.toFluxQuery()).append(" ");
        }
        return sb.toString().trim();
    }
}
