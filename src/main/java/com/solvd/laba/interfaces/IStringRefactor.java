package com.solvd.laba.interfaces;
@FunctionalInterface
public interface IStringRefactor<T, U> {
    U refactor(T t);
}
