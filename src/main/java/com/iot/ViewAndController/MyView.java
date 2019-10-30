package com.iot.ViewAndController;

import com.iot.model.*;
import com.iot.model.metadata.TableMetaData;
import com.iot.persistant.ConnectionManager;
import com.iot.service.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class MyView {
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public MyView() {
        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();
        menu.put("A", "   A - Select all table");
        menu.put("B", "   B - Select structure of DB");

        menu.put("1", "   1 - Table: airbnb_wallet");
        menu.put("11", "  11 - Create for airbnb_wallet");
        menu.put("12", "  12 - Update airbnb_wallet");
        menu.put("13", "  13 - Delete from airbnb_wallet");
        menu.put("14", "  14 - Select airbnb_wallet");
        menu.put("15", "  15 - Find airbnb_wallet by ID");

        menu.put("2", "   2 - Table: Apartments");
        menu.put("21", "  21 - Create for Apartments");
        menu.put("22", "  22 - Update Apartments");
        menu.put("23", "  23 - Delete from Apartments");
        menu.put("24", "  24 - Select Apartments");
        menu.put("25", "  25 - Find Apartments by ID");

        menu.put("3", "   3 - Table: ApartmentsHasReservations");
        menu.put("31", "  31 - Create for ApartmentsHasReservations");
        menu.put("32", "  32 - Update ApartmentsHasReservations");
        menu.put("33", "  33 - Delete from ApartmentsHasReservations");
        menu.put("34", "  34 - Select ApartmentsHasReservations");
        menu.put("35", "  35 - Find ApartmentsHasReservations by ID");

        menu.put("4", "   4 - Table: Billings");
        menu.put("41", "  41 - Create for Billings");
        menu.put("42", "  42 - Update Billings");
        menu.put("43", "  43 - Delete from Billings");
        menu.put("44", "  44 - Select Billings");
        menu.put("45", "  45 - Find Billings by ID");

        menu.put("5", "   5 - Table: Buyers");
        menu.put("51", "  51 - Create for Buyers");
        menu.put("52", "  52 - Update Buyers");
        menu.put("53", "  53 - Delete from Buyers");
        menu.put("54", "  54 - Select Buyers");
        menu.put("55", "  55 - Find Buyers by ID");

        menu.put("6", "   6 - Table: Sellers");
        menu.put("61", "  61 - Create for Sellers");
        menu.put("62", "  62 - Update Sellers");
        menu.put("63", "  63 - Delete from Sellers");
        menu.put("64", "  64 - Select Sellers");
        menu.put("65", "  65 - Find Sellers by ID");

        menu.put("7", "   7 - Table: Reservations");
        menu.put("71", "  71 - Create for Reservations");
        menu.put("72", "  72 - Update Reservations");
        menu.put("73", "  73 - Delete from Reservations");
        menu.put("74", "  74 - Select Reservations");
        menu.put("75", "  75 - Find Reservations by ID");

        menu.put("Q", "   Q - exit");

        methodsMenu.put("A", this::selectAllTable);
        methodsMenu.put("B", this::takeStructureOfDB);

        methodsMenu.put("11", this::createAirbnbWallet);
        methodsMenu.put("12", this::updateAirbnbWallet);
        methodsMenu.put("13", this::deleteAirbnbWallet);
        methodsMenu.put("14", this::selectAirbnbWallet);
        methodsMenu.put("15", this::findAirbnbWalletById);

        methodsMenu.put("21", this::createApartments);
        methodsMenu.put("22", this::updateApartmnents);
        methodsMenu.put("23", this::deleteApartments);
        methodsMenu.put("24", this::selectApartments);
        methodsMenu.put("25", this::findApartmentById);

        methodsMenu.put("31", this::createApartmentsHasReservations);
        methodsMenu.put("32", this::updateApartmentsHasReservations);
        methodsMenu.put("33", this::deleteApartmentsHasReservations);
        methodsMenu.put("34", this::selectApartmentsHasReservations);
        methodsMenu.put("35", this::findApartmentsHasReservations);

        methodsMenu.put("41", this::createBillings);
        methodsMenu.put("42", this::updateBillings);
        methodsMenu.put("43", this::deleteBillings);
        methodsMenu.put("44", this::selectBillings);
        methodsMenu.put("45", this::findBillingsById);

        methodsMenu.put("51", this::createBuyers);
        methodsMenu.put("52", this::updateBuyers);
        methodsMenu.put("53", this::deleteBuyers);
        methodsMenu.put("54", this::selectBuyers);
        methodsMenu.put("55", this::findBuyersById);

        methodsMenu.put("61", this::createSellers);
        methodsMenu.put("62", this::updateSellers);
        methodsMenu.put("63", this::deleteSellers);
        methodsMenu.put("64", this::selectSellers);
        methodsMenu.put("65", this::findSellersById);

        methodsMenu.put("71", this::createReservations);
        methodsMenu.put("72", this::updateReservations);
        methodsMenu.put("73", this::deleteReservations);
        methodsMenu.put("74", this::selectReservations);
        methodsMenu.put("75", this::findReservationsById);
    }

    private void selectAllTable() throws SQLException {
        selectAirbnbWallet();
        selectApartments();
        selectApartmentsHasReservations();
        selectBillings();
        selectBuyers();
        selectSellers();
        selectReservations();
    }


    //------------------------------------------------------------------------

    private void selectAirbnbWallet() throws SQLException {
        System.out.println("\nTable: airbnb_wallet");
        AirbnbWalletService airbnbWalletService = new AirbnbWalletService();
        List<AirbnbWalletEntity> wallets = airbnbWalletService.findAll();
        for (AirbnbWalletEntity entity : wallets) {
            System.out.println(entity);
        }
    }

    private void deleteAirbnbWallet() throws SQLException {
        System.out.println("Input ID(id) for AirbnbWallet: ");
        Integer id = input.nextInt();
        input.nextLine();
        AirbnbWalletService airbnbWalletService = new AirbnbWalletService();
        int count = airbnbWalletService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createAirbnbWallet() throws SQLException {
        System.out.println("Input ID(wallet_id) for AirbnbWallet: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input money for AirbnbWallet: ");
        Integer money = input.nextInt();
        AirbnbWalletEntity entity = new AirbnbWalletEntity(id, money);
        AirbnbWalletService airbnbWalletService = new AirbnbWalletService();
        int count = airbnbWalletService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAirbnbWallet() throws SQLException {
        System.out.println("Input ID(wallet_id) for AirbnbWallet: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input money for AirbnbWallet: ");
        Integer money = input.nextInt();
        AirbnbWalletEntity entity = new AirbnbWalletEntity(id, money);
        AirbnbWalletService airbnbWalletService = new AirbnbWalletService();
        int count = airbnbWalletService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }


    private void findAirbnbWalletById() throws SQLException {
        System.out.println("Input ID(wallet_id) for AirbnbWallet: ");
        Integer id = input.nextInt();
        input.nextLine();
        AirbnbWalletService airbnbWalletService = new AirbnbWalletService();
        AirbnbWalletEntity entity = airbnbWalletService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------

    private void selectApartments() throws SQLException {
        System.out.println("\nTable: apartments");
        ApartmentsService apartmentsService = new ApartmentsService();
        List<ApartmentsEntity> wallets = apartmentsService.findAll();
        for (ApartmentsEntity entity : wallets) {
            System.out.println(entity);
        }
    }

    private void deleteApartments() throws SQLException {
        System.out.println("Input ID(id) for apartments: ");
        Integer id = input.nextInt();
        input.nextLine();
        ApartmentsService apartmentsService = new ApartmentsService();
        int count = apartmentsService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createApartments() throws SQLException {
        System.out.println("Input ID(id) for apartments: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input sellerId for apartments: ");
        Integer sellerId = input.nextInt();
        System.out.println("Input roomsNumber for apartments: ");
        Integer roomsNumber = input.nextInt();
        System.out.println("Input bedsNumber for apartments: ");
        Integer bedsNumber = input.nextInt();
        System.out.println("Input hourPrice for apartments: ");
        Integer hourPrice = input.nextInt();
        System.out.println("Input address for apartments: ");
        String address = input.next();
        ApartmentsEntity entity = new ApartmentsEntity(id, sellerId, roomsNumber, bedsNumber, hourPrice, address);
        ApartmentsService apartmentsService = new ApartmentsService();
        int count = apartmentsService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateApartmnents() throws SQLException {
        System.out.println("Input ID(id) for apartments: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input seller id for apartments: ");
        Integer sellerId = input.nextInt();
        System.out.println("Input roomsNumber for apartments: ");
        Integer roomsNumber = input.nextInt();
        System.out.println("Input bedsNumber for apartments: ");
        Integer bedsNumber = input.nextInt();
        System.out.println("Input hourPrice for apartments: ");
        Integer hourPrice = input.nextInt();
        System.out.println("Input address for apartments: ");
        String address = input.next();
        ApartmentsEntity entity = new ApartmentsEntity(id, sellerId, roomsNumber, bedsNumber, hourPrice, address);
        ApartmentsService apartmentsService = new ApartmentsService();
        int count = apartmentsService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }


    private void findApartmentById() throws SQLException {
        System.out.println("Input ID(id) for apartments: ");
        Integer id = input.nextInt();
        input.nextLine();
        ApartmentsService apartmentsService = new ApartmentsService();
        ApartmentsEntity entity = apartmentsService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------

    //------------------------------------------------------------------------

    private void selectApartmentsHasReservations() throws SQLException {
        System.out.println("\nTable: ApartmentsHasReservations");
        ApartmentsHasReservationsService apartmentsHasReservationsService = new ApartmentsHasReservationsService();
        List<ApartmentsHasReservationsEntity> reservations = apartmentsHasReservationsService.findAll();
        for (ApartmentsHasReservationsEntity entity : reservations) {
            System.out.println(entity);
        }
    }

    private void deleteApartmentsHasReservations() throws SQLException {
        System.out.println("Input ID(id) for ApartmentsHasReservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        ApartmentsHasReservationsService apartmentsHasReservationsService = new ApartmentsHasReservationsService();
        int count = apartmentsHasReservationsService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createApartmentsHasReservations() throws SQLException {
        System.out.println("Input ID(id) for ApartmentsHasReservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input apartmentsId for ApartmentsHasReservations: ");
        Integer apartmentsId = input.nextInt();
        System.out.println("Input buyers_id for ApartmentsHasReservations: ");
        Integer buyersId = input.nextInt();
        System.out.println("Input billings_id for ApartmentsHasReservations: ");
        Integer billingsId = input.nextInt();
        System.out.println("Input airbnb_wallet_id for ApartmentsHasReservations: ");
        Integer airbnbWalletId = input.nextInt();
        ApartmentsHasReservationsEntity entity = new ApartmentsHasReservationsEntity(id, apartmentsId, buyersId, billingsId, airbnbWalletId);
        ApartmentsHasReservationsService apartmentsHasReservationsService = new ApartmentsHasReservationsService();
        int count = apartmentsHasReservationsService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateApartmentsHasReservations() throws SQLException {
        System.out.println("Input ID(id) for ApartmentsHasReservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input apartmentsId for ApartmentsHasReservations: ");
        Integer apartmentsId = input.nextInt();
        System.out.println("Input buyers_id for ApartmentsHasReservations: ");
        Integer buyersId = input.nextInt();
        System.out.println("Input billings_id for ApartmentsHasReservations: ");
        Integer billingsId = input.nextInt();
        System.out.println("Input airbnb_wallet_id for ApartmentsHasReservations: ");
        Integer airbnbWalletId = input.nextInt();
        ApartmentsHasReservationsEntity entity = new ApartmentsHasReservationsEntity(id, apartmentsId, buyersId, billingsId, airbnbWalletId);
        ApartmentsHasReservationsService apartmentsHasReservationsService = new ApartmentsHasReservationsService();
        int count = apartmentsHasReservationsService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }


    private void findApartmentsHasReservations() throws SQLException {
        System.out.println("Input ID(id) for ApartmentsHasReservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        ApartmentsHasReservationsService apartmentsHasReservationsService = new ApartmentsHasReservationsService();
        ApartmentsHasReservationsEntity entity = apartmentsHasReservationsService.findById(id);
        System.out.println(entity);
    }


    //------------------------------------------------------------------------

    private void selectBillings() throws SQLException {
        System.out.println("\nTable: billings");
        BillingsService billingsService = new BillingsService();
        List<BillingsEntity> billings = billingsService.findAll();
        for (BillingsEntity entity : billings) {
            System.out.println(entity);
        }
    }

    private void deleteBillings() throws SQLException {
        System.out.println("Input ID(id) for billings: ");
        Integer id = input.nextInt();
        input.nextLine();
        BillingsService billingsService = new BillingsService();
        int count = billingsService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createBillings() throws SQLException {
        System.out.println("Input ID(id) for billings: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input date_payed for billings: ");
        String str = input.nextLine();
        Date enterDate = Date.valueOf(str);
        System.out.println("Input buyers_id for billings: ");
        Integer buyersId = input.nextInt();
        System.out.println("Input sellers_id for billings: ");
        Integer sellersId = input.nextInt();
        System.out.println("Input price for billings: ");
        Integer price = input.nextInt();
        BillingsEntity entity = new BillingsEntity(id, enterDate, buyersId, sellersId, price);
        BillingsService billingsService = new BillingsService();
        int count = billingsService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateBillings() throws SQLException {
        System.out.println("Input ID(id) for billings: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input date_payed for billings: ");
        String str = input.nextLine();
        Date enterDate = Date.valueOf(str);
        System.out.println("Input buyers_id for billings: ");
        Integer buyersId = input.nextInt();
        System.out.println("Input sellers_id for billings: ");
        Integer sellersId = input.nextInt();
        System.out.println("Input price for billings: ");
        Integer price = input.nextInt();
        BillingsEntity entity = new BillingsEntity(id, enterDate, buyersId, sellersId, price);
        BillingsService billingsService = new BillingsService();
        int count = billingsService.update(entity);
        System.out.printf("There are created %d rows\n", count);
    }


    private void findBillingsById() throws SQLException {
        System.out.println("Input ID(apartments_id) for billings: ");
        Integer id = input.nextInt();
        input.nextLine();
        BillingsService billingsService = new BillingsService();
        BillingsEntity entity = billingsService.findById(id);
        System.out.println(entity);
    }


    //------------------------------------------------------------------------

    private void selectBuyers() throws SQLException {
        System.out.println("\nTable: Buyers");
        BuyersService buyersService = new BuyersService();
        List<BuyersEntity> reservations = buyersService.findAll();
        for (BuyersEntity entity : reservations) {
            System.out.println(entity);
        }
    }

    private void deleteBuyers() throws SQLException {
        System.out.println("Input ID(id) for Buyers: ");
        Integer id = input.nextInt();
        input.nextLine();
        BuyersService buyersService = new BuyersService();
        int count = buyersService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createBuyers() throws SQLException {
        System.out.println("Input ID(apartments_id) for Buyers: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input name for Buyers: ");
        String name = input.next();
        System.out.println("Input surname for Buyers: ");
        String surname = input.next();
        System.out.println("Input phoneNumber for Buyers: ");
        String phoneNumber = input.next();
        System.out.println("Input email for Buyers: ");
        String email = input.next();
        System.out.println("Input birthday for Buyers as (1999-01-23): ");
        String str = input.next();
        Date birthday = Date.valueOf(str);
        BuyersEntity entity = new BuyersEntity(id, email, name, surname, phoneNumber, birthday);
        BuyersService buyersService = new BuyersService();
        int count = buyersService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateBuyers() throws SQLException {
        System.out.println("Input ID(apartments_id) for Buyers: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input name for Buyers: ");
        String name = input.next();
        System.out.println("Input surname for Buyers: ");
        String surname = input.next();
        System.out.println("Input phoneNumber for Buyers: ");
        String phoneNumber = input.next();
        System.out.println("Input email for Buyers: ");
        String email = input.next();
        System.out.println("Input birthday for Buyers as (1999-01-23): ");
        String str = input.next();
        Date birthday = Date.valueOf(str);
        BuyersEntity entity = new BuyersEntity(id, email, name, surname, phoneNumber, birthday);
        BuyersService buyersService = new BuyersService();
        int count = buyersService.update(entity);
        System.out.printf("There are created %d rows\n", count);
    }


    private void findBuyersById() throws SQLException {
        System.out.println("Input ID(id) for Buyers: ");
        Integer id = input.nextInt();
        input.nextLine();
        BuyersService buyersService = new BuyersService();
        BuyersEntity entity = buyersService.findById(id);
        System.out.println(entity);
    }


    //------------------------------------------------------------------------

    private void selectSellers() throws SQLException {
        System.out.println("\nTable: Sellers");
        SellersService sellersService = new SellersService();
        List<SellersEntity> reservations = sellersService.findAll();
        for (SellersEntity entity : reservations) {
            System.out.println(entity);
        }
    }

    private void deleteSellers() throws SQLException {
        System.out.println("Input ID(id) for Sellers: ");
        Integer id = input.nextInt();
        input.nextLine();
        SellersService sellersService = new SellersService();
        int count = sellersService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createSellers() throws SQLException {
        System.out.println("Input ID(Id) for Sellers: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input name for Sellers: ");
        String name = input.next();
        System.out.println("Input surname for Sellers: ");
        String surname = input.next();
        System.out.println("Input phoneNumber for Sellers: ");
        String phoneNumber = input.next();
        System.out.println("Input email for Sellers: ");
        String email = input.next();
        System.out.println("Input birthday for Sellers as (1999-01-23): ");
        String birthday = input.next();
        SellersEntity entity = new SellersEntity(id, email, name, surname, phoneNumber, birthday);
        SellersService sellersService = new SellersService();
        int count = sellersService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateSellers() throws SQLException {
        System.out.println("Input ID(id) for Sellers: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input name for Sellers: ");
        String name = input.next();
        System.out.println("Input surname for Sellers: ");
        String surname = input.next();
        System.out.println("Input phoneNumber for Sellers: ");
        String phoneNumber = input.next();
        System.out.println("Input email for Sellers: ");
        String email = input.next();
        System.out.println("Input birthday for Sellers as (1999-01-23): ");
        String birthday = input.next();
        SellersEntity entity = new SellersEntity(id, email, name, surname, phoneNumber, birthday);
        SellersService sellersService = new SellersService();
        int count = sellersService.update(entity);
        System.out.printf("There are created %d rows\n", count);
    }


    private void findSellersById() throws SQLException {
        System.out.println("Input ID(id) for Sellers: ");
        Integer id = input.nextInt();
        input.nextLine();
        SellersService sellersService = new SellersService();
        SellersEntity entity = sellersService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------


    //------------------------------------------------------------------------

    private void selectReservations() throws SQLException {
        System.out.println("\nTable: Reservations");
        ReservationsService reservationsService = new ReservationsService();
        List<ReservationsEntity> reservations = reservationsService.findAll();
        for (ReservationsEntity entity : reservations) {
            System.out.println(entity);
        }
    }

    private void deleteReservations() throws SQLException {
        System.out.println("Input ID(id) for Reservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        ReservationsService reservationsService = new ReservationsService();
        int count = reservationsService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createReservations() throws SQLException {
        System.out.println("Input ID(reservation_id) for Reservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input settlement_date for Reservations: ");
        String settlementDate = input.next();
        System.out.println("Input leave_date for Reservations: ");
        String leaveDate = input.next();
        System.out.println("Input paid(0 or 1) for Reservations: ");
        Integer paid = input.nextInt();
        ReservationsEntity entity = new ReservationsEntity(id, settlementDate, leaveDate, paid);
        ReservationsService reservationsService = new ReservationsService();
        int count = reservationsService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateReservations() throws SQLException {
        System.out.println("Input ID(reservation_id) for Reservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        System.out.println("Input settlement_date for Reservations: ");
        String settlementDate = input.next();
        System.out.println("Input leave_date for Reservations: ");
        String leaveDate = input.next();
        System.out.println("Input paid(0 or 1) for Reservations: ");
        Integer paid = input.nextInt();
        ReservationsEntity entity = new ReservationsEntity(id, settlementDate, leaveDate, paid);
        ReservationsService reservationsService = new ReservationsService();
        int count = reservationsService.update(entity);
        System.out.printf("There are created %d rows\n", count);
    }


    private void findReservationsById() throws SQLException {
        System.out.println("Input ID(id) for Reservations: ");
        Integer id = input.nextInt();
        input.nextLine();
        ReservationsService reservationsService = new ReservationsService();
        ReservationsEntity entity = reservationsService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------

    private void takeStructureOfDB() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        MetaDataService metaDataService = new MetaDataService();
        List<TableMetaData> tables = metaDataService.getTablesStructure();
        System.out.println("TABLE OF DATABASE: " + connection.getCatalog());

        for (TableMetaData table : tables) {
            System.out.println(table);
        }
    }

    //-------------------------------------------------------------------------

    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet()) {
            if (key.length() == 1) System.out.println(menu.get(key));{
            }

        }
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet()) {
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
            {

            }
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }
}
