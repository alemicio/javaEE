package it.screwdrivers.payroll.engine;

import it.screwdrivers.payroll.controller.HistoricalSalarycontroller;
import it.screwdrivers.payroll.controller.HistoricalUnionChargeController;
import it.screwdrivers.payroll.dao.EmployeeDao;
import it.screwdrivers.payroll.pojo.employee.CommissionedEmployee;
import it.screwdrivers.payroll.pojo.employee.SalariedEmployee;

import java.util.List;

import javax.inject.Inject;

public class SalariedPayEngine implements IPayEngine {

	@Inject
	EmployeeDao e_dao;
	@Inject
	HistoricalSalarycontroller h_controller;
	@Inject
	HistoricalUnionChargeController huc_controller;

	@Override
	public void pay() {

		paySalaried();
	}
	
	private void paySalaried() {
		List<SalariedEmployee> s_employees = e_dao.findAllSalaried();
		float total_charges = 0;

		for (SalariedEmployee s : s_employees) {

			if(s.getUnion() == null){
				h_controller.registerPay(s);
			}
			else {
				total_charges = huc_controller.UnionChargeByEmployee(s);
				h_controller.registerPay(s,total_charges);
				
			}
				

		}
	}

}
