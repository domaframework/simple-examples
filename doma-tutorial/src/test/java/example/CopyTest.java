package example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.seasar.doma.copy.CopyUtil;

import example.dao.EmployeeDao;
import example.dao.EmployeeDao_;
import example.domain.Identity;
import example.dto.EmployeeDto;
import example.entity.Employee;

public class CopyTest extends TutorialTestCase {

	private final EmployeeDao dao = new EmployeeDao_();

	public void testFromEntityToMap() throws Exception {
		Employee employee = dao.selectById(new Identity(1));
		Map<String, Object> map = new HashMap<String, Object>();
		CopyUtil.copy(employee, map);
		Logger.getAnonymousLogger().info(map.toString());
	}

	public void testFromEntityToBean() throws Exception {
		Employee employee = dao.selectById(new Identity(1));
		EmployeeDto employeeDto = new EmployeeDto();
		CopyUtil.copy(employee, employeeDto);
		Logger.getAnonymousLogger().info(employeeDto.toString());
	}
}
