package com.store.cli;


import com.store.checkout.DiscountCheckout;
import com.store.checkout.ShoppingBasket;
import com.store.model.Product;
import com.store.model.Unit;
import com.store.offers.AppleOffer;
import com.store.offers.Offer;
import com.store.offers.SoupBreadOffer;
import com.store.repos.ProductRepository;
import com.store.repos.SimpleProductRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.util.Arrays.asList;

public class CommandLineInterface {


    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(11L, "APPLE", Unit.SINGLE, valueOf(0.10)));
        products.add(new Product(22L, "SOUP", Unit.TIN, valueOf(0.65)));
        products.add(new Product(33L, "BREAD", Unit.LOAF, valueOf(0.80)));
        products.add(new Product(44L, "MILK", Unit.BOTTLE, valueOf(1.3)));
        SimpleProductRepo productRepo = new SimpleProductRepo(products);

        Offer soupBreadOffer = new SoupBreadOffer(now().minusDays(1), now().plusDays(7));
        Offer appleOffer = new AppleOffer(now().plusDays(3), now().plusMonths(1).with(lastDayOfMonth()));
        DiscountCheckout discountCheckout = new DiscountCheckout(asList(appleOffer, soupBreadOffer));

        new CommandLineInterface(productRepo, discountCheckout).start();
    }

    private final ProductRepository productRepo;
    private final DiscountCheckout discountCheckout;
    private ShoppingBasket shoppingBasket;

    public CommandLineInterface(ProductRepository productRepo, DiscountCheckout discountCheckout) {
        this.productRepo = productRepo;
        this.discountCheckout = discountCheckout;
    }

    private void start() {
        Scanner sc = new Scanner(System.in);

        int ch;
        while (true) {
            System.out.println("Welcome to Henry's store.\n Please select item in sequence to start shopping: \n " +
                    "0) Start Shopping for a date \n " +
                    "1) Add product to basket \n " +
                    "2) Print receipt \n " +
                    "3) Quit \n ");

            ch = sc.nextInt();

            switch (ch) {
                case 0:
                    System.out.println("Enter day to purchase goods, i.e. 0 for today, 1 for tomorrow etc");
                    String daysFromNow = sc.next();
                    shoppingBasket = new ShoppingBasket(LocalDate.now().plusDays(Integer.parseInt(daysFromNow)));
                    addToBasket(sc);
                    break;
                case 1:
                    addToBasket(sc);
                    break;
                case 2:
                    System.out.println(discountCheckout.printReceipt(shoppingBasket));
                    break;
                case 3:
                    System.out.println("\n" + "Thanks for shopping with us. See you again soon!");
                    System.exit(0);
                    break;
            }
        }
    }

    private void addToBasket(Scanner sc) {
        System.out.println("Enter product: [SOUP | BREAD | MILK | APPLE] \n");
        String product = sc.next();

        Optional<Product> optionalProduct = productRepo.getProductByName(product);
        if (optionalProduct.isPresent()) {
            shoppingBasket.addProduct(optionalProduct.get());
        } else {
            System.out.println(product + " NOT FOUND! Try another product");
        }
    }
}