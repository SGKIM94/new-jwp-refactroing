package kitchenpos.application;

import kitchenpos.dao.MenuGroupDao;
import kitchenpos.domain.MenuGroup;
import kitchenpos.dto.MenuGroupResponse;
import kitchenpos.dto.MenuGroupsResponse;
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
