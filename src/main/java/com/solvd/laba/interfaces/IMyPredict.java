package com.solvd.laba.interfaces;

@FunctionalInterface
public interface IMyPredict<T,R> {
    boolean predict(T t, R r);
}
