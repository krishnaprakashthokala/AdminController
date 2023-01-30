package org.ecommerce.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.ecommerce.config.web.frontend.exceptions.ProductLineNotFoundException;
import org.ecommerce.config.web.models.product.ProductLineDetail;
import org.ecommerce.config.web.models.search.SearchProduct;
import org.ecommerce.config.web.services.ProductService;
import org.ecommerce.persistence.models.Product;
import org.ecommerce.persistence.models.ProductLine;
import org.ecommerce.persistence.models.Review;
import org.ecommerce.persistence.models.ReviewStatusEnum;
import org.ecommerce.persistence.projection.ProductLineView;
import org.ecommerce.persistence.repositories.ProductLineRepository;
import org.ecommerce.persistence.repositories.ProductRepository;
import org.ecommerce.persistence.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author sergio
 */
@Service
//@ComponentScan(basePackages={"org.ecommerce.persistence.repositories"})
public class ProductServiceImpl implements ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductLineRepository productLineRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Product> getNewArrivals() {
		return productRepository.findFirst10ByOrderByCreateAtDesc();
	}

	@Override
	public List<Review> getApprovedReviews(Long productId) {
		return reviewRepository.findByProductIdAndStatus(productId, ReviewStatusEnum.APPROVED);
	}

	@Override
	public Page<Product> search(String query) {
		//return productRepository.search(query, new PageRequest(0, 20));
		return null;

	}

	@Override
	public Page<Product> search(SearchProduct searchProduct) {
	//	return productRepository.findAll(new SearchProductSpecification(searchProduct),
	//			new PageRequest(0, searchProduct.getLimit(), ProductUtils.getProductOrder(searchProduct.getSort())));
	
		return null;
}

	@Override
	public Page<Product> search(SearchProduct searchProduct, Integer page) {
		//return productRepository.findAll(new SearchProductSpecification(searchProduct),
			//	new PageRequest(page, searchProduct.getLimit(), ProductUtils.getProductOrder(searchProduct.getSort())));
		return null;

	}

	@Override
	public List<Product> getThreeFeaturedProducts() {
		//Page<Product> pageProducts = productRepository.findFeaturedProducts(new PageRequest(0, 3));
		//return pageProducts.getContent();
		return null;
	}

	@Override
	public List<Product> getTwoBestsellersProducts() {
		return null;
		/*
		 * Page<Product> pageProducts = productRepository.findBestsellersProducts(new
		 * PageRequest(0, 2)); return pageProducts.getContent();
		 */
	}

	@Override
	public ProductLineDetail getProductLineDetail(Long id) {
		ProductLine productLine = productLineRepository.findById(id).get();
		if (productLine == null) {
			throw new ProductLineNotFoundException();
		}
		Long productId = productLine.getProduct().getId();
		// Product Reviews
		List<Review> reviews = reviewRepository.findByProductIdAndStatus(productId, ReviewStatusEnum.APPROVED);
		// Product Rating Avg
		Double ratingAvg = reviewRepository.getRatingAvgByProduct(productId);
		// Other Product Lines avaliables
		List<ProductLineView> otherLines = productLineRepository.findByProductIdAndIdNotAndStockGreaterThan(productId,
				id, 0);
		return new ProductLineDetail(productLine, reviews, ratingAvg != null ? ratingAvg : 0.0, otherLines);
	}

	@Override
	public List<Product> getNewProducts() {
		return productRepository.findFirst3ByOrderByCreateAtDesc();
	}

	@Override
	public Page<Product> getByCategory(String slug, Integer page) {
		//return productRepository.findByCategorySlug(slug, new PageRequest(page, 20));
		return null;
	}

	@Override
	public Long getNewFeedbacks() {
		return reviewRepository.countByStatus(ReviewStatusEnum.PENDING);
	}

	@Override
	public Page<Product> search(SearchProduct searchProduct, Integer page, String category) {
		return null;
		//return productRepository.findAll(new SearchProductByCategorySpecification(searchProduct, category),
		//		new PageRequest(page, searchProduct.getLimit(), ProductUtils.getProductOrder(searchProduct.getSort())));
	}

	@Override
	public List<ProductLine> getProductLinesDetail(List<Long> ids) {
		List<ProductLine> lines = new ArrayList<ProductLine>();
		for (Long id : ids) {
			ProductLine line = productLineRepository.findById(id).get();
			lines.add(line);
		}
		return lines;
	}
}
