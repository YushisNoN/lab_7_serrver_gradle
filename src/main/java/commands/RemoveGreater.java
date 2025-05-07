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

public class RemoveGreater extends Add {
    private ProductManager<Product> productCollection;

    public RemoveGreater(ProductManager<Product> manager, ResponseHandler response, RequestHandler request) {
        super(manager, response, request);
        this.productCollection = manager;
    }

    @Override
    public void execute(String[] arguments) throws WrongArgumentsAmountException, IncorrectStringValueException {
        throw new WrongArgumentsAmountException();
    }

    @Override
    public String getDescription() {
        return "remove_greater {element} : удалить из коллекции все элементы, превыщающие заданный.";
    }

    @Override
    public String toString() {
        return "remove_greater";
    }

    @Override
    public void execute() {
        try {
            this.requestHandler.setExceptingProduct(true);
            this.responseHandler.sendResponse(this.requestHandler.getKey(), "add");
            Object request = this.requestHandler.readRequestCommand(this.responseHandler.getKey());
            while(request == null) {
                request = this.requestHandler.readRequestCommand(this.requestHandler.getKey());
            }
            this.requestHandler.setProduct((Product) request);
            Product product = this.requestHandler.getProduct();
            int sizeOld = this.productCollection.getCollection().size();
            this.productCollection.getCollection()
                    .removeAll(this.productCollection.getCollection().tailSet(product, false));
            int sizeNew = this.productCollection.getCollection().size();
            IDreceive iDreceive = new IDreceive(this.productCollection);
            iDreceive.updateID();
            IdGenerator.updateCounter(this.productCollection.getCollection());
            System.out.println("Из коллекции успешно удалено " + (sizeOld - sizeNew) + " Элементов");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
