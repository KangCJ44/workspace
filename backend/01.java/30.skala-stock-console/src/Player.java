import java.util.ArrayList;
import java.util.List;

class Player {
    // 사용자 ID
    private String playerId;
    private int playerMoney;
    private List<PlayerStock> playerStocks = new ArrayList<>();

    public Player() {
    }

    public Player(String id) {
        this.playerId = id;
        this.playerMoney = 10_000;
    }

    public void setPlayerId(String id) {
        this.playerId = id;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public int getPlayerMoney() {
        return this.playerMoney;
    }

    public void setPlayerMoney(int money) {
        this.playerMoney = money;
    }

    public List<PlayerStock> getPlayerStocks() {
        return this.playerStocks;
    }

    public void setPlayerStocks(List<PlayerStock> stocks) {
        this.playerStocks = stocks;
    }

    public void addStock(PlayerStock stock) {
        boolean stockExists = false;

        for (PlayerStock existingStock : playerStocks) {
            if (existingStock.getStockName().equals(stock.getStockName())) {
                existingStock.setStockPrice(stock.getStockPrice());
                existingStock.setStockQuantity(existingStock.getStockQuantity() + stock.getStockQuantity());
                stockExists = true;
                break;
            }
        }

        if (!stockExists) {
            playerStocks.add(stock);
        }
    }

    public void updatePlayerStock(PlayerStock stock) {
        for (PlayerStock existingStock : playerStocks) {
            if (existingStock.getStockName().equals(stock.getStockName())) {
                existingStock.setStockPrice(stock.getStockPrice());
                existingStock.setStockQuantity(existingStock.getStockQuantity());
                if (existingStock.getStockQuantity() == 0) {
                    playerStocks.remove(existingStock);
                }
                break;
            }
        }
    }

    public PlayerStock findStock(int index) {
        if (index >= 0 && index < playerStocks.size()) {
            return playerStocks.get(index);
        }
        return null;
    }

    public String getPlayerStocksForFile() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playerStocks.size(); i++) {
            if (i > 0) {
                sb.append("|");
            }
            sb.append(playerStocks.get(i));
        }
        return sb.toString();
    }

    public String getPlayerStocksForMenu() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playerStocks.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(playerStocks.get(i).toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
