package per.study.behavioral.strategy;

public class StrategyContext {

    final StrategyDB strategyDB;


    public StrategyContext(StrategyDB strategyDB) {
        this.strategyDB = strategyDB;
    }

    public void creatConnection(String db) {
        this.strategyDB.creatConnection(db);
    }


}
