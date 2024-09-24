package com.solvd.laba.interfaces;

import com.solvd.laba.models.MallRegion;
import com.solvd.laba.models.persons.employees.Janitor;

public interface IManger extends ICenterEmployee{

    public void firedJanitor(Janitor janitor, MallRegion mallRegion);

    public void replaceJanitorRegion(Janitor janitor, MallRegion mallRegion, MallRegion newRegion);

    public void hiredNewJanitor(Janitor janitor, MallRegion mallRegion);
}
