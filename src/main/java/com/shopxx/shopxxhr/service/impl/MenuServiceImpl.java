package com.shopxx.shopxxhr.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.*;
import com.shopxx.shopxxhr.repository.MenuRepository;
import com.shopxx.shopxxhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    MenuRepository menuRepository;

    protected JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Menu> getMenusByHrId(Integer id) {
        QMenu qMenu1 = QMenu.menu;
        QMenu qMenu2 = QMenu.menu;
        QHr qHr = QHr.hr;
        QRole qRole = QRole.role;

        List<Menu> menuChildren = jpaQueryFactory.select(
                Projections.bean(Menu.class,
                        qMenu2.id,
                        qMenu2.url,
                        qMenu2.component,
                        qMenu2.enabled,
                        qMenu2.iconCls,
                        qMenu2.keepAlive,
                        qMenu2.name,
                        qMenu2.requireAuth,
                        qMenu2.parentId,
                        qMenu2.path
                )).
                from(qMenu2).
                innerJoin(qMenu2.role, qRole).
                where(qMenu2.enabled.eq(true)).
                innerJoin(qRole.hr, qHr).
                where(qHr.id.eq(id)).
                distinct().
                fetch();
        List<Menu> menuParents = new ArrayList<>();
        menuChildren.stream().forEach(
                menuChild -> {
                    MenuMeta childMenuMeta = new MenuMeta();
                    childMenuMeta.setKeepAlive(menuChild.getKeepAlive());
                    childMenuMeta.setRequireAuth(menuChild.getRequireAuth());
                    menuChild.setMeta(childMenuMeta);
                    Menu menuParent = jpaQueryFactory.selectFrom(qMenu1).
                            where(qMenu1.id.eq(menuChild.getParentId())).fetchOne();
                    List<Menu> children = menuParent.getChildren() != null ? menuParent.getChildren() : new ArrayList<>();
                    children.add(menuChild);
                    menuParent.setChildren(children);
                    MenuMeta parentMenuMeta = new MenuMeta();
                    parentMenuMeta.setKeepAlive(menuParent.getKeepAlive());
                    parentMenuMeta.setRequireAuth(menuParent.getRequireAuth());
                    menuParent.setMeta(parentMenuMeta);
                    menuParents.add(menuParent);
                }
        );
        List menus = new ArrayList(new HashSet(menuParents));
        return menus;
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        QMenu qMenu = QMenu.menu;
        QRole qRole = QRole.role;

        List<Menu> menus = menuRepository.findAll();
        menus.stream().forEach(
                menu -> {
                    List<Role> roles = jpaQueryFactory.select(
                            Projections.bean(Role.class, qRole.id, qRole.name, qRole.nameZh)).
                            from(qRole).
                            innerJoin(qRole.menu, qMenu).
                            where(qMenu.eq(menu)).
                            distinct().fetch();
                    menu.setRoles(roles);
                }
        );

//        List<Menu> menus = jpaQueryFactory.select(
//                Projections.bean(Menu.class,
//                        qMenu.id,
//                        qMenu.url,
//                        qMenu.path,
//                        qMenu.component,
//                        qMenu.name,
//                        qMenu.iconCls,
//                        qMenu.parentId,
//                        qMenu.enabled,
//                        qMenu.keepAlive,
//                        qMenu.requireAuth,
//                        Projections.bean(Role.class, qRole.id, qRole.name, qRole.nameZh).as("role")
//                )).
//                from(qMenu).innerJoin(qMenu.role, qRole).distinct().fetch();
        return menus;
    }

}
