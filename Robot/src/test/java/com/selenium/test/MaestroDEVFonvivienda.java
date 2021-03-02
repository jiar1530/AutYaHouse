package com.selenium.test;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses ({RegistrarHogarCamposObligatorios.class,RegistrarHogarCamposNoObligatorios.class,MarcarHogarObligatorios.class,
	MarcacionPagado.class,ActosAdministrativos.class,BloqueoHogar.class,RenunciaConRestitucion.class,Renuncias.class,ReversarVencimiento.class,})

public class MaestroDEVFonvivienda {}
