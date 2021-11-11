package kitchenpos.menu.application;

import kitchenpos.menu.dao.MenuDao;
import kitchenpos.menu.dao.MenuProductDao;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuProducts;
import kitchenpos.menu.domain.Price;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuDao menuDao;
    private final MenuGroupService menuGroupService;
    private final MenuProductDao menuProductDao;

    public MenuService(
            MenuDao menuDao,
            MenuGroupService menuGroupService,
            MenuProductDao menuProductDao
    ) {
        this.menuDao = menuDao;
        this.menuGroupService = menuGroupService;
        this.menuProductDao = menuProductDao;
    }

    @Transactional
    public MenuResponse create(final MenuRequest menu) {
        menuGroupService.checkMenuGroupsExist(menu);

        MenuProducts menuProducts = findAllMenuProducts(menu);

        MenuGroup menuGroup = menuGroupService.findById(menu.getMenuGroupId());

        final Menu savedMenu = menuDao.save(menu.toEntity(menuGroup, menuProducts));

        saveMenuProducts(menuProducts, savedMenu);

        return MenuResponse.of(savedMenu);
    }

    private MenuProducts findAllMenuProducts(MenuRequest menu) {
        List<MenuProduct> menuProducts = menu.getMenuProductsId().stream()
                .map(menuProductDao::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return new MenuProducts(menuProducts);
    }

    private void saveMenuProducts(MenuProducts menuProducts, Menu savedMenu) {
        final List<MenuProduct> savedMenuProducts = new ArrayList<>();

        for (final MenuProduct menuProduct : menuProducts.getMenuProducts()) {
            savedMenuProducts.add(menuProductDao.save(menuProduct));
        }

        savedMenu.mappingMenuProducts(savedMenuProducts);
    }

    public List<Menu> list() {
        final List<Menu> menus = menuDao.findAll();

        for (final Menu menu : menus) {
            menu.mappingMenuProducts(menuProductDao.findAllByMenuId(menu.getId()));
        }

        return menus;
    }
}
