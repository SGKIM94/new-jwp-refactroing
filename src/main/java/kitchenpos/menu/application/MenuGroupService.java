package kitchenpos.menu.application;

import kitchenpos.menu.dao.MenuGroupDao;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.dto.MenuGroupResponse;
import kitchenpos.menu.dto.MenuGroupsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuGroupService {
    private final MenuGroupDao menuGroupDao;

    public MenuGroupService(final MenuGroupDao menuGroupDao) {
        this.menuGroupDao = menuGroupDao;
    }

    @Transactional
    public MenuGroupResponse create(final MenuGroup menuGroup) {
        return MenuGroupResponse.of(menuGroupDao.save(menuGroup));
    }

    public MenuGroupsResponse list() {
        return MenuGroupsResponse.of(menuGroupDao.findAll());
    }
}
