package kitchenpos.menu.application;

import kitchenpos.menu.dao.MenuGroupDao;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.dto.MenuGroupResponse;
import kitchenpos.menu.dto.MenuGroupsResponse;
import kitchenpos.menu.dto.MenuRequest;
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

    public MenuGroup findById(long id) {
        return menuGroupDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴그룹입니다. : " + id)
        );
    }

    public void checkMenuGroupsExist(MenuRequest menu) {
        if (!menuGroupDao.existsById(menu.getMenuGroupId())) {
            throw new IllegalArgumentException();
        }
    }
}
