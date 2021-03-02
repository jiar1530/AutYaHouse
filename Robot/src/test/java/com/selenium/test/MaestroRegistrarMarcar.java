package com.selenium.test;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses ({RegistrarHogarCamposObligatorios.class,RegistrarHogarCamposNoObligatorios.class,MarcarHogarObligatorios.class,})

public class MaestroRegistrarMarcar {}
