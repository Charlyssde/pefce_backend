package com.devx.software.ferias.DTOs.Enterprises;

import com.devx.software.ferias.DTOs.Files.FileDTO;

import java.util.List;

public class EnterpriseProductsRequestDTO {

    ProductDTO product;

    List<FileDTO> productTechnicalDocument;

    List<FileDTO> productImages;

    List<FileDTO> productVideos;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public List<FileDTO> getProductTechnicalDocument() {
        return productTechnicalDocument;
    }

    public void setProductTechnicalDocument(List<FileDTO> productTechnicalDocument) {
        this.productTechnicalDocument = productTechnicalDocument;
    }

    public List<FileDTO> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<FileDTO> productImages) {
        this.productImages = productImages;
    }

    public List<FileDTO> getProductVideos() {
        return productVideos;
    }

    public void setProductVideos(List<FileDTO> productVideos) {
        this.productVideos = productVideos;
    }
}
