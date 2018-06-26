package com.clouddo.system.service;

import com.clouddo.commons.common.model.Tree;
import com.clouddo.system.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface MenuService {
	Tree<Menu> getSysMenuTree(Long id);

	List<Tree<Menu>> listMenuTree(Long id);

	Tree<Menu> getTree();

	Tree<Menu> getTree(Long id);

	List<Menu> list(Map<String, Object> params);

	int remove(Long id);

	int save(Menu menu);

	int update(Menu menu);

	Menu get(Long id);

	Set<String> listPerms(Long userId);
}
