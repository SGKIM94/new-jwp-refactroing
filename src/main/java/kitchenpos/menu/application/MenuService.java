package kitchenpos.menu.application;

import kitchenpos.menu.dao.MenuDao;
import kitchenpos.menu.dao.MenuGroupDao;
import kitchenpos.menu.dao.MenuProductDao;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.Price;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.product.dao.ProductDao;
import kitchenpos.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuDao menuDao;
    private final MenuGroupDao menuGroupDao;
    private final MenuProductDao menuProductDao;
    private final ProductDao productDao;

    public MenuService(
            final MenuDao menuDao,
            final MenuGroupDao menuGroupDao,
            final MenuProductDao menuProductDao,
            final ProductDao productDao
    ) {
        this.menuDao = menuDao;
        this.menuGroupDao = menuGroupDao;
        this.menuProductDao = menuProductDao;
        this.productDao = productDao;
    }

    @Transactional
    public MenuResponse create(final MenuRequest menu) {
        final Price price = menu.getPrice();

        price.validatePrice();

        checkMenuGroupsExist(menu);

        List<MenuProduct> menuProducts = findAllMenuProducts(menu);

        Optional<MenuGroup> menuGroup = menuGroupDao.findById(menu.getMenuGroupId());
        BigDecimal sum = sumProductPriceByQuantity(menuProducts);

        price.validateSum(sum);

        final Menu savedMenu = menuDao.save(menu.toEntity(menuGroup.get(), menuProducts));

        saveMenuProducts(menuProducts, savedMenu);

        return MenuResponse.of(savedMenu);
    }

    private List<MenuProduct> findAllMenuProducts(MenuRequest menu) {
        return menu.getMenuProductsId().stream()
                .map(menuProductDao::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private void saveMenuProducts(List<MenuProduct> menuProducts, Menu savedMenu) {
        final List<MenuProduct> savedMenuProducts = new ArrayList<>();

        for (final MenuProduct menuProduct : menuProducts) {
            savedMenuProducts.add(menuProductDao.save(menuProduct));
        }

        savedMenu.setMenuProducts(savedMenuProducts);
    }

    private BigDecimal sumProductPriceByQuantity(List<MenuProduct> menuProducts) {
        BigDecimal sum = BigDecimal.ZERO;

        for (final MenuProduct menuProduct : menuProducts) {
            Product product = menuProduct.getProduct();
            sum = sum.add(product.getPrice().multiply(BigDecimal.valueOf(menuProduct.getQuantity())));
        }

        return sum;
    }

    private void checkMenuGroupsExist(MenuRequest menu) {
        if (!menuGroupDao.existsById(menu.getMenuGroupId())) {
            throw new IllegalArgumentException();
        }
    }

    public List<Menu> list() {
        final List<Menu> menus = menuDao.findAll();

        for (final Menu menu : menus) {
            menu.setMenuProducts(menuProductDao.findAllByMenuId(menu.getId()));
        }

        return menus;
    }
}
