package commands;


import commands.exceptions.WrongArgumentsAmountException;
import managers.ProductManager;
import models.IdGenerator;
import models.IncorrectStringValueException;
import models.Product;
import models.creators.ProductCreator;
import server.RequestHandler;
import server.ResponseHandler;
import utils.IDreceive;

import java.io.IOException;

public class AddIfMax extends Add {
    private ProductManager<Product> productCollection;

    public AddIfMax(ProductManager<Product> manager, ResponseHandler response, RequestHandler request) {
        super(manager, response, request);
        this.productCollection = manager;
    }

    @Override
    public void execute(String[] arguments) throws WrongArgumentsAmountException, IncorrectStringValueException {
        throw new WrongArgumentsAmountException();
    }

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента в этой коллекции";
    }

    @Override
    public String toString() {
        return "add_if_max";
    }

    @Override
    public void execute() throws WrongArgumentsAmountException {
        try {
            this.requestHandler.setExceptingProduct(true);
            this.responseHandler.sendResponse(this.requestHandler.getKey(), "add");
            Object request = this.requestHandler.readRequestCommand(this.responseHandler.getKey());
            while (request == null) {
                request = this.requestHandler.readRequestCommand(this.requestHandler.getKey());
            }
            this.requestHandler.setProduct((Product) request);
            Product product = this.requestHandler.getProduct();
            if (this.productCollection.getCollection().last().compareTo(product) < 0) {
                this.productCollection.addProdut(product);
                System.out.println("Продукт успешно добавлен в коллекцию");
                IDreceive iDreceive = new IDreceive(this.productCollection);
                iDreceive.updateID();
                IdGenerator.updateCounter(this.productCollection.getCollection());
                return;
            }
            System.out.println("Элемент невозможно добавить в коллекцию, так как он меньше наибольшего элемента ^^");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
