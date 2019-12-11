package com.shopxx.shopxxhr.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.Department;
import com.shopxx.shopxxhr.entity.QDepartment;
import com.shopxx.shopxxhr.repository.DepartmentRepository;
import com.shopxx.shopxxhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    DepartmentRepository departmentRepository;

    protected JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Department> getAllDepartmentsByParentId(Integer pid) {
        List<Department> departments = new ArrayList<>();
        QDepartment qDepartment = QDepartment.department;
        Department ancentDepartment = jpaQueryFactory.select(
                Projections.bean(Department.class, qDepartment.id, qDepartment.name, qDepartment.parentId, qDepartment.depPath, qDepartment.enabled, qDepartment.isParent))
                .from(qDepartment)
                .where(qDepartment.parentId.eq(-1))
                .fetchOne();
        findChildrenDepartments(ancentDepartment);
        departments.add(ancentDepartment);
        return departments;
    }

    @Override
    @Transactional
    public Department saveOrUpdateDepartment(Department dep) {
        dep.setEnabled(true);
        Department department = departmentRepository.save(dep);
        QDepartment parentDepartment = QDepartment.department;
        String depPath = jpaQueryFactory.select(parentDepartment.depPath).from(parentDepartment).where(parentDepartment.id.eq(dep.getParentId())).fetchOne();
        department.setDepPath(depPath + "," + department.getId());
        jpaQueryFactory.update(parentDepartment).set(parentDepartment.isParent, true).where(parentDepartment.id.eq(dep.getParentId())).execute();
        return department;
    }

    private void findChildrenDepartments(Department department) {
        QDepartment qDepartment = new QDepartment("department" + department.getId());
        List<Department> childrenDepartments = jpaQueryFactory.select(
                Projections.bean(Department.class, qDepartment.id, qDepartment.name, qDepartment.parentId, qDepartment.depPath, qDepartment.enabled, qDepartment.isParent))
                .from(qDepartment)
                .where(qDepartment.parentId.eq(department.getId()))
                .fetch();
        if (childrenDepartments.size() == 0) {
            return;
        }
        department.setChildren(childrenDepartments);
        for (Department childrenDepartment : childrenDepartments) {
            findChildrenDepartments(childrenDepartment);
        }
        return;
    }

}
