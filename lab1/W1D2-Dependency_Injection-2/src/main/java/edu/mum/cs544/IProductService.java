package edu.mum.cs544;

public interface IProductService {
    Product getProduct(int productNumber);

    int getNumberInStock(int productNumber);
}
