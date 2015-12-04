package com.mypro;

import com.mypro.factory.ControllerFactory;
import com.mypro.factory.DaoFactory;
import com.mypro.factory.DaoImplFactory;
import com.mypro.factory.PojoFactory;
import com.mypro.factory.ServiceFactory;
import com.mypro.factory.ServiceImplFactory;

public class StartUp {
	public static void main(String[] args) {
		PojoFactory.createPojo();
		DaoFactory.createDao();
		DaoImplFactory.createDaoImpl();
		ServiceFactory.createService();
		ServiceImplFactory.createserviceImpl();
		ControllerFactory.createController();
	}
}
