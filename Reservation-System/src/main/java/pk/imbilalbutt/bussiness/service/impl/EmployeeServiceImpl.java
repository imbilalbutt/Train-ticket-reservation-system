package pk.imbilalbutt.bussiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.EmployeeConverter;
import pk.imbilalbutt.bussiness.dto.EmployeeDto;
import pk.imbilalbutt.bussiness.model.Employee;
import pk.imbilalbutt.bussiness.repository.EmployeeRepository;
import pk.imbilalbutt.bussiness.service.EmployeeService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service(value="EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeConverter employeeConverter;

    @Override
    public EmployeeDto getById(Long id) {

        logger.log(Level.INFO, "EmployeeServiceImpl - getById() called id : {0}.", id);

        if(!ObjectUtils.isEmpty(id)){

            Optional<Employee> employee = employeeRepository.findById(id);

            if(employee.isPresent()) {

                logger.log(Level.INFO, "EmployeeServiceImpl - getById() Found : {0}.", employee.get().getId());

                return employeeConverter.convertToDtoUsingLambda.apply(employee.get());
            }
        }

        logger.log(Level.INFO, "EmployeeServiceImpl - getById() NOT found.");

        return null;
    }

    @Override
    public List<EmployeeDto> getAll() {

        logger.log(Level.INFO, "EmployeeServiceImpl - getAll() - called in.");

        List<Employee> employees = new ArrayList<>();
        try{
            employees = employeeRepository.findAll();

            logger.log(Level.INFO, "EmployeeServiceImpl - getAll() - employees.size() : {0}.", employees.size());

            return !ObjectUtils.isEmpty(employees) ? employeeConverter.convertToDtoList(employees) : new ArrayList<>();

        } catch (NullPointerException npe) {

            logger.log(Level.WARNING, "EmployeeServiceImpl - getAll() - Not Found.", employees.size());

            throw new NullPointerException("Not Found Any Single One.");

        }
    }

    @Override
    public Boolean delete(Long id) throws EntityNotFoundException {

        logger.log(Level.INFO, "EmployeeServiceImpl - delete() id : {0}.", id);

        try{

            Employee employee = employeeRepository.getById(id);

            employeeRepository.delete(employee);

            logger.log(Level.INFO, "EmployeeServiceImpl - delete() successfully.");

            return Boolean.TRUE;

        } catch (EntityNotFoundException enf) {

            logger.log(Level.WARNING, "EmployeeServiceImpl - delete() failed.");

            return Boolean.FALSE;
        }

    }

    @Override
    public EmployeeDto saveOrUpdate(EmployeeDto dto) {

        logger.log(Level.INFO, "EmployeeServiceImpl - saveOrUpdate() called in." + dto);

        Employee employee = new Employee();
        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                employee = employeeRepository.findById(dto.getId()).get();

                if (!ObjectUtils.isEmpty(employee) && !ObjectUtils.isEmpty(employee.getId())) {
                    employee = employeeConverter.convertToModelUsingLambda.apply(dto);
                    employee = employeeRepository.save(employee);

                    logger.log(Level.INFO, "EmployeeServiceImpl - saveOrUpdate() - update(): {0} success.", employee.getId());
                }
            } else {
                employee = employeeConverter.convertToModelUsingLambda.apply(dto);
                employee = employeeRepository.save(employee);

                if (!ObjectUtils.isEmpty(employee) && !ObjectUtils.isEmpty(employee.getId())) {
                    dto.setId(employee.getId());
                }
                logger.log(Level.INFO, "EmployeeServiceImpl - saveOrUpdate() - save(): {0} success." , employee.getId());
            }
        }
        return dto;
    }

    @Override
    public List<Employee> getEmployeeByCourseId(Long courseId) {
        return null;
    }
}
