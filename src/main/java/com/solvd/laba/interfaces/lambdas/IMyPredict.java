package com.solvd.laba.interfaces.lambdas;

@FunctionalInterface
public interface IMyPredict<T,R> {
    boolean predict(T t, R r);
}
