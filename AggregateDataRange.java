package com.example;

import org.h2.expression.Aggregate;

public class AggregateDataRange implements AggregateData {

    private final AggregateType aggregateType;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    public void AggregateDataRange(AggregateType aggregateType, int min, int max) {
        this.aggregateType = aggregateType;
        this.min = min;
        this.max = max;
    }

    @Override
    void add(SessionLocal session, Value v) {
        int value = v.getInt();
        if (value < min) {
            min = value;
        }
        if (value > max) {
            max = value;
        }
    }
    @Override
    Value getRangeValue(SessionLocal session) {
        if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
            return 0;
        }
        return max - min;
    }
}