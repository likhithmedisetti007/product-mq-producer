package com.likhith.producer.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.likhith.producer.document.Product;
import com.likhith.producer.dto.ProductResponse;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProducerMapper {

	List<ProductResponse> mapToProductResponseListFromProductList(List<Product> productList);

}