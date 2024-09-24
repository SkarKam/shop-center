package com.solvd.laba.interfaces;

import com.solvd.laba.interfaces.lambdas.IMyPredict;
import com.solvd.laba.models.premises.Premise;
import com.solvd.laba.models.premises.Shop;

public interface IShopOwner {

    public void rentAPremise(Premise premise, Shop shop, IMyPredict<Shop,Integer> predict);


}
