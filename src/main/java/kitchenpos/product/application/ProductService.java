package kitchenpos.product.application;

import kitchenpos.product.dao.ProductDao;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.product.dto.ProductsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public ProductResponse create(final ProductRequest request) {
        return ProductResponse.of(productDao.save(request.toProduct()));
    }

    public ProductsResponse list() {
        return ProductsResponse.of(productDao.findAll());
    }
}
