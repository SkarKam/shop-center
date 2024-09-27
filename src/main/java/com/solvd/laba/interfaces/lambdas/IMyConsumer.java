package com.solvd.laba.interfaces.lambdas;

@FunctionalInterface
public interface IMyConsumer<T,U> {
     void accept(T t,U u);
}
